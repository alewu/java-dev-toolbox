package com.ale;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateTest {
    @Test
    public void testTimestamps(){
        long newScore = System.currentTimeMillis() + 5400 * 1000;
        System.out.println(Instant.ofEpochMilli(newScore).atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(DateUtil.date(newScore));
    }

    @Test
    public void testFormater(){
        System.out.println(DateUtil.format(DateUtil.date(),"yyyyMMddHHmm"));

        System.out.println(LocalDate.now());
    }

    @Test
    public void test(){
        System.out.println(DateUtil.date(1592768108711L));
        final long timeStamp = DateUtil.offset(DateUtil.date(), DateField.SECOND, 2128).getTime();
        System.out.println(DateUtil.date(timeStamp));
        System.out.println(DateUtil.date(1592895414719L));
        System.out.println(Instant.now().toEpochMilli());
    }
}

