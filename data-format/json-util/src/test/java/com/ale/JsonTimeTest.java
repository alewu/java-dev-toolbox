package com.ale;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.ale.bean.FetchUserMessageDTO;
import com.ale.bean.User;
import com.ale.bean.UserAlbumInfo;
import com.ale.bean.UserDTO;
import com.ale.util.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Test
    void test(){
        String json = FileUtil.readUtf8String(new File("D:\\code\\java-dev-toolbox\\data-format\\json-util\\src\\test" +
                                                               "\\resources\\demo.json"));

        List<UserAlbumInfo> userAlbumInfos = JSON.parseArray(json, UserAlbumInfo.class);
        System.out.println(JSON.toJSONString(userAlbumInfos));

    }

    @Test
    void test11(){
        String json = FileUtil.readUtf8String(new File("D:\\code\\java-dev-toolbox\\data-format\\json-util\\src\\test" +
                                                               "\\resources\\sms.json"));

        List<FetchUserMessageDTO> userAlbumInfos = JSON.parseArray(json, FetchUserMessageDTO.class);
        List<FetchUserMessageDTO> collect =
                userAlbumInfos.stream().filter(fetchUserMessageDTO -> StrUtil.isBlank(fetchUserMessageDTO.getOther_mobile())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));

    }




}
