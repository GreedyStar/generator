package com.greedystar.generator.utils.convertor;

import java.sql.JDBCType;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * Author GreedyStar
 * Date   2019/6/2
 */
public class DefaultConvertor implements TypeConvertor {

    /**
     * 将JDBC类型转换为Java类型
     *
     * @param type JDBC类型名
     */
    @Override
    public String convertType(JDBCType type) {
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case BIT:
            case BOOLEAN:
                sb.append("boolean");
                break;
            case TINYINT:
                sb.append("byte");
                break;
            case SMALLINT:
                sb.append("short");
                break;
            case INTEGER:
                sb.append("int");
                break;
            case BIGINT:
                sb.append("long");
                break;
            case REAL:
                sb.append("float");
                break;
            case FLOAT:
            case DOUBLE:
                sb.append("double");
                break;
            case DECIMAL:
            case NUMERIC:
                sb.append("BigDecimal");
                break;
            case VARCHAR:
            case CHAR:
            case NCHAR:
            case NVARCHAR:
            case LONGVARCHAR:
            case LONGNVARCHAR:
                sb.append("String");
                break;
            case DATE:
                sb.append("Date");
                break;
            case TIME:
                sb.append("Time");
                break;
            case TIMESTAMP:
                sb.append("Timestamp");
                break;
            case CLOB:
            case NCLOB:
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                sb.append("byte[]");
                break;
            case NULL:
            case OTHER:
            case JAVA_OBJECT:
                sb.append("Object");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

}
