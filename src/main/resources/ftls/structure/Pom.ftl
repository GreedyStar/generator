<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${Configuration.packageName}</groupId>
    <artifactId>${Configuration.projectName}</artifactId>
    <version>${Configuration.projectVersion}</version>
    <#if (Configuration.parent.artifactId)??>
        <parent>
            <groupId>${Configuration.parent.groupId}</groupId>
            <artifactId>${Configuration.parent.artifactId}</artifactId>
            <version>${Configuration.parent.version}</version>
        </parent>
    </#if>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <#if Configuration.swaggerEnable>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.15</version>
            </dependency>
        </#if>
        <#if Configuration.swaggerEnable>
            <dependency>
                <groupId>io.springfox</groupId>
                <artifactId>springfox-boot-starter</artifactId>
                <version>3.0.0</version>
            </dependency>
        </#if>
        <#if Configuration.lombokEnable>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.22</version>
            </dependency>
        </#if>
        <#if Configuration.mybatisPlusEnable>
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.5.2</version>
            </dependency>
        </#if>
        <#if Configuration.knif4jEnable>
            <dependency>
                <groupId>com.github.xiaoymin</groupId>
                <artifactId>knife4j-spring-boot-autoconfigure</artifactId>
                <version>${Configuration.knif4jVersion}</version>
            </dependency>
        </#if>
    </dependencies>

</project>