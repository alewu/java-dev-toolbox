package com.ale;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


    }

    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);

    }


}
