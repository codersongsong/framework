/**
* @Title: TblTaskRunningLogSerMapper.java
* @Package com.gome.login.dao.mapper.ser
* @Description: 任务运行日志表查询接口类
* @author renhanxiang
* @date Fri Mar 24 15:43:27 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;
import java.util.Map;

import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.po.TaskRunningLogPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;

/**
* @ClassName: TblTaskRunningLogSerMapper
* @Description: 任务运行日志表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTaskRunningLogSerMapper extends PersistenceLayerSerMapper<TblTaskRunningLog>{

	/**
	* @Title: query
	* @Description:  查询实体对象-任务运行日志表
	* @param  String id
	* @param  entityClass
	* @return TblTaskRunningLog    返回类型
	 */
	public TblTaskRunningLog query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-任务运行日志表
	* @param  TblTaskRunningLog
	* @return TblTaskRunningLog    返回类型
	 */
	public TblTaskRunningLog queryObj(TblTaskRunningLog entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-任务运行日志表
	* @param  TaskRunningLogPo
	* @return TaskRunningLogPo    返回类型
	 */
	public TaskRunningLogPo queryPo(TblTaskRunningLog entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-任务运行日志表
	* @param  taskNo
	* @return List<TblTaskRunningLog>    返回类型
	 */
	public List<TblTaskRunningLog> queryList(TblTaskRunningLog entity);
	
	public List<TblTaskRunningLog> queryNullList(String taskNo);
	
	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-任务运行日志表-排除作废的信息
	* @param  TblTaskRunningLog
	* @return List<TblTaskRunningLog>    返回类型
	 */
	public List<TblTaskRunningLog> query040List(TblTaskRunningLog entity);
	/**
	* @Title: queryPageListCount
	* @Description: 根据条件查询总条数
	* @param @param entity
	* @param @return    设定文件
	* @return int    返回类型
	* @throws
	 */
	public int queryPageListCount(PageQueryEntitys entity);

	/**
	 * 根据 taskNo 和 实例状态查询 ip 端口
 	 * @param entity
	 * @return
	 */
	public List<TblTaskRunningLog> queryAddrList(Map<String, Object> params);


	/**
	 * 根据 taskNo 和 实例状态查询数据
	 * @param entity
	 * @return
	 */
	public List<TblTaskRunningLog> queryListByStatus(Map<String, Object> params);

}
