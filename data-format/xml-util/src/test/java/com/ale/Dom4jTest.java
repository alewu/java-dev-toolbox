package com.ale;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class Dom4jTest {

    @Test
    void test() throws DocumentException {
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<person>\n" +
                "    <p1>\n" +
                "        <name>zhangsan</name>\n" +
                "        <age>18</age>\n" +
                "    </p1>\n" +
                "    <p1>\n" +
                "        <name>lisi</name>\n" +
                "        <age>20</age>\n" +
                "    </p1>\n" +
                "</person>";
        Document document = DocumentHelper.parseText(text);
        Element rootElement = document.getRootElement();
        Element person = rootElement.element("p1");
        String name = person.elementText("name");
        System.out.println(name);
    }

    @Test
    void testRootElement() throws DocumentException {
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<person>\n" +
                "    <p1>\n" +
                "        <name>zhangsan</name>\n" +
                "        <age>18</age>\n" +
                "    </p1>\n" +
                "    <p1>\n" +
                "        <name>lisi</name>\n" +
                "        <age>20</age>\n" +
                "    </p1>\n" +
                "</person>";
        Document document = DocumentHelper.parseText(text);
        Element rootElement = document.getRootElement();
        String name1 = rootElement.getName();
        System.out.println(name1);
        Element person = rootElement.element("p1");
        String name = person.elementText("name");
        System.out.println(name);
    }

    @Test
    void testXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("xml/test.xml");
        Document document = saxReader.read(input);
        Element rootElement = document.getRootElement();
        String name1 = rootElement.getName();
        System.out.println(name1);
        //        List<Node> list = document.selectNodes("//name");
        //        for (int i = 0; i < list.size(); i++)
        //            System.out.println(list.get(i).getText());
        //        xpath 需要引入以下包
        //         <dependency>
        //            <groupId>jaxen</groupId>
        //            <artifactId>jaxen</artifactId>
        //            <version>1.2.0</version>
        //        </dependency>
    }

}
