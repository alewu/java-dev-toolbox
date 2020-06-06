package com.ale;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeToTimeStamp {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println(timestamp);
    }
}
