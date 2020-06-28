package com.ale;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

    @Test
    public void testOffset(){
        Integer remainSecondsOneDay = getRemainSecondsOneDay(new Date());
        System.out.println(remainSecondsOneDay);
    }

    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),ZoneId.systemDefault())
                                              .plusDays(1).withHour(0).withMinute(0)
                                              .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                                                                ZoneId.systemDefault());
        System.out.println(midnight);
        System.out.println(currentDate);
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }

    @Test
    public void testBeginOfDate(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天零点
        System.out.println(today_start);
        System.out.println(today_end);
    }
}

