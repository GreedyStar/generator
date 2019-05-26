package com.greedystar.generator.db;


import com.greedystar.generator.entity.ColumnInfo;
import com.greedystar.generator.utils.ConfigUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class ConnectionUtil {
    private Connection connection;

    public ConnectionUtil() {
    }

    /**
     * 初始化数据库连接
     *
     * @return
     */
    public boolean initConnection() {
        try {
            Class.forName(DriverFactory.getDriver(ConfigUtil.getConfiguration().getDb().getUrl()));
            String url = ConfigUtil.getConfiguration().getDb().getUrl();
            String username = ConfigUtil.getConfiguration().getDb().getUsername();
            String password = ConfigUtil.getConfiguration().getDb().getPassword();
            Properties properties = new Properties();
            properties.put("user", username);
            properties.put("password", password == null ? "" : password);
            properties.setProperty("remarks", "true");
            properties.setProperty("useInformationSchema", "true");
            connection = DriverManager.getConnection(url, properties);
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
     *
     * @param tableName 表名
     * @return 包含表结构数据的列表
     */
    public List<ColumnInfo> getMetaData(String tableName) throws SQLException {
        // 获取主键
        ResultSet keyResultSet = connection.getMetaData().getPrimaryKeys(null, null, tableName);
        String primaryKey = null;
        if (keyResultSet.next()) {
            primaryKey = keyResultSet.getObject(4).toString();
        }
        // 获取表注释
        ResultSet tableResultSet = connection.getMetaData().getTables(null, connection.getSchema(), tableName.toUpperCase(), new String[]{"TABLE"});
        String tableRemarks = null;
        if (tableResultSet.next()) {
            tableRemarks = tableResultSet.getString("REMARKS") == null ? "Unknown Table" : tableResultSet.getString("REMARKS");
        }
        // 获取列信息
        List<ColumnInfo> columnInfos = new ArrayList<>();
        ResultSet columnResultSet = connection.getMetaData().getColumns(null, connection.getSchema(), tableName.toUpperCase(), "%");
        while (columnResultSet.next()) {
            boolean isPrimaryKey;
            if (columnResultSet.getString("COLUMN_NAME").equals(primaryKey)) {
                isPrimaryKey = true;
            } else {
                isPrimaryKey = false;
            }
            ColumnInfo info = new ColumnInfo(columnResultSet.getString("COLUMN_NAME"), columnResultSet.getString("TYPE_NAME"), columnResultSet.getString("REMARKS"), tableRemarks, isPrimaryKey);
            columnInfos.add(info);
        }
        columnResultSet.close();
        return columnInfos;
    }

    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
