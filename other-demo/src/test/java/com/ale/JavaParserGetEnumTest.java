package com.ale;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.ale.parser.JavaParseUtil;
import com.ale.parser.ParserSummary;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JavaParserGetEnumTest {
    public static final String ROOT = "D:\\code\\loan\\cash2-vn";
    public static final String PROJECT = "\\";

    @Test
    void testReplace(){

    }



    @Test
    void testGetEnum() throws FileNotFoundException {

        Map<String, String> map = getMap("D:\\docs\\projects\\VN\\国际化.txt");
        List<File> files = FileUtil.loopFiles(ROOT + PROJECT, file -> file.getName().endsWith(".java"));
//        List<File> files = Lists.newArrayList();
//        File file1 = new File("D:\\code\\loan\\cash2-vn");
//        files.add(file1);
        JavaParser parser = JavaParseUtil.getSourceParser(ROOT);
        for (File file : files) {
            ParseResult<CompilationUnit> parseResult = parser.parse(file);
            parseResult.ifSuccessful((cu) -> {
                LexicalPreservingPrinter.setup(cu);

                NodeList<TypeDeclaration<?>> types = cu.getTypes();
                for (TypeDeclaration<?> type : types) {
                    if (!type.isEnumDeclaration()) {
                        continue;
                    }
                    // public enum Season
                    EnumDeclaration enumDeclaration = type.asEnumDeclaration();
                    // 获取枚举里面的成员 SPRING,SUMMER
                    NodeList<EnumConstantDeclaration> entries = enumDeclaration.getEntries();
                    for (EnumConstantDeclaration entry : entries) {
                        // 获取枚举里面的成员变量值 如 枚举 SPRING("spring", "1"), 获取到的是  ["spring", "1"]
                        String id = entry.getName().getId();

                        String value = map.get(id);

                        if (StringUtils.isEmpty(value)) {
                            continue;
                        }

//                        System.out.println(id);
                        NodeList<Expression> arguments = entry.getArguments();
                        List<String> argumentValues = getArgumentValues(arguments);
                        // 匹配泰文
//                        boolean anyMatch = argumentValues.stream().anyMatch(a -> a.matches("[\u0E00-\u0E7F]+"));
//                        System.out.println(argumentValues);
                        boolean anyMatch = argumentValues.stream().filter(StrUtil::isNotBlank).anyMatch(a -> isThai(a.charAt(0)));
                        if (true) {
//                            System.out.println("value:" + value + " 替换前：" + entry);
                            StringLiteralExpr stringLiteralExpr = new StringLiteralExpr();

                            stringLiteralExpr.setValue(value);
                            // 修改最后一个参数
                            entry.setArgument(arguments.size() - 1, stringLiteralExpr);
//                            targetEnumDeclarations.add(entry);
//                            System.out.println("替换后：" + entry);
                        }
                    }

                    String print = LexicalPreservingPrinter.print(cu);
                    //                System.out.println(print);
                    FileUtil.writeString(print, file, StandardCharsets.UTF_8);

                }

            });
        }

    }

    private List<String> getArgumentValues(NodeList<Expression> arguments) {
        return arguments.stream().filter(Expression::isStringLiteralExpr).map(argument -> argument.asStringLiteralExpr().getValue())
                .collect(Collectors.toCollection(() -> Lists.newArrayListWithExpectedSize(arguments.size())));
    }


    public static boolean isThai(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        return sc == Character.UnicodeScript.THAI;
    }

    @Test
    public void testParse() {
        Map<String, String> map = getMap("D:\\docs\\projects\\VN\\国际化.txt");
        System.out.println(map);

    }

    public static Map<String, String> getMap(String path) {
        Map<String, String> map = Maps.newHashMap();
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        List<String> lines = FileUtil.readLines(path, StandardCharsets.UTF_8);
        List<String> collect = lines.stream().filter(s -> !s.startsWith("//")).collect(Collectors.toList());
        for (String s : collect) {
            List<String> strings = Splitter.on("(").splitToList(s);
            String x = strings.get(0);
//            System.out.println(x);
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String group = matcher.group(0);
                List<String> strings1 = Splitter.on(",").splitToList(group);
                String s1 = strings1.get(strings1.size() - 1);
//                System.out.println(s1);
                map.put(x, s1.replace("\"", "").trim());
            }
        }
        return map;
    }


    @Test
    public void test() {
        boolean matches = "Ngân hàng  TMCP Bản Việt".matches("[\u0E00-\u0E7F]+");
        System.out.println(matches);

        boolean matches1 = "จำนวนผู้ใช้ค้างชำระในสมุดรายชื่อ".matches("[\u0E00-\u0E7F]+");
        System.out.println(matches1);
    }
}
