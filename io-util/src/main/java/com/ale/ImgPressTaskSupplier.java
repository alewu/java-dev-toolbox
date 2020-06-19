package com.ale;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author alewu
 * @date 2020/6/16
 */
@Slf4j
public class ImgPressTaskSupplier implements Supplier<String> {
    private String name;

    public ImgPressTaskSupplier(String name) {
        super();
        this.name = name;
    }

    public String scale() {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(5);
            log.info("scale {} execute time: {}", Thread.currentThread().getName(), timeout);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String press() {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(5);
            log.info("press {} execute time: {}", Thread.currentThread().getName(), timeout);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public String get() {
        return press();
    }
}
