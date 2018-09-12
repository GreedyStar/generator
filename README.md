# Welcome to the code generator

Generator is a Java code generating tool based on **Spring, SpringMVC, Mybatis and MySql**.

You may use Generator:

> * Generate entity classes based on database tables.
> * Generate Mapper files that contains the insert, delete, select and update operations.
> * Generate Dao, Service, Controller codes.

# Getting Start

## Configure the framework

### Define template files

The template files are the basis for code generation and need to be placed in the resources/ftls/ directory. 

The template files can be customized according to the specific schema of the project. For the custom template files, please refer to xxx.ftl files that under the resources/ftls directory.

#### Note : 
Mapper.ftl is used for the single-table relationship and one-to-many relationship, and Mapper_M2M.ftl is used for the many-to-many relationship.

If you need to customize the template files, you need to place all the template files required by the framework in the resources/ftls/ directory, and make sure that the template files' name is consistent with the default.

#### The example is as follows:
> resources
>> ftls
>>> Controller.ftl<br>
>>> Service.ftl<br>
>>> Dao.ftl<br>
>>> Entity.ftl<br>
>>> Mapper.ftl<br>
>>> Mapper_M2M.ftl<br>


### Configure global parameters
You can create the generator.yaml file under the resources directory to configure global parameters, otherwise the framework will use the default configuration, example as follows:

    author: Tom
    packageName: com.tom
    path:
        controller: controller
        service: service
        dao: dao
        entity: entity
        mapper: mappers
    

The above configuration generates the code for the following package path:

    com.tom.controller.XXXController.java
    ...
    mappers/XXXMapper.xml

## Start generating code
The database tables used in the example are as follows:
![image](https://github.com/GreedyStar/generator/raw/master/screenshots/tables.png)

### single-table relationship
```
Invoker invoker = new SingleInvoker.Builder()
        .setDatabase("generator-demo")
        .setUsername("root")
        .setPassword(null) // Nullable
        .setTableName("user")
        .setClassName("User") // Nullable. If it is empty, it is automatically generated based on the table name,for example: user->User，sys_role->SysRole
        .build();
invoker.execute();
```
### one-to-many relationship
```
Invoker invoker = new One2ManyInvoker.Builder()
        .setDatabase("generator-demo")
        .setUsername("root")
        .setPassword(null) // Nullable
        .setTableName("user")
        .setClassName("User") // Nullable
        .setParentTableName("office")
        .setParentClassName("Office") // Nullable
        .setForeignKey("officeId")
        .build();
invoker.execute();
```
### many-to-many relationship
```
Invoker invoker = new Many2ManyInvoker.Builder()
        .setDatabase("generator-demo")
        .setUsername("root")
        .setPassword(null) // Nullable
        .setTableName("user")
        .setClassName("User") // Nullable
        .setParentTableName("role")
        .setParentClassName("Role") // Nullable
        .setRelationTableName("user_role")
        .setForeignKey("userId")
        .setParentForeignKey("roleId")
        .build();
invoker.execute();
```
# Parameters to describe

## Invoker Parameters
![image](https://github.com/GreedyStar/generator/raw/master/screenshots/invoker_parameters.jpg)
## Template file parameters
![image](https://github.com/GreedyStar/generator/raw/master/screenshots/template_parameters.png)