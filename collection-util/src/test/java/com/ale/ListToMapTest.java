package com.ale;

import com.ale.pojo.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListToMapTest {
    private static List<User> userImmutableList = ImmutableList.of(new User(1, "jack"),
                                                                   new User(2, "rose"),
                                                                   new User(3, "alice"));

    public static List<User> users;

    static {
        users = Lists.newArrayList();
        users.add(new User(1, "jack"));
        users.add(new User(2, "rose"));

    }

    @Test
    void testUniqueIndex() {
        Map<Integer, User> immutableMap = Maps.uniqueIndex(userImmutableList, User::getUserId);
        assertNotNull(immutableMap);
        immutableMap.entrySet().forEach(System.out::println);
    }

    @Test
    void testPropertiesToEntityMap() {
        // Function.identity() 等价于 user -> user
        // users.stream().collect(Collectors.toMap(User::getUserId, Function.identity()));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);
    }

    @Test
    void testPropertiesToPropertiesMap() {
        // Function.identity() 等价于 user -> user
        Map<Integer, String> userIdToUserName = users.stream().collect(Collectors.toMap(User::getUserId, User::getUserName));
        System.out.println(userIdToUserName);
        assertNotNull(userIdToUserName);
        userIdToUserName.entrySet().forEach(System.out::println);
    }

    @Test
    void testDuplicateKey() {
        users.add(new User(1, "bob"));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, Function.identity(),
                                                                              (key1, key2) -> key2));
        assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);

    }

    @Test
    void testResolvedDuplicateKey() {
        users.add(new User(1, "bob"));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, Function.identity(),
                                                                              (key1, key2) -> key2));
        assertNotNull(collect1);
        collect1.entrySet().forEach(System.out::println);

    }

}
