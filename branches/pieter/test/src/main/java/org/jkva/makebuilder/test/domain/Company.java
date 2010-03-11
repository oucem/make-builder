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

package org.jkva.makebuilder.test.domain;

import net.jcip.annotations.Immutable;

import java.util.Date;
import java.util.Set;

/**
 * A company object with some special types for testing.
 *
 * $Author$
 * $Revision$
 */
@Immutable
public interface Company {
    long getId();

    String getName();

    Address getAddress();

    Address getPostalAddress();

    Date getDateFounded();

    Set<Person> getEmployees();

}
