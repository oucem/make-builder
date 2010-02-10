package org.jkva.makebuilder.core;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * FreeMarker implementation of the class writer.
 */
public class FreeMarkerClassWriterImpl implements ClassWriter {

    private Configuration freemarkerConfiguration;

    /**
     * Generate implementation for this immutable type.
     *
     * @param filer The Filer used to generate the sources.
     * @param propertyMap The map with properties used to generate the class.
     */
    @Override
    public void generateImpl(Map<String, Object> propertyMap, Filer filer) {
        Writer writer = null;
        try {
            initializeFreemarker();

            String classQName = (String) propertyMap.get("implClassQName");
//            String classSimpleName = propertyMap.implClassSimpleName;

            Template template = freemarkerConfiguration.getTemplate("objectImpl.ftl");
            StringWriter strWtr = new StringWriter();
            template.process(propertyMap, strWtr);

            JavaFileObject sourceFile = filer.createSourceFile(classQName);
            writer = sourceFile.openWriter();
            writer.write(strWtr.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading template", e);
        } catch (TemplateException e) {
            throw new RuntimeException("Error processing template", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Generate annotations class for this immutable type.
     *
     * @param filer The Filer used to generate the sources.
     * @param propertyMap The map with properties used to generate the class.
     */
    @Override
    public void generateBuilder(Map<String, Object> propertyMap, Filer filer) {
        Writer writer = null;
        try {
            initializeFreemarker();

            String classQName = (String) propertyMap.get("builderClassQName");

            Template template = freemarkerConfiguration.getTemplate("builder.ftl");
            StringWriter strWtr = new StringWriter();
            template.process(propertyMap, strWtr);

            JavaFileObject sourceFile = filer.createSourceFile(classQName);
            writer = sourceFile.openWriter();
            writer.write(strWtr.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading template", e);
        } catch (TemplateException e) {
            throw new RuntimeException("Error processing template", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create a FreeMarker configuration.
     */
    private void initializeFreemarker() {
        if (freemarkerConfiguration == null) {
            freemarkerConfiguration = new Configuration();
            freemarkerConfiguration.setClassForTemplateLoading(BuilderGeneratorProcessor.class, "");
            freemarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
        }
    }

}
