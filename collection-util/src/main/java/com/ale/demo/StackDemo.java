package com.ale.demo;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        // 添加元素
        s.push("a");
        s.push("b");
        s.push("c");
        s.push("d");
        s.push("e");
        s.push("f");

        // 查看栈顶元素
        // 如果栈为空，则抛出空栈异常，因此最好判断栈是否为空
        if(s.isEmpty())
            System.out.println(s.peek());

        // 移除栈顶元素
        System.out.println(s.pop());

        // 查找元素
        // 从栈顶网栈底查找，且以1为基数
        System.out.println(s.search("b"));

        System.out.println(s);
    }
}
