package ${javaClassDefinition.POJO.fullPackage};

<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * ${tableInfo.tableComment}
 *
 * @author  ${classCommentInfo.author}
 * @version ${classCommentInfo.version}
 * @date    ${classCommentInfo.createTime}
 */
public class ${javaClassDefinition.POJO.className} {

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

<#list tableInfo.columnLists as colm>
	<#if colm??>
	public ${colm.javaDataType} get${colm.javaColumnName?cap_first}() {
		return ${colm.javaColumnName};
	}

	public void set${colm.javaColumnName?cap_first}(${colm.javaDataType} ${colm.javaColumnName}) {
		this.${colm.javaColumnName} = ${colm.javaColumnName};
	}
	
	</#if>
</#list>
}