package com.ale;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

/**
 * @author wywuj
 */
public class JarUtil {

    /**
     * copy jar，不带过滤器，就是copy，带条件，就是copy某些pkg下的class
     *
     * @param src    要copy的jar
     * @param des    copy出来叫的名称
     * @param filter 过滤器，copy什么pkg开头的class文件
     * @throws Exception 异常
     */
    public static void copyJar(File src, File des, String filter) throws Exception {
        JarInputStream srcJar = null;
        JarOutputStream desJar = null;
        boolean none = true;
        try {
            srcJar = new JarInputStream(new BufferedInputStream(new FileInputStream(src)));
            Manifest manifest = srcJar.getManifest();
            if (manifest == null) {
                desJar = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(des)));
            } else {
                desJar = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(des)), manifest);
            }

            byte[] bytes = new byte[1024];
            while (true) {
                //重点
                ZipEntry entry = srcJar.getNextJarEntry();
                if (entry == null) {
                    break;
                }
                String name = entry.getName();
                if (!name.startsWith(filter)) {
                    continue;
                }
                none = false;
                //desJar.putNextEntry(entry);//这个会报错zipexception invalid entry compressed size (expected 36586 but got 37005 bytes)
                desJar.putNextEntry(new ZipEntry(entry.getName()));


                int len = srcJar.read(bytes, 0, bytes.length);
                while (len != -1) {
                    desJar.write(bytes, 0, len);
                    len = srcJar.read(bytes, 0, bytes.length);
                }
            }
            desJar.finish();
        } finally {
            if (srcJar != null) {
                srcJar.close();
            }
            if (desJar != null) {
                desJar.close();
            }
        }

        if (none) {
            boolean delete = des.delete();
        }
    }

    /**
     * 解压jar包
     *
     * @param src    要解压的包
     * @param desDir 解压到的目录
     * @throws Exception 异常
     */
    public static void unJar(File src, File desDir) throws Exception {
        JarInputStream jarIn = new JarInputStream(new BufferedInputStream(new FileInputStream(src)));
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        byte[] bytes = new byte[1024];

        while (true) {
            ZipEntry entry = jarIn.getNextJarEntry();
            if (entry == null) {
                break;
            }

            File desTemp = new File(desDir.getAbsoluteFile() + File.separator + entry.getName());

            //jar条目是空目录
            if (entry.isDirectory()) {
                if (!desTemp.exists()) {
                    desTemp.mkdirs();
                }
            } else {
                File parentDir = new File(desTemp.getParent());
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                //jar条目是文件
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(desTemp));
                int len = jarIn.read(bytes, 0, bytes.length);
                while (len != -1) {
                    out.write(bytes, 0, len);
                    len = jarIn.read(bytes, 0, bytes.length);
                }

                out.flush();
                out.close();

            }
            jarIn.closeEntry();
        }

        //解压Manifest文件
        Manifest manifest = jarIn.getManifest();
        if (manifest != null) {
            File manifestFile = new File(desDir.getAbsoluteFile() + File.separator + JarFile.MANIFEST_NAME);
            if (!manifestFile.getParentFile().exists()) {
                manifestFile.getParentFile().mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(manifestFile));
            manifest.write(out);
            out.close();
        }

        //关闭JarInputStream
        jarIn.close();
    }


    /**
     * 读取文件内容并压缩
     *
     * @param filePath 文件路径
     */
    public static void compressFileToJar(String filePath, String jarFilePath) {
        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFilePath))) {
            //递归的压缩文件夹和文件
            doCompress("", filePath, jos);
            //必须
            jos.finish();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void doCompress(String parentFilePath, String filePath, JarOutputStream jos) {
        File sourceFile = new File(filePath);
        if (!sourceFile.exists()) {
            return;
        }
        String jarEntryName = parentFilePath + "/" + sourceFile.getName();
        if (parentFilePath.isEmpty()) {
            jarEntryName = sourceFile.getName();
        }
        if (sourceFile.isDirectory()) {
            File[] childFiles = sourceFile.listFiles();
            if (Objects.isNull(childFiles)) {
                return;
            }
            for (File childFile : childFiles) {
                doCompress(jarEntryName, childFile.getAbsolutePath(), jos);
            }
        } else {
            int len = -1;
            byte[] buf = new byte[1024];
            jarEntryName = jarEntryName.substring(jarEntryName.indexOf("/") + 1);
            try (InputStream input = new BufferedInputStream(new FileInputStream(sourceFile))) {
                jos.putNextEntry(new JarEntry(jarEntryName));
                while ((len = input.read(buf)) != -1) {
                    jos.write(buf, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}