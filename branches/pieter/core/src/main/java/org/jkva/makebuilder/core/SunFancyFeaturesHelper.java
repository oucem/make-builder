package org.jkva.makebuilder.core;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.tools.javac.model.FilteredMemberList;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;

import javax.lang.model.element.TypeElement;

/**
 * Sun implementation of FancyFeaturesHelper.
 */
public class SunFancyFeaturesHelper implements FancyFeaturesHelper {

    private final JavacProcessingEnvironment javacProcessingEnvironment;

    private final Trees trees;

    public SunFancyFeaturesHelper(JavacProcessingEnvironment javacProcessingEnvironment) {
        this.javacProcessingEnvironment = javacProcessingEnvironment;
        trees = Trees.instance(javacProcessingEnvironment);
    }

    @Override
    public void addMethod(GeneratedMethod method, TypeElement typeElement) {
        final JavacElements javacElements = javacProcessingEnvironment.getElementUtils();
        final FilteredMemberList memberList = javacElements.getAllMembers(typeElement);
        System.out.println("memberList = " + memberList);
        final TreePath treePath = trees.getPath(typeElement);
        final CompilationUnitTree compilationUnit = treePath.getCompilationUnit();
        System.out.println("compilationUnit = " + compilationUnit);
    }
}
