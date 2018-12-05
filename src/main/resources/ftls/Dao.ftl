package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Author ${Author}
* Date  ${Date}
*/
@Mapper
public interface ${ClassName}Dao {

    public ${ClassName} get(String id);

    public List<${ClassName}> findList(${ClassName} ${EntityName});

    public List<${ClassName}> findAllList();

    public int insert(${ClassName} ${EntityName});

    public int insertBatch(List<${ClassName}> ${EntityName}s);

    public int update(${ClassName} ${EntityName});

    public int delete(${ClassName} ${EntityName});

}