package ${Configuration.packageName}.${Configuration.path.service};

import ${Configuration.packageName}.${Configuration.path.dao}.${DaoClassName};
import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
${InterfaceImport}
<#if Configuration.mybatisPlusEnable>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<#else>
import java.io.Serializable;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
@Service
<#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
public class ${ServiceClassName} extends ServiceImpl<${DaoClassName}, ${ClassName}> ${Implements} {

}
<#else><#-- mybatis或jpa模式 -->
public class ${ServiceClassName} ${Implements} {
    @Autowired
    private ${DaoClassName} ${DaoEntityName};
    <#if Configuration.jpaEnable><#-- jpa模式 -->
    ${Override}
    public ${ClassName} get(Serializable id) {
        return ${DaoEntityName}.findById(id).orElse(null);
    }
    ${Override}
    public List<${ClassName}> findAll() {
        return ${DaoEntityName}.findAll();
    }
    ${Override}
    public ${ClassName} insert(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.save(${EntityName});
    }
    ${Override}
    public List<${ClassName}> insertBatch(List<${ClassName}> ${EntityName}s){
        return ${DaoEntityName}.saveAll(${EntityName}s);
    }
    ${Override}
    public ${ClassName} update(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.save(${EntityName});
    }
    ${Override}
    public void delete(${ClassName} ${EntityName}) {
        ${DaoEntityName}.delete(${EntityName});
    }
    <#else><#-- mybatis模式 -->
    ${Override}
    public ${ClassName} get(Serializable id) {
        return ${DaoEntityName}.get(id);
    }
    ${Override}
    public List<${ClassName}> findAll() {
        return ${DaoEntityName}.findAll();
    }
    ${Override}
    public int insert(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.insert(${EntityName});
    }
    ${Override}
    public int insertBatch(List<${ClassName}> ${EntityName}s) {
        return ${DaoEntityName}.insertBatch(${EntityName}s);
    }
    ${Override}
    public int update(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.update(${EntityName});
    }
    ${Override}
    public int delete(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.delete(${EntityName});
    }
    </#if>
}
</#if>