package com.ale.parser;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaParseUtil {


    public static CombinedTypeSolver generateTypeSolver(String sourcePath, String libPath) throws IOException {
        CombinedTypeSolver solver = new CombinedTypeSolver();
        // 1. JavaParserTypeSolver
        solver.add(new JavaParserTypeSolver(sourcePath));
        // 2. JarTypeSolver
        FindFileVisitor findJarVisitor = new FindFileVisitor(".jar");
        Files.walkFileTree(Paths.get(libPath), findJarVisitor);
        for (String name : findJarVisitor.getFileNameList()) {
            solver.add(new JarTypeSolver(name));
        }
        // 3. ReflectionTypeSolver
        solver.add(new ReflectionTypeSolver());

        return solver;
    }


    public static JavaParser getParser(String sourcePath, String libPath) throws IOException {
        File base = new File("./");
        TypeSolver typeSolver = generateTypeSolver(base.getCanonicalPath() + File.separator + sourcePath,
                                                   libPath);
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        ParserConfiguration configuration = new ParserConfiguration();
        configuration.setSymbolResolver(symbolSolver);
        return new JavaParser(configuration);
    }

    public static JavaParser getSourceParser(String sourcePath) {
        TypeSolver typeSolver = new JavaParserTypeSolver(sourcePath);
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        ParserConfiguration configuration = new ParserConfiguration();
        configuration.setSymbolResolver(symbolSolver);
        return new JavaParser(configuration);
    }

    public static SingleMemberAnnotationExpr genSingleAnnotation(String annotationName, String className) {
        SingleMemberAnnotationExpr singleMemberAnnotationExpr = new SingleMemberAnnotationExpr();
        singleMemberAnnotationExpr.setName(annotationName);
        ClassExpr classExpr = new ClassExpr();
        classExpr.getType().asClassOrInterfaceType().setName(className);
        singleMemberAnnotationExpr.setMemberValue(classExpr);
        return singleMemberAnnotationExpr;
    }

    public static NormalAnnotationExpr genNormalAnnotationExprWithString(String annotationName, String key1,
                                                                         String key2) {
        NormalAnnotationExpr normalAnnotationExpr = new NormalAnnotationExpr();
        StringLiteralExpr stringLiteralExpr = new StringLiteralExpr();
        stringLiteralExpr.setString("");
        normalAnnotationExpr.addPair(key1, stringLiteralExpr);
        normalAnnotationExpr.addPair(key2, stringLiteralExpr);
        normalAnnotationExpr.setName(annotationName);
        return normalAnnotationExpr;
    }

    public static NormalAnnotationExpr genNormalAnnotationExprWithBoolean(String annotationName, String key1,
                                                                          String key2) {
        NormalAnnotationExpr normalAnnotationExpr = new NormalAnnotationExpr();
        BooleanLiteralExpr falseLiteralExpr = new BooleanLiteralExpr();
        falseLiteralExpr.setValue(false);
        BooleanLiteralExpr trueLiteralExpr = new BooleanLiteralExpr();
        trueLiteralExpr.setValue(true);
        normalAnnotationExpr.addPair(key1, trueLiteralExpr);
        normalAnnotationExpr.addPair(key2, falseLiteralExpr);
        normalAnnotationExpr.setName(annotationName);
        return normalAnnotationExpr;
    }

    public static List<String> getAnnotationNames(NodeList<AnnotationExpr> annotations) {
        return annotations.stream().map(AnnotationExpr::getName).map(Name::getIdentifier).collect(Collectors.toList());
    }

    /**
     * 在类上添加注解
     *
     * @param type type
     */
    public static void addAnnotations(TypeDeclaration<?> type) {
        ClassOrInterfaceDeclaration classOrInterfaceDeclaration = type.asClassOrInterfaceDeclaration();
        SingleMemberAnnotationExpr singleMemberAnnotationExpr = JavaParseUtil.genSingleAnnotation("ApiModel", "Log");
        classOrInterfaceDeclaration.addAnnotation(singleMemberAnnotationExpr);
        classOrInterfaceDeclaration.addAnnotation(JavaParseUtil.genNormalAnnotationExprWithBoolean("Auth", "url",
                                                                                                   "permission"));
    }

    public static void addImportDeclarations(CompilationUnit cu) {
        //  https://github.com/javaparser/javaparser/issues/3403
        //        cu.addImport("org.springframework.context.annotation.Conditional");
        String conditional = "org.springframework.context.annotation.Conditional";
        ImportDeclaration conditionalImportDeclaration = new ImportDeclaration(conditional, false, false);
        String log = "com.ale.log";
        ImportDeclaration logImportDeclaration = new ImportDeclaration(log, false, false);

        NodeList<ImportDeclaration> imports = cu.getImports();
        List<String> packages =
                imports.stream().map(ImportDeclaration::getName).map(Name::toString).collect(Collectors.toList());

        // 导入声明添加到指定的位置
        int m = getPosition(packages, "com.ale");
        int n = getPosition(packages, "org.springframework");
        imports.add(n, conditionalImportDeclaration);
        imports.add(m, logImportDeclaration);

        //        cu.getImports().addFirst(importDeclaration);
        //        cu.getImports().addFirst(importDeclaration1);
        //        cu.getImports().addFirst(importDeclaration2);

    }

    private static int getPosition(List<String> packages, String s) {
        int n = 0;
        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).startsWith(s)) {
                n = i;
                break;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        SingleMemberAnnotationExpr singleMemberAnnotationExpr = genSingleAnnotation("Conditional", "Log");
        System.out.println(singleMemberAnnotationExpr);
    }

    static class FindFileVisitor extends SimpleFileVisitor<Path> {

        private final String fileNameSuffix;
        private final List<String> fileNameList = new ArrayList<>();

        public FindFileVisitor(String fileNameSuffix) {
            this.fileNameSuffix = fileNameSuffix;
        }

        public List<String> getFileNameList() {
            return fileNameList;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            String fileName = file.toString();
            if (fileName.endsWith(fileNameSuffix)) {
                fileNameList.add(fileName);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

    }


}
