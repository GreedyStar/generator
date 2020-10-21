package ${Configuration.packageName}.${Configuration.path.interf};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
<#if Configuration.mybatisPlusEnable>
import com.baomidou.mybatisplus.extension.service.IService;
<#else>
import java.io.Serializable;
</#if>
import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
public interface ${InterfaceClassName} extends IService<${ClassName}> {

}
<#else><#-- mybatis或jpa模式 -->
public interface ${InterfaceClassName} {

    <#if Configuration.jpaEnable><#-- jpa模式 -->
    ${ClassName} get(Serializable id);

    List<${ClassName}> findAll();

    ${ClassName} insert(${ClassName} ${EntityName});

    List<${ClassName}> insertBatch(List<${ClassName}> ${EntityName}s);

    ${ClassName} update(${ClassName} ${EntityName});

    void delete(${ClassName} ${EntityName});

    <#else><#-- mybatis模式 -->
    ${ClassName} get(Serializable id);

    List<${ClassName}> findAll();

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int delete(${ClassName} ${EntityName});

    </#if>
}
</#if>
