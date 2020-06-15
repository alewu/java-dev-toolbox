package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class ReplaceTest {
    @Test
    public void test(){
        String content = "ZZZaaabbbccc中文1234";
        //通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
        System.out.println("replaceAll: "+ ReUtil.replaceAll(content, "(\\d+)", "->$1<-"));

    }
}
