package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void test(){
        String content = "ZZZaaabbbccc中文1234";
        //抽取多个分组然后把它们拼接起来
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        System.out.println("extractMulti: "+ resultExtractMulti);
    }
}
