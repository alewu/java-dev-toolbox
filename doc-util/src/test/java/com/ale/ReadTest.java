package com.ale;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.ale.util.TestFileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class ReadTest {
    public static final Map<String, String> javaTypeToSqlType = Maps.newHashMapWithExpectedSize(100);
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);

    static {
        javaTypeToSqlType.put("string", "varchar");
        javaTypeToSqlType.put("int", "int");
        javaTypeToSqlType.put("date", "datetime");
    }

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
                String filedName = tableMeta.getFiledName();
                String lowerUnderscore = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, filedName);
                String isMustInput = tableMeta.getIsMustInput();
                String isNull = "Y".equals(isMustInput) ? "null" : "not null";
                String filedType = tableMeta.getFiledType().toLowerCase(Locale.ROOT).trim();
                String filedComment = tableMeta.getFiledComment();
                String out = "    " + lowerUnderscore + "    " + javaTypeToSqlType.getOrDefault(filedType, "varchar" +
                        "(64)") + "    " + isNull + " comment " + "'" + filedComment + "'" + ",";

                System.out.println(out);
            }
        })).sheet("create").doRead();
    }
    /*
      uid      int                                          null comment '用户id',
      device_id     varchar(64)                          null comment '银行卡持有人姓名',
      os        varchar(64)                          null comment '开户行名称',
     */

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
                String alter = generateDDL(tableMeta);
                System.out.println(alter);
            }
            String s = generateEntityFiled(dataList);
            System.out.println(s);
        })).sheet("alter").doRead();
    }

    public String generateEntityFiled(List<TableMeta> tableMetas) {

        TemplateConfig templates = new TemplateConfig("templates",
                                                      TemplateConfig.ResourceMode.CLASSPATH);
        TemplateEngine engine = TemplateUtil.createEngine(templates);
        Template template = engine.getTemplate("test.ftl");
        return template.render(Dict.create().set("tableMetas", tableMetas));
    }


    private String generateDDL(TableMeta tableMeta) {
        String filedName = tableMeta.getFiledName();
        String lowerUnderscore = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, filedName);
        String isMustInput = tableMeta.getIsMustInput();
        String isNull = "Y".equals(isMustInput) ? "null" : "not null";
        String filedType = tableMeta.getFiledType().toLowerCase(Locale.ROOT).trim();
        String filedLength = tableMeta.getFiledLength();
        String boxedFiledLength = StringUtils.isNotBlank(filedLength) ? "(" + filedLength + ")" : "";
        String filedComment = tableMeta.getFiledComment();
        String tableName = tableMeta.getTableName();

        return MessageFormat.format("alter table {0} add {1} {2}{3} {4} comment ''{5}'';",
                                    tableName, lowerUnderscore,
                                    javaTypeToSqlType.getOrDefault(filedType, "varchar"), boxedFiledLength, isNull,
                                    filedComment);
    }

/*    alter table vaylien_user_base_info add column_16 varchar(64) null comment '助手';
      alter table vaylien_user_base_info add column_16 int not null comment '生活';
 */
}