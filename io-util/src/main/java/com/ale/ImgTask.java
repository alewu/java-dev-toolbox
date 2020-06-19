package com.ale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@AllArgsConstructor
public class ImgTask {
    private String name;

    /**
     * 压缩
     * @return 地址
     */
    public String scale() {
        int timeout = 0;
        try {
            timeout = ThreadLocalRandom.current().nextInt(5);
            log.info("{} scale execute time: {}", Thread.currentThread().getName(), timeout);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "scale " + timeout;
    }

    /**
     * 添加水印
     * @param scale 地址
     * @return 地址
     */
    public String press(String scale) {
        int timeout = 0;
        try {
            timeout = ThreadLocalRandom.current().nextInt(5);
            log.info("{} press execute time: {}", Thread.currentThread().getName(), timeout);
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "press " + scale + " takes time: " + timeout;
    }
}
