package com.ale;

import cn.hutool.core.collection.CollUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class JsoupUtils {


    public static void removeByTag(Document document, List<String> tags) {
        for (String tag : tags) {
            document.getElementsByTag(tag).remove();
        }
    }

    public static void removeByClassName(Document document, List<String> divClassList) {
        for (String div : divClassList) {
            Elements elementsByClass = document.getElementsByClass(div);
            elementsByClass.remove();
        }
    }

    public static void removeById(Document document, List<String> idList) {
        for (String id : idList) {
            Optional.ofNullable(document.getElementById(id)).ifPresent(Node::remove);
        }
    }

    public static void removeByText(Document document, String pText) {
        Elements elementsByTag1 = document.getElementsByTag("p");
        for (Element element : elementsByTag1) {
            if (element.text().equals(pText)) {
                element.remove();
            }
        }
    }

    public static void replaceText(Document document, String pText, String replacement) {
        Elements elementsByTag = document.getElementsByTag("span");
        for (Element element : elementsByTag) {
            if (element.text().contains(pText)) {
                element.text(replacement);
            }
        }
    }

    public static void removeImage(Document document, String attributeKey, String imgUrl) {
        String attrKey = Objects.isNull(attributeKey) ? "src" : attributeKey;
        Elements imgs = document.getElementsByTag("img");
        if (CollUtil.isEmpty(imgs)) {
            return;
        }
        Element element = imgs.get(0);
        element.remove();
        for (Element element1 : imgs) {
            if (element1.attr(attrKey).equals(imgUrl)) {
                element1.remove();
            }

        }
    }


}
