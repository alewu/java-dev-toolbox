package com.ale;

import cn.hutool.core.util.CharUtil;
import com.ale.pojo.Grouping;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 分组测试
 *
 * @author alewu
 * @date 2020/5/16
 */
public class GroupingTest {
    private static final List<Grouping> groupings = new ArrayList<>();

    @BeforeEach
    public void init() {
        //设置 语言 ，地区
        Locale local = new Locale("zh", "CN");
        //创建对象
        Faker faker = new Faker(local);
        for (int i = 0; i < 9; i++) {
            Grouping grouping = new Grouping();
            grouping.setId(i);
            grouping.setType(i % 2 == 0 ? "M" : "W");
            grouping.setName(faker.name().username());
            grouping.setAddress(faker.address().country());
            groupings.add(grouping);
        }
    }

    @DisplayName("GroupingByOneCondition")
    @Test
    public void testGroupingByOneCondition() {
        Map<String, List<Grouping>> filter =
                groupings.stream().collect(Collectors.groupingBy(Grouping::getType));
        System.out.println(filter);
        assertEquals(2, filter.size());
    }

    @DisplayName("GroupingByMultiCondition")
    @Test
    public void testGroupingByMultiCondition() {
        Map<String, List<Grouping>> filter =
                groupings.stream().collect(Collectors.groupingBy(this::getKey));
        System.out.println(filter);
        assertEquals(9, filter.size());
    }

    private String getKey(Grouping grouping) {
        return StringUtils.join(CharUtil.UNDERLINE, grouping.getType(), grouping.getAddress());
    }
}
