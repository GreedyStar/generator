
# 欢迎来到 Generator

Generator 是一款基于数据库表生成Java代码的工具，代码模板使用当前主流Java框架： **Spring, SpringMVC, Mybatis（Mybatis-Plus、JPA）** 组织，能够减少繁琐的重复性工作，让开发人员更专注于技术和性能，提高工作效率和编码热情。

你可以使用Generator：
> * 根据数据库业务表生成实体类
> * 生成包含简单的增、删、查、改操作的Mapper文件
> * 生成Controller、Service、Dao代码

### Release note：
* v1.0.0 框架基本功能，支持连接本地MySql数据库
* v1.0.1 修复默认模板文件中存在的错误
* v1.1.0 支持连接远程MySql、Oracle、SqlServer数据库(可配置)
* v1.2.0 添加列名到属性名的映射规则；代码生成任务多线程；可配置生成策略等
* v1.2.1 Service层添加接口和实现类的支持
* v1.3.0 添加实体的类注释和属性注释，自动创建代码生成目录，可自定义类型转换器，解决生成文件冲突
* v1.4.0 单表模式支持JPA和Mybatis-Plus，可自定义类名和文件名，支持Lombok和Swagger，添加代码配置方式，支持将MyBatis映射文件放于source目录下，调整代码生成策略（支持单表、一对多、多对一、多对多），可配置主键生成策略（数据库自增、UUID），可配置文件覆盖策略
* v1.4.1 修复配置DO实体类别名时文件名错误的问题

详细 Release note ：[GreedyStar/generator/Releases](https://github.com/GreedyStar/generator/releases)

### 具体请参考使用手册 : [Wiki-Generator](https://github.com/GreedyStar/generator/wiki)

## 致谢

感谢以下人员对本仓库提出的建议，我们期待本工具能够为更多的开发者提供便利。

<a href="https://github.com/yangmingliang">
    <img src="https://avatars3.githubusercontent.com/u/49601147?s=460&v=4" width="50px">
</a> 
<a href="https://github.com/wnjustdoit">
    <img src="https://avatars1.githubusercontent.com/u/16381569?s=460&u=912742a41633eeb20ade27e1e44cb391ceafb10b&v=4" width="50px">
</a> 
<a href="https://github.com/Wangzx2046">
    <img src="https://avatars3.githubusercontent.com/u/48973003?s=460&u=f0ac785acc3761959916612ede3901ce9e87288a&v=4" width="50px">
</a> 
<a href="https://github.com/Kirehdhjm">
    <img src="https://avatars1.githubusercontent.com/u/43332135?s=460&u=15e5569cf4f5bfd316e9f3326f051794cbd6e78e&v=4" width="50px">
</a> 

