package com.ale;

public class StringDemo {

    public static void main(String[] args) {
        String pattern = "\\d-\\d";
        String name = "1-1";
        System.out.println( name.matches(pattern));
    }
}
