package com.ale;

import com.google.common.collect.Lists;
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

    @Test
    void testArray(){
        String[] a = new String[]{};
        List<String> strings = Lists.newArrayList(a);
        List<String> list = Lists.newArrayListWithCapacity(10);
        list.add("1");
        list.addAll(strings);
        System.out.println(list);
    }
}
