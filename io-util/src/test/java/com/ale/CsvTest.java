package com.ale;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CsvTest {

    @Test
    void test() {
        String path = "xx";

        String sql = "xx";

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
