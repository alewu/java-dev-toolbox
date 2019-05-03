package com.ale.data.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
public class ChineseCharUtils {

    private static Random random = new Random(System.currentTimeMillis());

    public static String genOneChineseChars() {
        int highPos = 176 + Math.abs(random.nextInt(39));
        int lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[]{(byte) highPos, (byte) lowPos};
        String str = null;
        try {
            str = new String(b, "GB2312");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return str;
    }

    public static String genFixedLengthChineseChars(int length) {
        String str = "";
        for (int i = length; i > 0; i--) {
            str = str + genOneChineseChars();
        }

        return str;
    }

    public static String genRandomLengthChineseChars(int start, int end) {
        String str = "";
        int length = random.nextInt(end + 1);
        if (length < start) {
            str = genRandomLengthChineseChars(start, end);
        } else {
            for (int i = 0; i < length; i++) {
                str += genOneChineseChars();
            }
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(genOneChineseChars());
        System.out.println(genFixedLengthChineseChars(20));
        System.out.println(genRandomLengthChineseChars(2, 10));
    }
}
