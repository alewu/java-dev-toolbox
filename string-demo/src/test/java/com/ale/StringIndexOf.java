package com.ale;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

public class StringIndexOf {
    @Test
    void test(){
        String url = "https://wxe898b8e80c198a95.taotew.cn/t/2643419";
        System.out.println(url.substring(url.lastIndexOf("/") + 1));
        System.out.println(DateUtil.offsetDay(DateUtil.date(), -1).toJdkDate());
    }
}
