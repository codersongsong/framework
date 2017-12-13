package cn.com.gome.generator.service;

import java.util.Map;

public interface DatabaseTypeService {
	
	/**
	* @Title: service 
	* @Description: 获取表的结构信息 
	* @param databaseType
	* @param databaseDriver
	* @param databaseUrl
	* @param databaseAccount
	* @param databasePassword
	* @param tableEng 表名
	* @return Map<String,String>    返回类型
	 */
	public Map<String ,String> service(String databaseType,String databaseDriver,String databaseUrl,String databaseAccount,String databasePassword,String tableEng);
	
}
