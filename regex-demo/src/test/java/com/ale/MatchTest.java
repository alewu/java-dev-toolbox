package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class MatchTest {
    @Test
    public void test(){
        String content = "ZZZaaabbbccc中文1234";
        //给定字符串是否匹配给定正则
        System.out.println("isMatch: "+ ReUtil.isMatch("\\w+[\u4E00-\u9FFF]+\\d+", content));
    }
}
