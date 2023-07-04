package com.ale;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class LocalDateTimeToTimeStamp {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(timestamp);

        String s = DateUtil.parseDate("2022-05-12 06:29:51").toDateStr();
        System.out.println(s);

        DateTime dateTime = DateUtil.beginOfDay(new Date());
        long time = dateTime.getTime();
        System.out.println(dateTime);
        System.out.println(time);

        System.out.println(org.joda.time.DateTime.now().dayOfYear().addToCopy(1));
        System.out.println(org.joda.time.DateTime.now().toDate().getTime());
        System.out.println(System.currentTimeMillis());
    }
}
