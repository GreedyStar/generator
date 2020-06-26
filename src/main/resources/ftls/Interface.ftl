package ${BasePackageName}${InterfacePackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};

import java.io.Serializable;
import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
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
