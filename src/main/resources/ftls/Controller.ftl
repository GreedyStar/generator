package ${Configuration.packageName}.${Configuration.path.controller};

import ${Configuration.packageName}.${Configuration.path.entity}.${ClassName};
${ServiceImport}
<#if Configuration.swaggerEnable>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ${Configuration.author}
 * @date ${.now?date}
 */
<#if Configuration.swaggerEnable>
@Api(value = "/${EntityName}", tags = "${ClassName}管理接口")
</#if>
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ControllerClassName} {
    @Autowired
    private ${ServiceClassName} ${ServiceEntityName};

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "查询${ClassName}列表", httpMethod = "GET")
    </#if>
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object list() {
        List<${ClassName}> ${EntityName}s = ${ServiceEntityName}.findAllList();
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${EntityName}s);
        result.put("status", 200);
        result.put("message", "OK");
        return result;
    }

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "查看${ClassName}详情", httpMethod = "GET")
    </#if>
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable("id") String id) {
        ${ClassName} ${EntityName} = ${ServiceEntityName}.get(id);
        Map<String, Object> result = new HashMap<>();
        result.put("data", ${EntityName});
        result.put("status", 200);
        result.put("message", "OK");
        return result;
    }

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "创建${ClassName}", httpMethod = "POST")
    </#if>
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

    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "修改${ClassName}信息", httpMethod = "PUT")
    </#if>
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


    <#if Configuration.swaggerEnable>
    @ApiOperation(value = "删除${ClassName}", httpMethod = "DELETE")
    </#if>
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
