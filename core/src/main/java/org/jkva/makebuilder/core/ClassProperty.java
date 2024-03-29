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

/**
 * Holder for property metadata.
 */
public final class ClassProperty {
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

    /**
     * Indication for required properties.
     * These properties are identified by the
     * {@link org.jkva.makebuilder.annotations.Required} @Required annotation.
     */
    boolean required;

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

    public boolean isRequired() {
        return required;
    }
    // ------------------ Used by FreeMarker END ------------------
}
