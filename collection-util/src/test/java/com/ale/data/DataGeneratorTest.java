package com.ale.data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author alewu
 * @since 2019/5/1 10:37
 */
public class DataGeneratorTest {

    @Test
    public void getRandomJianHan() {
        System.out.println( DataGenerator.getRandomJianHan(3));
    }
}