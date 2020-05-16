package com.ale;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Editor;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    private static final ImmutableList<Integer> NUMBERS = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @DisplayName("Guava Collections2")
    @Test
    public void test() {
        List<Integer> numbers = ImmutableList.of(1, 2, 3);
        Collection<Integer> filter = Collections2.filter(NUMBERS, i -> !numbers.contains(i));
        System.out.println(filter);
        assertEquals(6, filter.size());
    }

    @DisplayName("Stream")
    @Test
    public void testStream() {
        List<Integer> numbers = ImmutableList.of(1, 2, 3);
        Collection<Integer> filter =
                NUMBERS.stream().filter(integer -> !numbers.contains(integer)).collect(Collectors.toList());
        System.out.println(filter);
        assertEquals(6, filter.size());
    }

    @DisplayName("CollUtil")
    @Test
    public void testCollUtil() {
        List<Integer> numbers = ImmutableList.of(1, 2, 3);
        List<Integer> filter = CollUtil.filter(NUMBERS, (Editor<Integer>) integer -> {
            if (!numbers.contains(integer)) {
                return integer;
            }
            return null;
        });
        System.out.println(filter);
        assertEquals(6, filter.size());
    }

}
