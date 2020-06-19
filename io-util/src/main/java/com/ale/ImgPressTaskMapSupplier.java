package com.ale;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author alewu
 * @date 2020/6/16
 */
@Slf4j
@ToString
public class ImgPressTaskMapSupplier implements Supplier<String> {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public ImgPressTaskMapSupplier(String name) {this.name = name;}

    @Override
    public String get() {
        return press();
    }

    public String press() {
        try {
            int timeout = ThreadLocalRandom.current().nextInt(5);
            log.info("{} execute time: {}", Thread.currentThread().getName(), timeout);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(ThreadLocalRandom.current().nextInt(5));
    }
}
