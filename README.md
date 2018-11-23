
# 欢迎来到 Generator

Generator 是一款基于数据库表生成相应Java代码的工具，代码模板使用当前主流Java框架： **Spring, SpringMVC, Mybatis** 组织，能够减少繁琐的重复性工作，让开发人员更专注于技术和性能，提高工作效率和编码热情。

你可以使用Generator：
> * 根据数据库业务表生成实体类
> * 生成包含简单的增、删、查、改操作的Mapper文件
> * 生成Controller、Service、Dao代码

### Release note：
* v1.0.0 框架基本功能，支持连接本地MySql数据库
* v1.0.1 修复默认模板文件中存在的错误
* v1.1.0 支持连接远程MySql、Oracle、SqlServer数据库(可配置)
* v1.1.1 添加数据表列名到实体属性名的映射规则，规则部分参考自《阿里巴巴Java开发手册》建表规约章节，例如：userName -> userName, CREATETIME -> createtime, CREATE_TIME -> createTime, create_time -> createTime，建议参照规约进行数据库表设计

### 具体请参考使用手册 : [Wiki-Generator](https://github.com/GreedyStar/generator/wiki)
