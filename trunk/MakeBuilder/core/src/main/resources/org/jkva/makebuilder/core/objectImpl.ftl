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
package ${targetPackage};

@javax.annotation.Generated("${generatorClass}")
public final class ${implClassSimpleName} implements ${superClassQName} {

<#list properties as property>
private final ${property.type} ${property.name};

</#list>

public ${implClassSimpleName}(
<#list properties as property>
${property.type} ${property.name}<#if property_has_next>,</#if>
</#list>
) {
<#list properties as property>
this.${property.name} = ${property.name};
</#list>
}

public ${builderClassQName} builder() {
return new ${builderClassQName}(
<#list properties as property>
${property.name}<#if property_has_next>,</#if>
</#list>
);
}

<#list properties as property>
public ${property.type} ${property.getter}() {
return ${property.name};
}

</#list>
}
