package com.ale;



import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() {
        String str = "SUN公司被Oracle收购，是否意味着java被逼上了死路？";
        String s = "\\w+";
        Pattern pattern = Pattern.compile(s);
        Matcher ma = pattern.matcher(str);

        while (ma.find()) {
            System.out.println(ma.group());
        }

    }
}
