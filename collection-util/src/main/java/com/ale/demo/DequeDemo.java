package com.ale.demo;

import cn.hutool.core.util.ReUtil;
import com.ale.pojo.Condition;

import java.util.ArrayDeque;
import java.util.Queue;

public class DequeDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        for (String s : queue) {
            System.out.println(s);
        }
        String temp = "xxxxxxayyyybzzzzcyyyyd";

        String recursion = recursionJoinMatchStr(temp,"$0uid", queue);
        System.out.println(recursion);
    }

    public static String recursionJoinMatchStr(String src, String replacementTemplate, Queue<String> queue) {
        if (queue.isEmpty()) {
            return src;
        } else {
            String value = ReUtil.replaceAll(src, queue.poll(), replacementTemplate);
            return recursionJoinMatchStr(value, replacementTemplate, queue);
        }
    }

    public static String recursionJoinMatchStr(String src, Queue<Condition> queue) {
        if (queue.isEmpty()) {
            return src;
        } else {
            Condition condition = queue.poll();
            String value = ReUtil.replaceAll(src, condition.getIndex(), condition.getReplacement());
            return recursionJoinMatchStr(value, queue);
        }
    }
}
