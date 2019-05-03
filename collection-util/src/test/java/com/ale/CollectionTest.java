package com.ale;

import com.ale.data.DataGenerator;
import com.ale.data.bean.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;

/**
 * @author alewu
 * @since 2019/4/30 22:50
 */
public class CollectionTest {
    @Test
    public void testListToMap() {
        List<User> users = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            User u = new User(i, DataGenerator.getRandomJianHan(3));//初始化对象
            users.add(u);//添加到集合中
        }
        ImmutableMap<String, User> immutableMap = Maps.uniqueIndex(users, User::getUserName);
        System.out.println(immutableMap);
    }
}
