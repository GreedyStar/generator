package ${Configuration.packageName}.${Configuration.path.vo};

<#if Configuration.lombokEnable>
import lombok.Data;
</#if>
<#if Configuration.swaggerEnable>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serializable;

/**
 * ${ClassName}${ClassType}VO
 *
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.lombokEnable>
@Data
</#if>
<#if Configuration.swaggerEnable>
@ApiModel(value = "${ClassName}${ClassType}VO")
</#if>
public class ${ClassName}${ClassType}VO implements Serializable {

}