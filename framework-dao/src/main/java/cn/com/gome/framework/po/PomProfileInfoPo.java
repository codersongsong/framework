/**
 * @Title: PomProfileInfoPo.java
 * @Package cn.com.gome.framework.vo
 * @Description: pom文件profile信息管理实体类
 * @author GOME
 * @date Thu Nov 30 15:59:20 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.framework.po;

import cn.com.gome.framework.util.VoUtil;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.jdbc.page.Entitys;

/**
 * @ClassName: PomProfileInfoPo
 * @Description: pom文件profile信息管理实体类
 * @author GOME
 * @date Thu Nov 30 15:59:20 CST 2017
 * <br>tableName = TBL_POM_PROFILE_INFO
 */
@SuppressWarnings("unused")
public class PomProfileInfoPo extends Entitys {

    private static final long serialVersionUID = 1L;

    /**主键ID:ID*/
    private String id;

    /**主键ID:ID*/
    private String id_colmun;

    /**配置描述:CONFIG_DESCRIPTION*/
    private String configDescription;

    /**配置描述:CONFIG_DESCRIPTION*/
    private String configDescription_colmun;

    /**配置标签:CONFIG_NAME*/
    private String configName;

    /**配置标签:CONFIG_NAME*/
    private String configName_colmun;

    /**配置类型：010：pom文件，020：properties文件，默认010:TYPE*/
    private String type;

    /**配置类型：010：pom文件，020：properties文件，默认010:TYPE*/
    private String type_colmun;

    /**关联依赖ID:AND_DEPENDENCY_ID*/
    private String andDependencyId;

    /**关联依赖ID:AND_DEPENDENCY_ID*/
    private String andDependencyId_colmun;

    /**开发环境配置:ENV_DEV*/
    private String envDev;

    /**开发环境配置:ENV_DEV*/
    private String envDev_colmun;

    /**UAT环境配置:ENV_UAT*/
    private String envUat;

    /**UAT环境配置:ENV_UAT*/
    private String envUat_colmun;

    /**PRE环境配置:ENV_PRE*/
    private String envPre;

    /**PRE环境配置:ENV_PRE*/
    private String envPre_colmun;

    /**生产环境配置:ENV_LIVE*/
    private String envLive;

    /**生产环境配置:ENV_LIVE*/
    private String envLive_colmun;

    /**分组:GROUP_INFO*/
    private String groupInfo;

    /**分组:GROUP_INFO*/
    private String groupInfo_colmun;

    /**创建时间:CREATE_TIME*/
    private String createTime;

    /**创建时间:CREATE_TIME*/
    private String createTime_colmun;

    /**更新时间:UPDATE_TIME*/
    private String updateTime;

    /**更新时间:UPDATE_TIME*/
    private String updateTime_colmun;

    /**
     * 关联主项目ID:AND_PROJECT_ID
     */
    private String andProjectId;

    /**
     * 关联主项目ID:AND_PROJECT_ID
     */
    private String andProjectId_colmun;

    /**主键ID:ID*/
    public String getId() {
        return id;
    }

    /**主键ID:ID*/
    public String getId_colmun() {
        return id;
    }

    /**主键ID:ID*/
    public void setId(String id) {
        this.id = id;
    }

    /**配置描述:CONFIG_DESCRIPTION*/
    public String getConfigDescription() {
        return configDescription;
    }

    /**配置描述:CONFIG_DESCRIPTION*/
    public String getConfigDescription_colmun() {
        return configDescription;
    }

    /**配置描述:CONFIG_DESCRIPTION*/
    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }

    /**配置标签:CONFIG_NAME*/
    public String getConfigName() {
        return configName;
    }

    /**配置标签:CONFIG_NAME*/
    public String getConfigName_colmun() {
        return configName;
    }

    /**配置标签:CONFIG_NAME*/
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**配置类型：010：pom文件，020：properties文件，默认010:TYPE*/
    public String getType() {
        return type;
    }

    /**配置类型：010：pom文件，020：properties文件，默认010:TYPE*/
    public String getType_colmun() {
        String temp = "";
        if (null != type) {
            temp = GlobalApplicationCache.getInstance().getStr("PROFILE_TYPE." + type);
        }
        return temp;
    }

    /**配置类型：010：pom文件，020：properties文件，默认010:TYPE*/
    public void setType(String type) {
        this.type = type;
    }

    /**关联依赖ID:AND_DEPENDENCY_ID*/
    public String getAndDependencyId() {
        return andDependencyId;
    }

    /**关联依赖ID:AND_DEPENDENCY_ID*/
    public String getAndDependencyId_colmun() {
        String temp = "";
        if (null != andDependencyId) {
            temp = GlobalApplicationCache.getInstance().getStr("DEPENDENCY." + andDependencyId);
        }
        return temp;
    }

    /**关联依赖ID:AND_DEPENDENCY_ID*/
    public void setAndDependencyId(String andDependencyId) {
        this.andDependencyId = andDependencyId;
    }

    /**开发环境配置:ENV_DEV*/
    public String getEnvDev() {
        return envDev;
    }

    /**开发环境配置:ENV_DEV*/
    public String getEnvDev_colmun() {
        return envDev;
    }

    /**开发环境配置:ENV_DEV*/
    public void setEnvDev(String envDev) {
        this.envDev = envDev;
    }

    /**UAT环境配置:ENV_UAT*/
    public String getEnvUat() {
        return envUat;
    }

    /**UAT环境配置:ENV_UAT*/
    public String getEnvUat_colmun() {
        return envUat;
    }

    /**UAT环境配置:ENV_UAT*/
    public void setEnvUat(String envUat) {
        this.envUat = envUat;
    }

    /**PRE环境配置:ENV_PRE*/
    public String getEnvPre() {
        return envPre;
    }

    /**PRE环境配置:ENV_PRE*/
    public String getEnvPre_colmun() {
        return envPre;
    }

    /**PRE环境配置:ENV_PRE*/
    public void setEnvPre(String envPre) {
        this.envPre = envPre;
    }

    /**生产环境配置:ENV_LIVE*/
    public String getEnvLive() {
        return envLive;
    }

    /**生产环境配置:ENV_LIVE*/
    public String getEnvLive_colmun() {
        return envLive;
    }

    /**生产环境配置:ENV_LIVE*/
    public void setEnvLive(String envLive) {
        this.envLive = envLive;
    }

    /**分组:GROUP_INFO*/
    public String getGroupInfo() {
        return groupInfo;
    }

    /**分组:GROUP_INFO*/
    public String getGroupInfo_colmun() {
        return groupInfo;
    }

    /**分组:GROUP_INFO*/
    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    /**创建时间:CREATE_TIME*/
    public String getCreateTime() {
        return createTime;
    }

    /**创建时间:CREATE_TIME*/
    public String getCreateTime_colmun() {
        return VoUtil.getDateTimeCollmun(createTime);
    }

    /**创建时间:CREATE_TIME*/
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**更新时间:UPDATE_TIME*/
    public String getUpdateTime() {
        return updateTime;
    }

    /**更新时间:UPDATE_TIME*/
    public String getUpdateTime_colmun() {
        return VoUtil.getDateTimeCollmun(updateTime);
    }

    /**更新时间:UPDATE_TIME*/
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 关联主项目ID:AND_PROJECT_ID
     */
    public String getAndProjectId() {
        return andProjectId;
    }

    /**
     * 关联主项目ID:AND_PROJECT_ID
     */
    public String getAndProjectId_colmun() {
        String temp = "";
        if (null != andProjectId) {
            temp = GlobalApplicationCache.getInstance().getStr("PROJECT_CODE." + andProjectId);
        }
        return temp;
    }

    /**
     * 关联主项目ID:AND_PROJECT_ID
     */
    public void setAndProjectId(String andProjectId) {
        this.andProjectId = andProjectId;
    }

}
