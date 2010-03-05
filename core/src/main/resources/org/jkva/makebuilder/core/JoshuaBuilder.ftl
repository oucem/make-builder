<#--
  ~ Copyright (C) 2010 Jan-Kees van Andel.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  -->
/*
 * Copyright (C) 2010 Jan-Kees van Andel/Pieter van der Meer
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
package ${targetPackage};

@javax.annotation.Generated("${generatorClass}")
/**
 * {@inheritDoc}
**/
public final class ${implClassSimpleName} implements ${superClassQName} {

    <#list properties as property>
    /* Property ${property.name} */
    private ${property.type} ${property.name};
    </#list>

    /**
     * Classic implementation of the Joshua Bloch
     * Builder pattern
    **/
    public static class Builder {
        <#list properties as property>
        private ${property.type} ${property.name};
        </#list>

        /**
         * Empty default constructor
        **/
        public Builder() {
            // Intentionally left empty
        }

        public Builder(
            <#list properties as property>
            ${property.type} ${property.name}<#if property_has_next>,</#if>
            </#list>
            ) {
                <#list properties as property>
                this.${property.name} = ${property.name};
                </#list>
            }

            /**
             * The Builder, build the immutable instance
            **/
            public ${superClassQName} build() {
                return new ${implClassSimpleName}(this);
            }

            <#list properties as property>
            /**
             * See {@link  ${superClassQName}#${property.getter}()  ${property.getter}} documentation of the field.
             *  @param ${property.name} Value to set
            **/
            public Builder ${property.name}(${property.type} ${property.name}) {
                this.${property.name} = ${property.name};
                return this;
            }
            </#list>
    }

    private ${implClassSimpleName}(Builder builder){
        <#list properties as property>
            this.${property.name} = builder.${property.name};
        </#list>
    }


    <#list properties as property>
    /**
     * {@inheritDoc}
    **/
    public ${property.type} ${property.getter}() {
        return ${property.name};
    }
    
    </#list>
}