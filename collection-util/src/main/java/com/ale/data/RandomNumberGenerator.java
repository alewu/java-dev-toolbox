package com.ale.data;

import java.util.Random;

/**
 * @author alewu
 * @since 2019/5/1 00:12
 */
public final class RandomNumberGenerator {

    private static final Random RANDOM = new Random();

    public static String getRandomNumber(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(RANDOM.nextInt(10));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(getRandomNumber(3));
        }
    }

}
