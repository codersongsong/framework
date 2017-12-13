/**
 * @Title: TblTaskObjectRegisterEditMapper.java
 * @Package com.gome.framework.dao.mapper.edit
 * @Description: 定时任务实例启动日志表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;

import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

/** 
 * @ClassName: TblTaskObjectRegisterEditMapper
 * @Description: 定时任务实例启动日志表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblTaskObjectRegisterEditMapper extends PersistenceLayerEditMapper<TblTaskObjectRegister>{

	/**
	* @Title: save
	* @Description: 实体对象持久化-定时任务实例启动日志表
	* @param  TblTaskObjectRegister
	* @return boolean    返回类型
	* @throws
	 */
	public boolean save(TblTaskObjectRegister entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-定时任务实例启动日志表
	* @param  TblTaskObjectRegister
	* @return int    返回类型
	* @throws
	 */
	public int edit(TblTaskObjectRegister entity);

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-定时任务实例启动日志表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

	/**
	* @Title: del 
	* @Description: 删除实体对象-定时任务实例启动日志表
	* @param  TblTaskObjectRegister
	* @return int    返回类型
	* @throws
	 */
	public int del(TblTaskObjectRegister entity);
	
	/**
	* @Title: delList 
	* @Description: 批量删除实体对象-定时任务实例启动日志表
	* @param  String[]
	* @param  entityClass
	* @return int    返回类型 
	* @throws
	 */
	public int delList(List<String> list);
	
}
