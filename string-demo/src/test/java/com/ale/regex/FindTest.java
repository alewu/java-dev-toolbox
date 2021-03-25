package com.ale.regex;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {


    @Test
    public void testFindMatchFirst() {
        String content = "ZZZxx中文1234";
        //get demo 正则查找匹配的第一个字符串
        String resultGet = ReUtil.get("\\w{2}", content, 0);
        assertEquals("ZZ", resultGet);
    }


    @Test
    public void testFindAll() {
        String content = "ZZZaaabbbccc中文1234";
        //查找所有匹配文本
        List<String> resultFindAll = ReUtil.findAll("\\w{2}", content, 0, new ArrayList<>());
        System.out.println("findAll: " + resultFindAll);
    }


    @Test
    public void testFindFirstNumber() {
        String content = "ZZZxx中文1234";
        //找到匹配的第一个数字
        Integer resultGetFirstNumber = ReUtil.getFirstNumber(content);
        System.out.println("getFirstNumber: " + resultGetFirstNumber);
        assertEquals(1234, resultGetFirstNumber);
    }
}
