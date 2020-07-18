package com.ale;

import com.ale.demo.DequeDemo;
import com.ale.pojo.Condition;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class DequeTest {
    @Test
    public void test(){
        Queue<Condition> queue = new ArrayDeque<>();

        queue.add(new Condition("a", "111"));
        queue.add(new Condition("b", "222"));
        queue.add(new Condition("c", "333"));
        queue.add(new Condition("d", "444"));

        String result = DequeDemo.recursionJoinMatchStr("axxxbxxxcxxxd", queue);
        System.out.println(result);
    }
}
