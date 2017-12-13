package cn.com.gome.generator.util;

import java.util.HashMap;
import java.util.Map;

public class MysqlCache {
	
	private static Map<String, String> mapMysql = null;
	
	private static Map<String, String> mapMysqlXml = null;
	
	private static Map<String, String> mapOracle = null;
	
	private static Map<String, String> mapOracleXml = null;
	
	static{
		mapMysql = new HashMap<String, String>();
		mapMysql.put("int", "Integer");
		mapMysql.put("varchar", "String");
		mapMysql.put("char", "String");
		mapMysql.put("char3", "String");
		mapMysql.put("decimal", "BigDecimal");
		mapMysql.put("float", "Float");
		mapMysql.put("double", "Double");
		mapMysql.put("bigint", "Long");
		mapMysql.put("datetime", "Date");
		
		mapMysqlXml = new HashMap<String, String>();
		mapMysqlXml.put("int", "INTEGER");
		mapMysqlXml.put("varchar", "VARCHAR");
		mapMysqlXml.put("char", "CHAR");
		mapMysqlXml.put("char3", "CHAR");
		mapMysqlXml.put("decimal", "DECIMAL");
		mapMysqlXml.put("float", "FLOAT");
		mapMysqlXml.put("double", "DOUBLE");
		mapMysqlXml.put("bigint", "BIGINT");
		mapMysqlXml.put("datetime", "DATETIME");
		
		mapOracle = new HashMap<String, String>();
		mapOracle.put("int", "Integer");
		mapOracle.put("varchar", "String");
		mapOracle.put("char", "String");
		mapOracle.put("char3", "String");
		mapOracle.put("number10", "BigDecimal");
		mapOracle.put("number9", "Integer");
		
		mapOracleXml = new HashMap<String, String>();
		mapOracleXml.put("int", "INTEGER");
		mapOracleXml.put("number10", "DECIMAL");
		mapOracleXml.put("number9", "INTEGER");
		mapOracleXml.put("varchar", "VARCHAR");
		mapOracleXml.put("char", "CHAR");
		mapOracleXml.put("char3", "CHAR");
		mapOracleXml.put("decimal", "DECIMAL");
		mapOracleXml.put("float", "FLOAT");
		mapOracleXml.put("double", "DOUBLE");
		mapOracleXml.put("bigint", "BIGINT");
		mapOracleXml.put("datetime", "DATETIME");
	}

	public static String getJavaType(String type,String databaseType){
		if("010".equals(databaseType)){//mysql
			if(type.indexOf("int") >=0 ){
				return mapMysql.get("int");
			}else if(type.indexOf("varchar") >=0 ){
				return mapMysql.get("varchar");
			}else if(type.indexOf("char") >=0 ){
				return mapMysql.get("char");
			}else if(type.indexOf("char3") >=0 ){
				return mapMysql.get("char3");
			}else if(type.indexOf("decimal") >=0 ){
				return mapMysql.get("decimal");
			}else if(type.indexOf("float") >=0 ){
				return mapMysql.get("float");
			}else if(type.indexOf("double") >=0 ){
				return mapMysql.get("double");
			}else if(type.indexOf("datetime") >=0 ){
				return mapMysql.get("datetime");
			}else if(type.indexOf("bigint") >=0 ){
				return mapMysql.get("bigint");
			}else{
				return "String";
			}
		}else{//orcale
			if(type.indexOf("int") >=0 ){
				return mapOracle.get("int");
			}else if(type.indexOf("number9") >=0 ){
				return mapOracle.get("number9");
			}else if(type.indexOf("number10") >=0 ){
				return mapOracle.get("number10");
			}else if(type.indexOf("varchar") >=0 ){
				return mapOracle.get("varchar");
			}else if(type.indexOf("char") >=0 ){
				return mapOracle.get("char");
			}else if(type.indexOf("char3") >=0 ){
				return mapOracle.get("char3");
			}else if(type.indexOf("decimal") >=0 ){
				return mapOracle.get("decimal");
			}else if(type.indexOf("float") >=0 ){
				return mapOracle.get("float");
			}else if(type.indexOf("double") >=0 ){
				return mapOracle.get("double");
			}else if(type.indexOf("datetime") >=0 ){
				return mapOracle.get("datetime");
			}else if(type.indexOf("bigint") >=0 ){
				return mapOracle.get("bigint");
			}else{
				return "String";
			}
		}
	}
	
	public static String getXmlType(String type,String databaseType){
		if("010".equals(databaseType)){//mysql
			if(type.indexOf("int") >=0 ){
				return mapMysqlXml.get("int");
			}else if(type.indexOf("varchar") >=0 ){
				return mapMysqlXml.get("varchar");
			}else if(type.indexOf("char") >=0 ){
				return mapMysqlXml.get("char");
			}else if(type.indexOf("char3") >=0 ){
				return mapMysqlXml.get("char3");
			}else if(type.indexOf("decimal") >=0 ){
				return mapMysqlXml.get("decimal");
			}else if(type.indexOf("float") >=0 ){
				return mapMysqlXml.get("float");
			}else if(type.indexOf("double") >=0 ){
				return mapMysqlXml.get("double");
			}else{
				return "VARCHAR";
			}
		}else{//orcale
			if(type.indexOf("int") >=0 ){
				return mapOracleXml.get("int");
			}else if(type.indexOf("number9") >=0 ){
				return mapOracleXml.get("number9");
			}else if(type.indexOf("number10") >=0 ){
				return mapOracleXml.get("number10");
			}else if(type.indexOf("varchar") >=0 ){
				return mapOracleXml.get("varchar");
			}else if(type.indexOf("char") >=0 ){
				return mapOracleXml.get("char");
			}else if(type.indexOf("char3") >=0 ){
				return mapOracleXml.get("char3");
			}else if(type.indexOf("decimal") >=0 ){
				return mapOracleXml.get("decimal");
			}else if(type.indexOf("float") >=0 ){
				return mapOracleXml.get("float");
			}else if(type.indexOf("double") >=0 ){
				return mapOracleXml.get("double");
			}else{
				return "VARCHAR";
			}
		}
	}
}
