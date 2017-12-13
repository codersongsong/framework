package cn.com.gome.framework.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class QueryOracleTableInfo {
	
	
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

	@SuppressWarnings("resource")
	public Map<String ,String> tableInfo(String DATABASE_DRIVER,String DATABASE_URL,String DATABASE_ACCOUNT,String DATABASE_PASSWORD,String tableCode){
		
		ResultSet rs = null;
		Statement statement = null;
		Connection connection = null;
		Map<String, String> map = new HashMap<String, String>();

		try {
			connection = getConn( DATABASE_DRIVER, DATABASE_URL, DATABASE_ACCOUNT, DATABASE_PASSWORD);
			
			StringBuffer COLUMNS_TYPE = new StringBuffer();
			StringBuffer COLUMNS_CODE = new StringBuffer();
			StringBuffer COLUMNS_NAME = new StringBuffer();
			
			statement = connection.createStatement();
			String sql = "select a.COLUMN_NAME,a.COMMENTS,b.DATA_TYPE,b.DATA_PRECISION,c.COLUMN_NAME as PRK,b.DATA_LENGTH from user_col_comments a left join user_tab_cols b  on  a.COLUMN_NAME=b.COLUMN_NAME "
					+ "left join (select COLUMN_NAME from user_cons_columns where TABLE_NAME='"+tableCode+"' and position=1) c on a.COLUMN_NAME=c.column_name "
					+ "where a.TABLE_NAME='"+tableCode+"' and b.TABLE_NAME='"+tableCode+"' order by b.INTERNAL_COLUMN_ID";
			rs = statement.executeQuery(sql);
			String key = "";
			while (rs.next()) {
				if(StringUtils.isEmpty(rs.getString("DATA_TYPE")))
					continue;
				
				if ("VARCHAR2".equals(rs.getString("DATA_TYPE"))) {
					COLUMNS_TYPE.append("varchar,");
				}else if ("VARCHAR".equals(rs.getString("DATA_TYPE"))) {
					COLUMNS_TYPE.append("varchar,");
				} else if ("CHAR".equals(rs.getString("DATA_TYPE"))) {
					if(rs.getInt("DATA_LENGTH") == 3){
						COLUMNS_TYPE.append("char3,");
					}else{
						COLUMNS_TYPE.append("char,");
					}
				} else if ("NUMBER".equals(rs.getString("DATA_TYPE"))) {
					if(StringUtils.isNotEmpty(rs.getString("DATA_PRECISION"))){
						int a = Integer.valueOf(rs.getString("DATA_PRECISION"));
						if(a>=10){
							COLUMNS_TYPE.append("number10,");
						}else{
							COLUMNS_TYPE.append("number9,");
						}
					}else{
						COLUMNS_TYPE.append("number9,");
					}
					
				} else {
					COLUMNS_TYPE.append(rs.getString("DATA_TYPE").toLowerCase() + ",");
				}
				
				COLUMNS_NAME.append(rs.getString("COMMENTS") + ",");
				
				COLUMNS_CODE.append(rs.getString("COLUMN_NAME") + ",");
				
				System.out.print(rs.getString("COLUMN_NAME"));
				System.out.print(rs.getString("DATA_TYPE"));
				System.out.println(rs.getString("COMMENTS"));
				if(StringUtils.isNotEmpty(rs.getString("PRK")))
					key = rs.getString("PRK");
			}
			
			String COLUMNS_TYPE_STR = COLUMNS_TYPE.toString();
			COLUMNS_TYPE_STR = COLUMNS_TYPE_STR.substring(0, COLUMNS_TYPE_STR.length()-1);
			String COLUMNS_CODE_STR = COLUMNS_CODE.toString();
			COLUMNS_CODE_STR = COLUMNS_CODE_STR.substring(0, COLUMNS_CODE_STR.length()-1);
			String COLUMNS_NAME_STR = COLUMNS_NAME.toString();
			COLUMNS_NAME_STR = COLUMNS_NAME_STR.substring(0, COLUMNS_NAME_STR.length()-1);
			map.put("COLUMNS_TYPE", COLUMNS_TYPE_STR.toLowerCase());
			map.put("COLUMNS_CODE", COLUMNS_CODE_STR);
			map.put("COLUMNS_NAME", COLUMNS_NAME_STR);
			map.put("KEY", key);
			
			String tableSql = "select * from user_tab_comments where TABLE_NAME='"+tableCode+"'";
			rs = statement.executeQuery(tableSql);
			String tableName = "";
			while (rs.next()) {
				tableName = rs.getString("COMMENTS");
			}
			map.put("TABLE_NAME", tableName);
			
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
	
	public static void main(String[] args) {
		Map<String ,String> map = new QueryOracleTableInfo().tableInfo("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@10.126.53.197:1521:finance", "mammonuat", "mammonuat", "TBL_BUSINESS_SYSTEM");
		System.out.println(map.toString());
	}
}
