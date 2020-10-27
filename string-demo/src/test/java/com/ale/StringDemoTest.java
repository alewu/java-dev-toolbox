package com.ale;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringDemoTest {


    @Test
    void testReverse() {
        String s = "123456";
        char[] chars = s.toCharArray();
        int initValue = s.length() - 1;
        for (int i = initValue; i >= 0; i--) {
            System.out.print(chars[i]);
        }
    }

    @Test
    void testReverseSentence() {
        String sentence = "I am a boy";
        String[] s = sentence.split(" ");
        int initValue = s.length - 1;
        for (int i = initValue; i >= 0; i--) {
            System.out.print(s[i] + " ");
        }

    }

    @Test
    void testSort() {
        String[] words = new String[]{"cap", "to", "cat", "card", "two", "too", "up", "boat", "boot"};

        Arrays.sort(words);

        for (String word : words) {
            System.out.println(word);
        }

    }

    @Test
    void test() {
        Integer[] integers = new Integer[]{3, 5, 8, 10, 81, 0};
        for (Integer integer : integers) {
            if (integer == 0) {
                break;
            }
            if (integer % 2 == 0) {
                System.out.println(integer / 2);
            } else {
                System.out.println(((integer - 1) / 2));
            }


        }

    }


}
