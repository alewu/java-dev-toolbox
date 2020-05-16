package com.ale;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class RandomDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(RandomUtil.randomEle(list));
        System.out.println(0L==0);


    }
}
