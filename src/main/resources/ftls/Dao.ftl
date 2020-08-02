package ${Configuration.packageName}.${Configuration.path.dao};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
@Mapper
public interface ${DaoClassName} {

    ${ClassName} get(Serializable id);

    List<${ClassName}> findList(${ClassName} ${EntityName});

    List<${ClassName}> findAllList();

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int delete(${ClassName} ${EntityName});

}