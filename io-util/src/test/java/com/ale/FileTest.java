package com.ale;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class FileTest {
    @Test
    void testCopy() throws IOException, InterruptedException {
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

    @Test
    void testCreateTempDirectory() throws IOException {
        Path dir = Files.createTempDirectory("my-dir");
        Path fileToCreatePath = dir.resolve("test-file.txt");
        System.out.println("File to create path: " + fileToCreatePath);
        Path newFilePath = Files.createFile(fileToCreatePath);
        System.out.println("New file created: " + newFilePath);
        System.out.println("New File exits: " + Files.exists(newFilePath));
    }

    @Test
    void testExists() {
        boolean exists = Files.exists(Paths.get("tmp", "qrcode.jpg"));
        Assertions.assertTrue(exists);
    }


    @Test
    void testWalkFileTree() throws IOException {
        List<Path> paths = FileHelper.getFiles("xxx");
        String newPath = "D:\\tmp\\xx";
        List<Path> collect = paths.stream().skip(1).collect(Collectors.toList());
        for (Path path : collect) {
            System.out.println(path.toFile().getName());
            List<String> strings = Files.readAllLines(path);
            String targetLine = "";
            for (String string : strings) {
                if (string.contains("精选留言")) {
                    targetLine = string;
                    System.out.println(string);
                }
            }
            int x = strings.indexOf(targetLine);
            System.out.println(strings.get(x - 1));
            System.out.println(strings.get(strings.size()-6));

            List<String> strings1 = strings.subList(0, x-1);
            List<String> strings2 = strings.subList(strings.size() - 5, strings.size());
            List<String> ss = Lists.newArrayList();
            ss.addAll(strings1);
            ss.addAll(strings2);


            Path path1 = Paths.get(newPath, path.toFile().getName());
            Files.createFile(path1);
            Files.write(path1, ss);
        }
    }

    @Test
    void testDelete() throws IOException {

    }
}
