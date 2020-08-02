package ${Configuration.packageName}.${Configuration.path.interf};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
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
