package com.ale;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

class JarUtilTest {
    public static final String ROOT = "";
    public static final String DES_DIR = "";
    private static final String OPENAPI = "";
    private static final String MANAGE_V2 = "";
    private static final String PROVIDER = "";
    private static final String PAYCL = "";
    private static final String JOB = "";


    private static final String[] array = new String[]{OPENAPI, MANAGE_V2, PROVIDER, PAYCL, JOB};

    @Test
    void unJar() throws Exception {

        for (String s : array) {
            String project = s;
            String jarName = project.concat("-1.0.0-SNAPSHOT.jar");
            String lib = DES_DIR.concat(project).concat("\\BOOT-INF\\lib");

            JarUtil.unJar(new File(ROOT.concat(project).concat("\\target\\").concat(jarName)), new File(DES_DIR.concat(project)));

            List<File> files = FileUtil.loopFiles(lib, item -> item.getName().startsWith(""));
            for (File file : files) {
                System.out.println(file.getName());
            }
            List<String> collect = files.stream().map(File::getName).collect(Collectors.toList());
            FileUtil.writeLines(collect, DES_DIR.concat(project).concat(".txt"), StandardCharsets.UTF_8);

        }


    }
}