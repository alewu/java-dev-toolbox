package com.ale;


import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static final Pattern compile = Pattern.compile("<a[^>]*href=[\"'](?<url>[^\"']*?)[\"'][^>]*>(?<text>[\\w\\W]*?)</a>");
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void test(){
        String text = "踩踩踩从<a href='www.baidu.com?linkId=12'>xx</a><a href='www.google.com?linkId=1225'>vvv</a>aaaa";

        List<String> srcLinks = ReUtil.findAll(compile, text, 1, new ArrayList<>());
        srcLinks.forEach(System.out::println);

        String replace = "";
        for (String srcLink : srcLinks) {
            String concat = srcLink.concat("&uid=123");
            System.out.println(concat);
             replace = text.replace(srcLink, concat);
        }
        System.out.println(replace);
        // 匹配
        // 修改

        // 弄回去
    }

    @Test
    public void testadd(){
        String text = "踩踩踩从<a href='www.baidu.com?linkId=12'>xx</a><a href='www.google.com?linkId=1225'>vvv</a>aaaa";
    }
}
