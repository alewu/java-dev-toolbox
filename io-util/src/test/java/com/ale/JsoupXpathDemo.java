package com.ale;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alewu
 * @date 2020/10/17
 */
public class JsoupXpathDemo {
    public static void main(String[] args) throws IOException {
        String dir = "E:\\tmp\\xxx\\101-xxx";
        String targetDir = "E:\\tmp\\101-xxx";
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        List<Path> files = FileUtils.getFiles(dir);
        List<Path> html1 =
                files.stream().filter(path -> path.toFile().getName().endsWith("html")).collect(Collectors.toList());
        List<Path> paths = Collections.singletonList(html1.get(0));
        for (Path path : paths) {
            System.out.println(path.toFile().getName());
            Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());
            String html = document.html();

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

            List<JXNode> jxNodes = ImmutableList.of(jxNode, jxNode1, jxNode2, jxNode5, audioNode, imageNode, imageNode1,
                                                    commentNode, commentNode1, jxNode4);
            for (JXNode node : jxNodes) {
                Element element = node.asElement();
                String className = element.className();

                if (StringUtils.isBlank(className)) {
                } else {
                    Elements elementsByClass = document.getElementsByClass(className);
                    System.out.println(className);
                    elementsByClass.remove();
                }
            }

            JsoupUtils.removeByTag(document, ImmutableList.of("script", "svg"));
            JsoupUtils.removeById(document, Collections.singletonList("gkui-modal-controller"));
            //            List<String> strings = ImmutableList.of();
            //            for (String string : strings) {
            //                JXNode selNOne = jxDocument.selNOne(string);
            //                Element element = selNOne.asElement();
            //                element.remove();
            //            }

            String html2 = document.html();
            //            System.out.println(html2);
            Files.write(html2.getBytes(StandardCharsets.UTF_8.name()),
                        Paths.get(targetDir, path.toFile().getName()).toFile());
        }

    }

    @Test
    void test() throws IOException {
        String dir = "E:\\tmp\\极客时间\\101-后端技术面试38讲";
        String targetDir = "E:\\tmp\\101-后端技术面试38讲";
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        List<Path> files = FileUtils.getFiles(dir);
        List<Path> html1 =
                files.stream().filter(path -> path.toFile().getName().endsWith("html")).collect(Collectors.toList());
        List<Path> paths = Collections.singletonList(html1.get(0));
        for (Path path : paths) {
            Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());
            String html = document.html();
            JXDocument jxDocument = JXDocument.create(html);
            JXNode jxNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[3]");

            Element element = jxNode.asElement();


            String html2 = document.html();
            //            System.out.println(html2);
            Files.write(html2.getBytes(StandardCharsets.UTF_8.name()),
                        Paths.get(targetDir, path.toFile().getName()).toFile());
        }


    }


}
