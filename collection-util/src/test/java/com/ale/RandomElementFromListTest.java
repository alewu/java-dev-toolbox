package com.ale;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 从List中获取随机元素
 */
class RandomElementFromListTest {

    /**
     * 单个随机元素
     */
    @Test
    void testRandom() {
        List<Integer> givenList = Arrays.asList(1, 2, 3);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));
        System.out.println(randomElement);
        double random = Math.random();
        int i = RandomUtils.nextInt(0, givenList.size());
        System.out.println(i);

    }
}
