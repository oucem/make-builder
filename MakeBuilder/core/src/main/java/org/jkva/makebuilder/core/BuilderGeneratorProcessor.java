/*
 * Copyright (C) 2010 Jan-Kees van Andel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jkva.makebuilder.core;

import org.jkva.makebuilder.annotations.Immutable;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.tools.Diagnostic;
import java.util.*;

/**
 * Annotation processor for {Immutable} classes. For classes annotated with
 * {Immutable}, a Builder will be generated.
 */
@SupportedAnnotationTypes("org.jkva.makebuilder.annotations.Immutable")
@SupportedSourceVersion(javax.lang.model.SourceVersion.RELEASE_6)
public class BuilderGeneratorProcessor extends AbstractProcessor {


    /**
     * The attribute name used to specify the package to put the generated classes.
     * <p/>
     * This constant defines the name of a compiler option passed to the processor.
     */
    private static final String TARGET_PACKAGE_ATTR = "targetPackage";

    /**
     * The writer implementation to use when generating the sources.
     */
    ClassWriter classWriter = new FreeMarkerClassWriterImpl();

    /** {@inheritDoc} */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment env) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.WARNING,
                "Test");
        for (final TypeElement type : annotations) {
            for (final Element element : env.getElementsAnnotatedWith(type)) {
                final Immutable immutable = element.getAnnotation(Immutable.class);
                if (immutable != null
                 && immutable.generateBuilder()) {
                    generate((TypeElement) element, processingEnv);
                }
            }
        }
        return true;
    }

    /**
     * Holder for information about the superclass.
     */
    private static final class SuperClassInfo {
        String qualifiedName;
    }

    /**
     * Generate code for the given element.
     *
     * @param element The element for which the code must be generated.
     * @param processingEnv The global processing environment.
     */
    private void generate(final TypeElement element, final ProcessingEnvironment processingEnv) {
        final SuperClassInfo superClassInfo = determineSuperClass(element);
        final Property[] properties = listProperties(element);

        generateImpl(superClassInfo, properties, processingEnv);
        generateBuilder(superClassInfo, properties, processingEnv);
    }

    /**
     * Go through all properties in the type hierarchy.
     *
     * @param element The element for which the properties must be listed.
     * @return An array of {Property}s.
     */
    private Property[] listProperties(final TypeElement element) {
        List<TypeElement> hierarchy = createTypeHierarchy(element);
        Set<Property> ret = processTypeHierarchy(hierarchy);
        return ret.toArray(new Property[ret.size()]);
    }

    /**
     * Determine all superclasses/interfaces for which properties must be listed.
     *
     * @param element The element for which a type hierarchy must be created.
     * @return A List of all {TypeElement}s that have properties that must be processed.
     */
    private List<TypeElement> createTypeHierarchy(final TypeElement element) {
        final List<TypeElement> hierarchy = new ArrayList<TypeElement>();

        final List<? extends TypeMirror> interfaces = element.getInterfaces();
        for (final TypeMirror interfaze : interfaces) {
            // Only include super interface if it's marked with {Immutable}.
            final Boolean includeSuperInterface = interfaze.accept(IMMUTABLE_VISITOR, null);

            if (includeSuperInterface != null && includeSuperInterface) {
                final List<TypeElement> superHierarchy = createTypeHierarchy((TypeElement) ((DeclaredType) interfaze).asElement());
                hierarchy.addAll(superHierarchy);
            }
        }

        hierarchy.add(element);

        return hierarchy;
    }

    /**
     * Work through the given types and returning a Set with properties.
     *
     * @param hierarchy The type hierarchy, listed.
     * @return An ordered Set with properties.
     */
    private Set<Property> processTypeHierarchy(final List<TypeElement> hierarchy) {
        final Set<Property> properties = new LinkedHashSet<Property>();

        for (final TypeElement typeElement : hierarchy) {
            final List<Element> methods = new ArrayList<Element>(typeElement.getEnclosedElements());
            for (final Element method : methods) {
                final String signature = method.toString();
                if (BeanUtils.couldBeAMethod(signature)) {
                    final String methodName = BeanUtils.determineMethodName(signature);
                    if (BeanUtils.isGetter(methodName)) {
                        final String propertyName = BeanUtils.determinePropertyName(methodName);
                        final String setter = BeanUtils.determineSetter(propertyName);

                        final Property property = new Property();
                        property.name = propertyName;
                        property.type = method.asType().accept(GET_RETURNTYPE_VISITOR, null);
                        property.getter = methodName;
                        property.setter = setter;

                        properties.add(property);
                    }
                }
            }
        }

        return properties;
    }

    /**
     * Determine the superclass for this element.
     *
     * @param element The current element.
     * @return The superclass metadata.
     */
    private SuperClassInfo determineSuperClass(TypeElement element) {
        SuperClassInfo info = new SuperClassInfo();
        element.accept(SUPERCLASS_VISITOR, info);

        return info;
    }

    /**
     * Generate implementation for this immutable type.
     *
     * @param superClassInfo The superclass metadata.
     * @param properties The properties that needs getters and setters.
     * @param processingEnv The current processing environment.
     */
    private void generateImpl(SuperClassInfo superClassInfo, Property[] properties, ProcessingEnvironment processingEnv) {
        Map<String, Object> rootMap = createRootMap(superClassInfo, properties, processingEnv);
        classWriter.generateImpl(rootMap, this.processingEnv.getFiler());
    }

    private Map<String, Object> createRootMap(SuperClassInfo superClassInfo, Property[] properties, ProcessingEnvironment processingEnv) {
        Map<String, Object> root = new HashMap<String, Object>();
        String superClassQName = superClassInfo.qualifiedName;

        String superClassSimpleName = superClassQName.substring(superClassQName.lastIndexOf('.') + 1);
        String targetPackage = processingEnv.getOptions().get(TARGET_PACKAGE_ATTR);
        if (targetPackage == null || targetPackage.trim().equals("")) {
            targetPackage = "org.jkva.makebuilder.generated";
        }

        int idx = superClassQName.lastIndexOf('.') + 1;
        String implClassQName = targetPackage + '.' + superClassQName.substring(idx) + "Impl";
        String implClassSimpleName = implClassQName.substring(implClassQName.lastIndexOf('.') + 1);
        String builderClassQName = targetPackage + '.' + superClassQName.substring(idx) + "BuilderImpl";
        String builderClassSimpleName = builderClassQName.substring(builderClassQName.lastIndexOf('.') + 1);

        root.put(TARGET_PACKAGE_ATTR, targetPackage);
        root.put("superClassQName", superClassQName);
        root.put("superClassSimpleName", superClassSimpleName);
        root.put("implClassQName", implClassQName);
        root.put("implClassSimpleName", implClassSimpleName);
        root.put("builderClassQName", builderClassQName);
        root.put("builderClassSimpleName", builderClassSimpleName);
        root.put("properties", Arrays.asList(properties));
        root.put("generatorClass", BuilderGeneratorProcessor.class);
        return root;
    }

    /**
     * Generate annotations class for this immutable type.
     *
     * @param superClassInfo The superclass metadata.
     * @param properties The properties that needs getters and setters.
     * @param processingEnv The current processing environment.
     */
    private void generateBuilder(SuperClassInfo superClassInfo, Property[] properties, ProcessingEnvironment processingEnv) {
        Map<String, Object> rootMap = createRootMap(superClassInfo, properties, processingEnv);
        classWriter.generateBuilder(rootMap, this.processingEnv.getFiler());
    }

    /**
     * Visitor to determine the return type of the method.
     */
    private static final TypeVisitor<String, Object> GET_RETURNTYPE_VISITOR
            = new SimpleTypeVisitor6<String, Object>() {
        /** {@inheritDoc} */
        @Override
        public String visitExecutable(ExecutableType t, Object o) {
            return t.getReturnType().toString();
        }
    };

    /**
     * Holder for property metadata.
     */
    public static final class Property {
        /**
         * The property name.
         */
        String name;

        /**
         * The property type.
         */
        String type;

        /**
         * The name of the getter.
         */
        String getter;

        /**
         * The name of the setter. Of course only used in the
         * Builder class, not the immutable class.
         */
        String setter;

        // ++++++++++++++++++ Used by FreeMarker BEGIN ++++++++++++++++++

        /**
         * Get the name.
         * @return The name.
         */
        public String getName() {
            return name;
        }

        /**
         * Get the type.
         * @return The type.
         */
        public String getType() {
            return type;
        }

        /**
         * Get the getter.
         * @return The getter.
         */
        public String getGetter() {
            return getter;
        }

        /**
         * Get the setter.
         * @return The setter.
         */
        public String getSetter() {
            return setter;
        }
        // ------------------ Used by FreeMarker END ------------------
    }

    /**
     * This Visitor determines the superclass of the current element.
     */
    private final ElementVisitor<Object, SuperClassInfo> SUPERCLASS_VISITOR = new ElementVisitor<Object, SuperClassInfo>() {
        public Object visit(Element e, SuperClassInfo info) {
            return null;
        }

        public Object visit(Element e) {
            return null;
        }

        public Object visitPackage(PackageElement e, SuperClassInfo info) {
            return null;
        }

        public Object visitType(TypeElement e, SuperClassInfo info) {
            // Get the qualified name of the type.
            info.qualifiedName = e.getQualifiedName().toString();
            return null;
        }

        public Object visitVariable(VariableElement e, SuperClassInfo info) {
            return null;
        }

        public Object visitExecutable(ExecutableElement e, SuperClassInfo info) {
            return null;
        }

        public Object visitTypeParameter(TypeParameterElement e, SuperClassInfo info) {
            return null;
        }

        public Object visitUnknown(Element e, SuperClassInfo info) {
            return null;
        }
    };

    private static final SimpleTypeVisitor6<Boolean,Object> IMMUTABLE_VISITOR = new SimpleTypeVisitor6<Boolean, Object>() {
        @Override
        public Boolean visitDeclared(final DeclaredType t, final Object o) {
            final Immutable immutable = t.asElement().getAnnotation(Immutable.class);
            return (immutable != null);
        }
    };
}
