package com.ale.stream;

import com.ale.pojo.BankAccount;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * reduce是一个终结操作，它能够通过某一个方法，对元素进行削减操作。
 * 该操作的结果会放在一个Optional变量里返回。可以利用它来实现很多聚合方法比如count,max,min等。
 * @author alewu
 * @date 2020/8/11
 */
class StreamReduceTest {

    /**
     * 集合中包含对象并求其中的一个元素的累加之和
     */
    @Test
    void testReduce() {
        BankAccount jack = new BankAccount("jack", new BigDecimal("10.5"));
        BankAccount rose = new BankAccount("rose", new BigDecimal("10.5"));
        BankAccount lucy = new BankAccount("lucy", new BigDecimal("10.5"));
        BankAccount mark = new BankAccount("mark", new BigDecimal("10.5"));
        List<BankAccount> bankAccounts = ImmutableList.of(jack, rose, lucy, mark);
        BigDecimal sum = bankAccounts.stream().map(BankAccount::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sum);
        assertEquals(new BigDecimal("42.0"), sum);
    }

}
