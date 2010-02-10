package org.jkva.makebuilder.test.domain;

import org.jkva.makebuilder.annotations.Immutable;
import org.jkva.makebuilder.annotations.ImmutableObject;
import org.jkva.makebuilder.annotations.ObjectBuilder;

/**
 * An address for testing.
 */
@Immutable
public interface Address extends ImmutableObject<Address>, SuperInterface {
    String getStreet();

    int getNumber();

    String getSuffix();

    String getCity();

    Country getCountry();

    ObjectBuilder<Address> builder();
}
