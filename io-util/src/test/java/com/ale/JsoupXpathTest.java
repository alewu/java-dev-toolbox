package com.ale;

import com.google.common.io.Files;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

class JsoupXpathTest {

    @Test
    void testBatch() throws IOException, InterruptedException {
        String dir = "D:\\tmp\\xx";
        File file = new File(dir);
        String[] list = file.list();
        for (String s : list) {
            String targetDir = "D:\\tmp\\yy\\" + s;
            FileHelper.mkdir(targetDir);
            System.out.println(s);
            String src = "D:\\tmp\\xx\\" + s;

            List<Path> files = FileHelper.getFiles(src, "html");

            List<Path> paths = Collections.singletonList(files.get(0));
            CountDownLatch latch = new CountDownLatch(files.size());

            for (Path path : files) {
                CompletableFuture.runAsync(() -> {
                    try {
                        dealOne(targetDir, path);
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
    }

    @Test
    void test() throws IOException, InterruptedException {
        String dir = "E:\\tmp\\xxx";
        String targetDir = "E:\\tmp\\xxx";
        FileHelper.mkdir(targetDir);

        List<Path> files = FileHelper.getFiles(dir, "html");

        List<Path> paths = Collections.singletonList(files.get(0));
        CountDownLatch latch = new CountDownLatch(files.size());

        for (Path path : files) {
            CompletableFuture.runAsync(() -> {
                try {
                    dealOne(targetDir, path);
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

    private void dealOne(String targetDir, Path path) throws IOException {
        Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());
        String html = document.html();
        JXDocument jxDocument = JXDocument.create(html);

        Elements select = document.select("head");
        Element head = document.head();
        head.html(select.html());

        JXNode jxNode = jxDocument.selNOne("//*[@id=\"app\"]/div[1]/div/div/div[2]/div[2]/div[3]");
        Element element = jxNode.asElement();



        Element body = document.body();
        body.html(element.html());

        JsoupUtils.removeByTag(document, Collections.singletonList("script"));

        String html2 = document.html();
        //            System.out.println(html2);
        Files.write(html2.getBytes(StandardCharsets.UTF_8.name()),
                    Paths.get(targetDir, path.toFile().getName()).toFile());
    }
}
