package com.ale.parser;

import java.util.LinkedHashMap;

public class EnumI18nUtil {

    public static <E extends Enum<E>> LinkedHashMap<String, E> getEnumMap(Class<E> enumClass) {
        LinkedHashMap<String, E> map = new LinkedHashMap();
//        Enum[] var2 = (Enum[])enumClass.getEnumConstants();
//        int var3 = var2.length;
//
//        for(int var4 = 0; var4 < var3; ++var4) {
//            E e = var2[var4];
//            map.put(e.name(), e);
//        }

        return map;
    }
}
