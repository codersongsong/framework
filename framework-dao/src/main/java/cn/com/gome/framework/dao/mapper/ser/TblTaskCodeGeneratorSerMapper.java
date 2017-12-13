/**
* @Title: TblTaskCodeGeneratorSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: 定时任务代码生成表查询接口类
* @author renhanxiang
* @date Fri May 05 14:26:27 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblTaskCodeGenerator;
import cn.com.gome.framework.po.TaskCodeGeneratorPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblTaskCodeGeneratorSerMapper
* @Description: 定时任务代码生成表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTaskCodeGeneratorSerMapper extends PersistenceLayerSerMapper<TblTaskCodeGenerator>{

	/**
	* @Title: query
	* @Description:  查询实体对象-定时任务代码生成表
	* @param  String id
	* @param  entityClass
	* @return TblTaskCodeGenerator    返回类型
	* @throws
	 */
	public TblTaskCodeGenerator query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-定时任务代码生成表
	* @param  TblTaskCodeGenerator
	* @return TblTaskCodeGenerator    返回类型
	* @throws
	 */
	public TblTaskCodeGenerator queryObj(TblTaskCodeGenerator entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-定时任务代码生成表
	* @param  TaskCodeGeneratorPo
	* @return TaskCodeGeneratorPo    返回类型
	* @throws
	 */
	public TaskCodeGeneratorPo queryPo(TblTaskCodeGenerator entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-定时任务代码生成表
	* @param  TblTaskCodeGenerator
	* @return List<TblTaskCodeGenerator>    返回类型
	* @throws
	 */
	public List<TblTaskCodeGenerator> queryList(TblTaskCodeGenerator entity);

	/**
	* @Title: queryPageListCount
	* @Description: 根据条件查询总条数
	* @param @param entity
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int queryPageListCount(PageQueryEntitys entity);

}
