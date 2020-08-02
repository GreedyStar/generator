package ${Configuration.packageName}.${Configuration.path.service};

import ${Configuration.packageName}.${Configuration.path.dao}.${DaoClassName};
import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
${InterfaceImport}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
@Service
public class ${ServiceClassName} ${Implements} {
    @Autowired
    private ${DaoClassName} ${DaoEntityName};
    ${Override}
    public ${ClassName} get(Serializable id) {
        return ${DaoEntityName}.get(id);
    }
    ${Override}
    public List<${ClassName}> findList(${ClassName} ${EntityName}) {
        return ${DaoEntityName}.findList(${EntityName});
    }
    ${Override}
    public List<${ClassName}> findAllList() {
        return ${DaoEntityName}.findAllList();
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

}
