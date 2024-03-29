/*
 * Copyright (C) 2010 Jan-Kees van Andel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jkva.makebuilder.core;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * FreeMarker implementation of the class writer.
 *
 * $Author$
 * $Revision$
 */
public class FreeMarkerClassWriterImpl implements ClassWriter {

    private Configuration freeMarkerConfiguration;

    private Map<String, Object> createRootMap(SuperClassInfo superClassInfo, ClassProperty[] properties, ProcessingEnvironment processingEnv) {
        Map<String, Object> root = new HashMap<String, Object>();
        String superClassQName = superClassInfo.qualifiedName;

        String superClassSimpleName = superClassQName.substring(superClassQName.lastIndexOf('.') + 1);
        String targetPackage = superClassQName.substring(0, superClassQName.length() - superClassSimpleName.length() - 1);

        int idx = superClassQName.lastIndexOf('.') + 1;
        String implClassQName = targetPackage + '.' + superClassQName.substring(idx) + "Impl";
        String implClassSimpleName = implClassQName.substring(implClassQName.lastIndexOf('.') + 1);
        String builderClassQName = targetPackage + '.' + superClassQName.substring(idx) + "BuilderImpl";
        String builderClassSimpleName = builderClassQName.substring(builderClassQName.lastIndexOf('.') + 1);

        root.put("targetPackage", targetPackage);
        root.put("superClassQName", superClassQName);
        root.put("superClassSimpleName", superClassSimpleName);
        root.put("implClassQName", implClassQName);
        root.put("implClassSimpleName", implClassSimpleName);
        root.put("builderClassQName", builderClassQName);
        root.put("builderClassSimpleName", builderClassSimpleName);

        List<ClassProperty> required = new ArrayList<ClassProperty>();
        List<ClassProperty> optional = new ArrayList<ClassProperty>();
        for(ClassProperty property : properties) {
            if (property.isRequired()) {
                required.add(property);
            } else {
                optional.add(property);
            }
        }
        root.put("required", required.toArray(new ClassProperty[]{}));
        root.put("optional", optional.toArray(new ClassProperty[]{}));
        root.put("generatorClass", MakeBuilderProcessor.class);

        return root;
    }

    @Override
    public void generateBuilder(ClassMetaData metaData, ProcessingEnvironment processingEnv) {
        Map<String, Object> rootMap = createRootMap(metaData.getSuperClassInfo(), metaData.getProperties(), processingEnv);

        Writer writer = null;
        try {
            initializeFreeMarker();

            String classQName = (String) rootMap.get("implClassQName");
            Template template = freeMarkerConfiguration.getTemplate("JoshuaBuilder.ftl");
            StringWriter strWtr = new StringWriter();
            template.process(rootMap, strWtr);

            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(classQName);
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
    private void initializeFreeMarker() {
        if (freeMarkerConfiguration == null) {
            freeMarkerConfiguration = new Configuration();
            freeMarkerConfiguration.setClassForTemplateLoading(MakeBuilderProcessor.class, "");
            freeMarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
        }
    }

}
