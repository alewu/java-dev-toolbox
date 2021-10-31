package com.ale.bean;

import lombok.Data;


@Data
public class CareerScoreInfo {
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号是否匹配
     */
    private Boolean phoneMatched;
    /**
     * 分数
     */
    private String score;
    /**
     * 地址
     */
    private String address;


}
