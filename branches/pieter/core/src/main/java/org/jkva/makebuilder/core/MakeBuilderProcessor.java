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

import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import org.jkva.makebuilder.annotations.Immutable;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Annotation processor for {Immutable} classes. For classes annotated with
 * {Immutable}, a Builder will be generated.
 *
 * This class is used to bootstrap the generation process.
 *
 * $Author$
 * $Revision$
 */
@SupportedAnnotationTypes({"org.jkva.makebuilder.annotations.Immutable", MakeBuilderProcessor.JCIP_IMMUTABLE})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class MakeBuilderProcessor extends AbstractProcessor {

    /**
     * The parser used to gather metadata from the type.
     */
    private final ClassParser classParser;

    /**
     * The writer implementation to use when generating the sources.
     */
    private final ClassWriter classWriter;

    /**
     * The name of the Java Concurrency in Practice type that
     * defines the Immutable annotation.
     */
    public static final String JCIP_IMMUTABLE = "net.jcip.annotations.Immutable";

    private FancyFeaturesHelper fancyFeaturesHelper;

    /**
     * Default constructor, used in production.
     */
    public MakeBuilderProcessor() {
        classParser = new DefaultClassParserImpl();
        classWriter = new FreeMarkerClassWriterImpl();
    }

    /**
     * Only used for testing.
     *
     * @param classParser The user specified ClassParser.
     * @param classWriter The user specified ClassWriter.
     */
    public MakeBuilderProcessor(ClassParser classParser, ClassWriter classWriter) {
        this.classParser = classParser;
        this.classWriter = classWriter;
    }

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("processingEnv = " + processingEnv);
        if (processingEnv instanceof JavacProcessingEnvironment) {
            fancyFeaturesHelper = new SunFancyFeaturesHelper((JavacProcessingEnvironment) processingEnv);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment env) {
        for (final TypeElement type : annotations) {
            for (final Element element : env.getElementsAnnotatedWith(type)) {
                if (shouldProcessType(element)) {
                    generate((TypeElement) element, processingEnv);
                }
            }
        }
        return true;
    }

    /**
     * Determine if the current element should be processed by the processor.
     *
     * @param element The current element.
     * @return <code>true</code> if this element should be processed, <code>false</code> otherwise.
     */
    private boolean shouldProcessType(Element element) {
        final Immutable immutable = element.getAnnotation(Immutable.class);
        if (immutable != null && immutable.generateBuilder()) {
            return true;
        }

        if (jcipAnnotationsOnType(element)) {
            return true;
        }

        return false;
    }

    /**
     * Determine if the current element is annotated with a JCiP annotation.
     *
     * @param element The current element.
     * @return <code>true</code> if this element is annotated with a JCiP Immutable annotation,
     *         <code>false</code> otherwise.
     */
    private boolean jcipAnnotationsOnType(Element element) {
        try {
            @SuppressWarnings({"unchecked"}) Class<? extends Annotation> jcipImmutableClass =
                    (Class<? extends Annotation>) Class.forName(JCIP_IMMUTABLE);

            return element.getAnnotation(jcipImmutableClass) != null;
        } catch (Exception e) {
            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.ERROR,
                    "JCiP annotations are not processed. Reason: " + e.getMessage());
            return false;
        }
    }

    /**
     * Generate code for the given element.
     *
     * @param element The element for which the code must be generated.
     * @param processingEnv The global processing environment.
     */
    private void generate(final TypeElement element, final ProcessingEnvironment processingEnv) {
        ClassMetaData classMetaData = classParser.readMetaData(element);

        if (classMetaData.isInterface()) {
            classWriter.generateImpl(classMetaData.getSuperClassInfo(), classMetaData.getProperties(), processingEnv);
            classWriter.generateBuilder(classMetaData.getSuperClassInfo(), classMetaData.getProperties(), processingEnv);
        } else {
            fancyFeaturesHelper.addMethod(new GeneratedMethod(), element);
        }
    }

}
