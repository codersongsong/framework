/**
* @Title: TblBusinessSystemSerMapper.java
* @Package com.gome.login.dao.mapper.ser
* @Description: 业务系统表查询接口类
* @author renhanxiang
* @date Fri Jan 06 10:14:43 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblBusinessSystem;
import cn.com.gome.framework.po.BusinessSystemPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblBusinessSystemSerMapper
* @Description: 业务系统表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblBusinessSystemSerMapper extends PersistenceLayerSerMapper<TblBusinessSystem>{

	/**
	* @Title: query
	* @Description:  查询实体对象-业务系统表
	* @param  String id
	* @param  entityClass
	* @return TblBusinessSystem    返回类型
	* @throws
	 */
	public TblBusinessSystem query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-业务系统表
	* @param  TblBusinessSystem
	* @return TblBusinessSystem    返回类型
	* @throws
	 */
	public TblBusinessSystem queryObj(TblBusinessSystem entity);
	public TblBusinessSystem queryObjMax(TblBusinessSystem entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-业务系统表
	* @param  BusinessSystemPo
	* @return BusinessSystemPo    返回类型
	* @throws
	 */
	public BusinessSystemPo queryPo(TblBusinessSystem entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-业务系统表
	* @param  TblBusinessSystem
	* @return List<TblBusinessSystem>    返回类型
	* @throws
	 */
	public List<TblBusinessSystem> queryList(TblBusinessSystem entity);
	public List<TblBusinessSystem> queryUserSysList(String userNo);

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
