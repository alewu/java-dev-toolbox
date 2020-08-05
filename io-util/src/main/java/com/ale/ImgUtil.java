package com.ale;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
/**
  *  
  * @author alewu
  * @date 2020/8/5
  */
public class ImgUtil {

    public static boolean isRgbOrCmyk(String filename) throws IOException {
        File file = new File(filename);
        //true是Rgb否则是Cmyk
        boolean isRgb = true;
        //创建输入流
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator readers = ImageIO.getImageReaders(input);
        if (readers == null || !readers.hasNext()) {
            throw new RuntimeException("No ImageReaders found");
        }
        ImageReader reader = (ImageReader) readers.next();
        reader.setInput(input);
        //获取文件格式
        BufferedImage image;
        try {
            // 尝试读取图片 (包括颜色的转换).
            // RGB
            reader.read(0);
            isRgb = true;
        } catch (IIOException e) {
            // 读取Raster (没有颜色的转换).
            reader.readRaster(0, null);// CMYK
            isRgb = false;
        }
        return isRgb;
    }
}
