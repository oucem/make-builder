package org.jkva.makebuilder.test.domain;

import org.jkva.makebuilder.annotations.Immutable;

/**
 * This super interface is used to test type hierarchies.
 */
@Immutable(generateBuilder = false)
public interface SuperInterface {
    String getSomething();
}
