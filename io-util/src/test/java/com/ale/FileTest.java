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

    @Test
    void test1() throws IOException {
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
        List<Path> paths = FileUtils.getFiles("D:\\tmp\\22 深入拆解 Java 虚拟机-20201017T080609Z-001\\22 深入拆解 Java 虚拟机");
        String newPath = "D:\\tmp\\深入拆解 Java 虚拟机";
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
        List<Path> paths = FileUtils.getFiles("D:\\tmp\\59 程序员进阶攻略（61讲）-20201017T053659Z-001\\59 程序员进阶攻略（61讲）");
        String newPath = "D:\\tmp\\程序员进阶攻略";

    }
}
