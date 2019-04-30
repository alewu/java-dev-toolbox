package com.ale;

import com.ale.data.DataGenerator;
import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.List;

/**
 * @author alewu
 * @since 2019/4/30 23:19
 */
public class DataGeneratorTest {


    @Test
    public void generateAlphabet() {
        List<Character> characters = DataGenerator.generateAlphabet();
        System.out.println(characters);
        System.out.println(Joiner.on("").join(characters));
    }
}