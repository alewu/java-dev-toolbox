package com.ale.util;

import cn.hutool.core.convert.Convert;
import com.ale.demo.ExcelListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {


    /**
     * 读取Excel（多个sheet可以用同一个实体类解析）
     *
     * @param excelInputStream
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readExcel(InputStream excelInputStream, String fileName, Class<T> clazz) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = getReader(excelInputStream, fileName, clazz, excelListener);
        if (excelReader == null) {
            return new ArrayList<>();
        }
        List<ReadSheet> readSheetList = excelReader.excelExecutor().sheetList();
        for (ReadSheet readSheet : readSheetList) {
            excelReader.read(readSheet);
        }
        excelReader.finish();
        return Convert.toList(clazz, excelListener.getDataList());
    }


    /**
     * 返回ExcelReader
     *
     * @param excel         文件
     * @param clazz         实体类
     * @param excelListener
     */
    private static <T> ExcelReader getReader(InputStream inputStream, String filename, Class<T> clazz,
                                             ExcelListener excelListener) {
        try {
            if (filename == null ||
                    (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
                return null;
            }
            ExcelReader excelReader = EasyExcel.read(inputStream, clazz, excelListener).build();
            inputStream.close();
            return excelReader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}