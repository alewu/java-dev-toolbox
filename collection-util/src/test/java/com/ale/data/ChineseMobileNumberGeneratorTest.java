package com.ale.data;


import org.junit.Test;

import java.util.stream.IntStream;


public class ChineseMobileNumberGeneratorTest {

    @Test
    public void testGenerate() {
        IntStream.range(0, 100)
                 .mapToObj(i -> ChineseMobileNumberGenerator.getInstance().generate())
                 .forEach(System.out::println);
    }

}
