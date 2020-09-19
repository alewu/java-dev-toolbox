package com.ale;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author alewu
 * @date 2020/9/17
 */
class SetsTest {
    Map<String, Integer> m1 = ImmutableMap.of("a", 1, "b", 2, "d", 3);
    Map<String, Integer> m2 = ImmutableMap.of("a", 1, "b", 4, "e", 5);
    Map<String, Integer> m3 = ImmutableMap.of();


    @Test
    void testDifference(){
        Sets.SetView<String> difference1 = Sets.difference(m1.keySet(), m2.keySet());
        // 返回在m1中存在， 但不再m2中存在的
        System.out.println("difference1: " + difference1);

        Sets.SetView<String> difference2 = Sets.difference(m2.keySet(), m1.keySet());
        System.out.println("difference2: " + difference2);
    }

    @Test
    void testSymmetricDifference(){
        Sets.SetView<String> symmetricDifference = Sets.symmetricDifference(m1.keySet(), m2.keySet());
        System.out.println("symmetricDifference: " + symmetricDifference);
    }

    @Test
    void testUnion(){
        Sets.SetView<String> difference2 = Sets.difference(m2.keySet(), m1.keySet());
        Sets.SetView<String> intersection = Sets.intersection(m1.keySet(), m2.keySet());
        Sets.SetView<String> union = Sets.union(m2.keySet(), intersection);
        System.out.println("union" + union);
    }
}
