package com.ale;


// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            // 获取第一个整数
            int n = in.nextInt();
            // 获取第二个正整数
            int m = in.nextInt();
            if (n < 3 || n > 7 || m < 0) {
                // 输入不合法，返回-1
                System.out.println(-1);
            }
            List<Integer> shuixianhuas = shuiXianHua(n);
            int size = shuixianhuas.size();
            if (size < m) {
                System.out.println(shuixianhuas.get(size - 1) * m);
            } else {
                System.out.println(shuixianhuas.get(m));
            }

        }


    }

    public static List<Integer> shuiXianHua(int n) {
        List<Integer> shuixianhua = new ArrayList<>();
        // 遍历每一个n位数
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);
        int[] pow = new int[10];
        pow[1] = 1;

        for (int i = 2; i < pow.length; i++) {
            pow[i] = (int) Math.pow(i, n);
        }

        for (int i = min; i < max; i++) {
            int sum = 0;
            // 求每个位上的数字的n次幂的和
            for (int j = 10; j < max; j *= 10) {
                sum += pow[i / j % 10];
            }
            sum += pow[i % 10];
            if (sum == i) {
                shuixianhua.add(i);
            }
        }
        return shuixianhua;
    }


}