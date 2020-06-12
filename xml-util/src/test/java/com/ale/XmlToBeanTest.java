package com.ale;

import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import org.junit.jupiter.api.Test;

public class XmlToBeanTest {
    @Test
    public void test() {
        String xml = "<xml>\n" +
                "  <ToUserName><![CDATA[o_cxxxsMUWXI6aG14]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[gh_xxx]]></FromUserName>\n" +
                "  <CreateTime><![CDATA[1591961648]]></CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[踩踩踩从<a href='www.baidu.com'>xx</a>]]></Content>\n" +
                "</xml> \n";
        JSONObject jsonObject = XML.toJSONObject(xml);
        String s = jsonObject.toString();
        System.out.println(s);
    }
}
