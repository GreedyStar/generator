
# 欢迎来到 Generator

Generator 是一款基于 **Spring, SpringMVC, Mybatis and MySql** 架构的Java代码生成工具.  

你可以使用Generator：
> * 根据数据库业务表生成实体类
> * 生成包含简单的增、删、查、改操作的Mapper文件
> * 生成Controller、Service、Dao代码

  
# Getting Start  

## 添加依赖

    <dependency>
	    <groupId>com.greedystar</groupId>
	    <artifactId>generator</artifactId>
	    <version>1.0.0</version>
	</dependency>

  
## 配置框架
  
### 定义模板文件
  
模板文件是代码生成的基础，框架提供了默认的模板文件，位于 src/main/resources/ftls/ 目录下。用户可根据项目的具体架构自定义模板文件，具体请请参考默认的 ftl 文件。

####  注意：若需要自定义模板，则需在项目目录的 resources/ftls/ 目录下放置框架所需的所有模板文件，且保证模板文件名与默认一致。其中，Mapper.ftl 用于单表关系和一对多关系，Mapper_M2M.ftl为多对多关系。
#### 示例如下 ：
> resources  
>> ftls  
>>> Controller.ftl<br>  
>>> Service.ftl<br>  
>>> Dao.ftl<br>  
>>> Entity.ftl<br>  
>>> Mapper.ftl<br>  
>>> Mapper_M2M.ftl<br>  
  
  
### 配置公共参数

用户可在 resources 目录下创建 generator.yaml 文件，配置代码生成器的公共参数 ，示例如下：

    author: GreedyStar // 代码作者
    packageName: com.greedystar.test // 基础包名
    path: // 各模块包名（路径）
	    controller: controller
	    service: service
	    dao: dao
	    entity: entity
	    mapper: mappers

上述配置将会生成如下路径的代码：

    com.greedystar.test
        controller
            UserController.java 
        service
            UserService.java 
        dao
            UserDao.java 
        entity
            User.java 
    mappers/XXXMapper.xml  

框架为用户提供了默认的配置文件，如下：

    author: unknown
    packageName: 
    path: 
	    controller: controller
	    service: service
	    dao: dao
	    entity: entity
	    mapper: mappers

为了保证在正确路径下生成代码，建议用户新建配置文件，覆盖框架的默认配置

## 开始生成代码 

示例使用的数据库业务表如下所示：

![image](https://github.com/GreedyStar/generator/raw/master/screenshots/tables.png)  
  
### 单表关系
```  
Invoker invoker = new SingleInvoker.Builder()  
	.setDatabase("generator-demo")
	.setUsername("root")
	.setPassword(null) // 可空
	.setTableName("user")
	.setClassName("User") // 可空. 若空则自动根据表明生成类名，如: user->User，sys_role->SysRole  
	.build();
invoker.execute();  
```  
### 一对多关系
```  
Invoker invoker = new One2ManyInvoker.Builder()  
	.setDatabase("generator-demo")
	.setUsername("root")
	.setPassword(null) // 可空
	.setTableName("user")
	.setClassName("User") // 可空
	.setParentTableName("office")
	.setParentClassName("Office") // 可空
	.setForeignKey("officeId")
	.build();
invoker.execute();  
```  
### 多对多关系 
```  
Invoker invoker = new Many2ManyInvoker.Builder()  
	.setDatabase("generator-demo") 
	.setUsername("root") 
	.setPassword(null) // 可空 
	.setTableName("user") 
	.setClassName("User") // 可空 
	.setParentTableName("role") 
	.setParentClassName("Role") // 可空
	.setRelationTableName("user_role") 
	.setForeignKey("userId") 
	.setParentForeignKey("roleId") 
	.build();
invoker.execute();  
```  
# 参数描述
  
## Invoker 参数
![image](https://github.com/GreedyStar/generator/raw/master/screenshots/invoker_parameters.jpg)  
## 模板文件参数
![image](https://github.com/GreedyStar/generator/raw/master/screenshots/template_parameters.jpg)