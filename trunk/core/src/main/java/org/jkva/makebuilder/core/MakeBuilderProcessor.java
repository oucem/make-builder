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
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
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
@SupportedAnnotationTypes("org.jkva.makebuilder.annotations.Immutable")
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
     * Default constructor, used in production.
     */
    public MakeBuilderProcessor() {
        classParser = new DefaultClassParserImpl();
        classWriter = new FreeMarkerClassWriterImpl();
    }

    /**
     * Only used in test.
     *
     * @param classParser The user specified ClassParser.
     * @param classWriter The user specified ClassWriter.
     */
    public MakeBuilderProcessor(ClassParser classParser, ClassWriter classWriter) {
        this.classParser = classParser;
        this.classWriter = classWriter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment env) {
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
     * Generate code for the given element.
     *
     * @param element The element for which the code must be generated.
     * @param processingEnv The global processing environment.
     */
    private void generate(final TypeElement element, final ProcessingEnvironment processingEnv) {
        ClassMetaData classMetaData = classParser.readMetaData(element);

        classWriter.generateImpl(classMetaData.getSuperClassInfo(), classMetaData.getProperties(), processingEnv);
        classWriter.generateBuilder(classMetaData.getSuperClassInfo(), classMetaData.getProperties(), processingEnv);
    }

}
