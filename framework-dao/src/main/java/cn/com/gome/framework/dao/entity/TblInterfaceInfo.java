/**
* @Title: TblInterfaceInfo.java
* @Package cn.com.gome.framework.dao.entity
* @Description: 接口表实体类
* @author GOME
* @date Mon Dec 04 17:09:38 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.dao.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TblInterfaceInfo
 * @Description: 接口表实体类
 * @author GOME
 * @date Mon Dec 04 17:09:38 CST 2017
 * <br>tableName = TBL_INTERFACE_INFO
 */
public class TblInterfaceInfo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**接口ID:ID*/
	private String id;

	/**DUBBO接口ID:DUBBO_ID*/
	private String dubboId;

	/**接口名称:CLASS_NAME*/
	private String className;

	/**请求/响应:REQUEST_TYPE*/
	private String requestType;

	/**参数名称:PARAMNAME*/
	private String paramname;

	/**参数名称中文:PARAMNAME_CN*/
	private String paramnameCn;

	/**参数类型:PARAMTYPE*/
	private String paramtype;

	/**是否必须:IS_REQUIRE*/
	private String isRequire;

	/**是否日志打印:IS_PRINT*/
	private String isPrint;

	/**长度判断:IS_LENGTH*/
	private String isLength;

	/**格式判断:REGEX*/
	private String regex;

	/**参数说明:DESCRIPTION*/
	private String description;

	/**更新时间:UPDATE_TIME*/
	private String updateTime;

	/**创建时间:CREATE_TIME*/
	private String createTime;

	/**接口ID:ID*/
	public String getId() {
		return id;
	}

	/**接口ID:ID*/
	public void setId(String id) {
		this.id = id;
	}

	/**DUBBO接口ID:DUBBO_ID*/
	public String getDubboId() {
		return dubboId;
	}

	/**DUBBO接口ID:DUBBO_ID*/
	public void setDubboId(String dubboId) {
		this.dubboId = dubboId;
	}

	/**接口名称:CLASS_NAME*/
	public String getClassName() {
		return className;
	}

	/**接口名称:CLASS_NAME*/
	public void setClassName(String className) {
		this.className = className;
	}

	/**请求/响应:REQUEST_TYPE*/
	public String getRequestType() {
		return requestType;
	}

	/**请求/响应:REQUEST_TYPE*/
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**参数名称:PARAMNAME*/
	public String getParamname() {
		return paramname;
	}

	/**参数名称:PARAMNAME*/
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	/**参数名称中文:PARAMNAME_CN*/
	public String getParamnameCn() {
		return paramnameCn;
	}

	/**参数名称中文:PARAMNAME_CN*/
	public void setParamnameCn(String paramnameCn) {
		this.paramnameCn = paramnameCn;
	}

	/**参数类型:PARAMTYPE*/
	public String getParamtype() {
		return paramtype;
	}

	/**参数类型:PARAMTYPE*/
	public void setParamtype(String paramtype) {
		this.paramtype = paramtype;
	}

	/**是否必须:IS_REQUIRE*/
	public String getIsRequire() {
		return isRequire;
	}

	/**是否必须:IS_REQUIRE*/
	public void setIsRequire(String isRequire) {
		this.isRequire = isRequire;
	}

	/**是否日志打印:IS_PRINT*/
	public String getIsPrint() {
		return isPrint;
	}

	/**是否日志打印:IS_PRINT*/
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	/**长度判断:IS_LENGTH*/
	public String getIsLength() {
		return isLength;
	}

	/**长度判断:IS_LENGTH*/
	public void setIsLength(String isLength) {
		this.isLength = isLength;
	}

	/**格式判断:REGEX*/
	public String getRegex() {
		return regex;
	}

	/**格式判断:REGEX*/
	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**参数说明:DESCRIPTION*/
	public String getDescription() {
		return description;
	}

	/**参数说明:DESCRIPTION*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**更新时间:UPDATE_TIME*/
	public String getUpdateTime() {
		return updateTime;
	}

	/**更新时间:UPDATE_TIME*/
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**创建时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**创建时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
