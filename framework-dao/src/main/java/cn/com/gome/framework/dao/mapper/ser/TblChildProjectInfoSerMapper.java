/**
* @Title: TblChildProjectInfoSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: 子项目信息配置表查询接口类
* @author renhanxiang
* @date Fri May 05 14:26:19 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.po.ChildProjectInfoPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblChildProjectInfoSerMapper
* @Description: 子项目信息配置表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblChildProjectInfoSerMapper extends PersistenceLayerSerMapper<TblChildProjectInfo>{

	/**
	* @Title: query
	* @Description:  查询实体对象-子项目信息配置表
	* @param  String id
	* @param  entityClass
	* @return TblChildProjectInfo    返回类型
	* @throws
	 */
	public TblChildProjectInfo query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-子项目信息配置表
	* @param  TblChildProjectInfo
	* @return TblChildProjectInfo    返回类型
	* @throws
	 */
	public TblChildProjectInfo queryObj(TblChildProjectInfo entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-子项目信息配置表
	* @param  ChildProjectInfoPo
	* @return ChildProjectInfoPo    返回类型
	* @throws
	 */
	public ChildProjectInfoPo queryPo(TblChildProjectInfo entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-子项目信息配置表
	* @param  TblChildProjectInfo
	* @return List<TblChildProjectInfo>    返回类型
	* @throws
	 */
	public List<TblChildProjectInfo> queryList(TblChildProjectInfo entity);

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
