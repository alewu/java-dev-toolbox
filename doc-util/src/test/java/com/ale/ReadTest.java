package com.ale;

import com.ale.util.TestFileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Locale;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class ReadTest {
    public static final Map<String, String> javaTypeToSqlType = Maps.newHashMapWithExpectedSize(100);
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);

    static {
        javaTypeToSqlType.put("string", "varchar(64)");
        javaTypeToSqlType.put("int", "int");
        javaTypeToSqlType.put("date", "datetime");
    }

    @Test
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
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
        })).sheet().doRead();
    }

/*
    uid      int                                          null comment '用户id',
    device_id     varchar(64)                          null comment '银行卡持有人姓名',
    os        varchar(64)                          null comment '开户行名称',
    os_version varchar(64)                          null comment '分行名称',
    brand       varchar(64)                          null comment '银行账号',
    phone_type           tinyint(1) default 1                 null comment '状态',
    remark           varchar(255)                         null comment '备注',
    create_time      datetime   default CURRENT_TIMESTAMP null,
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    version          int        default 0                 null comment '本条记录操作的最新版本号',
    del_flag         tinyint(1) default 0                 null comment '删除标记(0-否，1-是)'
    
 */
}