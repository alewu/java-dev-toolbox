package com.ale;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ForDemo {
    public static void main(String[] args) {
        ArrayList<String> objects = new ArrayList<>();
        objects.forEach(s -> System.out.println(s));

        int i = ThreadLocalRandom.current().nextInt(700);
        int i1 = i / 60;
        System.out.println(i + " /: " + i1 + "  %: " + i % 60);
        String a = getString(i1);
        System.out.println(a);
        for (int j = 0; j < 10; j++) {

        }
    }

    private static String getString(int i1) {
        String a = "";
        if (i1 < 9) {
            a = "" + i1;
        } else {
            a = "" + 10;
        }
        return a;
    }
}
