package com.ale;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;


public class DateDemo {

    public static void main(String[] args) {
        System.out.println(DateUtil.format(DateUtil.date(),"yyyyMMddHHmm"));
        System.out.println(LocalDate.now());
    }
}
