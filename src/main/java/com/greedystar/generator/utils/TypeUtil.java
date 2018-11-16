package com.greedystar.generator.utils;

import java.sql.Types;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class TypeUtil {

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param sqlType
     * @return
     */
    public static String parseTypeFormSqlType(int sqlType) {
        StringBuilder sb = new StringBuilder();
        switch (sqlType) {
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.NCHAR:
            case Types.LONGVARCHAR:
                sb.append("String");
                break;
            case Types.INTEGER:
                sb.append("int");
                break;
            case Types.BIGINT:
                sb.append("long");
                break;
            case Types.TINYINT:
            case Types.BIT:
                sb.append("boolean");
                break;
            case Types.BLOB:
                sb.append("byte[]");
                break;
            case Types.DECIMAL:
            case Types.DOUBLE:
            case Types.NUMERIC:
            case Types.FLOAT:
                sb.append("double");
                break;
            case Types.REAL:
                sb.append("float");
                break;
            case Types.SMALLINT:
                sb.append("short");
                break;
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                sb.append("Date");
                break;

        }
        return sb.toString();
    }

}
