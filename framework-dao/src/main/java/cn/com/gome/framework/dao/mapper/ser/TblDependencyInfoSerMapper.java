/**
* @Title: TblDependencyInfoSerMapper.java
* @Package cn.com.gome.framework.dao.mapper.ser
* @Description: 依赖管理表查询接口类
* @author GOME
* @date Thu Nov 30 15:59:16 CST 2017
* @company com.gomeplus
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import cn.com.gome.framework.dao.entity.TblDependencyInfo;
import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

import java.util.List;

/**
* @ClassName: TblDependencyInfoSerMapper
* @Description: 依赖管理表查询接口类
* @author GOME
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblDependencyInfoSerMapper extends PersistenceLayerSerMapper<TblDependencyInfo>{
    /**
     * 根据id列表查询列表
     *
     * @param list
     * @return
     */
    List<TblDependencyInfo> queryListByIds(List<String> list);
}
