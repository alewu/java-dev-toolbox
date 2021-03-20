package com.ale.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class JsoupXpathUtils {

    public static JXDocument getJXDocument(Path path) throws IOException {
        Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());
        String html = document.html();
        return JXDocument.create(html);
    }
}
