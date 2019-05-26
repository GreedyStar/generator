package com.greedystar.generator.utils;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class TypeUtil {

    /**
     * 将数据库数据类型转换为Java数据类型
     *
     * @param typeName
     * @return
     */
    public static String parseTypeFormSqlType(String typeName) {
        StringBuilder sb = new StringBuilder();
        switch (typeName) {
            case "BIT":
            case "BOOLEAN":
                sb.append("boolean");
                break;
            case "TINYINT":
                sb.append("byte");
                break;
            case "SMALLINT":
                sb.append("short");
                break;
            case "INTEGER":
            case "INT":
                sb.append("int");
                break;
            case "BIGINT":
                sb.append("long");
                break;
            case "REAL":
                sb.append("float");
                break;
            case "FLOAT":
            case "DOUBLE":
                sb.append("double");
                break;
            case "DECIMAL":
            case "NUMERIC":
                sb.append("BigDecimal");
                break;
            case "VARCHAR":
            case "CHAR":
            case "NCHAR":
            case "NVARCHAR":
            case "LONGVARCHAR":
            case "LONGNVARCHAR":
                sb.append("String");
                break;
            case "DATE":
                sb.append("Date");
                break;
            case "TIME":
                sb.append("Time");
                break;
            case "TIMESTAMP":
                sb.append("Timestamp");
                break;
            case "NCLOB":
            case "CLOB":
            case "BLOB":
            case "BINARY":
            case "VARBINARY":
            case "LONGVARBINARY":
                sb.append("byte[]");
                break;
            case "NULL":
            case "OTHER":
            case "JAVA_OBJECT":
                sb.append("Object");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

}
