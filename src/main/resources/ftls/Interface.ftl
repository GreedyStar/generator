package ${BasePackageName}${InterfacePackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
public interface ${ClassName}Service {

    public ${ClassName} get(String id);

    public List<${ClassName}> findList(${ClassName} ${EntityName});

    public List<${ClassName}> findAllList();

    public int insert(${ClassName} ${EntityName});

    public int insertBatch(List<${ClassName}> ${EntityName}s);

    public int update(${ClassName} ${EntityName});

    public int delete(${ClassName} ${EntityName});

}
