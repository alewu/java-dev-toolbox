package com.ale;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeToTimeStamp {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(timestamp);

        String s = DateUtil.parseDate("2022-05-12 06:29:51").toDateStr();
        System.out.println(s);
    }
}
