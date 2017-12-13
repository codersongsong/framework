/**
 * @Title: TblInterfaceInfoEditMapper.java
 * @Package cn.com.gome.framework.dao.mapper.edit
 * @Description: 接口表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company com.gomepulus
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import java.util.Map;
import com.gomeplus.jdbc.mybatis.MybatisRepository;
import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import cn.com.gome.framework.dao.entity.TblInterfaceInfo;

/** 
 * @ClassName: TblInterfaceInfoEditMapper
 * @Description: 接口表的实体增、删、改接口
 * @author GOME
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblInterfaceInfoEditMapper extends PersistenceLayerEditMapper<TblInterfaceInfo>{

	/**
	* @Title: edit
	* @Description: 实体对象持久化修改-接口表
	* @param  Map<String , String>
	* @return int    返回类型
	* @throws
	 */
	public int editInfo(Map<String , String> reqMap);

}
