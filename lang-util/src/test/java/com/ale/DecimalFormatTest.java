package com.ale;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

class DecimalFormatTest {
    /**
     * DecimalFormat 包含一个模式 和一组符号
     */
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Test
    void test() {
        double pi = 3.1415927;//圆周率
        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//3
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));// 03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%

        /**
         * 上面的代码就是网上很经典的案例，下面我们来分析另外的一个值
         */
        pi = 12.34567;
        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//12
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//12.35
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));// 12.346
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//12
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//1234.57%

        /**
         * 扩展，如果是其他的数字会是下面的效果
         */
        pi = 12.34;
        //整数
        System.out.println(new DecimalFormat("6").format(pi));//612
        System.out.println(new DecimalFormat("60").format(pi));//612
        System.out.println(new DecimalFormat("06").format(pi));//126
        System.out.println(new DecimalFormat("00600").format(pi));//00126
        System.out.println(new DecimalFormat("#####60000").format(pi));//00126
        //小数
        System.out.println(new DecimalFormat(".6").format(pi));//12.6
        System.out.println(new DecimalFormat(".06").format(pi));//12.36
        System.out.println(new DecimalFormat(".60").format(pi));//12.36
        System.out.println(new DecimalFormat(".0600").format(pi));//12.3406
        System.out.println(new DecimalFormat(".6000").format(pi));//12.3406
        System.out.println(new DecimalFormat(".600000##").format(pi));//12.340006
    }
}
