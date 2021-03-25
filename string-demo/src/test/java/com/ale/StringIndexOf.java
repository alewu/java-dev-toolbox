package com.ale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringIndexOf {
    @Test
    void test() {
        String url = "https://wxe898b8e80c198a95.taotew.cn/t/2643419";
        String substring = url.substring(url.lastIndexOf("/") + 1);
        Assertions.assertEquals("2643419", substring);

    }
}
