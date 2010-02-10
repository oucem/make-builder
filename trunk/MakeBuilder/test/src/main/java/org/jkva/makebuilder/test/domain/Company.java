package org.jkva.makebuilder.test.domain;

import org.jkva.makebuilder.annotations.Immutable;
import org.jkva.makebuilder.annotations.ImmutableObject;
import org.jkva.makebuilder.annotations.ObjectBuilder;

import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Jan-Kees
 * Date: 1-nov-2009
 * Time: 15:04:41
 * To change this template use File | Settings | File Templates.
 */
@Immutable
public interface Company extends ImmutableObject<Company> {
    long getId();

    String getName();

    Address getAddress();

    Address getPostalAddress();

    Date getDateFounded();

    Set<Person> getEmployees();

    ObjectBuilder<Company> builder();
}
