/**
* @Title: TblTaskConfigInfoSerMapper.java
* @Package com.gome.login.dao.mapper.ser
* @Description: 任务基本信息配置表查询接口类
* @author renhanxiang
* @date Fri Mar 24 15:43:30 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.po.TaskConfigInfoPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblTaskConfigInfoSerMapper
* @Description: 任务基本信息配置表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTaskConfigInfoSerMapper extends PersistenceLayerSerMapper<TblTaskConfigInfo>{

	/**
	* @Title: query
	* @Description:  查询实体对象-任务基本信息配置表
	* @param  String id
	* @param  entityClass
	* @return TblTaskConfigInfo    返回类型
	 */
	public TblTaskConfigInfo query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-任务基本信息配置表
	* @param  TblTaskConfigInfo
	* @return TblTaskConfigInfo    返回类型
	 */
	public TblTaskConfigInfo queryObj(TblTaskConfigInfo entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-任务基本信息配置表
	* @param  TaskConfigInfoPo
	* @return TaskConfigInfoPo    返回类型
	 */
	public TaskConfigInfoPo queryPo(TblTaskConfigInfo entity);

	/**
	 * @Title: queryList
	 * @Description: 根据条件查询集合-任务基本信息配置表
	 * @param TblTaskConfigInfo
	 * @return List<TblTaskConfigInfo> 返回类型
	 */
	public List<TblTaskConfigInfo> queryList(TblTaskConfigInfo entity);
	
	/**
	* @Title: queryGlobalUnicityList
	* @Description: 根据条件查询集合-任务基本信息配置表
	* @param  TblTaskConfigInfo
	* @return List<TblTaskConfigInfo>    返回类型
	 */
	public List<TblTaskConfigInfo> queryGlobalUnicityList();

	/**
	* @Title: queryPageListCount
	* @Description: 根据条件查询总条数
	* @param @param entity
	* @param @return    设定文件
	* @return int    返回类型
	 */
	public int queryPageListCount(PageQueryEntitys entity);

}
