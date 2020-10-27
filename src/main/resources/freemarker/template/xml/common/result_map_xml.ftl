    <resultMap id="${mybatisXmlDefinition.resultMap.id}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        <#if colm.primaryKey>
        <id property="${colm.javaColumnName}" column="${colm.columnName}"/>
        <#else>
        <result property="${colm.javaColumnName}" column="${colm.columnName}"/>
        </#if>
    </#if>
</#list>
    </resultMap>
