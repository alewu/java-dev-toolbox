package com.ale.util;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author alewu
 * @date 2021/3/26
 */
public class TemplateHelper {

    public static void gen(String templateName, Object dataModel) {
        String fileName = templateName.substring(templateName.indexOf(".") + 1);
        String txtName = templateName.replace(fileName, "txt");
        gen(templateName, dataModel, txtName);
    }

    @SneakyThrows
    public static void gen(String templateName, Object dataModel, String out) {
        String result = gen(templateName, (Map<?, ?>) dataModel);
        FileUtils.writeStringToFile(new File(out), result, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static void gen(String templateName, Object dataModel, File out) {
        String result = gen(templateName, (Map<?, ?>) dataModel);
        FileUtils.writeStringToFile(out, result, StandardCharsets.UTF_8);
    }

    private static String gen(String templateName, Map<?, ?> dataModel) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates",
                                                                             TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate(templateName);
        return template.render(dataModel);
    }
}
