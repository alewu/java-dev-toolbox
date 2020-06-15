package com.ale;

import java.text.DecimalFormat;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format((float) 19 / 100));

        System.out.println((float) 19 / 230);

        String xml = "<xml>\n" +
                "  <ToUserName><![CDATA[o_cxDuBE2Bn9IvxSUsMUWXI6aG14]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[gh_57e091b030dc]]></FromUserName>\n" +
                "  <CreateTime><![CDATA[1592018777]]></CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[踩踩踩从<a href='http://sp.upuptec.cn/h5/index.html?linkId=1187'>xx</a>]]></Content>\n" +
                "</xml> ";
        System.out.println(xml.replace("踩踩踩从<a href='http://sp.upuptec.cn/h5/index.html?linkId=1187'>xx</a>", "踩踩踩从<a href='http://sp.upuptec.cn/h5/index.html?linkId=1187&uid=75'>xx</a>"));

    }
}
