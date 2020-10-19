package com.ale;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alewu
 * @date 2020/10/17
 */
public class JsoupDemo {
    public static void main(String[] args) throws IOException {
        String dir = "D:\\tmp\\软件工程之美\\";
        List<Path> files = FileUtils.getFiles("D:\\tmp\\60 软件工程之美-20201017T080810Z-001\\60 软件工程之美");
        List<Path> html1 =
                files.stream().filter(path -> path.toFile().getName().endsWith("html")).collect(Collectors.toList());
        Path path1 = files.get(0);
//        List<Path> paths = Collections.singletonList(path1);
        for (Path path : files) {
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
            Elements breadcrumb = document.getElementsByClass("breadcrumb breadcrumb");
            breadcrumb.remove();


            Elements img = document.getElementsByTag("img");
            Element element = img.get(0);
            element.remove();
            for (Element element1 : img) {
                if (element1.attr("src").equals("https://static001.geekbang" +
                                                        ".org/resource/image/2a/d5/2a62e58cbdf56a5dc40748567d346fd5" +
                                                        ".jpg")) {
                    element1.remove();
                }
            }


            Elements elementsByClass = document.getElementsByClass("mini-audio-player");
            System.out.println(elementsByClass);
            elementsByClass.remove();

            Elements toComment = document.getElementsByClass("to-comment");
            toComment.remove();

            Elements elementsByClass1 = document.getElementsByClass("article-comments");
            elementsByClass1.remove();

            String html = document.html();
            System.out.println(html);
            Files.write(html.getBytes("UTF-8"), Paths.get(dir, path.toFile().getName()).toFile());
        }

    }


}
