package ${targetPackage};

@javax.annotation.Generated("${generatorClass}")
public final class ${builderClassSimpleName} implements nl.jkva.builder.ObjectBuilder<${superClassQName}> {

<#list properties as property>
private ${property.type} ${property.name};

</#list>

<#if properties?size != 0>
public ${builderClassSimpleName}() { }
</#if>

public ${builderClassSimpleName}(
<#list properties as property>
${property.type} ${property.name}<#if property_has_next>,</#if>
</#list>
) {
<#list properties as property>
this.${property.name} = ${property.name};
</#list>
}

public ${superClassQName} build() {
return new ${implClassQName}(
<#list properties as property>
this.${property.name}<#if property_has_next>,</#if>
</#list>
);
}

<#list properties as property>
public ${builderClassSimpleName} ${property.setter}(${property.type} ${property.name}) {
this.${property.name} = ${property.name};
return this;
}

</#list>
}
