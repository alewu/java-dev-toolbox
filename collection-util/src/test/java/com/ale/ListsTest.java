package com.ale;

import com.ale.pojo.Person;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author alewu
 * @since 2019/4/30 22:47
 */
 class ListsTest {
    /**
     * 应用场景：构造一个不可变的list，用来作为测试数据
     */
    @Test
     void test() {
        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsWithCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Add test",
                                        () -> assertEquals(2, Math.addExact(1, 1))),
                DynamicTest.dynamicTest("Multiply Test",
                                        () -> assertEquals(4, Math.multiplyExact(2, 2))));
    }

    @Test
     void testss() {
        //        Stack
        Person p1 = new Person("jack", 12);
        Person p2 = new Person("rose", 13);
        Person p3 = new Person("bob", 15);
        List<Person> persons = Lists.newArrayList(p1, p2, p3);
        Person p4 = new Person("bob", 15);
        boolean remove = persons.remove(p4);
        assertTrue(remove);
        for (Person aBoolean : persons) {
            System.out.println(aBoolean);
        }

    }
}
