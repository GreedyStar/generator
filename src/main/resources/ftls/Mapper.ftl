<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${BasePackageName}.${ModulePackageName}.${ClassName}Dao">

    <sql id="${EntityName}Columns">
        ${ColumnMap}
    </sql>

    <sql id="${EntityName}Tables">
        ${TableName} ${Tables}
    </sql>

    <sql id="${EntityName}Joins">
        ${Joins}
    </sql>

    <select id="findList" resultType="${EntityPackageName}.${ClassName}">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM <include refid="${EntityName}Tables" />
        <where>
            <include refid="${EntityName}Joins" />
            <#-- AND ${TableName}.name LIKE concat('%',#{name},'%')-->
        </where>
    </select>

    <select id="findAllList" resultType="${EntityPackageName}.${ClassName}">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM <include refid="${EntityName}Tables" />
        <where>
            <include refid="${EntityName}Joins" />
        </where>
    </select>

    <insert id="insert">
        INSERT INTO ${TableName}(
            ${InsertProperties})
        VALUES (
            ${InsertValues})
    </insert>

    <update id="update">
        UPDATE ${TableName} SET
        ${UpdateProperties}
        WHERE id = ${WhereId}
    </update>

    <update id="delete">
        DELETE FROM ${TableName}
        WHERE id = ${WhereId}
    </update>

</mapper>