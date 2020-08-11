package com.ale.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
  *
  * @author alewu
  * @date 2020/8/11
  */
class StreamMatchTest {
    @Test
    void test(){
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE

    }

    @Test
    void test1(){
        List<Boolean> results = ImmutableList.of(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        boolean b = results.stream().allMatch(result -> Objects.equals(result, Boolean.TRUE));
        System.out.println(b);
    }
}
