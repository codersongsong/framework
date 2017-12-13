/**
 * @Title: TblTaskPartConfigSerMapper.java
 * @Package com.gome.login.dao.mapper.ser
 * @Description: 任务分片配置表查询接口类
 * @author renhanxiang
 * @date Sat Apr 08 10:30:54 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblTaskPartConfig;
import cn.com.gome.framework.po.TaskPartConfigPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;

/**
 * @ClassName: TblTaskPartConfigSerMapper
 * @Description: 任务分片配置表查询接口类
 * @author renhanxiang
 * @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTaskPartConfigSerMapper extends PersistenceLayerSerMapper<TblTaskPartConfig>{

	/**
	 * @Title: query
	 * @Description: 查询实体对象-任务分片配置表
	 * @param String id
	 * @param entityClass
	 * @return TblTaskPartConfig 返回类型
	 */
	public TblTaskPartConfig query(String id);

	/**
	 * @Title: queryObj
	 * @Description: 查询实体对象-任务分片配置表
	 * @param TblTaskPartConfig
	 * @return TblTaskPartConfig 返回类型
	 */
	public TblTaskPartConfig queryObj(TblTaskPartConfig entity);

	/**
	 * @Title: queryPo
	 * @Description: 查询实体对象-任务分片配置表
	 * @param TaskPartConfigPo
	 * @return TaskPartConfigPo 返回类型
	 */
	public TaskPartConfigPo queryPo(TblTaskPartConfig entity);

	/**
	 * @Title: queryList
	 * @Description: 根据条件查询集合-任务分片配置表
	 * @param TblTaskPartConfig
	 * @return List<TblTaskPartConfig> 返回类型
	 */
	public List<TblTaskPartConfig> queryList(TblTaskPartConfig entity);

	/**
	 * @Title: queryTaskList
	 * @Description: 根据条件查询集合-参加任务分片的列表
	 * @param TblTaskPartConfig
	 * @return List<TblTaskPartConfig> 返回类型
	 */
	public List<TblTaskPartConfig> queryTaskList();

	public List<TblTaskPartConfig> queryNullList(String taskNo);
	
	/**
	 * @Title: queryPageListCount
	 * @Description: 根据条件查询总条数
	 * @param @param entity
	 * @param @return 设定文件
	 * @return int 返回类型
	 */
	public int queryPageListCount(PageQueryEntitys entity);


	public List<TblTaskPartConfig> queryUniquePartList();

}
