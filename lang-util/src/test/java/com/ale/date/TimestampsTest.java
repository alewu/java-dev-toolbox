package com.ale.date;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;

class TimestampsTest {
    @Test
    void testTimestamps(){
        long newScore = System.currentTimeMillis() + 5400 * 1000;
        System.out.println(Instant.ofEpochMilli(newScore).atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(DateUtil.date(newScore));
    }

    @Test
    void test(){
        System.out.println(DateUtil.date(1592768108711L));
        final long timeStamp = DateUtil.offset(DateUtil.date(), DateField.SECOND, 2128).getTime();
        System.out.println(DateUtil.date(timeStamp));
        System.out.println(DateUtil.date(1592895414719L));
        System.out.println(Instant.now().toEpochMilli());

        System.out.println(DateUtil.date(1599736521 * 1000));
        System.out.println(DateUtil.date(1599736521 * 1000L));
    }
}
