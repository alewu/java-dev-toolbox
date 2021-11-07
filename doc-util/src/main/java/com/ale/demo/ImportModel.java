package com.ale.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ImportModel {

    @ExcelProperty(index = 0)
    private String date;

    @ExcelProperty(index = 1)
    private String author;

    @ExcelProperty(index = 2)
    private String book;

}