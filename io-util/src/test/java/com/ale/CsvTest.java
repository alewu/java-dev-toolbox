package com.ale;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CsvTest {

    @Test
    void test() {
        String path = "D:\\code\\java-dev-toolbox\\io-util\\src\\test\\resources\\ng_bank.csv";

        String sql = "INSERT INTO `loan_pay`.`t_pay_bank_map`(`common_bank_code`, `third_bank_code`, `pay_channel`) VALUES ('%s', '%s', 'monnify');";

        CsvReader reader = CsvUtil.getReader();
        //假设csv文件在classpath目录下
        List<TILockdataMidModel> result = reader.read(ResourceUtil.getUtf8Reader(path), TILockdataMidModel.class);

        List<TILockdataMidModel> collect = result.stream().sorted(Comparator.comparing(TILockdataMidModel::getBINCode)).collect(Collectors.toList());

        for (TILockdataMidModel model : collect) {
            System.out.println(String.format(sql, model.getBINCode(), model.getBINCode()));

//            System.out.println(model);
        }


        log.info("====>{}", result.size());
//        log.info("====>{}", JSON.toJSONString(collect));
    }

    @Data
    static class TILockdataMidModel {
        private String VietnameseBank;
        private String BankName;
        private String BINCode;
        private String Status;

        public String toString() {
            return this.BINCode + "," + this.BankName + "," + this.VietnameseBank +"," + this.Status;
        }

    }

}
