/**
* @Title: TblLoginSysDicSerMapper.java
* @Package com.gome.login.dao.mapper.ser
* @Description: 统一登录数据字典表查询接口类
* @author renhanxiang
* @date Fri Jan 13 18:29:54 CST 2017
* @company cn.com.gome
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import java.util.List;

import cn.com.gome.framework.dao.entity.TblLoginSysDic;
import cn.com.gome.framework.po.LoginSysDicPo;

import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.page.PageQueryEntitys;
/**
* @ClassName: TblLoginSysDicSerMapper
* @Description: 统一登录数据字典表查询接口类
* @author renhanxiang
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblLoginSysDicSerMapper extends PersistenceLayerSerMapper<TblLoginSysDic>{

	/**
	* @Title: query
	* @Description:  查询实体对象-统一登录数据字典表
	* @param  String id
	* @param  entityClass
	* @return TblLoginSysDic    返回类型
	* @throws
	 */
	public TblLoginSysDic query(String id);

	/**
	* @Title: queryObj
	* @Description: 查询实体对象-统一登录数据字典表
	* @param  TblLoginSysDic
	* @return TblLoginSysDic    返回类型
	* @throws
	 */
	public TblLoginSysDic queryObj(TblLoginSysDic entity);

	/**
	* @Title: queryPo
	* @Description: 查询实体对象-统一登录数据字典表
	* @param  LoginSysDicPo
	* @return LoginSysDicPo    返回类型
	* @throws
	 */
	public LoginSysDicPo queryPo(TblLoginSysDic entity);

	/**
	* @Title: queryList
	* @Description: 根据条件查询集合-统一登录数据字典表
	* @param  TblLoginSysDic
	* @return List<TblLoginSysDic>    返回类型
	* @throws
	 */
	public List<TblLoginSysDic> queryList(TblLoginSysDic entity);

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
