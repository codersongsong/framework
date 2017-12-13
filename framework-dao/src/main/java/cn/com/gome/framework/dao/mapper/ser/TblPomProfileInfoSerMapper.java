/**
* @Title: TblPomProfileInfoSerMapper.java
* @Package cn.com.gome.framework.dao.mapper.ser
* @Description: pom文件profile信息管理查询接口类
* @author GOME
* @date Thu Nov 30 15:59:20 CST 2017
* @company com.gomeplus
* @version V1.0
*/
package cn.com.gome.framework.dao.mapper.ser;

import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.mybatis.MybatisRepository;

import java.util.List;

/**
* @ClassName: TblPomProfileInfoSerMapper
* @Description: pom文件profile信息管理查询接口类
* @author GOME
* @date 2015年2月10日 下午5:11:39
 */
@MybatisRepository
public interface TblPomProfileInfoSerMapper extends PersistenceLayerSerMapper<TblPomProfileInfo>{
    /**
     * 根据id列表查询列表
     *
     * @param list
     * @return
     */
    List<TblPomProfileInfo> queryListByIds(List<String> list);

    /**
     * 查询配置下拉列表
     */
    List<TblPomProfileInfo> queryPomProfiles(String projectCode);
}
