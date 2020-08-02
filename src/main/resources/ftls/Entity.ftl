package ${Configuration.packageName}.${Configuration.path.entity};

<#if Configuration.lombokEnable>
import lombok.Data;
</#if>
<#if Configuration.swaggerEnable>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ${Remarks}
 *
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.lombokEnable>
@Data
</#if>
<#if Configuration.swaggerEnable>
@ApiModel(value = "${Remarks}")
</#if>
public class ${ClassName} implements Serializable {
    private static final long serialVersionUID = 1L;
    ${Properties}

    public ${ClassName}() {
    }

    ${Methods}
}