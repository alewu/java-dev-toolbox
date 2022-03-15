# JavaParser AST(抽象语法树)

maven 导入

``` xml
 <!-- https://mvnrepository.com/artifact/com.github.javaparser/javaparser-core -->
    <dependency>
      <groupId>com.github.javaparser</groupId>
      <artifactId>javaparser-core</artifactId>
      <version>3.24.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.github.javaparser/javaparser-symbol-solver-core -->
    <dependency>
      <groupId>com.github.javaparser</groupId>
      <artifactId>javaparser-symbol-solver-core</artifactId>
      <version>3.24.0</version>
    </dependency>
```

## 表达式 `Expression`

### 注解表达式

##### AnnotationExpr

分类

- 标记注解(`MarkerAnnotationExpr`), eg. `@Override`
- 单成员注解(`SingleMemberAnnotationExpr`), eg. `@Count(15)`
- 正式注解(`NormalAnnotationExpr`), eg. `@Mapping(a=5, d=10)`

## 声明 `Declaration`

- 类型声明`TypeDeclaration`
- 导入声明 `ImportDeclaration`

### 按词法保留原格式输出

`LexicalPreservingPrinter.setup(cu)`
`LexicalPreservingPrinter.print(cu);`

```java
/**
* Prepares the node so it can be used in the print methods.
* The correct order is:
* <ol>
* <li>Parse some code</li>
* <li>Call this setup method on the result</li>
* <li>Make changes to the AST as desired</li>
* <li>Use one of the print methods on this class to print out the original source code with your changes added</li>
* </ol>
*
* @return the node passed as a parameter for your convenience.
*/

```

