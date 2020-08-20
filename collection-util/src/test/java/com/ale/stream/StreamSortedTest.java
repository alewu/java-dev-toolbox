package com.ale.stream;

import com.ale.pojo.Student;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class StreamSortedTest {
    @Test
    void test() {
        List<Student> students = ImmutableList.of(
                new Student(1, "jack", 18, 1.72, LocalDate.of(2001, 3, 23)),
                new Student(2, "rose", 18, 1.52, LocalDate.of(2001, 6, 3)),
                new Student(3, "lucy", 19, 1.82, LocalDate.of(2000, 3, 11)),
                new Student(4, "alan", 19, 1.62, LocalDate.of(2000, 3, 11)),
                new Student(5, "mike", 19, 1.92, LocalDate.of(2000, 3, 11)));
        List<Student> collect =
                students.stream().sorted(Comparator.comparing(Student::getBirthday).thenComparing(Student::getHeight)).collect(Collectors.toList());
        Map<Integer, List<Student>> collect1 = collect.stream().collect(Collectors.groupingBy(Student::getAge));
        int i = 0;
        for (Map.Entry<Integer, List<Student>> entry : collect1.entrySet()) {
            Integer key = entry.getKey();
            double j = 0.10;
            for (Student student : entry.getValue()) {
                System.out.println(key + ":" + student.getId());
                student.setHeight(student.getHeight() + i);
                j += 0.10;
            }
            i++;

        }
        collect.forEach(
                System.out::println
        );
    }

    @Test
    void test1() {
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        System.out.println(Instant.now().toEpochMilli() - start);

    }

    @Test
    void testTime(){
        long l = TimeUnit.SECONDS.toMillis(2);
        System.out.println(l);
    }
}
