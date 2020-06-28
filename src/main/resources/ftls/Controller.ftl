package ${BasePackageName}.${ControllerPackageName};

import ${BasePackageName}.${EntityPackageName}.${ClassName};
import ${BasePackageName}.${ServicePackageName}.${ServiceClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ControllerClassName} {
    @Autowired
    private ${ServiceClassName} ${ServiceEntityName};

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object list() {
        List<${ClassName}> ${EntityName}s = ${ServiceEntityName}.findAllList();
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${EntityName}s);
        result.put("status", 200);
        result.put("message", "OK");
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") String id) {
        ${ClassName} ${EntityName} = ${ServiceEntityName}.get(id);
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${EntityName});
        result.put("status", 200);
        result.put("message", "OK");
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object post(@RequestBody ${ClassName} ${EntityName}) {
        Map<String, Object> result = new HashMap<>();
        try {
            ${ServiceEntityName}.insert(${EntityName});
            result.put("status", 200);
            result.put("message", "OK");
            result.put("data", ${EntityName});
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
            result.put("message", "ERROR");
        }
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Object put(@RequestBody ${ClassName} ${EntityName}) {
        Map<String, Object> result = new HashMap<>();
        try {
            ${ServiceEntityName}.update(${EntityName});
            result.put("status", 200);
            result.put("message", "OK");
            result.put("data", ${EntityName});
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
            result.put("message", "ERROR");
        }
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Object delete(@RequestBody ${ClassName} ${EntityName}) {
        Map<String, Object> result = new HashMap<>();
        try {
            ${ServiceEntityName}.delete(${EntityName});
            result.put("status", 200);
            result.put("message", "OK");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
            result.put("message", "ERROR");
        }
        return result;
    }

}
