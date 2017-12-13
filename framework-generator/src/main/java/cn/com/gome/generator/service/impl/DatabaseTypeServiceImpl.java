/**   
* @Title: DatabaseTypeServiceImpl.java 
* @Package cn.com.gome.generator.service.impl 
* @Description: 获取表的结构信息 
* @author GOME
* @date 2017年5月11日 下午3:11:14 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.impl.QueryMysqlTableInfo;
import cn.com.gome.framework.dao.impl.QueryOracleTableInfo;
import cn.com.gome.generator.service.DatabaseTypeService;

/** 
 * @ClassName: DatabaseTypeServiceImpl 
 * @Description: 获取表的结构信息 
 * @author GOME
 * @date 2017年5月11日 下午3:11:14  
 */
@Service
public class DatabaseTypeServiceImpl implements DatabaseTypeService {

	/* (非 Javadoc) 
	 * <p>Title: service</p> 
	 * <p>Description: 获取表的结构信息 </p> 
	 * @param databaseType
	 * @param databaseDriver
	 * @param databaseUrl
	 * @param databaseAccount
	 * @param databasePassword
	 * @param tableEng
	 * @return 
	 * @see cn.com.gome.generator.service.DatabaseTypeService#service(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
	 */
	@Override
    public Map<String, String> service(String databaseType, String databaseDriver, String databaseUrl, String databaseAccount, String databasePassword, String tableEng) {
		if("010".equals(databaseType)){
			return new QueryMysqlTableInfo().tableInfo(databaseDriver, databaseUrl, databaseAccount, databasePassword, tableEng);
		}else{
			return new QueryOracleTableInfo().tableInfo(databaseDriver, databaseUrl, databaseAccount, databasePassword, tableEng);
		}
	}

}
