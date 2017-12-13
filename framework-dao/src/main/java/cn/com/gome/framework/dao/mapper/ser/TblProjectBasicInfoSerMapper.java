/**
* @Title: TblProjectBasicInfoSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: 项目基本信息表查询接口类
* @author renhanxiang
* @date Fri May 05 14:26:15 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.po.ProjectBasicInfoPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblProjectBasicInfoSerMapper
* @Description: 项目基本信息表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblProjectBasicInfoSerMapper extends PersistenceLayerSerMapper<TblProjectBasicInfo>{

	/**
	* @Title: query
	* @Description:  查询实体对象-项目基本信息表
	* @param  String id
	* @param  entityClass
	* @return TblProjectBasicInfo    返回类型
	* @throws
	 */
	public TblProjectBasicInfo query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-项目基本信息表
	* @param  TblProjectBasicInfo
	* @return TblProjectBasicInfo    返回类型
	* @throws
	 */
	public TblProjectBasicInfo queryObj(TblProjectBasicInfo entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-项目基本信息表
	* @param  ProjectBasicInfoPo
	* @return ProjectBasicInfoPo    返回类型
	* @throws
	 */
	public ProjectBasicInfoPo queryPo(TblProjectBasicInfo entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-项目基本信息表
	* @param  TblProjectBasicInfo
	* @return List<TblProjectBasicInfo>    返回类型
	* @throws
	 */
	public List<TblProjectBasicInfo> queryList(TblProjectBasicInfo entity);

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
