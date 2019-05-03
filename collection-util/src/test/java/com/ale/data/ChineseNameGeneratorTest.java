package com.ale.data;


import org.junit.Test;

import java.util.stream.IntStream;

public class ChineseNameGeneratorTest {

    @Test
    public void testGenerate() {
        IntStream.range(0, 100)
                 .mapToObj(i -> ChineseNameGenerator.getInstance().generate())
                 .forEach(System.out::println);
    }


}
