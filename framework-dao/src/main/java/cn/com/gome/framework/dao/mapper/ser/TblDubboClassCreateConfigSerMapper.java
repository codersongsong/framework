/**
* @Title: TblDubboClassCreateConfigSerMapper.java
* @Package com.gome.framework.dao.mapper.ser
* @Description: DUBBO服务类生产配置查询接口类
* @author renhanxiang
* @date Fri May 05 14:26:30 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import cn.com.gome.framework.po.DubboClassCreateConfigPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblDubboClassCreateConfigSerMapper
* @Description: DUBBO服务类生产配置查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblDubboClassCreateConfigSerMapper extends PersistenceLayerSerMapper<TblDubboClassCreateConfig>{

	/**
	* @Title: query
	* @Description:  查询实体对象-DUBBO服务类生产配置
	* @param  String id
	* @param  entityClass
	* @return TblDubboClassCreateConfig    返回类型
	* @throws
	 */
	public TblDubboClassCreateConfig query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-DUBBO服务类生产配置
	* @param  TblDubboClassCreateConfig
	* @return TblDubboClassCreateConfig    返回类型
	* @throws
	 */
	public TblDubboClassCreateConfig queryObj(TblDubboClassCreateConfig entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-DUBBO服务类生产配置
	* @param  DubboClassCreateConfigPo
	* @return DubboClassCreateConfigPo    返回类型
	* @throws
	 */
	public DubboClassCreateConfigPo queryPo(TblDubboClassCreateConfig entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-DUBBO服务类生产配置
	* @param  TblDubboClassCreateConfig
	* @return List<TblDubboClassCreateConfig>    返回类型
	* @throws
	 */
	public List<TblDubboClassCreateConfig> queryList(TblDubboClassCreateConfig entity);

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
