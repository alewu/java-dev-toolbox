package com.ale;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

/**
 * @author alewu
 * @date 2020/7/21
 */
class QrCodeTest {
    @Test
    void test() throws IOException {
        long start = Instant.now().toEpochMilli();
        QrConfig config = new QrConfig(100, 100);
        config.setMargin(0);
        Path tmp = Paths.get("tmp", "a","qrcode.jpg");
        Path parent = tmp.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        //        Files.createFile(tmp, "")
        File generate = QrCodeUtil.generate("https://wxe898b8e80c198a95.taotew.cn/t/2580349", config, tmp.toFile());
        System.out.println(generate.getAbsolutePath());
        System.out.println(Instant.now().toEpochMilli() - start);
    }


}
