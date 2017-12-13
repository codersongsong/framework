/**
* @Title: DubboClassCreateConfigPo.java
* @Package com.gome.framework.po
* @Description: DUBBO服务类生产配置实体类
* @author renhanxiang
* @date Fri May 05 14:26:30 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: DubboClassCreateConfigPo
 * @Description: DUBBO服务类生产配置实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_DUBBO_CLASS_CREATE_CONFIG
 */
@SuppressWarnings("unused")
public class DubboClassCreateConfigPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**类序号:CLASS_NO*/
	private Integer classNo;

	/**类序号:CLASS_NO*/
	private String classNo_colmun;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private Integer childProjectCode;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private String childProjectCode_colmun;

	/**生成类型:CREATE_TYPE*/
	private String createType;

	/**生成类型:CREATE_TYPE*/
	private String createType_colmun;

	/**DUBBO服务接口类:DUBBO_CLASS*/
	private String dubboClass;

	/**DUBBO服务接口类:DUBBO_CLASS*/
	private String dubboClass_colmun;

	/**服务接口类功能说明:DUBBO_CLASS_ASK*/
	private String dubboClassAsk;

	/**服务接口类功能说明:DUBBO_CLASS_ASK*/
	private String dubboClassAsk_colmun;

	/**DUBBO服务接口方法:DUBBO_CLASS_METHOD*/
	private String dubboClassMethod;

	/**DUBBO服务接口方法:DUBBO_CLASS_METHOD*/
	private String dubboClassMethod_colmun;

	/**服务接口方法说明:DUBBO_CLASS_METHOD_ASK*/
	private String dubboClassMethodAsk;

	/**服务接口方法说明:DUBBO_CLASS_METHOD_ASK*/
	private String dubboClassMethodAsk_colmun;

	/**服务层接口类:SERVICE_CALSS*/
	private String serviceCalss;

	/**服务层接口类:SERVICE_CALSS*/
	private String serviceCalss_colmun;

	/**服务层接口类功能说明:SERVICE_CALSS_ASK*/
	private String serviceCalssAsk;

	/**服务层接口类功能说明:SERVICE_CALSS_ASK*/
	private String serviceCalssAsk_colmun;

	/**服务层接口方法:SERVICE_CALSS_METHOD*/
	private String serviceCalssMethod;

	/**服务层接口方法:SERVICE_CALSS_METHOD*/
	private String serviceCalssMethod_colmun;

	/**服务层接口方法功能说明:SERVICE_CALSS_METHOD_ASK*/
	private String serviceCalssMethodAsk;

	/**服务层接口方法功能说明:SERVICE_CALSS_METHOD_ASK*/
	private String serviceCalssMethodAsk_colmun;

	/**业务层类名称:LOGIC_CALSS*/
	private String logicCalss;

	/**业务层类名称:LOGIC_CALSS*/
	private String logicCalss_colmun;

	/**业务层类功能说明:LOGIC_CALSS_ASK*/
	private String logicCalssAsk;

	/**业务层类功能说明:LOGIC_CALSS_ASK*/
	private String logicCalssAsk_colmun;

	/**保存地址:SAVE_ADDRESS*/
	private String saveAddress;

	/**保存地址:SAVE_ADDRESS*/
	private String saveAddress_colmun;

	/**添加时间:CREATE_TIME*/
	private String createTime;

	/**添加时间:CREATE_TIME*/
	private String createTime_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;

	/**类序号:CLASS_NO*/
	public Integer getClassNo() {
		return classNo;
	}

	/**类序号:CLASS_NO*/
	public String getClassNo_colmun() {
		String temp = "";
		if(null != classNo){
			temp = String.valueOf(classNo);
		}
		return temp;
	}

	/**类序号:CLASS_NO*/
	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
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

	/**生成类型:CREATE_TYPE*/
	public String getCreateType() {
		return createType;
	}

	/**生成类型:CREATE_TYPE*/
	public String getCreateType_colmun() {
		return createType;
	}

	/**生成类型:CREATE_TYPE*/
	public void setCreateType(String createType) {
		this.createType = createType;
	}

	/**DUBBO服务接口类:DUBBO_CLASS*/
	public String getDubboClass() {
		return dubboClass;
	}

	/**DUBBO服务接口类:DUBBO_CLASS*/
	public String getDubboClass_colmun() {
		return dubboClass;
	}

	/**DUBBO服务接口类:DUBBO_CLASS*/
	public void setDubboClass(String dubboClass) {
		this.dubboClass = dubboClass;
	}

	/**服务接口类功能说明:DUBBO_CLASS_ASK*/
	public String getDubboClassAsk() {
		return dubboClassAsk;
	}

	/**服务接口类功能说明:DUBBO_CLASS_ASK*/
	public String getDubboClassAsk_colmun() {
		return dubboClassAsk;
	}

	/**服务接口类功能说明:DUBBO_CLASS_ASK*/
	public void setDubboClassAsk(String dubboClassAsk) {
		this.dubboClassAsk = dubboClassAsk;
	}

	/**DUBBO服务接口方法:DUBBO_CLASS_METHOD*/
	public String getDubboClassMethod() {
		return dubboClassMethod;
	}

	/**DUBBO服务接口方法:DUBBO_CLASS_METHOD*/
	public String getDubboClassMethod_colmun() {
		return dubboClassMethod;
	}

	/**DUBBO服务接口方法:DUBBO_CLASS_METHOD*/
	public void setDubboClassMethod(String dubboClassMethod) {
		this.dubboClassMethod = dubboClassMethod;
	}

	/**服务接口方法说明:DUBBO_CLASS_METHOD_ASK*/
	public String getDubboClassMethodAsk() {
		return dubboClassMethodAsk;
	}

	/**服务接口方法说明:DUBBO_CLASS_METHOD_ASK*/
	public String getDubboClassMethodAsk_colmun() {
		return dubboClassMethodAsk;
	}

	/**服务接口方法说明:DUBBO_CLASS_METHOD_ASK*/
	public void setDubboClassMethodAsk(String dubboClassMethodAsk) {
		this.dubboClassMethodAsk = dubboClassMethodAsk;
	}

	/**服务层接口类:SERVICE_CALSS*/
	public String getServiceCalss() {
		return serviceCalss;
	}

	/**服务层接口类:SERVICE_CALSS*/
	public String getServiceCalss_colmun() {
		return serviceCalss;
	}

	/**服务层接口类:SERVICE_CALSS*/
	public void setServiceCalss(String serviceCalss) {
		this.serviceCalss = serviceCalss;
	}

	/**服务层接口类功能说明:SERVICE_CALSS_ASK*/
	public String getServiceCalssAsk() {
		return serviceCalssAsk;
	}

	/**服务层接口类功能说明:SERVICE_CALSS_ASK*/
	public String getServiceCalssAsk_colmun() {
		return serviceCalssAsk;
	}

	/**服务层接口类功能说明:SERVICE_CALSS_ASK*/
	public void setServiceCalssAsk(String serviceCalssAsk) {
		this.serviceCalssAsk = serviceCalssAsk;
	}

	/**服务层接口方法:SERVICE_CALSS_METHOD*/
	public String getServiceCalssMethod() {
		return serviceCalssMethod;
	}

	/**服务层接口方法:SERVICE_CALSS_METHOD*/
	public String getServiceCalssMethod_colmun() {
		return serviceCalssMethod;
	}

	/**服务层接口方法:SERVICE_CALSS_METHOD*/
	public void setServiceCalssMethod(String serviceCalssMethod) {
		this.serviceCalssMethod = serviceCalssMethod;
	}

	/**服务层接口方法功能说明:SERVICE_CALSS_METHOD_ASK*/
	public String getServiceCalssMethodAsk() {
		return serviceCalssMethodAsk;
	}

	/**服务层接口方法功能说明:SERVICE_CALSS_METHOD_ASK*/
	public String getServiceCalssMethodAsk_colmun() {
		return serviceCalssMethodAsk;
	}

	/**服务层接口方法功能说明:SERVICE_CALSS_METHOD_ASK*/
	public void setServiceCalssMethodAsk(String serviceCalssMethodAsk) {
		this.serviceCalssMethodAsk = serviceCalssMethodAsk;
	}

	/**业务层类名称:LOGIC_CALSS*/
	public String getLogicCalss() {
		return logicCalss;
	}

	/**业务层类名称:LOGIC_CALSS*/
	public String getLogicCalss_colmun() {
		return logicCalss;
	}

	/**业务层类名称:LOGIC_CALSS*/
	public void setLogicCalss(String logicCalss) {
		this.logicCalss = logicCalss;
	}

	/**业务层类功能说明:LOGIC_CALSS_ASK*/
	public String getLogicCalssAsk() {
		return logicCalssAsk;
	}

	/**业务层类功能说明:LOGIC_CALSS_ASK*/
	public String getLogicCalssAsk_colmun() {
		return logicCalssAsk;
	}

	/**业务层类功能说明:LOGIC_CALSS_ASK*/
	public void setLogicCalssAsk(String logicCalssAsk) {
		this.logicCalssAsk = logicCalssAsk;
	}

	/**保存地址:SAVE_ADDRESS*/
	public String getSaveAddress() {
		return saveAddress;
	}

	/**保存地址:SAVE_ADDRESS*/
	public String getSaveAddress_colmun() {
		return saveAddress;
	}

	/**保存地址:SAVE_ADDRESS*/
	public void setSaveAddress(String saveAddress) {
		this.saveAddress = saveAddress;
	}

	/**添加时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**添加时间:CREATE_TIME*/
	public String getCreateTime_colmun() {
		return createTime;
	}

	/**添加时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**修改时间:UP_TIME*/
	public String getUpTime() {
		return upTime;
	}

	/**修改时间:UP_TIME*/
	public String getUpTime_colmun() {
		return upTime;
	}

	/**修改时间:UP_TIME*/
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

}
