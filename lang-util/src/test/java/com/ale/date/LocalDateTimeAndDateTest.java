package com.ale.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LocalDateTimeAndDateTest {
    @Test
    void test(){
        LocalDateTime now = LocalDateTime.now();
        DateTime date = DateUtil.date(now);
        System.out.println(now);
        System.out.println(date);
    }
}
