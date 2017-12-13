/**
* @Title: TblTaskObjectRegisterSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: 定时任务实例启动日志表查询接口类
* @author renhanxiang
* @date Thu Jun 15 10:00:02 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;
import cn.com.gome.framework.po.TaskObjectRegisterPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblTaskObjectRegisterSerMapper
* @Description: 定时任务实例启动日志表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTaskObjectRegisterSerMapper extends PersistenceLayerSerMapper<TblTaskObjectRegister>{

	/**
	* @Title: query
	* @Description:  查询实体对象-定时任务实例启动日志表
	* @param  String id
	* @param  entityClass
	* @return TblTaskObjectRegister    返回类型
	* @throws
	 */
	public TblTaskObjectRegister query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-定时任务实例启动日志表
	* @param  TblTaskObjectRegister
	* @return TblTaskObjectRegister    返回类型
	* @throws
	 */
	public TblTaskObjectRegister queryObj(TblTaskObjectRegister entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-定时任务实例启动日志表
	* @param  TaskObjectRegisterPo
	* @return TaskObjectRegisterPo    返回类型
	* @throws
	 */
	public TaskObjectRegisterPo queryPo(TblTaskObjectRegister entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-定时任务实例启动日志表
	* @param  TblTaskObjectRegister
	* @return List<TblTaskObjectRegister>    返回类型
	* @throws
	 */
	public List<TblTaskObjectRegister> queryList(TblTaskObjectRegister entity);

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
