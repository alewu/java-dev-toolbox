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
        String dir = "D:\\tmp\\软件工程之美";
        List<Path> files = FileUtils.getFiles(dir);
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

            document.getElementsByTag("svg").remove();

            document.getElementsByTag("script").remove();


            Elements elementsByTag1 = document.getElementsByTag("p");
            for (Element element : elementsByTag1) {
                if (element.text().equals("欢迎你留言和我讨论。！")) {
                    element.remove();
                }
            }

            Element element = document.getElementById("gkui-modal-controller");
            if (element != null) {
                element.remove();
            }


            List<String> divClassList = ImmutableList.of("_1qHJ5OLn_0","_2sRsF5RP_0");
            removeByClassName(document, divClassList);

            removeImage(document);


            String html = document.html();
            System.out.println(html);
            Files.write(html.getBytes("UTF-8"), Paths.get(dir, path.toFile().getName()).toFile());
        }

    }

    private static void removeByClassName(Document document, List<String> divClassList) {
        for (String div : divClassList) {
            Elements elementsByClass = document.getElementsByClass(div);
            elementsByClass.remove();
        }
    }

    private static void removeImage(Document document) {
        Elements img = document.getElementsByTag("img");
        if (img.size() == 0) {
            return;
        }
        Element element = img.get(0);
        element.remove();
        for (Element element1 : img) {
            if (element1.attr("src").equals("https://static001.geekbang" +
                                                    ".org/resource/image/b5/fb/b5bc14cb81d3630919fee94a512cc3fb.jpg")) {
                element1.remove();
            }

        }
    }


}
