package com.ale.data;

import com.ale.data.bean.AreaCode;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 身份证号码
 * 1、号码的结构
 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * 2、地址码(前六位数）
 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
 * 3、出生日期码（第七位至十四位）
 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
 * 4、顺序码（第十五位至十七位）
 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
 * 顺序码的奇数分配给男性，偶数分配给女性。
 * 5、校验码（第十八位数）
 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
 * （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10
 * 校验码: 1 0 X 9 8 7 6 5 4 3 2
 */
public class ChineseIDCardNumberGenerator extends GenericGenerator {
    private static GenericGenerator instance = new ChineseIDCardNumberGenerator();

    public static GenericGenerator getInstance() {
        return instance;
    }

    /**
     * 生成方法
     *
     * @return
     */
    public String generate() {
        StringBuilder generator = new StringBuilder();
        generator.append(getRandomAreaCode());
        generator.append(getRandomBirthday());
        generator.append(this.randomCode());
        generator.append(this.getVerifyCode(generator.toString()));
        return generator.toString();
    }

    /**
     * 随机地区
     *
     * @return int
     */
    public static String getRandomAreaCode() {
        Map<Integer, String> areaCode = AreaCode.idRegion.inverse();
        return areaCode.keySet().toArray(new Integer[0])[RandomUtils
                .nextInt(0, areaCode.size())].toString();
    }

    /**
     * 取两个日期之间的随机日期
     * 先转换为long类型的数字，然后取连个数之间的随机数，再转换成对应的日期
     * @return 随机日期
     */
    public static String getRandomBirthday() {
        long start = LocalDateTime.of(1970, Month.JANUARY, 2, 0, 0).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long chosenDate = RandomUtils.nextLong(start, end);
        LocalDateTime birthday = LocalDateTime.ofEpochSecond(chosenDate / 1000, 0, ZoneOffset.ofHours(8));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return birthday.format(formatter);
    }

    /**
     * 校验码（第十八位数）
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * （2）计算模 Y = mod(S, 11)
     * （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10
     *                    校验码: 1 0 X 9 8 7 6 5 4 3 2
     * @param cardId 17位数字
     * @return 校验码
     */
    public String getVerifyCode(String cardId) {
        int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] verifyCodes = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = IntStream.range(0, wi.length).map(i -> Integer.parseInt(String.valueOf(cardId.charAt(i))) * wi[i]).sum();
        int modValue = Math.floorMod(sum, 11);
        return String.valueOf(verifyCodes[modValue]);
    }

    /**
     * 随机产生3位数
     *
     * @return
     */
    public String randomCode() {
        return RandomNumberGenerator.getRandomNumber(3);
    }


    public static void main(String[] args) {
        ChineseIDCardNumberGenerator g = new ChineseIDCardNumberGenerator();
        for (int i = 0; i < 500; i++) {
            String id = g.generate();
            System.out.println(id);
        }
    }

}