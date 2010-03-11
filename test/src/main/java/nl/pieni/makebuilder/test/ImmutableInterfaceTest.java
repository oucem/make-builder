package nl.pieni.makebuilder.test;

import net.jcip.annotations.Immutable;
import org.jkva.makebuilder.annotations.Required;


/**
 * Created by IntelliJ IDEA.
 * User: pieter
 * Date: 3-mrt-2010
 * Time: 22:16:00
 * To change this template use File | Settings | File Templates.
 */
@Immutable
public interface ImmutableInterfaceTest {
    /**
     * This value represents the ID of the thinggy
     * @return
     */
    @Required
    long getId();

    String getName();

}
