package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
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