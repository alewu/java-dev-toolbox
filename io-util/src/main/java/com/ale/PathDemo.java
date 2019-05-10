package com.ale;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author alewu
 * @since 2019/5/3 12:53
 */
public class PathDemo {

    public static String getProPath() {
        String path = PathDemo.class.getResource("/").getPath();
        System.out.println(path);

        return path;
    }

    public static void main(String[] args) {
        PathDemo pathDemo = new PathDemo();
        pathDemo.getFilePath();
    }

    public void getFilePath() {
        // 获取当前类的所在工程路径;
        File f0 = new File(this.getClass().getResource("/").getPath());
        System.out.println(f0);
        // 获取当前类的绝对路径；
        File f1 = new File(this.getClass().getResource("").getPath());
        System.out.println(f1);
        File directory = new File("");//参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);
        URL xmlpath = this.getClass().getClassLoader().getResource("./json/firstName_zh.json");
        System.out.println(xmlpath);

    }
}
