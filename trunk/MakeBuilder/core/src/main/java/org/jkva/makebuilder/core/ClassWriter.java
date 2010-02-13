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
     * @param rootMap The attributes used when generating the source file.
     * @param filer The filer object that writes the file to the file system.
     */
    void generateImpl(Map<String, Object> rootMap, Filer filer);

    /**
     * Generate a valid implementation Java source file for the given immutable type.
     *
     * @param rootMap The attributes used when generating the source file.
     * @param filer The filer object that writes the file to the file system.
     */
    void generateBuilder(Map<String, Object> rootMap, Filer filer);
}
