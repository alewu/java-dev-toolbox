package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReplaceTest {

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
            "      <Url><![CDATA[www.google.com?linkId=12]]></Url>\n" +
            "    </item>\n" +
            "    <item>\n" +
            "      <Title><![CDATA[title2]]></Title>\n" +
            "      <Description><![CDATA[description2]]></Description>\n" +
            "      <PicUrl><![CDATA[picurl2]]></PicUrl>\n" +
            "      <Url><![CDATA[www.baidu.com?linkId=14]]></Url>\n" +
            "    </item>\n" +
            "      <item>\n" +
            "          <Title><![CDATA[title2]]></Title>\n" +
            "          <Description><![CDATA[description2]]></Description>\n" +
            "          <PicUrl><![CDATA[picurl]]></PicUrl>\n" +
            "          <Url><![CDATA[www.regex101.com]]></Url>\n" +
            "      </item>\n" +
            "  </Articles>\n" +
            "</xml>";

    @Test
    public void test(){
        String content = "ZZZaaabbbccc中文1234";
        //通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
        System.out.println("replaceAll: "+ ReUtil.replaceAll(content, "(\\d+)", "->$1<-"));

    }

    @Test
    public void testMatchAndReplace(){
        String replaceAll = ReUtil.replaceAll(NEWS_XML, "(linkId=\\d+)", "$1&uid=12");
        System.out.println("replaceAll: "+ replaceAll);
        assertNotNull(replaceAll);
    }
}
