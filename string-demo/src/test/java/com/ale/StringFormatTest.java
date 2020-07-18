package com.ale;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Date;

public class StringFormatTest {
    /**
     * 这是源码注释中的一个例子
     * 在这个例子中静态方法format第一个参数是字符串类型的，
     * 即模式字符串，第二个参数是个可变参数，实际上就是一个Object类型的数组。
     * 在模式字符串中使用"{}"标识一个FormatElement。"{}"中的ArgumentIndex对应Object数组中响应索引处的值。
     */
    @Test
    public void testMessageFormat(){
        int planet = 7;
        String event = "a disturbance in the Force";
        String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
                                             planet, new Date(), event);
        System.out.println(result);
    }

    @Test
    public void test(){
        Object[] strings = new Object[]{"a", "b", "c"};
        String result = MessageFormat.format("At {1} on {1}, there was {2} on planet {0}.",
                                             strings);
        System.out.println(result);
    }
}
