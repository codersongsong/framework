/**
 * @Title: TblTaskCodeGeneratorEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: 定时任务代码生成表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTaskCodeGenerator;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblTaskCodeGeneratorEditMapper
 * @Description: 定时任务代码生成表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblTaskCodeGeneratorEditMapper extends PersistenceLayerEditMapper<TblTaskCodeGenerator>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-定时任务代码生成表
	* @param  TblTaskCodeGenerator
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblTaskCodeGenerator entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-定时任务代码生成表
	* @param  TblTaskCodeGenerator
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblTaskCodeGenerator entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-定时任务代码生成表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-定时任务代码生成表
	* @param  TblTaskCodeGenerator
	* @return int    返回类型
	* @throws
	 */
	public int del(TblTaskCodeGenerator entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-定时任务代码生成表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
