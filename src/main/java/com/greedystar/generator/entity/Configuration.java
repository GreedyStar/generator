package com.greedystar.generator.entity;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class Configuration implements Serializable {
    /**
     * 代码作者
     */
    private String author;
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
    private Suffix suffix;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName + ".";
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

    public Suffix getSuffix() {
        return suffix;
    }

    public void setSuffix(Suffix suffix) {
        this.suffix = suffix;
    }

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

    public static class Path {
        /**
         * Controller代码包路径
         */
        private String controller;
        /**
         * Service或ServiceImpl代码包路径
         */
        private String service;
        /**
         * Service接口代码包路径
         */
        private String interf;
        /**
         * Dao代码包路径
         */
        private String dao;
        /**
         * Entity代码包路径
         */
        private String entity;
        /**
         * Mapper映射文件路径
         */
        private String mapper;

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
            return controller == null ? "" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "" : service;
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
            return dao == null ? "" : dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper == null ? "" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

    public static class Suffix {
        /**
         * Controller类的后缀，默认为Controller，即XXController
         */
        private String controller;
        /**
         * Service类或ServiceImpl类的后缀，默认为Service或ServiceImpl，即XXService或XXServiceImpl
         */
        private String service;
        /**
         * Dao类的后缀，默认为Dao，即XXDao
         */
        private String dao;
        /**
         * Entity类的后缀，默认为空，即XX
         */
        private String entity;
        /**
         * Mapper映射文件的后缀，默认为Mapper，即XXMapper
         */
        private String mapper;

        public Suffix() {
        }

        public Suffix(String controller, String service, String dao, String entity, String mapper) {
            this.controller = controller;
            this.service = service;
            this.dao = dao;
            this.entity = entity;
            this.mapper = mapper;
        }

        public String getController() {
            return controller == null ? "Controller" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "Service" : service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getDao() {
            return dao == null ? "Dao" : dao;
        }

        public void setDao(String dao) {
            this.dao = dao;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getMapper() {
            return mapper == null ? "Mapper" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

    }

}
