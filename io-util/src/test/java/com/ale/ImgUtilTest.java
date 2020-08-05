package com.ale;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImgUtilTest {

    @Test
    void isRgbOrCmyk() throws IOException {
        boolean rgbOrCmyk = ImgUtil.isRgbOrCmyk("C:\\Users\\win10\\Pictures\\055b32590766d1fbee35f0660fc3c8c.jpg");
        assertFalse(rgbOrCmyk);
    }
}