package com.ale;

/**
  *  中文处理
  * @author alewu
  * @date 2019/8/18
  */
public class ChineseUtil {

    public static boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        return sc == Character.UnicodeScript.HAN;
    }
}
