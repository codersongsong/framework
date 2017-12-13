/**
 * @Title: TblDependencyInfoEditMapper.java
 * @Package cn.com.gome.framework.dao.mapper.edit
 * @Description: 依赖管理表的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company com.gomepulus
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import cn.com.gome.framework.dao.entity.TblDependencyInfo;
import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

import java.util.Map;

/**
 * @author GOME
 * @ClassName: TblDependencyInfoEditMapper
 * @Description: 依赖管理表的实体增、删、改接口
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblDependencyInfoEditMapper extends PersistenceLayerEditMapper<TblDependencyInfo> {

    /**
     * @param reqMap <String , String>
     * @return int    返回类型
     * @throws
     * @Title: edit
     * @Description: 实体对象持久化修改-依赖管理表
     */
    public int editInfo(Map<String, String> reqMap);

}
