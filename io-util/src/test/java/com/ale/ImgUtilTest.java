package com.ale;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImgUtilTest {

    @Test
    void isRgbOrCmyk() throws IOException {
        boolean rgbOrCmyk = ImgUtil.isRgbOrCmyk("C:\\Users\\win10\\Pictures\\微信图片_20200805171644.jpg");
        assertFalse(rgbOrCmyk);
    }
}