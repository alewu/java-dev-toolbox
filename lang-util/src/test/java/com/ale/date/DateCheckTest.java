package com.ale.date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

public class DateCheckTest {

    @Test
    void testFormmater(){
        System.out.println();

        DateTime parse = DateUtil.parse("1930-11-31 12:00:00", DatePattern.NORM_DATETIME_PATTERN);

        System.out.println(parse);
    }
}
