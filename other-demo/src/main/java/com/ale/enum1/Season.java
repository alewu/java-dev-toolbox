package com.ale.enum1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum Season {
    //    SPRING("spring", 1),
    //    SUMMER("summer", 2);
    SPRING("spring", "1"),
    SUMMER("summer", "2");


    private final String name;
    private final String value;

    public static String getValue(String value) {
        Season[] values = values();
        for (Season season : values) {
            if (Objects.equals(season.getValue(), value)) {
                return season.getName();
            }
        }
        return null;
    }

    public static String getName(String name) {
        Season[] values = values();
        for (Season season : values) {
            if (Objects.equals(season.getValue(), name)) {
                return season.getName();
            }
        }
        return null;
    }
}
