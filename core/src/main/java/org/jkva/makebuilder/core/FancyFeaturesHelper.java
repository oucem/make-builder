package org.jkva.makebuilder.core;

import javax.lang.model.element.TypeElement;

/**
 * Support for extra, not standardized, compiler features.
 */
public interface FancyFeaturesHelper {
    void addMethod(GeneratedMethod method, TypeElement typeElement);
}
