package com.ale.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
  *
  * @author alewu
  * @date 2020/9/17
  */
@Data
@AllArgsConstructor
public class BankAccount {
    private String accountName;
    private BigDecimal balance;
}
