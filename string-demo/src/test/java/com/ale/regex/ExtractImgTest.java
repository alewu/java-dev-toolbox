package com.ale.regex;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.regex.Pattern;

/**
  *  
  * @author alewu
  * @date 2020/7/18
  */
class ExtractImgTest {
    public static final String IMG = "<img.*?src\\s*=\\s*\"(.+?)\"";
    Pattern pattern = Pattern.compile(IMG);
    long start = 0L;

    @BeforeEach
    public void before() {
        start = Instant.now().toEpochMilli();
    }

    @AfterEach
    public void after() {
        System.out.println(String.format("耗时：%d ms", Instant.now().toEpochMilli() - start));
    }

    @Test
    void test(){
        String html = "<div id=\"wx-article-cover\"><img src=\"https://cpscdn.zsjwaw" +
                ".cn/uploads/20200714/69de430363d20d7f131006b9f2779d80.jpg\"></div><div " +
                "id=\"wx-article-body\"><section><section><section><section></section><section><p>第1章</p></section" +
                "></section><section><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-1974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-2974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-3974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-4974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-5974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-6974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-7974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-8974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-9974df.jpg\"></p><p><img src=\"https://cpsn.zsjwaw" +
                ".cn/uploads/chapter/11010058559/473126516-10974df.jpg\"></p></section></section></section></div><div" +
                " id=\"wx-article-qrcode\"></div><div " +
                "id=\"wx-article-footer\"><section><strong>由于微信篇幅限制，只能发到这里啦！</strong></section>&nbsp;" +
                "<section><strong><strong><img width=\"30px\" src=\"https://admin.yifengaf" +
                ".cn/assets/img/essay/arrow-down.gif\"></strong></strong><strong><strong></strong></strong><strong" +
                "><strong></strong> 点击下方阅读原文，后续剧情高潮不断！</strong></section></div>";
        List<String> allGroup1 = ReUtil.findAllGroup1(pattern, html);
        allGroup1.forEach(System.out::println);

    }
}
