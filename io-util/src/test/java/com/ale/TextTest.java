package com.ale;

import com.ale.bean.Account;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextTest {

    @Test
    void testStringToFile() throws IOException {
        List<String> strings = FileUtils.readLines(new File("D:\\docs\\projects\\国际化_已翻译.txt"), StandardCharsets.UTF_8);
        List<String> objects = Lists.newArrayList();
        for (String string : strings) {
            string = string + "\"" + ")";
            objects.add(string);
        }
        FileUtils.writeLines(new File("test.txt"), objects);

    }


    @Test
    void testFileToString() throws IOException {
        List<String> strings = FileUtils.readLines(new File("D:\\temp\\vaylien_blacklist_2022-04-08.sql"),
                                                   StandardCharsets.UTF_8);
        List<String> objects = Lists.newArrayList();
        List<String> collect =
                strings.stream().filter(s -> s.startsWith("INSERT INTO `vaylien_blacklist` VALUES (")).collect(Collectors.toList());
        int i = collect.size();
        for (String string : collect) {
            List<String> strings1 = Splitter.on(",").omitEmptyStrings().splitToList(string);
            String target = MessageFormat.format("({0}, {1}, {2}, {3}, 1, 2, 1, 0, now()),", i+"", strings1.get(2), strings1.get(3), strings1.get(5));
            objects.add(target);
            i--;
        }
        FileUtils.writeLines(new File("test.sql"), objects);

    }

    @Test
    void test(){
        Account account = null;
        String s = Optional.ofNullable(account).map(Account::getAppId).orElse("0");
        System.out.println(s);

    }

    @Test
    void testFileToString1() throws IOException {
        List<String> strings = FileUtils.readLines(new File("C:\\Users\\wywuj\\Desktop\\t_fetch_risk_call.txt"),
                                                   StandardCharsets.UTF_8);

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
