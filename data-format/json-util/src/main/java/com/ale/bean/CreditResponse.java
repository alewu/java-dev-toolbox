package com.ale.bean;

import lombok.Data;


@Data
public class CreditResponse<T> {
    private int status;
    private String error;
    private T data;
}
