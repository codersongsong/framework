/**
* @Title: TblTableDaoInfoSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: 表持久化信息表查询接口类
* @author renhanxiang
* @date Fri May 05 14:26:22 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.framework.po.TableDaoInfoPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblTableDaoInfoSerMapper
* @Description: 表持久化信息表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblTableDaoInfoSerMapper extends PersistenceLayerSerMapper<TblTableDaoInfo>{

	/**
	* @Title: query
	* @Description:  查询实体对象-表持久化信息表
	* @param  String id
	* @param  entityClass
	* @return TblTableDaoInfo    返回类型
	* @throws
	 */
	public TblTableDaoInfo query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-表持久化信息表
	* @param  TblTableDaoInfo
	* @return TblTableDaoInfo    返回类型
	* @throws
	 */
	public TblTableDaoInfo queryObj(TblTableDaoInfo entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-表持久化信息表
	* @param  TableDaoInfoPo
	* @return TableDaoInfoPo    返回类型
	* @throws
	 */
	public TableDaoInfoPo queryPo(TblTableDaoInfo entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-表持久化信息表
	* @param  TblTableDaoInfo
	* @return List<TblTableDaoInfo>    返回类型
	* @throws
	 */
	public List<TblTableDaoInfo> queryList(TblTableDaoInfo entity);

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
