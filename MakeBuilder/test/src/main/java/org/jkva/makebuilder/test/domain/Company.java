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

import org.jkva.makebuilder.annotations.Immutable;
import org.jkva.makebuilder.annotations.ImmutableObject;
import org.jkva.makebuilder.annotations.ObjectBuilder;

import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Jan-Kees
 * Date: 1-nov-2009
 * Time: 15:04:41
 * To change this template use File | Settings | File Templates.
 */
@Immutable
public interface Company extends ImmutableObject<Company> {
    long getId();

    String getName();

    Address getAddress();

    Address getPostalAddress();

    Date getDateFounded();

    Set<Person> getEmployees();

    ObjectBuilder<Company> builder();
}
