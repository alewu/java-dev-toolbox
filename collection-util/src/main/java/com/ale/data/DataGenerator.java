package com.ale.data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author alewu
 * @since 2019/4/30 23:13
 */
public final class DataGenerator {
    private DataGenerator(){}

    /**
     * 生成 26 小写字母
     * @return 26 小写字母
     */
    public static List<Character> generateAlphabet() {
        return IntStream.rangeClosed(1, 26).mapToObj(i -> (char) (96 + i)).collect(Collectors.toList());
    }

}
