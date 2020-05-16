package com.ale;

import com.ale.data.DataGenerator;
import com.ale.data.bean.User;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;


import java.util.List;

/**
 * @author alewu
 * @since 2019/4/30 22:29
 */
public class MapsTest {

    /**
     * Maps.uniqueIndex()
     * 应用场景：有一组对象，它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象
     */
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

    /**
     * Multimap
     * 应用场景：有时候我们需要这样的数据类型Map<String,Collection<String>>，guava中的Multimap就是为了解决这类问题的。
     */
    @Test
    public void testMultiMap(){
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("C", "good");
        map.put("C", "Better");
        map.put("Java", "good");
        map.put("Python", "good");
        System.out.println(map.get("C"));
    }

    /**
     * 应用场景：构造一个不可变的map，用来作为测试数据
     */
    @Test
    public void testImmutableMap() {
        ImmutableMap<String, String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
    }


}
