package com.ale;

import cn.hutool.core.util.ReUtil;

import java.util.ArrayList;
import java.util.List;

public class RegexDemo {
    public static void main(String[] args) {
        String content = "ZZZaaabbbccc中文1234";

        //get demo 正则查找匹配的第一个字符串
        String resultGet = ReUtil.get("\\w{2}", content, 0);
        System.out.println("get: "+ resultGet);

        System.out.println("---------------------------------------------------------");

        //抽取多个分组然后把它们拼接起来
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        System.out.println("extractMulti: "+ resultExtractMulti);

        System.out.println("---------------------------------------------------------");

        //抽取多个分组然后把原文匹配到位置之前的内容都删除
        String[] contents = new String[]{content};
//        String s = "(\\w)aa(\\w)";
//        String resultExtractMultiAndDelPre = ReUtil.extractMultiAndDelPre(s, contents, "$1-$2");
//        System.out.println("extractMultiAndDelPre: content: {}, extract: "+ contents[0], resultExtractMultiAndDelPre);

        System.out.println("---------------------------------------------------------");

        //删除第一个匹配到的内容
        String resultDelFirst = ReUtil.delFirst("(\\w)aa(\\w)", content);
        System.out.println("delFirst: "+ resultDelFirst);

        System.out.println("---------------------------------------------------------");

        //删除第一个匹配到的内容以及之前的文本
        String resultDelPre = ReUtil.delPre("(\\w)aa(\\w)", content);
        System.out.println("delPre: "+ resultDelPre);

        System.out.println("---------------------------------------------------------");

        //查找所有匹配文本
        List<String> resultFindAll = ReUtil.findAll("\\w{2}", content, 0, new ArrayList<String>());
        System.out.println("findAll: "+ resultFindAll);

        System.out.println("---------------------------------------------------------");

        //找到匹配的第一个数字
        Integer resultGetFirstNumber= ReUtil.getFirstNumber(content);
        System.out.println("getFirstNumber: "+ resultGetFirstNumber);

        System.out.println("---------------------------------------------------------");

        //格式是否符合Ipv4格式
//        System.out.println("isIpv4: "+ ReUtil.isIpv4("127.0.0.1"));

        System.out.println("---------------------------------------------------------");

        //给定字符串是否匹配给定正则
        System.out.println("isMatch: "+ ReUtil.isMatch("\\w+[\u4E00-\u9FFF]+\\d+", content));

        System.out.println("---------------------------------------------------------");

        //通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
        System.out.println("replaceAll: "+ ReUtil.replaceAll(content, "(\\d+)", "->$1<-"));

        System.out.println("---------------------------------------------------------");

        //转义给定字符串，为正则相关的特殊符号转义
        System.out.println("replaceAll: "+ ReUtil.escape("我有个$符号{}"));

        System.out.println("---------------------------------------------------------");

    }


    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }
}
