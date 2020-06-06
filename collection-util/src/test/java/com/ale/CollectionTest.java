package com.ale;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alewu
 * @since 2019/4/30 22:50
 */
public class CollectionTest {
    private static final ImmutableList<Integer> NUMBERS = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * skip(n):跳过前面 n 个元素， limit(n):取前n个元素
     * 应用场景：分页,与subList的替换
     */
    @Test
    public void testStreamPage() {
        System.out.println(page(4, 2));
    }

    public List<Integer> page(int curPage, int pageSize) {
        return NUMBERS.stream().skip((curPage - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    /**
     * 可以截取List中的某一部分
     */
    @Test
    public void testSubList() {
        List<Integer> expected = NUMBERS.subList(3, 9);
        List<Integer> result = NUMBERS.stream()
                                      .skip(3)
                                      .limit(6)
                                      .collect(Collectors.toList());
        assertEquals(expected, result);
    }

    @Test
    public void testSets(){
        List<Integer> expected = NUMBERS.subList(3, 9);
        System.out.println(expected);
        List<Integer> expected1 = NUMBERS.subList(1, 5);
        System.out.println(expected1);
        Collection<Integer> intersection = CollUtil.intersection(expected, expected1);
        System.out.println(intersection);

        System.out.println(CollUtil.disjunction(expected1, intersection));
    }

}
