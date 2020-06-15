package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExtracterLinkTest {
    public static final Pattern compile = Pattern.compile("<a[^>]*href=[\"'](?<url>[^\"']*?)[\"'][^>]*>(?<text>[\\w\\W]*?)</a>");
    @Test
    public void testLinkExacter(){
        String text = "我的心<a href='www.baidu.com?linkId=12'>xx</a>消息<a href='www.google.com?linkId=1225'>vvv</a>你的心";

        List<String> srcLinks = ReUtil.findAll(compile, text, 1, new ArrayList<>());

        srcLinks.forEach(System.out::println);

        Assertions.assertNotNull(srcLinks);

    }


}
