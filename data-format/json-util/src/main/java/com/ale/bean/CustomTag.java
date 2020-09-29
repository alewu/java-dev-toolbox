package com.ale.bean;

import lombok.Data;

@Data
public class CustomTag {
    /**
     * 付费状态：0 未付费， 1 已付费
     */
    private Integer payStatus;

    /**
     * 付费状态：0 未付费， 1 已付费
     */
    private Integer isPurchased;
}
