package com.ale;

import cn.hutool.core.math.MathUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONNull;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @date 2023/4/27
 */
public class ListCombinationTest {

    @Test
    public void test() {
        ArrayList<String> integers = Lists.newArrayList("1", "1", "2");
        List<String[]> subset = subset(integers.toArray(new String[]{}));

        Map<Integer, String[] > map = new HashMap<>();

        subset.forEach(p -> {
            List<String> collect = Arrays.stream(p).collect(Collectors.toList());
            System.out.println(collect);


            int sum = Arrays.stream(p).mapToInt(Integer::parseInt).sum();

            map.put(sum, p);

        });
        System.out.println("------------------------------------------");
        for (Map.Entry<Integer, String[]> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String[] value = entry.getValue();
            System.out.println("amount: " + key + " \n " + String.join(",", value));
        }

        Long a =12L;
        System.out.println(a.compareTo(BigDecimal.ZERO.longValue()));
        Long b =0L;
        System.out.println(b.compareTo(BigDecimal.ZERO.longValue()));
        Long c =-1L;
        System.out.println(c.compareTo(BigDecimal.ZERO.longValue()));






    }

    public static List<String[]> subset(String[] lists) {
        List<String[]> result = new ArrayList<>();
        for (int i = 1; i <= lists.length; i++) {

            List<String[]> strings = MathUtil.combinationSelect(lists, i);
            result.addAll(strings);

        }
        return result;

    }

    @Test
    public void test2() {
        ArrayList<String> integers = Lists.newArrayList("1", "1");
        List<String> subset = permutationNoRepeat(integers, 1);
        subset.forEach(p -> {
            System.out.println(p);
        });

    }

    /**
     * 排列组合(字符不重复排列)<br>
     * 内存占用：需注意结果集大小对内存的占用（list:10位，length:8，结果集:[10! / (10-8)! = 1814400]）
     * @param list 待排列组合字符集合(忽略重复字符)
     * @param length 排列组合生成长度
     * @return 指定长度的排列组合后的字符串集合
     * @author www@yiynx.cn
     */
    public static List<String> permutationNoRepeat(List<String> list, int length) {
        Stream<String> stream = list.stream().distinct();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream()
                    .filter(temp -> !str.contains(temp))
                    .map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }
}
