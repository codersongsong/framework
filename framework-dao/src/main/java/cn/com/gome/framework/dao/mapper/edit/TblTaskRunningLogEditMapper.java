/**
 * @Title: TblTaskRunningLogEditMapper.java
 * @Package com.gome.login.dao.mapper.edit
 * @Description: 任务运行日志表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTaskRunningLog;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblTaskRunningLogEditMapper
 * @Description: 任务运行日志表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblTaskRunningLogEditMapper extends PersistenceLayerEditMapper<TblTaskRunningLog>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-任务运行日志表
	* @param  TblTaskRunningLog
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblTaskRunningLog entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-任务运行日志表
	* @param  TblTaskRunningLog
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblTaskRunningLog entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-任务运行日志表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-任务运行日志表
	* @param  TblTaskRunningLog
	* @return int    返回类型
	* @throws
	 */
	public int del(TblTaskRunningLog entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-任务运行日志表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
