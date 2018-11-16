package com.greedystar.generator.utils;


import com.greedystar.generator.entity.ColumnInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ConnectionUtil {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * 初始化数据库连接
     * @param database 数据库名
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean initConnection(String database, String username, String password) {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://localhost:3306/" + database + "?useUnicode=true&amp;characterEncoding=UTF-8";
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取表结构数据
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) {
        List<ColumnInfo> columnInfos = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM " + tableName.toUpperCase() + " WHERE 1 != 1";
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                ColumnInfo info = new ColumnInfo(metaData.getColumnName(i), metaData.getColumnType(i));
                columnInfos.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return columnInfos;
    }

    public boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void close() {
        if (!isClosed()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
