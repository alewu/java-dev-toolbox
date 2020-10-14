package com.ale.stream;

import com.ale.pojo.Teacher;
import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alewu
 * @date 2020/8/11
 */
class StreamCollectorsTest {
    private static final List<Teacher> groupings = new ArrayList<>();
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

    @Test
    void testGroup(){
        //设置 语言 ，地区
        Locale local = new Locale("zh", "CN");
        //创建对象
        Faker faker = new Faker(local);
        for (int i = 0; i < 10; i++) {
            Teacher teacher = new Teacher();
            teacher.setClassNo(i % 2 == 0 ? "M" : "W");
            teacher.setName(faker.name().username());
            teacher.setAge(i);
            groupings.add(teacher);
        }
        System.out.println(groupings);
        Map<String, Teacher> map = groupings.stream().collect(Collectors.groupingBy(Teacher::getClassNo,
                                                                                         Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> c1.getName().equals(c2.getName()) ? c1 : c2), Optional::get)));
        System.out.println(map);
    }
}
