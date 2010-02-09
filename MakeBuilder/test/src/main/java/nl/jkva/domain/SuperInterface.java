package nl.jkva.domain;

import nl.jkva.builder.Immutable;

/**
 * This super interface is used to test type hierarchies.
 */
@Immutable(generateBuilder = false)
public interface SuperInterface {
    String getSomething();
}
