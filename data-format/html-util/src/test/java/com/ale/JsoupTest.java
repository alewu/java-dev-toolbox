package com.ale;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author alewu
 * @date 2020/10/17
 */
class JsoupTest {
    @Test
    void testJSoupFrom() throws IOException {
        String s = FileUtils.readFileToString(new File("D://docs//接口文档.html"), StandardCharsets.UTF_8);
        Document doc = Jsoup.parse(s);
        Elements tables = doc.select("table");
        for (Element element : tables) {
            Elements trs = element.select("tr");
            for (Element tr : trs) {
                System.out.println(tr.text());
                Elements tds = tr.select("td");
                for (Element td : tds) {
                    System.out.print(td.text() + ",");
                }
                System.out.println("");
            }

        }
    }


    @Test
    void testJSoupFromStringEx() {
        String htmlString = "<html>" +
                "<head>" +
                "<title>My title</title>" +
                "</head>" +
                "<body>Body content</body>" +
                "</html>";

        Document doc = Jsoup.parse(htmlString);
        String title = doc.title();
        String body = doc.body().text();

        System.out.printf("Title: %s%n", title);
        System.out.printf("Body: %s", body);
    }

    @Test
    void testJSoupFromFileEx() throws IOException {
        URL resource = this.getClass().getClassLoader().getResource("local.html");
        Assertions.assertNotNull(resource);
        Document doc = Jsoup.parse(new File(resource.getFile()), StandardCharsets.UTF_8.name());
        Element divTag = doc.getElementById("mydiv");
        System.out.println(divTag.text());

    }

    @Test
    void testJSoupHTMLSourceEx() throws IOException {
        String webPage = "http://www.jsoup.org";
        String html = Jsoup.connect(webPage).get().html();
        System.out.println(html);
    }


    @Test
    void test() {
        String unsafe = "<p><a href='http://www.oschina.net/' οnclick='stealCookies()'>开源中国社区</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        System.out.println(safe);
    }
}
