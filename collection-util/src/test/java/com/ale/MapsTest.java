package com.ale;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

/**
 * @author alewu
 * @since 2019/4/30 22:29
 */
public class MapsTest {

    @Test
    public void testMultiMap(){
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("C", "good");
        map.put("C", "Better");
        map.put("Java", "good");
        map.put("Python", "good");
        System.out.println(map.get("C"));

    }
}
