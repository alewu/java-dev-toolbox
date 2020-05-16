package com.ale.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author alewu
 */
@Data
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;

}
