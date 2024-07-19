package com.ale;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.ale.bean.User;
import com.ale.bean.UserDTO;
import com.ale.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class JsonTimeTest {

    @Test
    void testTimestamps() {
        String json = "{\"id\":1,\"birthday\":\"1600097232\"}";
        //         String json = "{\"id\":1,\"birthday\":\"1599736521000\"}";
        User parse = Optional.ofNullable(JsonUtil.parse(json, new TypeReference<User>() {})).orElse(new User());
        UserDTO target = new UserDTO();
        parse.setBirthday(parse.getBirthday() * 1000);
        BeanUtil.copyProperties(parse, target);
        System.out.println(parse.toString());
        System.out.println(target.toString());
        System.out.println(DateUtil.date(parse.getBirthday()));



    }




}
