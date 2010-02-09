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
