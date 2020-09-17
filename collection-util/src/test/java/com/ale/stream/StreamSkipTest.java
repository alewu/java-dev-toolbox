package com.ale.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
  *
  * @author alewu
  * @date 2020/8/11
  */
class StreamSkipTest {
    @Test
    void testSkip(){
        List<String> strs = Arrays.asList("1", "2", "3", "4", "5");
        Stream<String> stream = strs.stream();
        List<String> collect = stream.skip(1).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
        assertEquals(4, collect.size());

    }
}
