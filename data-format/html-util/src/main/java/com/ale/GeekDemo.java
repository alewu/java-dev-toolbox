package com.ale;

import com.ale.util.JsoupUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class GeekDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dir = "E:\\tmp\\极客时间\\专题\\119-Java 业务开发常见错误 100 例";
        String targetDir = "E:\\tmp\\119-Java 业务开发常见错误 100 例";

        List<Path> files = FileHelper.getFiles(dir, "html");
        CountDownLatch latch = new CountDownLatch(files.size());

        for (Path path : files) {
            CompletableFuture.runAsync(() -> {
                try {
                    parseTask(targetDir, path);
                    long count = latch.getCount();
                    System.out.println("正在处理：" + count + "--" + path.getFileName());
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.await();
    }

    private static void parseTask(String targetDir, Path path) throws IOException {
        String html = FileUtils.readFileToString(path.toFile(), StandardCharsets.UTF_8.name());
        String out = dealOneHtml(html);
        FileHelper.mkdir(targetDir);
        Files.write(out.getBytes(StandardCharsets.UTF_8.name()),
                    Paths.get(targetDir, path.toFile().getName()).toFile());
    }


    public static String dealOneHtml(String html) {
        List<JXNode> jxNodes = getJxNodes(html);

        Document document = Jsoup.parse(html, StandardCharsets.UTF_8.name());
        for (JXNode node : jxNodes) {
            String className = node.asElement().className();
            if (StringUtils.isNotBlank(className)) {
                Elements elementsByClass = document.getElementsByClass(className);
                elementsByClass.remove();
            }
        }

        JsoupUtils.removeByTags(document, ImmutableList.of("script", "svg"));
        JsoupUtils.removeByIds(document, Collections.singletonList("gkui-modal-controller"));

        return document.html();

    }

    private static List<JXNode> getJxNodes(String html) {
        JXDocument jxDocument = JXDocument.create(html);
        // 最上面
        JXNode jxNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[1]/div[1]");

        JXNode jxNode5 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[1]/div[2]");

        JXNode jxNode4 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[1]/div[3]");
        // 中间
        JXNode jxNode1 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[1]/div");

        JXNode jxNode2 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[1]/div[1]");
        // 音频
        JXNode audioNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[2]/div[2]");

        // 图片
        JXNode imageNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[2]/img");

        JXNode imageNode1 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[2]/div[4]/img");

        // 评论
        JXNode commentNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[4]");

        JXNode commentNode1 = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[3]");

        return ImmutableList.of(jxNode, jxNode1, jxNode2, jxNode5, audioNode, imageNode, imageNode1,
                                commentNode, commentNode1, jxNode4);
    }
}
