package com.ale.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
/**
  *  
  * @author alewu
  * @date 2020/8/19
  */
@Data
@AllArgsConstructor
public class Student implements Comparable<Student> {
    private Integer id;
    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高
     */
    private Double height;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    @Override
    public int compareTo(Student o) {
        return this.age.compareTo(o.getAge());
    }
}
