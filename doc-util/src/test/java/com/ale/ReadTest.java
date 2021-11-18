package com.ale;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.ale.util.SQLUtil;
import com.ale.util.TestFileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ReadTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);


    @Test
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "数据库表结构生成模板.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, TableMeta.class, new PageReadListener<TableMeta>(dataList -> {
            for (TableMeta tableMeta : dataList) {
                //                LOGGER.info("读取到一条数据{}", JSONUtil.toJsonStr(tableMeta));
                String out = SQLUtil.generateDDL1(tableMeta);

                System.out.println(out);
            }
        })).sheet("create").doRead();
    }


    @Test
    public void simpleReadSheet() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "数据库表结构生成模板.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取3000条数据 然后返回过来 直接调用使用数据就行
        EasyExcel.read(fileName, TableMeta.class, new PageReadListener<TableMeta>(dataList -> {
            for (TableMeta tableMeta : dataList) {
                //                LOGGER.info("读取到一条数据{}", JSONUtil.toJsonStr(tableMeta));
                String alter = SQLUtil.generateDDLAlter(tableMeta);
                System.out.println(alter);
            }
            String s = generateEntityFiled(dataList);
            System.out.println(s);
        })).sheet("create").doRead();
    }

    public String generateEntityFiled(List<TableMeta> tableMetas) {

        TemplateConfig templates = new TemplateConfig("templates",
                                                      TemplateConfig.ResourceMode.CLASSPATH);
        TemplateEngine engine = TemplateUtil.createEngine(templates);
        Template template = engine.getTemplate("entity-property.ftl");
        return template.render(Dict.create().set("tableMetas", tableMetas));
    }


/*    alter table vaylien_user_base_info add column_16 varchar(64) null comment '助手';
      alter table vaylien_user_base_info add column_16 int not null comment '生活';
 */
}