package com.ale.regex;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void test() {
        String content = "ZZZaaabbbccc中文1234";
        //通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
        System.out.println("replaceAll: " + ReUtil.replaceAll(content, "(\\d+)", "->$1<-"));

    }

    @Test
    public void testMatchAndReplace() {
        String replaceAll = ReUtil.replaceAll(NEWS_XML, "(linkId=\\d+)", "$1&uid=12");
        System.out.println("replaceAll: " + replaceAll);
        assertNotNull(replaceAll);
    }

    @Test
    public void testMatchAndReplaceByJava() {
        Pattern p = Pattern.compile("Indian");
        Matcher m = p.matcher("One little Indian,two little Indian,three little Indian");
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        while (result) {//如果匹配成功就替换
            m.appendReplacement(sb, "Chinese");
            result = m.find();//继续下一步匹配
        }
        //		m.appendTail(sb);
        System.out.println(sb.toString());
        assertEquals("One little Chinese,two little Chinese,three little Chinese", sb.toString());
    }

    @Test
    void testR() {
        String json = " {\"error_code\":0,\"error_msg\":\"\",\"data\":{\"last_id\":98544955,\"count\":4," +
                "\"push_time\":1600231659,\"list\":[{\"user_id\":\"96160479\"," +
                "\"merchant_id\":\"20200911080028_96160479_UaYe\",\"transaction_id\":\"2009110800286850231000120\"," +
                "\"type\":\"1\",\"money\":\"30.00\",\"state\":\"1\",\"from\":\"0\",\"create_time\":\"1599782429\"," +
                "\"finish_time\":\"1599782458\",\"referral_id\":\"2948798\",\"referral_id_permanent\":\"300380\"," +
                "\"channel_id\":\"12913\",\"book_name\":\"华丽逆袭\",\"book_tags\":\"都市\"," +
                "\"referral_url\":\"/t/2948798\",\"subscribe_time\":\"1567168446\"," +
                "\"user_createtime\":\"1567029038\",\"openid\":\"oH_OQ58JVLtAhb8KHsjF81Xn9KkE\",\"register_ip\":\"223" +
                ".104.90.199\",\"nickname\":\"1314520\",\"avatar\":\"http://thirdwx.qlogo" +
                ".cn/mmopw01obJJM1ALVmwnogjN2zBEkW02TicV4z56nib3fhlqRnjthYsMW0r0Eiaa0EnK1iccqOEntBV3qDJ8MwszC11dkuj18ATZ9m2/132\"}" +
                ",{\"user_id\":\"95478210\",\"merchant_id\":\"20200911103147_95478210_EorR\"," +
                "\"transaction_id\":\"4200000772202009118251051132\",\"type\":\"1\",\"money\":\"50.00\"," +
                "\"state\":\"1\",\"from\":\"0\",\"create_time\":\"1599791507\",\"finish_time\":\"1599791515\"," +
                "\"referral_id\":\"2316076\",\"referral_id_permanent\":\"300380\",\"channel_id\":\"12913\"," +
                "\"book_name\":\"神都猛虎\",\"book_tags\":\"都市\",\"referral_url\":\"/t/2316076\"," +
                "\"subscribe_time\":\"1567518005\",\"user_createtime\":\"15669," +
                "\"openid\":\"oH_OQ50I4JdLK_bVqtFxJifUDQXg\",\"register_ip\":\"117.136.11.10\",\"nickname\":\"消失入海\"," +
                "\"avatar\":\"http://thirdwx.qlogo" +
                ".cn/mmopen" +
                "/9ibw01obJJM1J6YNWgCYq15T4NF2AZV3Cn2d9ribRTJicz9YenO3Bk6w02uwZalzlRDPnBdHujbYxudC7MJiaR3pnWSU0mB94qfS/132\"}," +
                "{\"user_id\":\"124883494\"rchant_id\":\"20200911113130_124883494_25yz\",\"transaction_id\":\"\"," +
                "\"type\":\"1\",\"money\":\"50.00\",\"state\":\"0\",\"from\":\"0\",\"create_time\":\"1599795090\"," +
                "\"finish_time\":\"\",\"referral_id\":\"2762036\",\"referral_id_permanent\":\"480310\"," +
                "\"channel_id\":\"12913\",\"book_name\":\"华丽逆袭\",\"books\":\"都市\",\"referral_url\":\"/t/2762036\"," +
                "\"subscribe_time\":\"1569626092\",\"user_createtime\":\"1569584079\"," +
                "\"openid\":\"oH_OQ53Yty8LzFtNvFM__z78BcDg\",\"register_ip\":\"112.17.241.66\"," +
                "\"nickname\":\"缘分忠有你有我\",\"avatar\":\"http://thirdwx.qlogo" +
                ".cn/mmopen" +
                "/niabI6jcFuyAMRhe0LF3nJQKpDSibqDEXjgfLu2XWiazdGZ7LtpUpEdlOR2zYuEUPx6F1BSunoQVjwKlL65HVUPGOHSQbuiamnY3/132\"}" +
                ",{\"user_id\":\"88958196\",\"merchant_id\":\"20200911211939_88958196_x8Cs\"," +
                "\"transaction_id\":\"4200000755202009114426155129\",\"type\":\"1\",\"money\":\"50.00\"," +
                "\"state\":\"1\",\"from\":\"0\",\"create_time\":\"1599830379\",\"finish_time\":\"1599830391\"," +
                "\"referral_id\":\"1963564\",\"referral_id_permanent\":\"280877\",\"channel_id\":\"12913\"," +
                "\"book_name\":\"华丽逆袭\",\"book_tags\":\"都市\",\"referral_url\":\"/t/1963564\"," +
                "\"subscribe_time\":\"1566697702\",\"user_createtime\":\"1566526297\"," +
                "\"openid\":\"oH_OQ5ey41WEXdMtG3lrEw\",\"register_ip\":\"117.136.87.22\",\"nickname\":\"龙\"," +
                "\"avatar\":\"http://thirdwx.qlogo" +
                ".cn/mmopen" +
                "/PkEqBwHZvw4NohNeSo1mUnWSmqicfyqmQRIgGVIFow8DP0pQ7Ss0UIAY1LY4l85kzGicKNdoHD2BH7tVUCeicDL2r0eIrhI5rme" +
                "/132\"}]}}\n";

        System.out.println(ReUtil.findAll("\\d{10}", json, 0));

    }
}
