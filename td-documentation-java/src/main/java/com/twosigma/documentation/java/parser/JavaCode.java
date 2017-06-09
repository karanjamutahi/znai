package com.twosigma.documentation.java.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

/**
 * @author mykola
 */
public class JavaCode {
    private final JavaCodeVisitor codeVisitor;
    private String fileContent;

    public JavaCode(String fileContent) {
        this.fileContent = fileContent;
        codeVisitor = parse(fileContent);
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getClassJavaDocText() {
        return codeVisitor.getTopLevelJavaDoc();
    }

    public String findJavaDoc(String methodNameWithOptionalTypes) {
        return codeVisitor.findJavaDoc(methodNameWithOptionalTypes);
    }

    public JavaMethod findMethod(String methodNameWithOptionalTypes) {
        return codeVisitor.findMethodDetails(methodNameWithOptionalTypes);
    }

    public JavaField fieldByName(String fieldName) {
        return codeVisitor.findFieldDetails(fieldName);
    }

    private static JavaCodeVisitor parse(String fileContent) {
        CompilationUnit compilationUnit = JavaParser.parse(fileContent);
        JavaCodeVisitor visitor = new JavaCodeVisitor(fileContent);
        compilationUnit.accept(visitor, "JavaCode");
        return visitor;
    }
}
