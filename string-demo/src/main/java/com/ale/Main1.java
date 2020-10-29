package com.ale;


// 本题为考试单行多行输入输出规范示例，无需提交，不计分。


import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String firstLine = in.nextLine();
            String secondLine = in.nextLine();
            // 解析成单词
            List<String> allWords = getNewWords(firstLine);
            // 查找前缀单词
            List<String> target = new ArrayList<>();
            for (String word : allWords) {
                boolean b = word.startsWith(secondLine);
                if (b) {
                    target.add(word);
                }
            }
            if (target.isEmpty()) {
                System.out.println(secondLine);
            }
            Collections.sort(target);
            // 去重
            Set<String> set = new TreeSet<>(target);
            System.out.println(String.join(" ", set));

        }


    }

    public static List<String> getNewWords(String firstLine) {
        List<String> allWords = new ArrayList<>();
        String[] words = firstLine.split(" ");
        for (String word : words) {
            String[] split1 = word.split("'");
            for (String s : split1) {
                String replace = s.replace(".", "").replace(",", "");
                allWords.add(replace);
            }

        }
        return allWords;
    }

    public static List<String> getWords(String firstLine) {

        String[] words = firstLine.split(" ");
        List<String> allWords = new ArrayList<>();
        // 处理缩略形式
        for (String word : words) {
            String[] split = word.split("'");
            List<String> splits = Arrays.asList(split);
            allWords.addAll(splits);
        }

        return allWords;
    }


}