package nl.jkva.builder;

/**
 * Defines the contract for immutable objects.
 * <p/>
 * An immutable object shoud be able to return a builder object for itself.
 */
public interface ImmutableObject<OBJECT extends ImmutableObject> {
    /**
     * Return an initialized builder instance for this object.
     * <p/>
     * Initialized means that the properties in the builder are populated according the data in the object.
     *
     * @return The builder instance.
     */
    public ObjectBuilder<OBJECT> builder();
}
