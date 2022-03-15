package com.ale;


import cn.hutool.core.io.FileUtil;
import com.ale.parser.JavaParseUtil;
import com.ale.parser.ParserSummary;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class JavaParserModifyClassTest {

    public static final String ROOT = "C:\\Users\\IdeaProjects\\";
    public static final String PROJECT = "\\java-tool-dev";


    @Test
    void testSingleFile() throws FileNotFoundException {
        parserSingleFile(new File(ROOT + ""));
    }


    @Test
    void testJavaParserModifyClass() {
        List<File> files = FileUtil.loopFiles(ROOT + PROJECT, file -> file.getName().endsWith(".java"));
        List<CompletableFuture<ParserSummary>> futures = Lists.newArrayListWithExpectedSize(files.size());


        for (File file : files) {
            CompletableFuture<ParserSummary> completableFuture = CompletableFuture.supplyAsync(() -> {
                ParserSummary parserSummary = new ParserSummary();
                try {
                    parserSummary = parserSingleFile(file);
                    return parserSummary;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return parserSummary;
            }, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));

            futures.add(completableFuture);
        }
        List<ParserSummary> parserSummaries =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();


        long count =
                parserSummaries.stream().filter(parserSummary -> Objects.nonNull(parserSummary.getTodoAddAnnotationClassName())).count();
        System.out.println("需要添加注解的类的总数: " + count);
        long addedCount =
                parserSummaries.stream().filter(parserSummary -> Objects.nonNull(parserSummary.getAddedConditionalClassName())).count();
        System.out.println("已添加添加注解的类的总数: " + addedCount);


    }

    private ParserSummary parserSingleFile(File file) throws FileNotFoundException {
        JavaParser parser = JavaParseUtil.getSourceParser(ROOT);
        ParseResult<CompilationUnit> parseResult = parser.parse(file);
        ParserSummary parserSummary = new ParserSummary();
        parseResult.ifSuccessful(cu -> {
            LexicalPreservingPrinter.setup(cu);
            for (TypeDeclaration<?> type : cu.getTypes()) {
                NodeList<AnnotationExpr> annotations = type.getAnnotations();
                if (isSkip(parserSummary, annotations)) {
                    continue;
                }
                parserSummary.setTodoAddAnnotationClassName(type.getNameAsString());
                try {

                    JavaParseUtil.addAnnotations(type);
                    JavaParseUtil.addImportDeclarations(cu);

                    parserSummary.setAddAnnotationSucceed(true);
                } catch (Exception e) {
                    parserSummary.setAddAnnotationSucceed(false);
                }
                String print = LexicalPreservingPrinter.print(cu);
                //                System.out.println(print);
                FileUtil.writeString(print, file, StandardCharsets.UTF_8);
            }
        });
        return parserSummary;
    }

    private boolean isSkip(ParserSummary parserSummary,
                           NodeList<AnnotationExpr> annotations) {
        List<String> annotationNames = JavaParseUtil.getAnnotationNames(annotations);
        if (!annotationNames.contains("Service") && !annotationNames.contains("Component") && !annotationNames.contains("RestController") && !annotationNames.contains("Controller")) {
            // 不包含Service，Component， RestController， Controller注解跳过
            return true;
        }

        // 包含了注解Conditional(Log.class) 跳过
        List<String> identifiers = getClassExpr(annotations);
        if (identifiers.contains("Log")) {
            return true;
        }
        if (annotationNames.contains("Conditional")) {
            parserSummary.setContainsConditional(true);
            return true;
        }
        return false;
    }

    private List<String> getClassExpr(NodeList<AnnotationExpr> annotations) {
        List<String> identifiers = Lists.newArrayList();
        annotations.forEach(annotationExpr -> {
            if (annotationExpr instanceof SingleMemberAnnotationExpr) {
                SingleMemberAnnotationExpr singleMemberAnnotationExpr = annotationExpr.asSingleMemberAnnotationExpr();
                Expression memberValue = singleMemberAnnotationExpr.getMemberValue();
                if (memberValue instanceof ClassExpr) {
                    ClassExpr classExpr = memberValue.asClassExpr();
                    SimpleName name = classExpr.getType().asClassOrInterfaceType().getName();
                    String identifier = name.getIdentifier();
                    identifiers.add(identifier);
                }
            }
        });
        return identifiers;
    }


}
