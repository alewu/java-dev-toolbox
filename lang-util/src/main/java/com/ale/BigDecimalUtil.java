package com.ale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数据格式化
 *
 * @author liusq
 * @date 2017/5/18
 */
public class BigDecimalUtil {

    public static void main(String[] args) {
        double divide = BigDecimalUtil.divide(914.00, 2748.00, 2);
        String percentage = BigDecimalUtil.percentage(divide);
        System.out.println(percentage);

        System.out.println(new BigDecimal("325.00"));

        System.out.println(new BigDecimal("35.12").setScale(0, RoundingMode.DOWN));

        System.out.println(new BigDecimal("145.123").longValue());

        System.out.println(new BigDecimal("21231345.00").setScale(0, RoundingMode.DOWN).toString());

        System.out.println(new BigDecimal("19877345.123").setScale(0, RoundingMode.DOWN).toString());

        System.out.println(new BigDecimal("21231345.00").setScale(0, RoundingMode.DOWN).toString());

        System.out.println(new BigDecimal("8726534245.01").stripTrailingZeros().toString());

        System.out.println(new BigDecimal("100000.0000").stripTrailingZeros().toString());

        System.out.println(new BigDecimal("200000.0000").setScale(0, RoundingMode.DOWN).toString());

        System.out.println(new BigDecimal("200000.0000").setScale(0, RoundingMode.DOWN).toPlainString());

        System.out.println(new BigDecimal("100000.0000").stripTrailingZeros().toPlainString());

        System.out.println(new BigDecimal("0.01").stripTrailingZeros());

    }

    static DecimalFormat df = new DecimalFormat("#,###.##");

    /**
     * 百分比格式化
     *
     * @param total
     * @param num
     * @param scale 保留小数
     * @return
     */
    public static String accuracy(BigDecimal total, BigDecimal num, int scale) {
        try {
            BigDecimal a1 = new BigDecimal(total + "").setScale(scale, BigDecimal.ROUND_HALF_UP);
            BigDecimal a2 = new BigDecimal(num + "").setScale(scale, BigDecimal.ROUND_HALF_UP);

            BigDecimal r = (a2).divide(a1, scale, BigDecimal.ROUND_HALF_EVEN).setScale(scale,
                    BigDecimal.ROUND_HALF_UP);

            NumberFormat percent = NumberFormat.getPercentInstance();
            percent.setMaximumFractionDigits(2);
            return percent.format(r.doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 保留小数
     *
     * @param value 数字
     * @param size  保留位数
     * @return
     */
    public static BigDecimal setScale(BigDecimal value, int size) {
        if (value != null && new BigDecimal(0).compareTo(value) < 0) {
            value = value.setScale(size, BigDecimal.ROUND_HALF_UP);
        }
        return value;
    }

    /**
     * 是否大于零
     *
     * @param value 数字
     *              true 否
     *              flase 是
     * @return
     */
    public static Boolean isNotZero(BigDecimal value) {
        if (value != null && new BigDecimal(0).compareTo(value) < 0) {
            return true;
        }
        return false;
    }

    public static String formatAmount(BigDecimal amount) {
        if (amount == null) {
            return "";
        }
        return df.format(amount);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static String percentage(double num) {
        if (num==0) {
            return "0.00%";
        }
        return  new BigDecimal(num* 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString()  + "%";
    }


}
