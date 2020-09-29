package com.ale.enum1;

public class Demo {
    public static void main(String[] args) {
        System.out.println(Season.valueOf("SPRING").getValue());
        System.out.println(Season.getValue("1"));
        System.out.println(Season.getName("spring"));
    }
}
