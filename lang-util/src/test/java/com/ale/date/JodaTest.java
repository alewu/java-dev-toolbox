package com.ale.date;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;

;


public class JodaTest {

    @Test
    void test() {
        long time = DateTime.now().toDate().getTime();
        System.out.println("time " + DateTime.now().toDate().getTime());
        long time1 = DateUtil.date().getTime();
        System.out.println("time1 " + DateUtil.date().getTime());
        long toEpochMilli = Instant.now().toEpochMilli();
        System.out.println("toEpochMilli " + toEpochMilli);

        System.out.println("DateTime " + org.joda.time.DateTime.now().dayOfYear().addToCopy(1).toDate().getTime());
        System.out.println("DateUtil " + DateUtil.offset(new Date(), DateField.HOUR, 24).getTime());

        LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);
        System.out.println(org.joda.time.DateTime.now().toString("yyyy-MM"));
        System.out.println(cn.hutool.core.date.DateTime.now().toString("yyyy-MM"));
        org.joda.time.DateTime deadline = DateTime.now().minus(5);
        System.out.println(new org.joda.time.Duration(deadline, DateTime.now()).getStandardDays());
        LocalDateTime minus = LocalDateTime.now().minusDays(1);
        System.out.println(minus);
        long l = Duration.between(LocalDateTime.now(), minus).toDays();
        System.out.println(l);
        System.out.println("JodaTest.test");


    }
}
