package com.ale;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
/**
  *
  * @author alewu
  * @date 2020/10/17
  */
public final class FileUtils {

    public static List<Path> getFiles(String root) throws IOException {
        Path path = Paths.get(root);
        List<Path> result = new ArrayList<>();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            // 访问文件时触发
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//                System.out.println("正在访问文件: " + file);
                result.add(file);
                return FileVisitResult.CONTINUE;
            }

            // 访问目录时触发
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
//                System.out.println("正在访问目录：" + dir);
                return FileVisitResult.CONTINUE;
            }
        });
        return result;
    }
}
