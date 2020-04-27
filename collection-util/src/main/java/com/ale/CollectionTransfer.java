package com.ale;

import com.ale.pojo.User;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionTransfer {

    private static List userImmutableList = ImmutableList.of(new User(1, "jack"), new User(2, "rose"), new User(3,
                                                                                                                "alice"
    ));

    public static List<User> users =null;

   static  {
        users = Lists.newArrayList();
        users.add(new User(1, "jack"));
        users.add(new User(2, "rose"));

    }

    public static void main(String[] args) {

        listToMap1();
    }

    public static void listToMap() {
        Map immutableMap = Maps.uniqueIndex(userImmutableList, User::getUserId);
        for (Object o : immutableMap.entrySet()) {
            System.out.println(o);
        }
    }

    public static void listToMap1() {
        Object collect = userImmutableList.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        System.out.println(collect);
    }

    /**
     * 重复key的情况
     */
    public static void listToMap2() {
        users.add(new User(1, "jack"));
        Map<Integer, User> collect1 = users.stream().collect(Collectors.toMap(User::getUserId, user -> user));
        System.out.println(collect1);
    }



}
