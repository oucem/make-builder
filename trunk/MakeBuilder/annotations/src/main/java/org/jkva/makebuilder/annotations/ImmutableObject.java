package org.jkva.makebuilder.annotations;

/**
 * Defines the contract for immutable objects.
 * <p/>
 * An immutable object shoud be able to return a annotations object for itself.
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
