package com.ale.demo;

import cn.hutool.json.JSONUtil;
import com.ale.bean.QueryParam;
import com.alibaba.fastjson2.JSON;

import java.util.Date;
import java.util.Map;

/**
  *
  * @author alewu
  * @date 2020/5/22
  */
public class JsonToMap {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "    \"type\": 1,\n" +
                "    \"date\": \"2020-05-20\",\n" +
                "    \"name\": \"java\"\n" +
                "}";

//        Map<String, Object> jsonObject = JSONUtil.parseObj(jsonStr);
//        Object data = jsonObject.get("date");
//        System.out.println(data);
//        System.out.println(jsonObject);
        QueryParam queryParam = new QueryParam();
        try {
             queryParam = JSON.parseObject(jsonStr, QueryParam.class);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(queryParam.getName());
//        System.out.println(queryParam);
    }
}
