package com.ale.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
  *
  * @author alewu
  * @date 2020/8/11
  */
class StreamSkipTest {
    @Test
    void test(){
        List<String> strs = Arrays.asList("1", "2", "3", "4", "5");
        List<String> collect = strs.stream().skip(1).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }

    }
}
