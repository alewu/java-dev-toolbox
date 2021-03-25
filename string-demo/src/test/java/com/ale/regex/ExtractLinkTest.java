package com.ale.regex;

import cn.hutool.core.util.ReUtil;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class ExtractLinkTest {
    public static final Pattern compile = Pattern.compile("<a[^>]*href=[\"'](?<url>[^\"']*?)[\"'][^>]*>" +
                                                                  "(?<text>[\\w\\W]*?)</a>");

    @Test
    void testLinkExacter() {
        String text = "我的心<a href='www.baidu.com?xx=12'>xx</a>消息<a href='www.google.com?linkId=1225'>vvv</a>你的心";

        List<String> srcLinks = ReUtil.findAll(compile, text, 1, new ArrayList<>());


        String aa = ReUtil.replaceAll(text, compile, "xxx");

        System.out.println(aa);
        srcLinks.forEach(System.out::println);

        Assertions.assertNotNull(srcLinks);

    }

    @Test
    void test() {
        Pattern compile = Pattern.compile("http://.+/h5/index\\.html\\?linkId=\\d+");
        String text = "xxxhttp://dev.lijukun.top/h5/index.html?linkId=1259www.cn.bing.com/search?q=%e8%b4%a3%e4%bb%bb%e9%93%be%e6%a8%a1%e5%bc%8f%e7%9a%84%e5%ae%9e" +
                "%e9%99%85%e5%ba%94%e7%94%a8&qs=SC&pq=%e8%b4%a3%e4%bb%bb%e9%93%bem&sk=SC1&sc=8-4&cvid" +
                "=206BF694B9CB4F0286BC7CCCB7CC4539&FORM=QBLH&sp=2";

        List<String> srcLinks = ReUtil.findAll(compile, text, 0, new ArrayList<>());
        String xx = ReUtil.replaceAll(text, compile, "aaaaxxxaaaa");
        System.out.println(xx);
        srcLinks.forEach(System.out::println);
    }

    @Test
    void test11(){
        String text = "http://dev.lijukun.top/h5/index.html?linkId=1256";

        String s = Splitter.on("/").omitEmptyStrings().splitToList(text).get(1);
        System.out.println(s);

    }


}
