package com.ale;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

/**
 * @author alewu
 * @since 2019/4/30 22:47
 */
public class ListsTest {
    /**
     * 应用场景：构造一个不可变的list，用来作为测试数据
     */
    @Test
    public void test() {
        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
    }
}
