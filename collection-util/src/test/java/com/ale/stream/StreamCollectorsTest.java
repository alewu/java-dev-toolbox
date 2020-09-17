package com.ale.stream;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alewu
 * @date 2020/8/11
 */
class StreamCollectorsTest {

    @Test
    void testSum(){
        List<Integer> results = ImmutableList.of(1, 2, 3, 4, 5, 6);
        IntSummaryStatistics intSummaryStatistics = results.stream().collect(Collectors.summarizingInt(value -> value));
        Long sum = intSummaryStatistics.getSum();
        assertEquals(21L, sum);
        System.out.println("集合中最小值：" + intSummaryStatistics.getMax());
        System.out.println("集合中最大值：" + intSummaryStatistics.getMin());
        System.out.println("集合中平均值：" + intSummaryStatistics.getAverage());
    }
}
