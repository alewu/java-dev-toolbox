package com.ale;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //    public static void main(String[] args) {
    //        Scanner sc = new Scanner(System.in);
    //        String input = sc.nextLine();
    //
    //    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (sc.hasNext()) {
            Integer s = sc.nextInt();
            if (s == 0) {
                break;
            }
            list.add(s);
        }

        for (Integer i : list) {
            if (i % 2 == 0) {
                System.out.println(i / 2);
            } else {
                System.out.println(((i - 1) / 2));
            }
        }
    }


}
