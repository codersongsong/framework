/**
 * @Title: TblPomProfileInfoEditMapper.java
 * @Package cn.com.gome.framework.dao.mapper.edit
 * @Description: pom文件profile信息管理的实体增、删、改接口
 * @author chenmin-ds
 * @date 2015年2月12日 下午3:47:57
 * @company com.gomepulus
 * @version V1.0
 */


package cn.com.gome.framework.dao.mapper.edit;

import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

import java.util.Map;

/**
 * @author GOME
 * @ClassName: TblPomProfileInfoEditMapper
 * @Description: pom文件profile信息管理的实体增、删、改接口
 * @date 2015年2月12日 下午3:47:57
 */
@MybatisRepository
public interface TblPomProfileInfoEditMapper extends PersistenceLayerEditMapper<TblPomProfileInfo> {

    /**
     * @param reqMap <String , String>
     * @return int    返回类型
     * @throws
     * @Title: edit
     * @Description: 实体对象持久化修改-pom文件profile信息管理
     */
    public int editInfo(Map<String, String> reqMap);

}
