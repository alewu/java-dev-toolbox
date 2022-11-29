package com.ale.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTest {


    @Test
     void testFormater(){
        System.out.println(DateUtil.format(DateUtil.date(),"yyyyMMddHHmm"));

        System.out.println(LocalDate.now());
    }

    @Test
     void testOffset(){
        Integer remainSecondsOneDay = getRemainSecondsOneDay(new Date());
        System.out.println(remainSecondsOneDay);
    }

     static Integer getRemainSecondsOneDay(Date currentDate) {
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
     void testBeginOfDate(){
        LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
        LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天零点
        System.out.println(today_start);
        System.out.println(today_end);
    }

     @Test
     void testBeginOfDateSomeTime(){
         LocalDateTime today_start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);//当天零点
         LocalDateTime localDateTime = today_start.plusHours(6);
         System.out.println(localDateTime);
         System.out.println(today_start);
         System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(15).plusMinutes(30));
     }

     @Test
     void testIsLeapYear(){
         LocalDate today = LocalDate.now();
         System.out.println(today.isLeapYear());
     }

    
    @Test
    void testBetween(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("计算两个时间的差：");
        LocalDateTime end = LocalDateTime.now().minusMinutes(1);
        Duration duration = Duration.between(now,end);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(end);

        System.out.println("相差【 "+days+"天："+hours+" 小时："+minutes+" 分钟："+millis+" 毫秒："+nanos+" 纳秒】");
    }

    @Test
    void testGetHour(){
        int hour = LocalDateTime.now().getHour();
        System.out.println(hour);
    }

    @Test
    void testDateParse(){
        Date parse = DateUtil.parse("22:45:00");
        //2020-09-05 22:45:00
        System.out.println(parse);
    }

    @Test
    void testDateBetween(){
        Date parse = DateUtil.parse("12:45");
        Date date = new Date();
        long betweenDay = DateUtil.between(parse, date, DateUnit.MINUTE);
        System.out.println(betweenDay);
    }

    @Test
    void test11(){
        LocalDateTime of = LocalDateTime.of(2020, 9, 7, 15, 25);

        Date parse = DateUtil.parse("12:45");
        Instant instant = parse.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        System.out.println("hour" + hour +  "minute" + minute);

        LocalDateTime to = of.plusWeeks(1);
        LocalDateTime today_start = LocalDateTime.of(to.toLocalDate(), LocalTime.MIN).plusHours(hour).plusMinutes(minute);
        System.out.println(today_start);

        LocalDateTime now = LocalDateTime.now();




    }

     @Test
     void testNow(){
         System.out.println(System.currentTimeMillis());
         DateTime x = DateUtil.beginOfDay(new Date());
         System.out.println(x);
         System.out.println(DateUtil.parse("2022-08-07 00:00:01").compareTo(x) == 0);
     }


     @Test
     void test(){
         System.out.println(DateUtil.parse(null));
     }


     @Test
     void test123(){
         LocalDateTime localDateTime = LocalDateTime.now();
         long epochMilli = localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
         long time = DateUtil.beginOfDay(new Date()).getTime();
         assertEquals(time, epochMilli);
     }
}

