package com.ale;

import com.ale.util.JsoupUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author alewu
 * @date 2020/10/17
 */
public class JsoupDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dir = "E:\\tmp\\xx\\xx";
        String targetDir = "E:\\tmp\\xx";
        FileHelper.mkdir(targetDir);

        List<Path> htmlFiles = FileHelper.getFiles(dir, "html");
        Path path1 = htmlFiles.get(0);
        List<Path> paths = Collections.singletonList(path1);

        for (Path path : paths) {
            CompletableFuture.runAsync(() -> {
                try {
                    dealOne(targetDir, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(30);

    }

    private static void dealOne(String targetDir, Path path) throws IOException {
        System.out.println(path.toFile().getName());
        Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());

        Elements meta = document.getElementsByTag("meta");
        for (Element element : meta) {
            String name = element.attr("name");
            if (name.equals("description")) {
                element.remove();
            }
            if (name.equals("Keywords")) {
                element.remove();
            }
        }

        JsoupUtils.replaceText(document, "xxx", "xx");

        List<String> tags = ImmutableList.of("script");
        JsoupUtils.removeByTags(document, tags);

        JsoupUtils.removeByText(document, "xxx");

        List<String> ids = ImmutableList.of("qb_collection_img_mask", "gkui-modal-controller");
        JsoupUtils.removeByIds(document, ids);

        // 按css删除
        List<String> divClassList = ImmutableList.of("Wz6esVdU_0", "_1Bg5E78Y_0 _25ls2Q2l_0", "_3FoXPaWx_0");
        JsoupUtils.removeByClassName(document, divClassList);

        String imageUrl = "https://static001.geekbang.org/resource/image/cf/aa/cf393cd748a4f0e6451807c4b61843aa" +
                ".jpg";
        String attrKey = "data-savepage-src";
        JsoupUtils.removeImage(document, attrKey, imageUrl);


        String html = document.html();
        System.out.println(html);
        Files.write(html.getBytes("UTF-8"), Paths.get(targetDir, path.toFile().getName()).toFile());
    }


}
