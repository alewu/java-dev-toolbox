package com.ale;


import org.apache.commons.lang3.StringUtils;

public class StringDemo {

    public static void main(String[] args) {
        String pattern = "\\d+-\\d+";
        String name = "1-1";
        System.out.println( name.matches(pattern));
        String s = StringUtils.joinWith(":", "jack", "rose", "bob");
        System.out.println(s);
        System.out.println(Double.valueOf(1.012).longValue());
    }
}
