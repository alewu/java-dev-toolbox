package com.ale;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.EnumUtil;
import com.ale.enum1.Season;
import com.google.common.base.Converter;
import com.google.common.base.Enums;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class EnumUtilTest {

    @Test
    void test(){
//        convertToMap();
//        convertToMap2(AdvancePayEnum.class);


    }

    public static Map<String, String> convertToMap() {
        Map<String, String> mapResult = new HashMap<>();
        Converter<String, Season> stringSeasonConverter = Enums.stringConverter(Season.class);
        Converter<Season, String> reverse = stringSeasonConverter.reverse();

        Arrays.stream(Season.values()).forEach(season -> {
            mapResult.put(season.getValue(), season.getName());
        });

        return mapResult;
    }

    public static Map<String, String> convertToMap2(Class clazz) {
        String simpleName = clazz.getSimpleName();
        Map<String, String> hashMap = MapUtil.newHashMap();
        Map value = EnumUtil.getNameFieldMap(clazz, "value");
        for (Object name : value.keySet()) {
            hashMap.put(String.valueOf(value.get(name)), simpleName + "." + name);
        }
        System.out.println(hashMap);

        return hashMap;

    }

}
