package com.ale;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class MapSplitterTest {


    /**
     * 多个键值对的字符串 与 Map 相互转换
     */
    @Test
    void test() {
        Map<String, String> maps = ImmutableMap.of("name", "jack", "age", "2", "gender", "M");

        String ss = Joiner.on("&").withKeyValueSeparator("=").join(maps);

        Assertions.assertEquals("name=jack&age=2&gender=M", ss);

        maps = Splitter.on("&").withKeyValueSeparator("=").split(ss);

        System.out.println(maps);
    }
}
