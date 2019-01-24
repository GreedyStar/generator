package ${BasePackageName}${ServicePackageName};

import ${BasePackageName}${DaoPackageName}.${ClassName}Dao;
import ${BasePackageName}${EntityPackageName}.${ClassName};
${InterfaceImport}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Service
public class ${ClassName}Service${Impl} {
    @Autowired
    private ${ClassName}Dao ${EntityName}Dao;
    ${Override}
    public ${ClassName} get(String id){
        return ${EntityName}Dao.get(id);
    }
    ${Override}
    public List<${ClassName}> findList(${ClassName} ${EntityName}) {
        return ${EntityName}Dao.findList(${EntityName});
    }
    ${Override}
    public List<${ClassName}> findAllList() {
        return ${EntityName}Dao.findAllList();
    }
    ${Override}
    public int insert(${ClassName} ${EntityName}) {
        return ${EntityName}Dao.insert(${EntityName});
    }
    ${Override}
    public int insertBatch(List<${ClassName}> ${EntityName}s){
        return ${EntityName}Dao.insertBatch(${EntityName}s);
    }
    ${Override}
    public int update(${ClassName} ${EntityName}) {
        return ${EntityName}Dao.update(${EntityName});
    }
    ${Override}
    public int delete(${ClassName} ${EntityName}) {
        return ${EntityName}Dao.delete(${EntityName});
    }

}
