package com.ale;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class DateDemo {

    public static void main(String[] args) {
        System.out.println(DateUtil.format(DateUtil.date(),"yyyyMMddHHmm"));
        System.out.println(LocalDate.now());

        long newScore = System.currentTimeMillis() + 5400 * 1000;
        System.out.println(Instant.ofEpochMilli(newScore).atZone(ZoneId.of("Asia/Shanghai")));
        System.out.println(DateUtil.date(newScore));

        System.out.println(System.currentTimeMillis());

        System.out.println(DateUtil.between(DateUtil.parseDate("2022-03-09"),DateUtil.parseDate( "2022-12-31"), DateUnit.DAY));
    }
}
