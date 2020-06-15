package com.ale;

import cn.hutool.core.util.ReUtil;
import org.junit.jupiter.api.Test;

public class DelFirstTest {
    @Test
    public void test(){
        String content = "ZZZaaabbbccc中文1234";

        //抽取多个分组然后把原文匹配到位置之前的内容都删除
        String[] contents = new String[]{content};
        //        String s = "(\\w)aa(\\w)";
        //        String resultExtractMultiAndDelPre = ReUtil.extractMultiAndDelPre(s, contents, "$1-$2");
        //        System.out.println("extractMultiAndDelPre: content: {}, extract: "+ contents[0], resultExtractMultiAndDelPre);

        //删除第一个匹配到的内容
        String resultDelFirst = ReUtil.delFirst("(\\w)aa(\\w)", content);
        System.out.println("delFirst: "+ resultDelFirst);

    }
}
