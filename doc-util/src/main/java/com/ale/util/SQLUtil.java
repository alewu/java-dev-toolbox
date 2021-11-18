package com.ale.util;

import com.ale.TableMeta;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

public class SQLUtil {
    public static final Map<String, String> javaTypeToSqlType = Maps.newHashMapWithExpectedSize(100);

    static {
        javaTypeToSqlType.put("string", "varchar");
        javaTypeToSqlType.put("int", "int");
        javaTypeToSqlType.put("tinyint", "tinyint");
        javaTypeToSqlType.put("date", "datetime");
    }

    public static String generateDDLAlter(TableMeta tableMeta) {
        String filedName = tableMeta.getFiledName();
        String lowerUnderscore = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, filedName);
        String isMustInput = tableMeta.getIsMustInput();
        String isNull = "Y".equals(isMustInput) ? "not null" : "null";
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


    public static String generateDDL1(TableMeta tableMeta) {
        String filedName = tableMeta.getFiledName();
        String lowerUnderscore = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, filedName);
        String isMustInput = tableMeta.getIsMustInput();
        String isNull = "Y".equals(isMustInput) ? "null" : "not null";
        String filedType = tableMeta.getFiledType().toLowerCase(Locale.ROOT).trim();
        String filedLength = tableMeta.getFiledLength();
        String boxedFiledLength = StringUtils.isNotBlank(filedLength) ? "(" + filedLength + ")" : "";
        String filedComment = tableMeta.getFiledComment();
        return MessageFormat.format("   {0}    {1}{2}   {3} comment ''{4}'',",
                                    lowerUnderscore, javaTypeToSqlType.getOrDefault(filedType, "varchar"),
                                    boxedFiledLength, isNull,
                                    filedComment);


    /*
      uid      int                                          null comment '用户id',
      device_id     varchar(64)                          null comment '银行卡持有人姓名',
      os        varchar(64)                          null comment '开户行名称',
     */
    }

}
