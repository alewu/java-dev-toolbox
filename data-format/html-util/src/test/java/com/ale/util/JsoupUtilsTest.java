package com.ale.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

class JsoupUtilsTest {

    @Test
    void removeByTags() {
    }

    @Test
    void removeByClassName() {
    }

    @Test
    void removeByIds() {
    }

    @Test
    void removeByText() {
    }

    @Test
    void replaceText() {
    }

    @Test
    void removeImage() {
        Document document = Jsoup.parse("html", StandardCharsets.UTF_8.name());
        Elements img = document.getElementsByTag("img");
        img.hasAttr("");
    }
}