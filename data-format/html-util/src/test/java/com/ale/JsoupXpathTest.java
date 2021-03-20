package com.ale;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;


class JsoupXpathTest {
    private JXDocument underTest;

    private JXDocument doubanTest;
    private JXDocument custom;
    private ClassLoader loader = getClass().getClassLoader();

    @BeforeEach
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>some body</div><div " +
                "class='xiao'>Two</div></body></html>";
        underTest = JXDocument.create(html);
        if (doubanTest == null) {
            URL t = loader.getResource("a.html");
            assert t != null;
            File dBook = new File(t.toURI());
            String context = FileUtils.readFileToString(dBook, StandardCharsets.UTF_8);
            doubanTest = JXDocument.create(context);
        }
        custom = JXDocument.create("<li><b>性别：</b>男</li>");
    }

    /**
     * Method: sel(String xpath)
     */
    @Test
    void testSel() throws Exception {
        String xpath = "//script[1]/text()";
        JXNode res = underTest.selNOne(xpath);


    }
}
