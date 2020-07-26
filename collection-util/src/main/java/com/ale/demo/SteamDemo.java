package com.ale.demo;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SteamDemo {
    private static final String aa = null;

    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        List<Integer> collect =
                list.parallelStream().map(integer -> SteamDemo.add(integer)).collect(Collectors.toList());

        System.out.println(collect);
    }

    @SneakyThrows
    public static int add(Integer integer) {

        aa.concat("");

        return 1;
    }
}
