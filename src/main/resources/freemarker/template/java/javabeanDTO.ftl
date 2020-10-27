package ${javaClassDefinition.POJO.fullPackage};

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
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
@Getter
@Setter
public class ${javaClassDefinition.DTO.className} implements Serializable {

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>
}