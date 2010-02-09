package nl.jkva.domain;

import nl.jkva.builder.Immutable;
import nl.jkva.builder.ImmutableObject;
import nl.jkva.builder.ObjectBuilder;

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
