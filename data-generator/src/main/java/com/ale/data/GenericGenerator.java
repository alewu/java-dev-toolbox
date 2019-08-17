package com.ale.data;

import java.util.Random;

public abstract class GenericGenerator {
    public abstract String generate();

    private static Random random = null;

    protected Random getRandomInstance() {
        if (random == null) {
            random = new Random(System.currentTimeMillis());
        }

        return random;
    }
}
