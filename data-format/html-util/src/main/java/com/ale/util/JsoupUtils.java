package com.ale.util;

import cn.hutool.core.collection.CollUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Html 操作
 *
 * @author alewu
 * @date 2021/3/20
 */
public class JsoupUtils {


    /**
     * 按标签删除
     *
     * @param document doc
     * @param tags     标签列表
     */
    public static void removeByTags(Document document, List<String> tags) {
        tags.forEach(tag -> document.getElementsByTag(tag).remove());
    }

    /**
     * 按类选择器删除
     *
     * @param document     doc
     * @param divClassList 类名列表
     */
    public static void removeByClassName(Document document, List<String> divClassList) {
        divClassList.stream().map(document::getElementsByClass).forEach(Elements::remove);
    }

    /**
     * 按id删除
     *
     * @param document doc
     * @param idList   id列表
     */
    public static void removeByIds(Document document, List<String> idList) {
        idList.forEach(id -> Optional.ofNullable(document.getElementById(id)).ifPresent(Node::remove));
    }

    /**
     * 删除文本
     *
     * @param document doc
     * @param pText    p text
     */
    public static void removeByText(Document document, String pText) {
        Elements elementsByTag1 = document.getElementsByTag("p");
        for (Element element : elementsByTag1) {
            if (element.text().equals(pText)) {
                element.remove();
            }
        }
    }

    /**
     * 替换文本
     *
     * @param document    doc
     * @param pText       源对象
     * @param replacement 替换直
     */
    public static void replaceText(Document document, String pText, String replacement) {
        Elements elementsByTag = document.getElementsByTag("span");
        for (Element element : elementsByTag) {
            if (element.text().contains(pText)) {
                element.text(replacement);
            }
        }
    }

    /**
     * 删除图片
     *
     * @param document     doc
     * @param attributeKey 属性
     * @param imgUrl       图片链接
     */
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
