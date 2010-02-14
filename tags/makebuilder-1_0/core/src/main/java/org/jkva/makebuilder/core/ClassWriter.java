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

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import java.util.Map;

/**
 * Interface for class generation.
 *
 * $Author$
 * $Revision$
 */
public interface ClassWriter {

    /**
     * Generate a valid implementation Java source file for the given immutable type.
     *
     * @param superClassInfo The superclass metadata.
     * @param properties The properties that needs getters and setters.
     * @param processingEnv The current processing environment.
     */
    void generateImpl(SuperClassInfo superClassInfo, ClassProperty[] properties, ProcessingEnvironment processingEnv);

    /**
     * Generate a valid implementation Java source file for the given immutable type.
     *
     * @param superClassInfo The superclass metadata.
     * @param properties The properties that needs getters and setters.
     * @param processingEnv The current processing environment.
     */
    void generateBuilder(SuperClassInfo superClassInfo, ClassProperty[] properties, ProcessingEnvironment processingEnv);
}
