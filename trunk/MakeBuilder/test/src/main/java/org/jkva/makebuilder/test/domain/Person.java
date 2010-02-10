package org.jkva.makebuilder.test.domain;

import org.jkva.makebuilder.annotations.Immutable;
import org.jkva.makebuilder.annotations.ImmutableObject;
import org.jkva.makebuilder.annotations.ObjectBuilder;

import java.util.Date;

/**
 *
 */
@Immutable
public interface Person extends ImmutableObject<Person> {
    long getId();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Date getBirthDate();

    Address getAddress();

    Company getEmployer();

    ObjectBuilder<Person> builder();
}
