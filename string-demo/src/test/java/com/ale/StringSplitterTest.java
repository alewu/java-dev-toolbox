package com.ale;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSplitterTest {
    @Test
    public void testSimpleSplit() {
        String url = "http://172.168.1.1:8080//picture/test.jpg";
        String substring = url.substring(url.lastIndexOf("//") + 2);
        assertEquals("picture/test.jpg", substring);

        String filename = url.substring(url.lastIndexOf("/") + 1);
        assertEquals("test.jpg", filename);

        String ext = url.substring(url.lastIndexOf('.') + 1);
        assertEquals("jpg", ext);

        String ipAndPort = url.substring(url.indexOf("//") + 2, url.lastIndexOf("//"));
        assertEquals("172.168.1.1:8080", ipAndPort);
    }

    @Test
    public void testSplitToList(){
        String url = "http://172.168.1.1:8080//picture/test.jpg";
        List<String> strings = Splitter.on("/").splitToList(url);
        strings.forEach(System.out::println);
        List<String> strings1 = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(url);
        strings1.forEach(System.out::println);
    }
}
