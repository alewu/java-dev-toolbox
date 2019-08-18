package com.ale;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * 单词前后空格工具
 *
 * @author alewu
 * @date 2019/8/17
 */
public class WordWhiteSpaceUtil {


    public static void main(String[] args) throws IOException, URISyntaxException {
        String filename = "eng_mix_chs.txt";
        insertWhiteSpace(filename);
    }

    /**
     *  插入空格
     * @param filename 文件名称
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void insertWhiteSpace(String filename) throws URISyntaxException, IOException {
        URL resource = WordWhiteSpaceUtil.class.getClassLoader().getResource(filename);
        Path path = Paths.get(Objects.requireNonNull(resource).toURI());
        List<String> lines = Files.readAllLines(path);
        List<String> newLines = Lists.newArrayList();
        for (String line : lines) {
            newLines.add(insertWhiteSpaceToLine(line));
        }
        FileUtils.writeLines(new File("new_" + filename), newLines);
    }

    /**
     * 插入空格
     * 注意
     *
     * @param line 行
     */
    private static String insertWhiteSpaceToLine(String line) {
        StringBuilder sb = new StringBuilder(line);
        // 记录要插入的位置
        List<Integer> insertIndexes = Lists.newArrayList();
        for (int i = 0; i < line.length() - 1; i++) {
            char c = line.charAt(i);
            char c1 = line.charAt(i + 1);
            if (checkIsInsertWhiteSpace(c, c1)) {
                insertIndexes.add(i + 1);
            }
        }
        // 插入后索引会变化
        for (int i = 0; i < insertIndexes.size(); i++) {
            sb.insert(insertIndexes.get(i) + i, ' ');
        }
       return sb.toString();
    }

    private static boolean checkIsInsertWhiteSpace(char c, char c1) {
        return (ChineseUtil.isChineseByScript(c) && CharUtils.isAsciiAlpha(c1)) || (CharUtils.isAsciiAlpha(c) && ChineseUtil.isChineseByScript(c1));
    }


}
