package com.ale;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alewu
 * @since 2019/4/30 22:50
 */
public class CollectionTest {
    private static ImmutableList list = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * 应用场景：代码里面做分页
     */
    @Test
    public void testStreamPage() {
        System.out.println(page(1, 2));
    }

    public List page(int curPage, int pageSize) {
        return (List) list.stream().limit(pageSize).skip(curPage - 1).collect(Collectors.toList());
    }

}
