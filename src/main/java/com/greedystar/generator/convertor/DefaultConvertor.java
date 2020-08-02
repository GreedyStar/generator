package com.greedystar.generator.convertor;

import java.sql.JDBCType;

/**
 * @author GreedyStar
 * @since 2019/6/2
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
                sb.append("Boolean");
                break;
            case TINYINT:
            case SMALLINT:
            case INTEGER:
                sb.append("Integer");
                break;
            case BIGINT:
                sb.append("Long");
                break;
            case REAL:
                sb.append("Float");
                break;
            case FLOAT:
            case DOUBLE:
                sb.append("Double");
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
            case TIME:
            case TIMESTAMP:
                sb.append("Date");
                break;
            case CLOB:
            case NCLOB:
            case BLOB:
            case BINARY:
            case VARBINARY:
            case LONGVARBINARY:
                sb.append("byte[]");
                break;
            default:
                sb.append("Object");

        }
        return sb.toString();
    }

}
