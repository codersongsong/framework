package cn.com.gome.framework.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取mysql数据库的表信息
 * @author wangheming 2017 12 04
 */
public class QueryTableName {
	
	// 获取数据库连接
	private Connection getConn(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD) {
		// java sql connection 数据库连接
		Connection conn = null;
		try {
			Class.forName(DATABASE_DRIVER);// 全类名获取类的实例 反射获取 （这个自动注册到DirverManager中）
			// com.mysql.jdbc.Driver;
			conn = DriverManager.getConnection(DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

    /**
     * 查询mysql数据库的表名
     * @param DATABASE_DRIVER
     * @param DATABASE_URL
     * @param DATABASE_ACCOUNT
     * @param DATABASE_PASSWORD
     * @param tableCode
     * @return
     */
	public List mysqlTableInfo(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD,String tableCode){
		ResultSet rs = null;// 结果集
		Statement statement = null;// 声明、叙述、报表、清单
		Connection connection = null; // 数据库连接
		List tblNames = new ArrayList();
		try {
			connection = getConn( DATABASE_DRIVER, DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
			statement = connection.createStatement();// sql 语句
            String dataBaseName = DATABASE_URL.substring(DATABASE_URL.lastIndexOf("/")+1);
            String sql = "select table_name from information_schema.tables where table_schema='"+dataBaseName+"' and table_type='base table'";
            rs = statement.executeQuery(sql);// 执行这条sql
            while (rs.next()) {
				Map<String,String> map = new HashMap(2);
				map.put("name",rs.getString("table_name"));
				tblNames.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tblNames;
	}

    @SuppressWarnings("resource")
    public List oracleTableInfo(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD,String tableCode){

        ResultSet rs = null;
        Statement statement = null;
        Connection connection = null;
        List tblNames = new ArrayList();
        try {
            connection = getConn( DATABASE_DRIVER, DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
            statement = connection.createStatement();
            String sql = "select t.table_name from user_tables t";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String,String> map = new HashMap(2);
                map.put("name",rs.getString("table_name"));
                tblNames.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tblNames;
    }
}
