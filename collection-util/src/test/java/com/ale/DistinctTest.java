package com.ale;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * List集合去重方式及效率对比
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class DistinctTest {
    static List<Integer> list = Lists.newArrayList();
    private static final Stopwatch sw = Stopwatch.createStarted();
    private static final String template = "{} 去重时间: {} 豪秒";

    static Stream<List<Integer>> listProvider() {
        IntStream.range(0, 10000).forEach(list::add);
        return Stream.of(list);
    }

    /**
     * 使用HuToolColl实现List去重(无序)
     */
    @DisplayName("Sets")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(0)
     void testDistinctBySets(List<Integer> list) {
        sw.reset().start();
        Sets.newHashSet(list);
        System.out.println(StrUtil.format(template,"Sets", sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用HuToolColl实现List去重(无序)
     */
    @DisplayName("Hu Tool CollUtil")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(1)
     void testDistinctByHuToolColl(List<Integer> list) {
        sw.reset().start();
        CollUtil.distinct(list);
        System.out.println(StrUtil.format(template,"HuToolColl", sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用HashSet实现List去重(无序)
     */
    @DisplayName("HashSet")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(2)
     void testDistinctByHashSet(List<Integer> list) {
        sw.reset().start();
        HashSet<Integer> set = new HashSet<>(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        System.out.println(StrUtil.format(template,"HashSet",  sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用实现List去重(有序)
     */
    @DisplayName("TreeSet")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(3)
     void testDistinctByTreeSet(List<Integer> list) {
        sw.reset().start();
        TreeSet<Integer> set = new TreeSet<>(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        System.out.println(StrUtil.format(template,"TreeSet", sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用java8新特性stream实现List去重(有序)
     */
    @DisplayName("Stream")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(4)
     void testDistinctByStream(List<Integer> list) {
        sw.reset().start();
        list.stream().distinct().collect(Collectors.toList());
        System.out.println(StrUtil.format(template, "Stream", sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用List集合contains方法循环遍历(有序)
     */
    @DisplayName("List Contains")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(5)
     void testDistinctByContains(List<Integer> list) {
        sw.reset().start();
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean isContains = newList.contains(list.get(i));
            if (!isContains) {
                newList.add(list.get(i));
            }
        }
        list.clear();
        list.addAll(newList);
        System.out.println(StrUtil.format(template,"Contains",  sw.elapsed(TimeUnit.MILLISECONDS)));
    }

    /**
     * 使用两个for循环实现List去重(有序)
     */
    @DisplayName("TwoFor")
    @ParameterizedTest(name = "run #{index}")
    @MethodSource("listProvider")
    @Order(6)
     void testDistinctBy2For(List<Integer> list) {
        sw.reset().start();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                }
            }
        }
        System.out.println(StrUtil.format(template,"TwoFor", sw.elapsed(TimeUnit.MILLISECONDS)));
    }


}
