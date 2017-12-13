/**
 * @Title: TblProjectBasicInfoEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: 项目基本信息表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblProjectBasicInfoEditMapper
 * @Description: 项目基本信息表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblProjectBasicInfoEditMapper extends PersistenceLayerEditMapper<TblProjectBasicInfo>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-项目基本信息表
	* @param  TblProjectBasicInfo
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblProjectBasicInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-项目基本信息表
	* @param  TblProjectBasicInfo
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblProjectBasicInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-项目基本信息表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-项目基本信息表
	* @param  TblProjectBasicInfo
	* @return int    返回类型
	* @throws
	 */
	public int del(TblProjectBasicInfo entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-项目基本信息表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
