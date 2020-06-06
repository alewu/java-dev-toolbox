package com.ale;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringJoinerTest {
    @Test
    public void testSimpleJoin() {
        String join = String.join("/", "a", "b", "c");
        assertEquals("a/b/c", join);
    }

    @Test
    public void testJoinList() {
        List<String> list = ImmutableList.of("hello", "world", "java");
        String simpleJoinList = String.join(",", list);
        assertEquals("hello,world,java", simpleJoinList);
    }

    @Test
    public void testJoinWithPrefixAndSuffix() {
        List<String> languages = ImmutableList.of("java", "python", "c");
        String delimiter = ",";
        String prefix = "{";
        String suffix = "}";

        StringJoiner joinWithPrefixAndSuffix = new StringJoiner(delimiter, prefix, suffix);
        for (String s : languages) {
            joinWithPrefixAndSuffix.add(s);
        }
        assertEquals("{java,python,c}", joinWithPrefixAndSuffix.toString());
    }
}
