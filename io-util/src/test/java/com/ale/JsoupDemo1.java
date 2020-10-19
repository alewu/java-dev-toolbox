package com.ale;

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
import java.util.stream.Collectors;

/**
 * @author alewu
 * @date 2020/10/17
 */
public class JsoupDemo1 {
    public static void main(String[] args) throws IOException {
        String dir = "D:\\tmp\\软件工程之美\\";
        List<Path> files = FileUtils.getFiles("D:\\tmp\\60 软件工程之美-20201017T080810Z-001\\60 软件工程之美");
        List<Path> html1 =
                files.stream().filter(path -> path.toFile().getName().endsWith("html")).collect(Collectors.toList());
        Path path1 = html1.get(0);
        List<Path> paths = Collections.singletonList(path1);
        for (Path path : html1) {
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

            Elements elementsByTag = document.getElementsByTag("span");
            for (Element element : elementsByTag) {
                if (element.text().contains("加微信")){
                    element.text("宝玉");
                }
            }

            Elements elementsByTag1 = document.getElementsByTag("p");
            for (Element element : elementsByTag1) {
                if (element.text().equals("感谢阅读，如果你觉得这篇文章对你有一些启发，也欢迎把它分享给你的朋友。")){
                    element.remove();
                }
            }



            List<String> divClassList = ImmutableList.of("_352wsGxH_0","Wz6esVdU_0", "_1-ZfmNK8_0", "_1Bg5E78Y_0 _25ls2Q2l_0", "_3FoXPaWx_0", "_1qhD3bdE_0 _2QmGFWqF_0","_2DmyW7ex_0 _2QmGFWqF_0", "_22WJb59B_0");
            removeByClass(document, divClassList);

            removeImage(document);


            String html = document.html();
            System.out.println(html);
            Files.write(html.getBytes("UTF-8"), Paths.get(dir, path.toFile().getName()).toFile());
        }

    }

    private static void removeByClass(Document document, List<String> divClassList) {
        for (String div : divClassList) {
            Elements elementsByClass = document.getElementsByClass(div);
            elementsByClass.remove();
        }
    }

    private static void removeImage(Document document) {
        Elements img = document.getElementsByTag("img");
        Element element = img.get(0);
        element.remove();
        for (Element element1 : img) {
            if (element1.attr("data-savepage-src").equals("https://static001.geekbang.org/resource/image/31/af/315c3c753591fbaf480f39cdc9e0f3af.jpg")) {
                element1.remove();
            }

            if (element1.attr("data-savepage-src").equals("https://static001.geekbang.org/resource/image/30/ed/3035c596dc0cde8f0da6d92355757eed.jpg")) {
                element1.remove();
            }
        }
    }


}
