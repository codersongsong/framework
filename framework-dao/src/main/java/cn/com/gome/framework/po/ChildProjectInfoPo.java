/**
* @Title: ChildProjectInfoPo.java
* @Package com.gome.framework.po
* @Description: 子项目信息配置表实体类
* @author renhanxiang
* @date Fri May 05 14:26:19 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: ChildProjectInfoPo
 * @Description: 子项目信息配置表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_CHILD_PROJECT_INFO
 */
@SuppressWarnings("unused")
public class ChildProjectInfoPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private Integer childProjectCode;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private String childProjectCode_colmun;

	/**项目编码:PROJECT_CODE*/
	private Integer projectCode;

	/**项目编码:PROJECT_CODE*/
	private String projectCode_colmun;

	/**项目名称:CHILD_PROJECT_NAME*/
	private String childProjectName;

	/**项目名称:CHILD_PROJECT_NAME*/
	private String childProjectName_colmun;

	/**项目英文名:CHILD_PROJECT_ENG*/
	private String childProjectEng;

	/**项目英文名:CHILD_PROJECT_ENG*/
	private String childProjectEng_colmun;

	/**项目类型:PROJECT_TYPE*/
	private String projectType;

	/**项目类型:PROJECT_TYPE*/
	private String projectType_colmun;

	/**项目存放路径:PROJECT_PATH*/
	private String projectPath;

	/**项目存放路径:PROJECT_PATH*/
	private String projectPath_colmun;

	/**版本配置文件名:CONFIG_FILE_NAME*/
	private String configFileName;

	/**版本配置文件名:CONFIG_FILE_NAME*/
	private String configFileName_colmun;

	/**环境版本配置:VERSION_CONFIG*/
	private String versionConfig;

	/**环境版本配置:VERSION_CONFIG*/
	private String versionConfig_colmun;

	/**基础依赖包:BASIC_JAR*/
	private String basicJar;

	/**基础依赖包:BASIC_JAR*/
	private String basicJar_colmun;

	/**项目创建时间:CREATE_TIME*/
	private String createTime;

	/**项目创建时间:CREATE_TIME*/
	private String createTime_colmun;
	
	/**打包类型:PACKAGE_TYPE*/
	private String packageType;
	
	/**打包类型:PACKAGE_TYPE*/
	private String packageType_colmun;

	/**打包类型:PACKAGE_TYPE*/
	public String getPackageType() {
		return packageType;
	}
	
	/**打包类型:PACKAGE_TYPE*/
	public String getPackageType_colmun() {
		return packageType;
	}

	/**打包类型:PACKAGE_TYPE*/
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public Integer getChildProjectCode() {
		return childProjectCode;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public String getChildProjectCode_colmun() {
		String temp = "";
		if(null != childProjectCode){
			temp = String.valueOf(childProjectCode);
		}
		return temp;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public void setChildProjectCode(Integer childProjectCode) {
		this.childProjectCode = childProjectCode;
	}

	/**项目编码:PROJECT_CODE*/
	public Integer getProjectCode() {
		return projectCode;
	}

	/**项目编码:PROJECT_CODE*/
	public String getProjectCode_colmun() {
		String temp = "";
		if(null != projectCode){
			temp = GlobalApplicationCache.getInstance().getStr("PROJECT_CODE." + projectCode);
		}
		return temp;
	}

	/**项目编码:PROJECT_CODE*/
	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	/**项目名称:CHILD_PROJECT_NAME*/
	public String getChildProjectName() {
		return childProjectName;
	}

	/**项目名称:CHILD_PROJECT_NAME*/
	public String getChildProjectName_colmun() {
		return childProjectName;
	}

	/**项目名称:CHILD_PROJECT_NAME*/
	public void setChildProjectName(String childProjectName) {
		this.childProjectName = childProjectName;
	}

	/**项目英文名:CHILD_PROJECT_ENG*/
	public String getChildProjectEng() {
		return childProjectEng;
	}

	/**项目英文名:CHILD_PROJECT_ENG*/
	public String getChildProjectEng_colmun() {
		return childProjectEng;
	}

	/**项目英文名:CHILD_PROJECT_ENG*/
	public void setChildProjectEng(String childProjectEng) {
		this.childProjectEng = childProjectEng;
	}

	/**项目类型:PROJECT_TYPE*/
	public String getProjectType() {
		return projectType;
	}

	/**
	 * 项目类型:PROJECT_TYPE
	 * {label: '持久层',value: '010'},{label: '前端WEB',value: '020'},{label: 'API接口',value: '030'},{label: 'DUBBO服务',value: '040'},{label: '定时任务',value: '050'}
	 * */
	public String getProjectType_colmun() {
		if("010".equals(projectType)){
			return "持久层";
		}else if("020".equals(projectType)){
			return "前端WEB";
		}else if("030".equals(projectType)){
			return "API接口";
		}else if("040".equals(projectType)){
			return "DUBBO服务";
		}else if("050".equals(projectType)){
			return "定时任务";
		}else if("060".equals(projectType)){
			return "后台管理";
		} else if ("999".equals(projectType)) {
			return "其他";
		}
		return projectType;
	}

	/**项目类型:PROJECT_TYPE*/
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**项目存放路径:PROJECT_PATH*/
	public String getProjectPath() {
		return projectPath;
	}

	/**项目存放路径:PROJECT_PATH*/
	public String getProjectPath_colmun() {
		return projectPath;
	}

	/**项目存放路径:PROJECT_PATH*/
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	/**版本配置文件名:CONFIG_FILE_NAME*/
	public String getConfigFileName() {
		return configFileName;
	}

	/**版本配置文件名:CONFIG_FILE_NAME*/
	public String getConfigFileName_colmun() {
		return configFileName;
	}

	/**版本配置文件名:CONFIG_FILE_NAME*/
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**环境版本配置:VERSION_CONFIG*/
	public String getVersionConfig() {
		return versionConfig;
	}

	/**环境版本配置:VERSION_CONFIG*/
	public String getVersionConfig_colmun() {
		return versionConfig;
	}

	/**环境版本配置:VERSION_CONFIG*/
	public void setVersionConfig(String versionConfig) {
		this.versionConfig = versionConfig;
	}

	/**基础依赖包:BASIC_JAR*/
	public String getBasicJar() {
		return basicJar;
	}

	/**基础依赖包:BASIC_JAR*/
	public String getBasicJar_colmun() {
		return basicJar;
	}

	/**基础依赖包:BASIC_JAR*/
	public void setBasicJar(String basicJar) {
		this.basicJar = basicJar;
	}

	/**项目创建时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**项目创建时间:CREATE_TIME*/
	public String getCreateTime_colmun() {
		return createTime;
	}

	/**项目创建时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
