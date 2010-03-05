package org.jkva.makebuilder.test.domain;

import net.jcip.annotations.Immutable;
import org.jkva.makebuilder.annotations.ImmutableObject;
import org.joda.time.DateTime;

/**
 * Example type that uses the JCiP annotation: "Immutable" to indicate immutability.
 * The process may generate a builder and implementation for this type.
 */
@Immutable
public interface JCiPExample extends ImmutableObject<JCiPExample> {
    String getMessage();
    boolean isActive();
    DateTime getTime();
}
