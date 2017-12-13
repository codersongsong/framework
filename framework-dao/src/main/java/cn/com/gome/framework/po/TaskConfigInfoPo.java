/**
* @Title: TaskConfigInfoPo.java
* @Package com.gome.login.po
* @Description: 任务基本信息配置表实体类
* @author renhanxiang
* @date Fri Mar 24 15:43:30 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TaskConfigInfoPo
 * @Description: 任务基本信息配置表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TASK_CONFIG_INFO
 */
@SuppressWarnings("unused")
public class TaskConfigInfoPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**任务编号:TASK_NO*/
	private String taskNo;

	/**任务编号:TASK_NO*/
	private String taskNo_colmun;

	/**所属业务系统:SYS_NO*/
	private Integer sysNo;

	/**所属业务系统:SYS_NO*/
	private String sysNo_colmun;

	/**任务名称:TASK_NAME*/
	private String taskName;

	/**任务名称:TASK_NAME*/
	private String taskName_colmun;

	/**执行规则:RUN_RULE*/
	private String runRule;

	/**执行规则:RUN_RULE*/
	private String runRule_colmun;

	/**业务对象名称:BUSINESS_OBJ_NAME*/
	private String businessObjName;

	/**业务对象名称:BUSINESS_OBJ_NAME*/
	private String businessObjName_colmun;

	/**单实例最大任务数:TASK_COUNT*/
	private Integer taskCount;

	/**单实例最大任务数:TASK_COUNT*/
	private String taskCount_colmun;

	/**单实例单任务并发上限数:ONETASK_LIMIT_COUNT*/
	private Integer onetaskLimitCount;

	/**单实例单任务并发上限数:ONETASK_LIMIT_COUNT*/
	private String onetaskLimitCount_colmun;

	/**全局任务数:FULL_TASK_COUNT*/
	private Integer fullTaskCount;

	/**全局任务数:FULL_TASK_COUNT*/
	private String fullTaskCount_colmun;

	/**业务参数:BUSINESS_INFO*/
	private String businessInfo;

	/**业务参数:BUSINESS_INFO*/
	private String businessInfo_colmun;

	/**分组标示:GROUP_TAL*/
	private String groupTal;

	/**分组标示:GROUP_TAL*/
	private String groupTal_colmun;

	/**任务执行模板:TASK_RUN_TEMPLATE*/
	private String taskRunTemplate;

	/**任务执行模板:TASK_RUN_TEMPLATE*/
	private String taskRunTemplate_colmun;

	/**任务状态:STATUS*/
	private Integer status;

	/**任务状态:STATUS*/
	private String status_colmun;

	/**配置时间:CONFIG_TIME*/
	private String configTime;

	/**配置时间:CONFIG_TIME*/
	private String configTime_colmun;

	/**修改人:UP_PERSON*/
	private String upPerson;

	/**修改人:UP_PERSON*/
	private String upPerson_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;
	
	/**预计实例个数:ESTIMATE_OBJ_COUNT*/
	private Integer estimateObjCount;
	
	/**预计实例个数:ESTIMATE_OBJ_COUNT*/
	public Integer getEstimateObjCount() {
		return estimateObjCount;
	}

	/**预计实例个数:ESTIMATE_OBJ_COUNT*/
	public void setEstimateObjCount(Integer estimateObjCount) {
		this.estimateObjCount = estimateObjCount;
	}

	/**任务编号:TASK_NO*/
	public String getTaskNo() {
		return taskNo;
	}

	/**任务编号:TASK_NO*/
	public String getTaskNo_colmun() {
		return taskNo;
	}

	/**任务编号:TASK_NO*/
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	/**所属业务系统:SYS_NO*/
	public Integer getSysNo() {
		return sysNo;
	}

	/**所属业务系统:SYS_NO*/
	public String getSysNo_colmun() {
		String temp = "";
		if(null != sysNo){
			temp = GlobalApplicationCache.getInstance().getStr("SYS_NO." + sysNo);
		}
		return temp;
	}

	/**所属业务系统:SYS_NO*/
	public void setSysNo(Integer sysNo) {
		this.sysNo = sysNo;
	}

	/**任务名称:TASK_NAME*/
	public String getTaskName() {
		return taskName;
	}

	/**任务名称:TASK_NAME*/
	public String getTaskName_colmun() {
		return taskName;
	}

	/**任务名称:TASK_NAME*/
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**执行规则:RUN_RULE*/
	public String getRunRule() {
		return runRule;
	}

	/**执行规则:RUN_RULE*/
	public String getRunRule_colmun() {
		return runRule;
	}

	/**执行规则:RUN_RULE*/
	public void setRunRule(String runRule) {
		this.runRule = runRule;
	}

	/**业务对象名称:BUSINESS_OBJ_NAME*/
	public String getBusinessObjName() {
		return businessObjName;
	}

	/**业务对象名称:BUSINESS_OBJ_NAME*/
	public String getBusinessObjName_colmun() {
		return businessObjName;
	}

	/**业务对象名称:BUSINESS_OBJ_NAME*/
	public void setBusinessObjName(String businessObjName) {
		this.businessObjName = businessObjName;
	}

	/**单实例最大任务数:TASK_COUNT*/
	public Integer getTaskCount() {
		return taskCount;
	}

	/**单实例最大任务数:TASK_COUNT*/
	public String getTaskCount_colmun() {
		String temp = "";
		if(null != taskCount){
			temp = String.valueOf(taskCount);
		}
		return temp;
	}

	/**单实例最大任务数:TASK_COUNT*/
	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	/**单实例单任务并发上限数:ONETASK_LIMIT_COUNT*/
	public Integer getOnetaskLimitCount() {
		return onetaskLimitCount;
	}

	/**单实例单任务并发上限数:ONETASK_LIMIT_COUNT*/
	public String getOnetaskLimitCount_colmun() {
		String temp = "";
		if(null != onetaskLimitCount){
			temp = String.valueOf(onetaskLimitCount);
		}
		return temp;
	}

	/**单实例单任务并发上限数:ONETASK_LIMIT_COUNT*/
	public void setOnetaskLimitCount(Integer onetaskLimitCount) {
		this.onetaskLimitCount = onetaskLimitCount;
	}

	/**全局任务数:FULL_TASK_COUNT*/
	public Integer getFullTaskCount() {
		return fullTaskCount;
	}

	/**全局任务数:FULL_TASK_COUNT*/
	public String getFullTaskCount_colmun() {
		String temp = "";
		if(null != fullTaskCount){
			temp = String.valueOf(fullTaskCount);
		}
		return temp;
	}

	/**全局任务数:FULL_TASK_COUNT*/
	public void setFullTaskCount(Integer fullTaskCount) {
		this.fullTaskCount = fullTaskCount;
	}

	/**业务参数:BUSINESS_INFO*/
	public String getBusinessInfo() {
		return businessInfo;
	}

	/**业务参数:BUSINESS_INFO*/
	public String getBusinessInfo_colmun() {
		return businessInfo;
	}

	/**业务参数:BUSINESS_INFO*/
	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	/**分组标示:GROUP_TAL*/
	public String getGroupTal() {
		return groupTal;
	}

	/**分组标示:GROUP_TAL*/
	public String getGroupTal_colmun() {
		return groupTal;
	}

	/**分组标示:GROUP_TAL*/
	public void setGroupTal(String groupTal) {
		this.groupTal = groupTal;
	}

	/**任务执行模板:TASK_RUN_TEMPLATE*/
	public String getTaskRunTemplate() {
		return taskRunTemplate;
	}

	/**任务执行模板:TASK_RUN_TEMPLATE*/
	public String getTaskRunTemplate_colmun() {
		String temp = "";
		if(null != taskRunTemplate){
			temp = GlobalApplicationCache.getInstance().getStr("TASK_RUN_TEMPLATE." + taskRunTemplate);
		}
		return temp;
	}

	/**任务执行模板:TASK_RUN_TEMPLATE*/
	public void setTaskRunTemplate(String taskRunTemplate) {
		this.taskRunTemplate = taskRunTemplate;
	}

	/**任务状态:STATUS*/
	public Integer getStatus() {
		return status;
	}

	/**任务状态:STATUS*/
	public String getStatus_colmun() {
		String temp = "";
		if(null != status){
			temp = GlobalApplicationCache.getInstance().getStr("STATUS." + status);
		}
		return temp;
	}

	/**任务状态:STATUS*/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**配置时间:CONFIG_TIME*/
	public String getConfigTime() {
		return configTime;
	}

	/**配置时间:CONFIG_TIME*/
	public String getConfigTime_colmun() {
		return configTime;
	}

	/**配置时间:CONFIG_TIME*/
	public void setConfigTime(String configTime) {
		this.configTime = configTime;
	}

	/**修改人:UP_PERSON*/
	public String getUpPerson() {
		return upPerson;
	}

	/**修改人:UP_PERSON*/
	public String getUpPerson_colmun() {
		return upPerson;
	}

	/**修改人:UP_PERSON*/
	public void setUpPerson(String upPerson) {
		this.upPerson = upPerson;
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
