package com.ale;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsoupXpathTest {
    private JXDocument underTest;

    private JXDocument doubanTest;
    private JXDocument custom;
    private ClassLoader loader = getClass().getClassLoader();

    @BeforeAll
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>some body</div><div " +
                "class='xiao'>Two</div></body></html>";
        underTest = JXDocument.create(html);
        if (doubanTest == null) {
            URL t = loader.getResource("d_test.html");
            assert t != null;
            File dBook = new File(t.toURI());
            String context = FileUtils.readFileToString(dBook, Charset.forName("utf8"));
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
