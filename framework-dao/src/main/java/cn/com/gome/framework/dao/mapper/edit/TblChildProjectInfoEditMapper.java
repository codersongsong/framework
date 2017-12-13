/**
 * @Title: TblChildProjectInfoEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: 子项目信息配置表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblChildProjectInfoEditMapper
 * @Description: 子项目信息配置表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblChildProjectInfoEditMapper extends PersistenceLayerEditMapper<TblChildProjectInfo>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-子项目信息配置表
	* @param  TblChildProjectInfo
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblChildProjectInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-子项目信息配置表
	* @param  TblChildProjectInfo
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblChildProjectInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-子项目信息配置表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-子项目信息配置表
	* @param  TblChildProjectInfo
	* @return int    返回类型
	* @throws
	 */
	public int del(TblChildProjectInfo entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-子项目信息配置表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
