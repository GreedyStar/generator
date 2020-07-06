package ${configuration.packageName}.${configuration.path.interf};

import ${configuration.packageName}.${configuration.path.entity}.${ClassName};

import java.io.Serializable;
import java.util.List;

/**
 * @author ${configuration.author}
 * @date ${.now?date}
 */
public interface ${InterfaceClassName} {

    ${ClassName} get(Serializable id);

    List<${ClassName}> findList(${ClassName} ${EntityName});

    List<${ClassName}> findAllList();

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int delete(${ClassName} ${EntityName});

}
