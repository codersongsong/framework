package cn.com.gome.framework.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class QueryMysqlTableInfo {
	
	
	private Connection getConn(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD) {
		Connection conn = null;
		try {
			Class.forName(DATABASE_DRIVER);
			conn = DriverManager.getConnection(DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Map<String ,String> tableInfo(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD,String tableCode){
		
		ResultSet rs = null;
		Statement statement = null;
		Connection connection = null;
		Map<String, String> map = new HashMap<String, String>();

		try {
			connection = getConn( DATABASE_DRIVER, DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("show full fields from " + tableCode);
			StringBuffer COLUMNS_TYPE = new StringBuffer();
			StringBuffer COLUMNS_CODE = new StringBuffer();
			StringBuffer COLUMNS_NAME = new StringBuffer();
			String key = "";
			String type = "";
			while (rs.next()) {
				COLUMNS_CODE.append(rs.getString("Field") + ",");
				type = rs.getString("Type");
				if(type.indexOf("(")>=0){
					type = type.substring(0, type.indexOf("("));
					COLUMNS_TYPE.append(type + ",");
				}else{
					COLUMNS_TYPE.append(rs.getString("Type") + ",");
				}
				COLUMNS_NAME.append(rs.getString("Comment") + ",");
				if(StringUtils.isNotEmpty(rs.getString("Key")) && "PRI".equals(rs.getString("Key"))){
					key = rs.getString("Field");
				}
			}
			String COLUMNS_TYPE_STR = COLUMNS_TYPE.toString();
			COLUMNS_TYPE_STR = COLUMNS_TYPE_STR.substring(0, COLUMNS_TYPE_STR.length()-1);
			
			String COLUMNS_CODE_STR = COLUMNS_CODE.toString();
			COLUMNS_CODE_STR = COLUMNS_CODE_STR.substring(0, COLUMNS_CODE_STR.length()-1);
			
			String COLUMNS_NAME_STR = COLUMNS_NAME.toString();
			COLUMNS_NAME_STR = COLUMNS_NAME_STR.substring(0, COLUMNS_NAME_STR.length()-1);
			
			map.put("COLUMNS_TYPE", COLUMNS_TYPE_STR);
			map.put("COLUMNS_CODE", COLUMNS_CODE_STR);
			map.put("COLUMNS_NAME", COLUMNS_NAME_STR);
			map.put("KEY", key);
			map.put("TABLE_NAME", tableCode);
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
		return map;
	}
}
