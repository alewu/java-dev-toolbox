package com.ale;


import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import org.junit.jupiter.api.Test;

/**
 * @author alewu
 * @date 2021/3/25
 */
class HutoolTemplateEngineTest {

    @Test
    void testTemplateEngine() {
        //自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
        //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        //假设我们引入的是Beetl引擎，则：
        Template template = engine.getTemplate("Hello ${name}");
        //Dict本质上为Map，此处可用Map
        String result = template.render(Dict.create().set("name", "Hutool"));
        //输出：Hello Hutool
        System.out.println(result);

    }

    @Test
    void test() {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates",
                                                                             TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("test.ftl");
        Product product = new Product();
        product.setName("jack");
        product.setUrl("www.baidu.com");
        String result = template.render(Dict.create().set("user", "Hutool").set("latestProduct", product));
        System.out.println(result);
    }
}
