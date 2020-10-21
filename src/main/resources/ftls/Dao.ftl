package ${Configuration.packageName}.${Configuration.path.dao};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
<#if Configuration.mybatisPlusEnable>
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
<#elseif Configuration.jpaEnable>
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<#else>
import org.apache.ibatis.annotations.Mapper;
</#if>

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.mybatisPlusEnable><#-- mybatis-plus模式 -->
@Mapper
public interface ${DaoClassName} extends BaseMapper<${ClassName}> {

}
<#elseif Configuration.jpaEnable><#-- jpa模式 -->
@Repository
public interface ${DaoClassName} extends JpaRepository<${ClassName}, Serializable> {

}
<#else><#-- mybatis模式 -->
@Mapper
public interface ${DaoClassName} {

    ${ClassName} get(Serializable id);

    List<${ClassName}> findAll();

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int delete(${ClassName} ${EntityName});

}
</#if>