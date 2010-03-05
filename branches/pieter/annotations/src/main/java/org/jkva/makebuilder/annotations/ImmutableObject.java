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

package org.jkva.makebuilder.annotations;

/**
 * Defines the contract for immutable objects.
 * <p/>
 * An immutable object shoud be able to return a annotations object for itself.
 *
 * $Author$
 * $Revision$
 * @deprecated
 */
public interface ImmutableObject<OBJECT extends ImmutableObject> {
    /**
     * Return an initialized annotations instance for this object.
     * <p/>
     * Initialized means that the properties in the annotations are populated according the data in the object.
     *
     * @return The annotations instance.
     */
    public ObjectBuilder<OBJECT> builder();
}
