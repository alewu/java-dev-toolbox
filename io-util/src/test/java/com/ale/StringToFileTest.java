package com.ale;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

class StringToFileTest {

    @SneakyThrows
    @Test
    void testStringToFile() {
        String out = "Its a test for interpreter.";
        FileUtils.writeStringToFile(new File("test.txt"), out, StandardCharsets.UTF_8);
        String s = FileUtils.readFileToString(new File("test.txt"), StandardCharsets.UTF_8);
        Assertions.assertEquals(out, s);
    }

    @SneakyThrows
    @Test
    void testStringToFileAppend() {
        String out = "Its a test for interpreter.";
        FileUtils.writeStringToFile(new File("test.txt"), out, StandardCharsets.UTF_8, true);
    }

    @SneakyThrows
    @Test
    void testString() {
        String out = "Its a test for interpreter. %s";
        String format = String.format(out, "12334");
        System.out.println(format);
    }
}
