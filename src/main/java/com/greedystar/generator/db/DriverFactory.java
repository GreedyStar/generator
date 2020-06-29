package com.greedystar.generator.db;

/**
 * 数据库驱动工厂类
 *
 * @author GreedyStar
 * @since 2018-10-24
 */
public class DriverFactory {
    private final static String DRIVER_MYSQL_5 = "com.mysql.jdbc.Driver";
    private final static String DRIVER_MYSQL_UPER = "com.mysql.cj.jdbc.Driver";
    private final static String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    private final static String DRIVER_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * 根据数据库连接url获取数据库驱动
     *
     * @param url
     * @return
     */
    public static String getDriver(String url) {
        if (url.contains("mysql")) {
            if (url.contains("serverTimezone")) {
                return DRIVER_MYSQL_UPER;
            } else {
                return DRIVER_MYSQL_5;
            }
        }
        if (url.contains("oracle")) {
            return DRIVER_ORACLE;
        }
        if (url.contains("sqlserver")) {
            return DRIVER_SQLSERVER;
        }
        return null;
    }

}
