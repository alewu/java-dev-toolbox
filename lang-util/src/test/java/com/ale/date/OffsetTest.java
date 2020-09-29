package com.ale.date;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

class OffsetTest {


    @Test
    void testOffsetMinute(){
        System.out.println(DateUtil.offsetMinute(new Date(), 5));
        System.out.println(DateUtil.offsetDay(new Date(), 3));
        System.out.println(DateUtil.beginOfDay(DateUtil.offsetDay(DateUtil.date(), -1)));
        System.out.println(DateUtil.endOfDay(DateUtil.offsetDay(DateUtil.date(), -1)));
    }
}
