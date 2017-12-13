/**
 * @Title: TblTableDaoInfoEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: 表持久化信息表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTableDaoInfo;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblTableDaoInfoEditMapper
 * @Description: 表持久化信息表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblTableDaoInfoEditMapper extends PersistenceLayerEditMapper<TblTableDaoInfo>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-表持久化信息表
	* @param  TblTableDaoInfo
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblTableDaoInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-表持久化信息表
	* @param  TblTableDaoInfo
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblTableDaoInfo entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-表持久化信息表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-表持久化信息表
	* @param  TblTableDaoInfo
	* @return int    返回类型
	* @throws
	 */
	public int del(TblTableDaoInfo entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-表持久化信息表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
