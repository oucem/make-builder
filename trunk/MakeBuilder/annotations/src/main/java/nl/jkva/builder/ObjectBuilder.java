package nl.jkva.builder;

/**
 * Created by IntelliJ IDEA.
 * User: Jan-Kees
 * Date: 1-nov-2009
 * Time: 15:04:06
 * To change this template use File | Settings | File Templates.
 */
public interface ObjectBuilder<T extends ImmutableObject> {
    public T build();
}
