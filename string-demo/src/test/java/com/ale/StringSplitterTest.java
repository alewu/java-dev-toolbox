package com.ale;

import cn.hutool.core.util.ReUtil;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StringSplitterTest {
    @Test
    public void testSimpleSplit() {
        String url = "http://172.168.1.1:8080//picture/test.jpg";
        String substring = url.substring(url.lastIndexOf("//") + 2);
        assertEquals("picture/test.jpg", substring);

        String filename = url.substring(url.lastIndexOf("/") + 1);
        assertEquals("test.jpg", filename);

        String ext = url.substring(url.lastIndexOf('.') + 1);
        assertEquals("jpg", ext);

        String ipAndPort = url.substring(url.indexOf("//") + 2, url.lastIndexOf("//"));
        assertEquals("172.168.1.1:8080", ipAndPort);
    }

    @Test
    public void testSplitToList() {
        String url = "http://172.168.1.1:8080//picture/test.jpg";
        List<String> strings = Splitter.on("/").splitToList(url);
        strings.forEach(System.out::println);
        List<String> strings1 = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(url);
        strings1.forEach(System.out::println);
    }
    @Test
    public void test(){
        String text = "踩踩踩从<a href='www.baidu.com?linkId=12'>xx</a><a href='www.google.com?linkId=1225'>vvv</a>aaaa";
        Pattern compile = Pattern.compile("linkId=\\d+");
        // 拆
        List<String> strings = Splitter.on(compile).splitToList(text);
        // 找
        List<String> all = ReUtil.findAll(compile, text, 0);
        // 拼
        List<String> collect = all.stream().map(s -> s.concat("&uid=12")).collect(Collectors.toList());

        all.forEach(System.out::println);
        strings.forEach(System.out::println);

        // 合
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = collect.iterator();
        for (String string : strings) {
            sb.append(string);
            if (iterator.hasNext()) {
                sb.append(iterator.next());
            }
        }
        System.out.println(sb);

    }
}
