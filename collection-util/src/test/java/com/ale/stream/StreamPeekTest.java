package com.ale.stream;

import com.ale.pojo.Person;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * peek是个中间操作，它提供了一种对流中所有元素操作的方法，而不会把这个流消费掉
 * 这种方法主要存在于支持调试，
 * 还可以应用于修改数据状态方面
 *
 * @author alewu
 * @date 2020/7/9
 */
class StreamPeekTest {

    @Test
    void test() {
        Person a = new Person("a", 18);
        Person b = new Person("b", 23);
        Person c = new Person("c", 34);
        Stream<Person> persons = Stream.of(a, b, c);
        persons.filter(person -> person.getAge() < 30)
               .peek(person -> System.out.println("filter " + person))
               .map(person -> new Person(person.getName() + " map", person.getAge()))
               .peek(person -> System.out.println("map " + person))
               .collect(Collectors.toList());
    }

    @Test
    void testModifiedProperties() {
        Person a = new Person("a", 18);
        Person b = new Person("b", 23);
        Person c = new Person("c", 34);
        Stream<Person> persons = Stream.of(a, b, c);
        persons.filter(person -> person.getAge() < 30)
               .peek(person -> person.setAge(18))
               .map(person -> new Person(person.getName() + " map", person.getAge()))
               .peek(person -> System.out.println("map " + person))
               .collect(Collectors.toList());
    }
}
