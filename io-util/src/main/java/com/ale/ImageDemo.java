package com.ale;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class ImageDemo {
    public static void main(String[] args) throws IOException {

        long t1 = System.currentTimeMillis();
        URL url = new URL("http://134.175.245.70:808//customerId_172_20200515/15895366136675538.jpg");
        ImgUtil.pressImage(
                FileUtil.file("C:\\Users\\win10\\Pictures\\8595798_160730747190_2.jpg"),
                FileUtil.file("C:\\Users\\win10\\Pictures\\1.jpg"),
                ImgUtil.read(FileUtil.file(url)), //水印图片
                0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                1.0f
        );
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);


//        ImgUtil.scale(
//                FileUtil.file("C:\\Users\\win10\\Pictures\\Saved Pictures\\9-1.jpg"),
//                FileUtil.file("C:\\Users\\win10\\Pictures\\Saved Pictures\\9-1-result.jpg"),
//                0.5f//缩放比例
//        );
    }
}
