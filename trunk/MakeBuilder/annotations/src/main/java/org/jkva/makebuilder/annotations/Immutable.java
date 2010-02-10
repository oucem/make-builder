package org.jkva.makebuilder.annotations;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jan-Kees
 * Date: 31-okt-2009
 * Time: 20:05:18
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.TYPE)
@Documented
@Inherited
public @interface Immutable {
    boolean generateBuilder() default true;
}
