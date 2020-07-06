package ${configuration.packageName}.${configuration.path.entity};

<#if configuration.lombokEnable>
import lombok.Data;
</#if>
import java.io.Serializable;
import java.util.List;

/**
 * ${Remarks}
 *
 * @author ${configuration.author}
 * @date ${.now?date}
 */
<#if configuration.lombokEnable>
@Data
</#if>
public class ${ClassName} implements Serializable {
    private static final long serialVersionUID = 1L;
    ${Properties}

    public ${ClassName}() {
    }

    ${Methods}
}