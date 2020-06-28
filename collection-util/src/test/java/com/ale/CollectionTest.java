package com.ale;

import cn.hutool.core.collection.CollUtil;
import com.ale.pojo.Pig;
import com.ale.pojo.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
    public void testSets() {
        List<Integer> one = NUMBERS.subList(7, 9);
        System.out.println("one" + one);
        List<Integer> two = NUMBERS.subList(1, 5);
        System.out.println("two" + two);
        Collection<Integer> intersection = CollUtil.intersection(one, two);
        System.out.println("intersection:" + intersection);

        System.out.println("disjunction" + CollUtil.disjunction(two, intersection));
    }

    @Test
    public void test() {
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            User user = new User(i, "");
            users.add(user);
        }
        List<Pig> pigs = users.parallelStream().map(user -> {
            Pig pig = new Pig();
            pig.setId(user.getUserId());
            pig.setPigName("");
            return pig;
        }).collect(Collectors.toList());
        System.out.println(pigs);
    }

}
