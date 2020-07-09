package com.ale;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ListToArrayTest {
    @Test
    void test() {
        List<String> strings = new ArrayList<>();
        strings.toArray(new String[3]);
        System.out.println(strings);
    }
}
