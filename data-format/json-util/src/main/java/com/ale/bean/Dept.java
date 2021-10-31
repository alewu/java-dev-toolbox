package com.ale.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Dept implements Serializable {
    private long l;
    private String name;


}
