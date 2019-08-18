package com.ale;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
  *  单词提取工具
  * @author alewu
  * @date 2019/8/18
  */
public class WordUtil {

    private static Pattern word = Pattern.compile("\\w+");
    /**
     *
     * @param line 行
     * @return 单词
     */
    public static List<String> getWords(String line){
        Matcher ma = word.matcher(line);
        List<String> words = Lists.newArrayList();
        while (ma.find()) {
            words.add(ma.group());
        }
        return words;
    }
}
