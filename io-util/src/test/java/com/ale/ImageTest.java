package com.ale;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;

/**
 * @author alewu
 * @date 2020/6/10
 */
public class ImageTest {
    public static long start;
    public static long end;

    @BeforeEach
    public void start() {
        start = System.currentTimeMillis();
    }

    @AfterEach
    public void end() {
        end = System.currentTimeMillis();
        System.out.println("执行时间： " + (end - start));
    }


    /**
     * 缩放图片：按照比例缩放
     */
    @Test
    public void testScale() {
        File srcImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\Saved Pictures\\9-1.jpg");
        File destImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\Saved Pictures\\9-1-result.jpg");
        ImgUtil.scale( srcImageFile, destImageFile,
                0.5f//缩放比例
        );

    }

    /**
     * 添加图片水印：x坐标修正值。 默认在中间
     */
    @Test
    public void testPressImage() {
        // 原图
        File srcImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\8595798_160730747190_2.jpg");
        // 水印图片
        BufferedImage pressImg = ImgUtil.read(FileUtil.file("C:\\Users\\win10\\Pictures\\1.jpg"));
        // 输出
        File destImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\1111111.jpg");
        // 透明度
        float alpha = 1.0f;
        ImgUtil.pressImage(srcImageFile, destImageFile, pressImg,
                           0, //x坐标修正值。 默认在中间，偏移量相对于中间偏移
                           0, //y坐标修正值。 默认在中间，偏移量相对于中间偏移
                           alpha
        );
    }

    /**
     * 添加图片水印：x坐标修正值。 默认在左上角
     */
    @Test
    public void testPressImageCoo() {
        // 原图
        File srcImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\110.jpg");
        // 水印图片
        File pressImg = FileUtil.file("C:\\Users\\win10\\Pictures\\1.jpg");
        // 输出
        File destImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\111.jpg");
        // 水印图片
        File pressImg1 = FileUtil.file("C:\\Users\\win10\\Pictures\\2.jpg");
        // 输出
        File destImageFile1 = FileUtil.file("C:\\Users\\win10\\Pictures\\222.jpg");
        // 透明度
        float alpha = 1.0f;
        // 设置坐标在左上角
        Img srcImg = Img.from(srcImageFile).setPositionBaseCentre(false);
        Image img = srcImg.pressImage(ImgUtil.read(pressImg),200, 200, alpha).getImg();
        ImgUtil.write(img, FileUtil.file(destImageFile));
        Image img1 = srcImg.pressImage(ImgUtil.read(pressImg1),200, 200, alpha).getImg();
        ImgUtil.write(img1, FileUtil.file(destImageFile1));
    }

    @Test
    public void testPressImage1() {
        // 原图
        File srcImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\110.jpg");
        // 水印图片
        File pressImg = FileUtil.file("C:\\Users\\win10\\Pictures\\1.jpg");
        // 输出
        File destImageFile = FileUtil.file("C:\\Users\\win10\\Pictures\\111.jpg");
        // 水印图片
        File pressImg1 = FileUtil.file("C:\\Users\\win10\\Pictures\\2.jpg");
        // 输出
        File destImageFile1 = FileUtil.file("C:\\Users\\win10\\Pictures\\222.jpg");
        // 透明度
        float alpha = 1.0f;
        // 设置坐标在左上角
        long start = Instant.now().toEpochMilli();
        Img srcImg = Img.from(srcImageFile).setPositionBaseCentre(false);
        long end = Instant.now().toEpochMilli();
        System.out.println(end- start);
        Image img = srcImg.pressImage(ImgUtil.read(pressImg),200, 200, alpha).getImg();
        ImgUtil.write(img, FileUtil.file(destImageFile));
        Image img1 = srcImg.pressImage(ImgUtil.read(pressImg1),200, 200, alpha).getImg();
        ImgUtil.write(img1, FileUtil.file(destImageFile1));
    }
}
