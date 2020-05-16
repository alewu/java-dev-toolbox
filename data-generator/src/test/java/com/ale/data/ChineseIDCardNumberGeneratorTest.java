package com.ale.data;




import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


public class ChineseIDCardNumberGeneratorTest {


    @Test
    public void generateRandomDate() {
        IntStream.range(0, 10000)
                 .mapToObj(i -> ChineseIDCardNumberGenerator.getRandomBirthday())
                 .forEach(System.out::println);
    }

    @Test
    public void testGenerate() {
        String idCard = ChineseIDCardNumberGenerator.getInstance().generate();
        System.err.println(idCard);
        if (idCard.charAt(idCard.length() - 2) % 2 == 0) {
            System.err.println("女");
        } else {
            System.err.println("男");
        }
    }


}
