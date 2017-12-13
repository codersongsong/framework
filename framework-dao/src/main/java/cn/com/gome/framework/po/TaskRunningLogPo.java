/**
* @Title: TaskRunningLogPo.java
* @Package com.gome.login.po
* @Description: 任务运行日志表实体类
* @author renhanxiang
* @date Fri Mar 24 15:43:27 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TaskRunningLogPo
 * @Description: 任务运行日志表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TASK_RUNNING_LOG
 */
@SuppressWarnings("unused")
public class TaskRunningLogPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**运行编号:RUN_NO*/
	private String runNo;

	/**运行编号:RUN_NO*/
	private String runNo_colmun;

	/**任务编号:TASK_NO*/
	private String taskNo;

	/**任务编号:TASK_NO*/
	private String taskNo_colmun;

	/**实例任务名称:OBJ_TASK_NAME*/
	private String objTaskName;

	/**实例任务名称:OBJ_TASK_NAME*/
	private String objTaskName_colmun;

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal;

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal_colmun;

	/**任务运行服务器:TASK_RUN_SERVER*/
	private String taskRunServer;

	/**任务运行服务器:TASK_RUN_SERVER*/
	private String taskRunServer_colmun;

	/**任务运行服务器端口:TASK_RUN_PORT*/
	private Integer taskRunPort;

	/**任务运行服务器端口:TASK_RUN_PORT*/
	private String taskRunPort_colmun;

	/**分片值:PART_VALUE*/
	private String partValue;

	/**分片值:PART_VALUE*/
	private String partValue_colmun;


	/**取模值:MODE_VALUE*/
	private String modeValue;

	/**取模值:MODE_VALUE*/
	private String modeValue_colmun;

	/**执行次数*/
	private String executeCount;

	/**执行次数*/
	private String executeCount_colmun;

	/**取模值:MODE_VALUE*/
	public String getModeValue() {
		return modeValue;
	}

	/**取模值:MODE_VALUE*/
	public String getModeValue_colmun() {
		return modeValue;
	}

	/**取模值:MODE_VALUE*/
	public void setModeValue(String modeValue) {
		this.modeValue = modeValue;
	}

	/**上次运行时间:PRE_RUN_TIME*/
	private String preRunTime;

	/**上次运行时间:PRE_RUN_TIME*/
	private String preRunTime_colmun;

	/**最新运行时间:NEXT_RUN_TIME*/
	private String nextRunTime;

	/**最新运行时间:NEXT_RUN_TIME*/
	private String nextRunTime_colmun;

	/**操作状态:DDL_STATUS*/
	private String ddlStatus;

	/**操作状态:DDL_STATUS*/
	private String ddlStatus_colmun;

	/**修改人:UP_PERSON*/
	private String upPerson;

	/**修改人:UP_PERSON*/
	private String upPerson_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;

	/**运行编号:RUN_NO*/
	public String getRunNo() {
		return runNo;
	}

	/**运行编号:RUN_NO*/
	public String getRunNo_colmun() {
		return runNo;
	}

	/**运行编号:RUN_NO*/
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	/**任务编号:TASK_NO*/
	public String getTaskNo() {
		return taskNo;
	}

	/**任务编号:TASK_NO*/
	public String getTaskNo_colmun() {
		String temp = "";
		if(null != taskNo){
			temp = GlobalApplicationCache.getInstance().getStr("TASK_NO." + taskNo);
		}
		return temp;
	}

	/**任务编号:TASK_NO*/
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	/**实例任务名称:OBJ_TASK_NAME*/
	public String getObjTaskName() {
		return objTaskName;
	}

	/**实例任务名称:OBJ_TASK_NAME*/
	public String getObjTaskName_colmun() {
		return objTaskName;
	}

	/**实例任务名称:OBJ_TASK_NAME*/
	public void setObjTaskName(String objTaskName) {
		this.objTaskName = objTaskName;
	}

	/**实例唯一标示:OBJECT_TAL*/
	public String getObjectTal() {
		return objectTal;
	}

	/**实例唯一标示:OBJECT_TAL*/
	public String getObjectTal_colmun() {
		return objectTal;
	}

	/**实例唯一标示:OBJECT_TAL*/
	public void setObjectTal(String objectTal) {
		this.objectTal = objectTal;
	}

	/**任务运行服务器:TASK_RUN_SERVER*/
	public String getTaskRunServer() {
		return taskRunServer;
	}

	/**任务运行服务器:TASK_RUN_SERVER*/
	public String getTaskRunServer_colmun() {
		return taskRunServer;
	}

	/**任务运行服务器:TASK_RUN_SERVER*/
	public void setTaskRunServer(String taskRunServer) {
		this.taskRunServer = taskRunServer;
	}

	/**任务运行服务器端口:TASK_RUN_PORT*/
	public Integer getTaskRunPort() {
		return taskRunPort;
	}

	/**任务运行服务器端口:TASK_RUN_PORT*/
	public String getTaskRunPort_colmun() {
		String temp = "";
		if(null != taskRunPort){
			temp = String.valueOf(taskRunPort);
		}
		return temp;
	}

	/**任务运行服务器端口:TASK_RUN_PORT*/
	public void setTaskRunPort(Integer taskRunPort) {
		this.taskRunPort = taskRunPort;
	}

	/**分片值:PART_VALUE*/
	public String getPartValue() {
		return partValue;
	}

	/**分片值:PART_VALUE*/
	public String getPartValue_colmun() {
		return partValue;
	}

	/**分片值:PART_VALUE*/
	public void setPartValue(String partValue) {
		this.partValue = partValue;
	}

	/**上次运行时间:PRE_RUN_TIME*/
	public String getPreRunTime() {
		return preRunTime;
	}

	/**上次运行时间:PRE_RUN_TIME*/
	public String getPreRunTime_colmun() {
		return preRunTime;
	}

	/**上次运行时间:PRE_RUN_TIME*/
	public void setPreRunTime(String preRunTime) {
		this.preRunTime = preRunTime;
	}

	/**最新运行时间:NEXT_RUN_TIME*/
	public String getNextRunTime() {
		return nextRunTime;
	}

	/**最新运行时间:NEXT_RUN_TIME*/
	public String getNextRunTime_colmun() {
		return nextRunTime;
	}

	/**最新运行时间:NEXT_RUN_TIME*/
	public void setNextRunTime(String nextRunTime) {
		this.nextRunTime = nextRunTime;
	}

	/**操作状态:DDL_STATUS*/
	public String getDdlStatus() {
		return ddlStatus;
	}

	/**操作状态:DDL_STATUS*/
	public String getDdlStatus_colmun() {
		String temp = "";
		if(null != ddlStatus){
			temp = GlobalApplicationCache.getInstance().getStr("DDL_STATUS." + ddlStatus);
		}
		return temp;
	}

	/**操作状态:DDL_STATUS*/
	public void setDdlStatus(String ddlStatus) {
		this.ddlStatus = ddlStatus;
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


	public String getExecuteCount() {
		return executeCount;
	}

	public void setExecuteCount(String executeCount) {
		this.executeCount = executeCount;
	}

	public String getExecuteCount_colmun() {
		return executeCount_colmun;
	}

	public void setExecuteCount_colmun(String executeCount_colmun) {
		this.executeCount_colmun = executeCount_colmun;
	}
}
