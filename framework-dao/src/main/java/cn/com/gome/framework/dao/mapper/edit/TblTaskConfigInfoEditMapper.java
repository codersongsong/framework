/**
 * @Title: TblTaskConfigInfoEditMapper.java
 * @Package com.gome.login.dao.mapper.edit
 * @Description: 任务基本信息配置表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblTaskConfigInfoEditMapper
 * @Description: 任务基本信息配置表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblTaskConfigInfoEditMapper extends PersistenceLayerEditMapper<TblTaskConfigInfo>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-任务基本信息配置表
	* @param  TblTaskConfigInfo
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblTaskConfigInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-任务基本信息配置表
	* @param  TblTaskConfigInfo
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblTaskConfigInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-任务基本信息配置表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-任务基本信息配置表
	* @param  TblTaskConfigInfo
	* @return int    返回类型
	* @throws
	 */
	public int del(TblTaskConfigInfo entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-任务基本信息配置表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
