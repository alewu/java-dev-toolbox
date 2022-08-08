package com.ale;

import cn.hutool.core.codec.Base64Encoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

class CommonTest {
    @Test
    void test(){
        String json = "{\n" +
                "  \"isToAll\": false,\n" +
                "  \"sex\": \"0\",\n" +
                "  \"followDays\": 17,\n" +
                "  \"lastActiveTime\": \"2020-07-27 14:45:52\"\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(json);
        String isToAll = jsonObject.getString("isToAll");
        Boolean isToAll1 = jsonObject.getBoolean("isToAll");
        System.out.println();
        System.out.println(isToAll);
        System.out.println(isToAll1);
    }

    @Test
    void test1(){
        CompletableFuture<Boolean> future = CompletableFuture.completedFuture(Boolean.TRUE);
        System.out.println(Objects.equals(future.join(), Boolean.TRUE));
    }
}
