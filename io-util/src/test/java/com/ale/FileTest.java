package com.ale;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

class FileTest {
    @Test
    void test() throws IOException, InterruptedException {
        String path1 = this.getClass().getResource("").getPath();
        String outputFile = "333.jpg";
        String path = "C:\\Users\\win10\\Pictures\\222.jpg";
        InputStream is = new FileInputStream(path);
        Path target = Paths.get(outputFile);
        Files.copy(is, target);
        File file = target.toFile();
        System.out.println(file.getAbsolutePath());
        System.out.println(path1);
        TimeUnit.SECONDS.sleep(6);
        Files.deleteIfExists(file.toPath());
    }
}
