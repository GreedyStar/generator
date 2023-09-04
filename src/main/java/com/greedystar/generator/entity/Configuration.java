package com.greedystar.generator.entity;

import java.io.Serializable;

/**
 * Generator 配置类
 *
 * @author GreedyStar
 * @since 2018/9/7
 */
public class Configuration implements Serializable {
    /**
     * 代码作者
     */
    private String author;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目实际生成路径
     */
    private String projectPath;

    /**
     * 项目版本
     */
    private String projectVersion;

    /**
     * 是否启用knif4j
     */
    private boolean knif4jEnable;

    /**
     * 顶级包名
     */
    private String packageName;
    /**
     * 类型转换器全限定类名
     */
    private String convertor;
    /**
     * 启用lombok
     */
    private boolean lombokEnable;
    /**
     * 是否将mybatis的xml映射文件放在源文件目录下
     */
    private boolean mapperUnderSource;
    /**
     * 启用swagger
     */
    private boolean swaggerEnable;
    /**
     * mybatis-plus模式
     */
    private boolean mybatisPlusEnable;
    /**
     * jpa模式
     */
    private boolean jpaEnable;
    /**
     * 文件覆盖
     */
    private boolean fileOverride;
    /**
     * id策略（auto：数据库自增，uuid：生成uuid）
     */
    private IdStrategy idStrategy;
    /**
     * 代码生成路径
     */
    private Path path;
    /**
     * 数据库配置
     */
    private Db db;
    /**
     * 代码文件后缀
     */
    private Name name;

    private ProjectInfo parent;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getConvertor() {
        return convertor;
    }

    public void setConvertor(String convertor) {
        this.convertor = convertor;
    }

    public boolean isLombokEnable() {
        return lombokEnable;
    }

    public void setLombokEnable(boolean lombokEnable) {
        this.lombokEnable = lombokEnable;
    }

    public boolean isMapperUnderSource() {
        return mapperUnderSource;
    }

    public void setMapperUnderSource(boolean mapperUnderSource) {
        this.mapperUnderSource = mapperUnderSource;
    }

    public boolean isSwaggerEnable() {
        return swaggerEnable;
    }

    public void setSwaggerEnable(boolean swaggerEnable) {
        this.swaggerEnable = swaggerEnable;
    }

    public boolean isMybatisPlusEnable() {
        return mybatisPlusEnable;
    }

    public void setMybatisPlusEnable(boolean mybatisPlusEnable) {
        this.mybatisPlusEnable = mybatisPlusEnable;
    }

    public boolean isJpaEnable() {
        return jpaEnable;
    }

    public void setJpaEnable(boolean jpaEnable) {
        this.jpaEnable = jpaEnable;
    }

    public boolean isFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public IdStrategy getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(IdStrategy idStrategy) {
        this.idStrategy = idStrategy;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public ProjectInfo getParent() {
        return parent;
    }

    public void setParent(ProjectInfo parent) {
        this.parent = parent;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public boolean isKnif4jEnable() {
        return knif4jEnable;
    }

    public void setKnif4jEnable(boolean knif4jEnable) {
        this.knif4jEnable = knif4jEnable;
    }

    public static class ProjectInfo{
        private String groupId;
        private String artifactId;
        private String version;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public void setArtifactId(String artifactId) {
            this.artifactId = artifactId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    /**
     * 数据库配置
     */
    public static class Db {
        /**
         * 数据库URL
         */
        private String url;
        /**
         * 数据库用户名
         */
        private String username;
        /**
         * 数据库密码
         */
        private String password;

        public Db() {
        }

        public Db(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * 代码路径配置
     */
    public static class Path {
        /**
         * Controller代码包路径
         */
        private String controller = "";
        /**
         * Service或ServiceImpl代码包路径
         */
        private String service = "";
        /**
         * Service接口代码包路径
         */
        private String interf = "";
        /**
         * Dao代码包路径
         */
        private String dao = "";
        /**
         * Entity代码包路径
         */
        private String entity = "";
        /**
         * Mapper映射文件路径
         */
        private String mapper = "";

        public Path() {
        }

        public Path(String controller, String service, String interf, String dao, String entity, String mapper) {
            this.controller = controller;
            this.service = service;
            this.interf = interf;
            this.dao = dao;
            this.entity = entity;
            this.mapper = mapper;
        }

        public String getController() {
            return controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getInterf() {
            return interf;
        }

        public void setInterf(String interf) {
            this.interf = interf;
        }

        public String getDao() {
            return dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

    /**
     * 类名配置
     */
    public static class Name {
        /**
         * Controller类的类名，默认为 $sController
         */
        private String controller = Constant.PLACEHOLDER + "Controller";
        /**
         * Service类或ServiceImpl类的类名，默认为$sService或$sServiceImpl
         */
        private String service = Constant.PLACEHOLDER + "Service";
        /**
         * Service接口类的类名，默认为$sService
         */
        private String interf = Constant.PLACEHOLDER + "Service";
        /**
         * Dao类的类名，默认为$sDao
         */
        private String dao = Constant.PLACEHOLDER + "Dao";
        /**
         * Entity类的类名，默认为$s
         */
        private String entity = Constant.PLACEHOLDER;
        /**
         * Mapper映射文件的文件名，默认$sMapper
         */
        private String mapper = Constant.PLACEHOLDER + "Mapper";

        public Name() {
        }

        public Name(String controller, String service, String dao, String entity, String mapper) {
            this.controller = controller;
            this.service = service;
            this.dao = dao;
            this.entity = entity;
            this.mapper = mapper;
        }

        public String getController() {
            return controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getInterf() {
            return interf;
        }

        public void setInterf(String interf) {
            this.interf = interf;
        }

        public String getDao() {
            return dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }
}
