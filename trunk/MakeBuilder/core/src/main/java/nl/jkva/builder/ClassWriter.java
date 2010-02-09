package nl.jkva.builder;

import javax.annotation.processing.Filer;
import java.util.Map;

/**
 *
 */
public interface ClassWriter {
    void generateImpl(Map<String, Object> rootMap, Filer filer);

    void generateBuilder(Map<String, Object> rootMap, Filer filer);
}
