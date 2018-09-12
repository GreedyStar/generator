package com.greedystar.generator.entity;

import com.greedystar.generator.utils.StringUtil;

import java.io.File;
import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class Configuration implements Serializable {
    private String author;
    private String packageName;
    private Path path;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackagePath() {
        if (StringUtil.isBlank(packageName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public static class Path {
        private String controller;
        private String service;
        private String dao;
        private String entity;
        private String mapper;

        public Path() {
        }

        public Path(String controller, String service, String dao, String entity, String mapper) {
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

}
