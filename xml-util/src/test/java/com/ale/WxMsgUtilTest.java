package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Stack;

class WxMsgUtilTest {
    public static long start;
    public static long end;

    public static final String XML = "<xml>\n" +
            "  <ToUserName><![CDATA[o_cxDuBE2Bn9IvxSUsMUWXI6aG14]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[gh_57e091b030dc]]></FromUserName>\n" +
            "  <CreateTime><![CDATA[1591949387]]></CreateTime>\n" +
            "  <MsgType><![CDATA[text]]></MsgType>\n" +
            "  <Content><![CDATA[踩踩踩从<a href='www.baidu.com'>xx</a>]]></Content>\n" +
            "</xml>";

    public static final String NEWS_XML = "<xml>\n" +
            "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            "  <CreateTime>12345678</CreateTime>\n" +
            "  <MsgType><![CDATA[news]]></MsgType>\n" +
            "  <ArticleCount>1</ArticleCount>\n" +
            "  <Articles>\n" +
            "    <item>\n" +
            "      <Title><![CDATA[title1]]></Title>\n" +
            "      <Description><![CDATA[description1]]></Description>\n" +
            "      <PicUrl><![CDATA[picurl]]></PicUrl>\n" +
            "      <Url><![CDATA[url]]></Url>\n" +
            "    </item>\n" +
            "    <item>\n" +
            "      <Title><![CDATA[title2]]></Title>\n" +
            "      <Description><![CDATA[description2]]></Description>\n" +
            "      <PicUrl><![CDATA[picurl2]]></PicUrl>\n" +
            "      <Url><![CDATA[url2]]></Url>\n" +
            "    </item>\n" +
            "  </Articles>\n" +
            "</xml>";


    @BeforeEach
    public void start() {
        start = Instant.now().toEpochMilli();
    }

    @AfterEach
    public void end() {
        end = Instant.now().toEpochMilli();
        System.out.println("执行时间： " + (end - start));
    }


    @Test
    void getContent() {
        System.out.println(WxMsgUtil.getContent(XML));
    }

    @Test
    void getMsgType() {
        System.out.println(WxMsgUtil.getMsgType(XML));
    }

    @Test
    void getUrl() {
        System.out.println(WxMsgUtil.getUrl(NEWS_XML));
    }

    @Test
    void replaceUrl() {
        List<String> url = WxMsgUtil.getUrl(NEWS_XML);
        Stack<String> strings = new Stack<>();
        for (String s : url) {
            strings.push(s);
        }
        StringBuilder s1 = new StringBuilder();
        for (String s : url) {
            StringBuilder sb = new StringBuilder(ReUtil.replaceAll(NEWS_XML, s, "$0&uid=12"));
            s1 = sb;
        }
//        System.out.println(sb);
        System.out.println(url);
    }

    public  String recursion(StringBuilder sb, Stack<String> strings) {
        if (strings.empty()) {
            return "";
        }
//        strings.peek()
//      return  new StringBuilder(ReUtil.replaceAll(NEWS_XML, "s", "$0&uid=12"));
        return "";
    }

    @Test
    void getToUserName() {
        System.out.println(WxMsgUtil.getToUserName(NEWS_XML));
    }
}