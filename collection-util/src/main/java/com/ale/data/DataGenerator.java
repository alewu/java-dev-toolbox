package com.ale.data;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author alewu
 * @since 2019/4/30 23:13
 */
public final class DataGenerator {
    private static final Random RANDOM = new Random();

    private DataGenerator(){}

    /**
     * 生成 26 小写字母
     * @return 26 小写字母
     */
    public static List<Character> generateAlphabet() {
        return IntStream.rangeClosed(1, 26).mapToObj(i -> (char) (96 + i)).collect(Collectors.toList());
    }

    //自动生成名字（中文）
    public static String getRandomJianHan(int len) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            hightPos = (176 + Math.abs(RANDOM.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(RANDOM.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ret.append(str);
        }
        return ret.toString();
    }

    //生成随机用户名，数字和字母组成,
    public String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
