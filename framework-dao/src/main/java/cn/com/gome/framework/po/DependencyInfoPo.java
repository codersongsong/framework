/**
* @Title: DependencyInfoPo.java
* @Package cn.com.gome.framework.vo
* @Description: 依赖管理表实体类
* @author GOME
* @date Fri Dec 01 10:28:59 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import cn.com.gome.framework.util.VoUtil;
import com.gomeplus.jdbc.page.Entitys;
import java.math.BigDecimal;
import com.gomeplus.frame.utils.MoneyFormatUtils;

/** 
 * @ClassName: DependencyInfoPo
 * @Description: 依赖管理表实体类
 * @author GOME
 * @date Fri Dec 01 10:28:59 CST 2017
 * <br>tableName = TBL_DEPENDENCY_INFO
 */
@SuppressWarnings("unused")
public class DependencyInfoPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**主键ID:ID*/
	private String id;

	/**主键ID:ID*/
	private String id_colmun;

	/**pom文件groupId:GROUP_ID*/
	private String groupId;

	/**pom文件groupId:GROUP_ID*/
	private String groupId_colmun;

	/**pom文件artifactId:ARTIFACT_ID*/
	private String artifactId;

	/**pom文件artifactId:ARTIFACT_ID*/
	private String artifactId_colmun;

	/**pom文件版本号version:VERSION*/
	private String version;

	/**pom文件版本号version:VERSION*/
	private String version_colmun;

	/**pom文件scope:SCOPE*/
	private String scope;

	/**pom文件scope:SCOPE*/
	private String scope_colmun;

	/**依赖名称:DEPENDENCY_NAME*/
	private String dependencyName;

	/**依赖名称:DEPENDENCY_NAME*/
	private String dependencyName_colmun;

	/**创建时间:CREATE_TIME*/
	private String createTime;

	/**创建时间:CREATE_TIME*/
	private String createTime_colmun;

	/**更新时间:UPDATE_TIME*/
	private String updateTime;

	/**更新时间:UPDATE_TIME*/
	private String updateTime_colmun;

	/**配置文件名称:CONFIG_FILE_NAME*/
	private String configFileName;

	/**配置文件名称:CONFIG_FILE_NAME*/
	private String configFileName_colmun;

	/**配置文件内容:CONFIG_FILE_CONTENT*/
	private String configFileContent;

	/**配置文件内容:CONFIG_FILE_CONTENT*/
	private String configFileContent_colmun;

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

	/**pom文件groupId:GROUP_ID*/
	public String getGroupId() {
		return groupId;
	}

	/**pom文件groupId:GROUP_ID*/
	public String getGroupId_colmun() {
		return groupId;
	}

	/**pom文件groupId:GROUP_ID*/
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**pom文件artifactId:ARTIFACT_ID*/
	public String getArtifactId() {
		return artifactId;
	}

	/**pom文件artifactId:ARTIFACT_ID*/
	public String getArtifactId_colmun() {
		return artifactId;
	}

	/**pom文件artifactId:ARTIFACT_ID*/
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**pom文件版本号version:VERSION*/
	public String getVersion() {
		return version;
	}

	/**pom文件版本号version:VERSION*/
	public String getVersion_colmun() {
		return version;
	}

	/**pom文件版本号version:VERSION*/
	public void setVersion(String version) {
		this.version = version;
	}

	/**pom文件scope:SCOPE*/
	public String getScope() {
		return scope;
	}

	/**pom文件scope:SCOPE*/
	public String getScope_colmun() {
		return scope;
	}

	/**pom文件scope:SCOPE*/
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**依赖名称:DEPENDENCY_NAME*/
	public String getDependencyName() {
		return dependencyName;
	}

	/**依赖名称:DEPENDENCY_NAME*/
	public String getDependencyName_colmun() {
		return dependencyName;
	}

	/**依赖名称:DEPENDENCY_NAME*/
	public void setDependencyName(String dependencyName) {
		this.dependencyName = dependencyName;
	}

	/**
	 * 创建时间:CREATE_TIME
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:CREATE_TIME
	 */
	public String getCreateTime_colmun() {
		return VoUtil.getDateTimeCollmun(createTime);
	}

	/**
	 * 创建时间:CREATE_TIME
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间:UPDATE_TIME
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间:UPDATE_TIME
	 */
	public String getUpdateTime_colmun() {
		return VoUtil.getDateTimeCollmun(updateTime);
	}

	/**
	 * 更新时间:UPDATE_TIME
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**配置文件名称:CONFIG_FILE_NAME*/
	public String getConfigFileName() {
		return configFileName;
	}

	/**配置文件名称:CONFIG_FILE_NAME*/
	public String getConfigFileName_colmun() {
		return configFileName;
	}

	/**配置文件名称:CONFIG_FILE_NAME*/
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**配置文件内容:CONFIG_FILE_CONTENT*/
	public String getConfigFileContent() {
		return configFileContent;
	}

	/**配置文件内容:CONFIG_FILE_CONTENT*/
	public String getConfigFileContent_colmun() {
		return configFileContent;
	}

	/**配置文件内容:CONFIG_FILE_CONTENT*/
	public void setConfigFileContent(String configFileContent) {
		this.configFileContent = configFileContent;
	}

}
