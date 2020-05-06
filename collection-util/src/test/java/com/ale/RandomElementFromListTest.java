package com.ale;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 从List中获取随机元素
 */
public class RandomElementFromListTest {

    /**
     * 单个随机元素
     */
    @Test
    public void test() {
        List<Integer> givenList = Arrays.asList(1, 2, 3);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        System.out.println(randomElement);
        double random = Math.random();

        int i = RandomUtils.nextInt(0, givenList.size());
        System.out.println(i);

    }

    @Test
    public void test1() {
        List<Integer> givenList = new ArrayList<>();
        givenList.forEach((a) -> {
            System.out.println(a);
        });
    }
    
    @Test
    public void test11(){
        int i = 6;
        if (i > 5) {
            System.out.println(i + ">5");
        } else if (i > 4) {
            System.out.println(i + ">6");
        }
        
    }
}
