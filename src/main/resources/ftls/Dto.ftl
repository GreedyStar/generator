package ${Configuration.packageName}.${Configuration.path.dto};

<#if Configuration.lombokEnable>
import lombok.Data;
</#if>
import java.io.Serializable;
<#if Configuration.swaggerEnable>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

/**
 * ${ClassName}${ClassType}DTO
 *
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.lombokEnable>
@Data
</#if>
<#if Configuration.swaggerEnable>
@ApiModel(value = "${ClassName}${ClassType}DTO")
</#if>
public class ${ClassName}${ClassType}DTO implements Serializable {

}