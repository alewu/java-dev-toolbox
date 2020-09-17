package com.ale.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(givenList);
        int randomSeriesLength = 3;
        List<Integer> randomSeries = givenList.subList(0, randomSeriesLength);
        randomSeries.stream().forEach(System.out::println);
    }

}
