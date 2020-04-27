package com.ale;

import com.ale.pojo.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToMapTest {
    private static List userImmutableList = ImmutableList.of(new User(1, "jack"), new User(2, "rose"), new User(3,
                                                                                                                "alice"
    ));

    public static List<User> users = null;

    static {
        users = Lists.newArrayList();
        users.add(new User(1, "jack"));
        users.add(new User(2, "rose"));

    }

    @Test
    public void testUniqueIndex() {
        Map<Integer, User> immutableMap = Maps.uniqueIndex(userImmutableList, User::getUserId);
        Assert.assertNotNull(immutableMap);
        immutableMap.entrySet().forEach(System.out::println);
    }

    @Test
    public void testToMap() {
        // Function.identity() 等价于 user -> user
        Object collect = userImmutableList.stream().collect(Collectors.toMap(User::getUserId, Function.identity()));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        Assert.assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);
    }

    @Test
    public void testDuplicateKey() {
        users.add(new User(1, "bob"));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, Function.identity(),
                                                                              (key1, key2) -> key2));
        Assert.assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);

    }
    @Test
    public void testDuplicateKey() {
        users.add(new User(1, "bob"));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, Function.identity(),
                                                                              (key1, key2) -> key2));
        Assert.assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);

    }

}
