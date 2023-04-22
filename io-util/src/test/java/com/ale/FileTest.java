package com.ale;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.lang.Tuple;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
            System.out.println(strings.get(strings.size() - 6));

            List<String> strings1 = strings.subList(0, x - 1);
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
    void testGetFileExtension() {
        String fullName = "123.jpg";
        String fileExtension = com.google.common.io.Files.getFileExtension(fullName);
        Assertions.assertEquals("jpg", fileExtension);
        String extension = FilenameUtils.getExtension(fullName);
        Assertions.assertEquals("jpg", extension);
    }

    @Test
    void testGetString() {
        List<String> strings = FileUtil.readLines("D:\\code\\java-dev-toolbox\\io-util\\src\\main\\resources\\test" +
                                                          ".txt", StandardCharsets.UTF_8);

        List<String> strings1 = FileUtil.readLines("D:\\code\\java-dev-toolbox\\io-util\\src\\main\\resources\\table" +
                                                           ".txt", StandardCharsets.UTF_8);

        ArrayList<MutablePair<String, String>> pairs = Lists.newArrayList();
        for (String s : strings1) {
            String[] split = s.split(",");
            MutablePair<String, String> pair = new MutablePair<>();
            pair.setRight(split[0]);
            pair.setLeft(split[1]);
            pairs.add(pair);
        }


        for (String string : strings) {
            String trim = StrUtil.trim(string);
            String sql = "update {0} set {1} = CONCAT(''NB'',{1}) where {1} = ''{2}'';";
            for (MutablePair<String, String> pair : pairs) {
                System.out.println(MessageFormat.format(sql, pair.right, pair.left, trim));
            }

        }
    }


    @Test
    void testDelete() throws IOException {

    }


    @Test
    void testAppendString() {
        //将String写入文件，追加模式
        String content = "9";
        long start = Instant.now().getEpochSecond();
        String path = "D:\\docs\\phone_md5.txt";
        for (long i = 9000000000L; i < 10000000000L; i++) {
            FileUtil.appendString(i + "", path, CharsetUtil.CHARSET_UTF_8);
        }
        System.out.println("耗时" + (Instant.now().getEpochSecond() - start));
        //        //path指定路径下的文件如不存在，则创建
        //        try {
        //            FileUtil.appendString(content, path, CharsetUtil.CHARSET_UTF_8);
        //        }catch (IORuntimeException e){
        //            //抛出一个运行时异常(直接停止掉程序)
        //            throw new RuntimeException("运行时异常",e);
        //        }

    }

    @Test
    void test() throws IOException {
        String pathStr = "D:\\docs\\phone_md5.txt";
        Path path = Paths.get(pathStr);
        long start = Instant.now().getEpochSecond();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path);) {
            for (long i = 9999000000L; i < 10000000000L; i++) {
                bufferedWriter.append(String.valueOf(i)).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("耗时 %s s", (Instant.now().getEpochSecond() - start)));
    }

    @Test
    void testDistinct(){
        List<String> strings = FileUtil.readLines("D:\\docs\\white.txt", StandardCharsets.UTF_8);
        List<String> distinct = strings.stream().distinct().collect(Collectors.toList());
        String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");
        String path = String.format("D:\\docs\\white_distinct_%d_%s.txt", distinct.size(), yyyyMMdd);
        FileUtil.writeLines(distinct, path, StandardCharsets.UTF_8);

    }

    @Test
    void test123() {
        List<String> strings = FileUtil.readLines("D:\\docs\\PH_API_JK_20221125.txt", StandardCharsets.UTF_8);
        List<String> white = FileUtil.readLines("D:\\docs\\white_distinct_20221125.txt", StandardCharsets.UTF_8);
        List<String> collect = white.stream().map((s) -> DigestUtil.md5Hex(s.substring(1)).toUpperCase()).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(collect));
        Collection<String> disjunction = CollUtil.disjunction(collect, strings);
        System.out.println(JSONUtil.toJsonStr(disjunction));
        FileUtil.writeLines(disjunction, "D:\\docs\\white_disjunction_20221125.txt", StandardCharsets.UTF_8);
    }
}
