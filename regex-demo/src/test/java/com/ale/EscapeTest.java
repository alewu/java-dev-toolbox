package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class EscapeTest {
    @Test
    public void test(){
        //转义给定字符串，为正则相关的特殊符号转义
        System.out.println("replaceAll: "+ ReUtil.escape("我有个$符号{}"));
    }
}
