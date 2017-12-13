/**
 * @Title: TblDubboClassCreateConfigEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: DUBBO服务类生产配置的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblDubboClassCreateConfigEditMapper
 * @Description: DUBBO服务类生产配置的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblDubboClassCreateConfigEditMapper extends PersistenceLayerEditMapper<TblDubboClassCreateConfig>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-DUBBO服务类生产配置
	* @param  TblDubboClassCreateConfig
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblDubboClassCreateConfig entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-DUBBO服务类生产配置
	* @param  TblDubboClassCreateConfig
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblDubboClassCreateConfig entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-DUBBO服务类生产配置
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-DUBBO服务类生产配置
	* @param  TblDubboClassCreateConfig
	* @return int    返回类型
	* @throws
	 */
	public int del(TblDubboClassCreateConfig entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-DUBBO服务类生产配置
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
