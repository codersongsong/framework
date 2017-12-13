/**
* @Title: TblChildProjectInfo.java
* @Package com.gome.framework.dao.entity
* @Description: 子项目信息配置表实体类
* @author renhanxiang
* @date Fri May 05 14:26:19 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.dao.entity;

import com.gomeplus.jdbc.page.Entitys;


/** 
 * @ClassName: TblChildProjectInfo
 * @Description: 子项目信息配置表实体类
 * @author GOME
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_CHILD_PROJECT_INFO
 */
public class TblChildProjectInfo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private Integer childProjectCode;

	/**项目编码:PROJECT_CODE*/
	private Integer projectCode;

	/**项目名称:CHILD_PROJECT_NAME*/
	private String childProjectName;

	/**项目英文名:CHILD_PROJECT_ENG*/
	private String childProjectEng;

	/**项目类型:PROJECT_TYPE*/
	private String projectType;
	
	/**项目存放路径:PROJECT_PATH*/
	private String projectPath;

	/**版本配置文件名:CONFIG_FILE_NAME*/
	private String configFileName;

	/**环境版本配置:VERSION_CONFIG*/
	private String versionConfig;

	/**基础依赖包:BASIC_JAR*/
	private String basicJar;

	/**项目创建时间:CREATE_TIME*/
	private String createTime;
	
	/**打包类型:PACKAGE_TYPE*/
	private String packageType;

	/**打包类型:PACKAGE_TYPE*/
	public String getPackageType() {
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
	public void setChildProjectCode(Integer childProjectCode) {
		this.childProjectCode = childProjectCode;
	}

	/**项目编码:PROJECT_CODE*/
	public Integer getProjectCode() {
		return projectCode;
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
	public void setChildProjectName(String childProjectName) {
		this.childProjectName = childProjectName;
	}

	/**项目英文名:CHILD_PROJECT_ENG*/
	public String getChildProjectEng() {
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

	/**项目类型:PROJECT_TYPE*/
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**项目存放路径:PROJECT_PATH*/
	public String getProjectPath() {
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
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**环境版本配置:VERSION_CONFIG*/
	public String getVersionConfig() {
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
	public void setBasicJar(String basicJar) {
		this.basicJar = basicJar;
	}

	/**项目创建时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**项目创建时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
