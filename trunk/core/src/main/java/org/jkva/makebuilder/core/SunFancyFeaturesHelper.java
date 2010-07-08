//package org.jkva.makebuilder.core;
//
//import com.sun.source.tree.CompilationUnitTree;
//import com.sun.source.util.TreePath;
//import com.sun.source.util.Trees;
//import com.sun.tools.javac.code.Flags;
//import com.sun.tools.javac.code.Symbol;
//import com.sun.tools.javac.model.FilteredMemberList;
//import com.sun.tools.javac.model.JavacElements;
//import com.sun.tools.javac.processing.JavacProcessingEnvironment;
//import com.sun.tools.javac.tree.JCTree;
//import com.sun.tools.javac.tree.TreeMaker;
//import com.sun.tools.javac.util.Context;
//import com.sun.tools.javac.util.List;
//import com.sun.tools.javac.util.Name;
//
//import javax.lang.model.element.TypeElement;
//import java.util.Collections;
//
///**
// * Sun implementation of FancyFeaturesHelper.
// */
//public class SunFancyFeaturesHelper implements FancyFeaturesHelper {
//
//    private final JavacProcessingEnvironment javacProcessingEnvironment;
//
//    private final Trees trees;
//
//    public SunFancyFeaturesHelper(JavacProcessingEnvironment javacProcessingEnvironment) {
//        this.javacProcessingEnvironment = javacProcessingEnvironment;
//        trees = Trees.instance(javacProcessingEnvironment);
//    }
//
//    @Override
//    public void addMethod(GeneratedMethod method, TypeElement typeElement) {
//        final JavacElements javacElements = javacProcessingEnvironment.getElementUtils();
//        final FilteredMemberList memberList = javacElements.getAllMembers(typeElement);
//        System.out.println("memberList = " + memberList);
//        final TreePath treePath = trees.getPath(typeElement);
//        final CompilationUnitTree compilationUnit = treePath.getCompilationUnit();
//        System.out.println("compilationUnit = " + compilationUnit);
//
//        //JCTree.JCClassDecl type = (JCTree.JCClassDecl) typeNode.get();
//        Name methodName = toName("hello", javacElements);
//        JCTree.JCMethodDecl method = createGetter(1, treeMaker, methodName);
//        type.defs = type.defs.append(method);
//    }
//
//    private JCTree.JCMethodDecl createGetter(long access, TreeMaker treeMaker, Name methodName) {
//        //long access = toJavacModifier(level) | (fieldDecl.mods.flags & Flags.STATIC);
//		JCTree.JCVariableDecl fieldNode = (JCTree.JCVariableDecl) field.get();
//		JCTree.JCStatement returnStatement = treeMaker.Return(treeMaker.Ident(fieldNode.getName()));
//
//		JCTree.JCBlock methodBody = treeMaker.Block(0, List.of(returnStatement));
//		JCTree.JCExpression methodType = fieldNode.type != null ? treeMaker.Type(fieldNode.type) : fieldNode.vartype;
//
//		List<JCTree.JCTypeParameter> methodGenericParams = List.nil();
//		List<JCTree.JCVariableDecl> parameters = List.nil();
//		List<JCTree.JCExpression> throwsClauses = List.nil();
//		JCTree.JCExpression annotationMethodDefaultValue = null;
//
//		List<JCTree.JCAnnotation> nonNulls = null;//Collections.emptyList(); //findAnnotations(field, TransformationsUtil.NON_NULL_PATTERN);
//		List<JCTree.JCAnnotation> nullables = null;//Collections.emptyList(); //findAnnotations(field, TransformationsUtil.NULLABLE_PATTERN);
//		return treeMaker.MethodDef(treeMaker.Modifiers(access, new List<JCTree.JCAnnotation>(), methodName, methodType,
//				methodGenericParams, parameters, throwsClauses, methodBody, annotationMethodDefaultValue);
//	}
//
//	public Name toName(String name, JavacElements elements) {
//		return elements.getName(name);
//	}
//}
