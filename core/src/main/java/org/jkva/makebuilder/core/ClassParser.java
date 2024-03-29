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

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;

/**
 * This class is responsible for reading all necessary information from a given Java class.
 *
 * $Author$
 * $Revision$
 */
public interface ClassParser {

    /**
     * Read the necessary meta data from the given type.
     *
     * @param typeElement The type to inspect.
     * @return The meta data for this type.
     */
    ClassMetaData readMetaData(TypeElement typeElement);
}
