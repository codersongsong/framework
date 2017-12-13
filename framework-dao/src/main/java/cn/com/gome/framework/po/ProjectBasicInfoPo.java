/**
* @Title: ProjectBasicInfoPo.java
* @Package com.gome.framework.po
* @Description: 项目基本信息表实体类
* @author renhanxiang
* @date Fri May 05 14:26:15 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: ProjectBasicInfoPo
 * @Description: 项目基本信息表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_PROJECT_BASIC_INFO
 */
@SuppressWarnings("unused")
public class ProjectBasicInfoPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**项目编码:PROJECT_CODE*/
	private Integer projectCode;

	/**项目编码:PROJECT_CODE*/
	private String projectCode_colmun;

	/**项目名称:PROJECT_NAME*/
	private String projectName;

	/**项目名称:PROJECT_NAME*/
	private String projectName_colmun;

	/**项目英文名:PROJECT_ENG*/
	private String projectEng;

	/**项目英文名:PROJECT_ENG*/
	private String projectEng_colmun;

	/**数据库类型:DATABASE_TYPE*/
	private String databaseType;

	/**数据库类型:DATABASE_TYPE*/
	private String databaseType_colmun;

	/**数据库驱动类:DATABASE_DRIVER*/
	private String databaseDriver;

	/**数据库驱动类:DATABASE_DRIVER*/
	private String databaseDriver_colmun;

	/**数据库连接URL:DATABASE_URL*/
	private String databaseUrl;

	/**数据库连接URL:DATABASE_URL*/
	private String databaseUrl_colmun;

	/**数据库账号:DATABASE_ACCOUNT*/
	private String databaseAccount;

	/**数据库账号:DATABASE_ACCOUNT*/
	private String databaseAccount_colmun;

	/**数据库密码:DATABASE_PASSWORD*/
	private String databasePassword;

	/**数据库密码:DATABASE_PASSWORD*/
	private String databasePassword_colmun;

	/**项目存放路径:PROJECT_PATH*/
	private String projectPath;

	/**项目存放路径:PROJECT_PATH*/
	private String projectPath_colmun;

	/**项目创建时间:CREATE_TIME*/
	private String createTime;

	/**项目创建时间:CREATE_TIME*/
	private String createTime_colmun;
	
	/**主包名:PACKAGES*/
	private String packages;
	
	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	/**项目编码:PROJECT_CODE*/
	public Integer getProjectCode() {
		return projectCode;
	}

	/**项目编码:PROJECT_CODE*/
	public String getProjectCode_colmun() {
		String temp = "";
		if(null != projectCode){
			temp = String.valueOf(projectCode);
		}
		return temp;
	}

	/**项目编码:PROJECT_CODE*/
	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	/**项目名称:PROJECT_NAME*/
	public String getProjectName() {
		return projectName;
	}

	/**项目名称:PROJECT_NAME*/
	public String getProjectName_colmun() {
		return projectName;
	}

	/**项目名称:PROJECT_NAME*/
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**项目英文名:PROJECT_ENG*/
	public String getProjectEng() {
		return projectEng;
	}

	/**项目英文名:PROJECT_ENG*/
	public String getProjectEng_colmun() {
		return projectEng;
	}

	/**项目英文名:PROJECT_ENG*/
	public void setProjectEng(String projectEng) {
		this.projectEng = projectEng;
	}

	/**数据库类型:DATABASE_TYPE*/
	public String getDatabaseType() {
		return databaseType;
	}

	/**数据库类型:DATABASE_TYPE*/
	public String getDatabaseType_colmun() {
		if("010".equals(databaseType)){
			return "mysql";
		}else if("020".equals(databaseType)){
			return "oracle";
		}else{
			return "其他";
		}
		
	}

	/**数据库类型:DATABASE_TYPE*/
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**数据库驱动类:DATABASE_DRIVER*/
	public String getDatabaseDriver() {
		return databaseDriver;
	}

	/**数据库驱动类:DATABASE_DRIVER*/
	public String getDatabaseDriver_colmun() {
		return databaseDriver;
	}

	/**数据库驱动类:DATABASE_DRIVER*/
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}

	/**数据库连接URL:DATABASE_URL*/
	public String getDatabaseUrl() {
		return databaseUrl;
	}

	/**数据库连接URL:DATABASE_URL*/
	public String getDatabaseUrl_colmun() {
		return databaseUrl;
	}

	/**数据库连接URL:DATABASE_URL*/
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	/**数据库账号:DATABASE_ACCOUNT*/
	public String getDatabaseAccount() {
		return databaseAccount;
	}

	/**数据库账号:DATABASE_ACCOUNT*/
	public String getDatabaseAccount_colmun() {
		return databaseAccount;
	}

	/**数据库账号:DATABASE_ACCOUNT*/
	public void setDatabaseAccount(String databaseAccount) {
		this.databaseAccount = databaseAccount;
	}

	/**数据库密码:DATABASE_PASSWORD*/
	public String getDatabasePassword() {
		return databasePassword;
	}

	/**数据库密码:DATABASE_PASSWORD*/
	public String getDatabasePassword_colmun() {
		return databasePassword;
	}

	/**数据库密码:DATABASE_PASSWORD*/
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
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
