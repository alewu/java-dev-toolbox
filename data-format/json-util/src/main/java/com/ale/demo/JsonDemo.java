package com.ale.demo;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class JsonDemo {
    public static void main(String[] args) {
        String a = "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"现在v从\"," +
                "\"description\":\"自增从v现在\",\"url\":\"支持v\",\"picurl\":\"http://134.175.245" +
                ".70:808/wxf85ab110b5265ca5/image/20200612/15919434261694513.jpg\"}]}}";
        Map map = JSON.parseObject(a, Map.class);
        Map news = (Map)map.get("news");
        System.out.println();
        String articles =
        news.get("articles").toString();
        System.out.println(articles);
    }
}
