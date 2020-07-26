package com.ale;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

/**
 * @author alewu
 * @date 2020/7/21
 */
class QrCodeTest {
    @Test
    void test() throws IOException {
        long start = Instant.now().toEpochMilli();
//        QrConfig config = new QrConfig(100, 100);
//        config.setMargin(0);
        File generate = QrCodeUtil.generate("https://wxe898b8e80c198a95.taotew.cn/t/2580349", 100,100, FileUtil.file("D:\\tmp\\qrcode.jpg"));
        System.out.println(generate.getAbsolutePath());
        System.out.println(Instant.now().toEpochMilli() - start);
    }
}
