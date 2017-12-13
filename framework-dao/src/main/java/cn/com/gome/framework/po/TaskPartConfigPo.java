/**
* @Title: TaskPartConfigPo.java
* @Package com.gome.login.po
* @Description: 任务分片配置表实体类
* @author renhanxiang
* @date Sat Apr 08 10:30:54 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TaskPartConfigPo
 * @Description: 任务分片配置表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TASK_PART_CONFIG
 */
@SuppressWarnings("unused")
public class TaskPartConfigPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**分片编号:PART_NO*/
	private String partNo;

	/**分片编号:PART_NO*/
	private String partNo_colmun;

	/**任务编号:TASK_NO*/
	private String taskNo;

	/**任务编号:TASK_NO*/
	private String taskNo_colmun;

	/**实例任务主键:OBJ_TASK_KEY*/
	private String objTaskKey;

	/**实例任务主键:OBJ_TASK_KEY*/
	private String objTaskKey_colmun;

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

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal;

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;

	/**预计实例个数:ESTIMATE_OBJ_COUNT*/
	private Integer estimateObjCount;

	public Integer getEstimateObjCount() {
		return estimateObjCount;
	}

	public void setEstimateObjCount(Integer estimateObjCount) {
		this.estimateObjCount = estimateObjCount;
	}

	/**分片编号:PART_NO*/
	public String getPartNo() {
		return partNo;
	}

	/**分片编号:PART_NO*/
	public String getPartNo_colmun() {
		return partNo;
	}

	/**分片编号:PART_NO*/
	public void setPartNo(String partNo) {
		this.partNo = partNo;
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

	/**实例任务主键:OBJ_TASK_KEY*/
	public String getObjTaskKey() {
		return objTaskKey;
	}

	/**实例任务主键:OBJ_TASK_KEY*/
	public String getObjTaskKey_colmun() {
		return objTaskKey;
	}

	/**实例任务主键:OBJ_TASK_KEY*/
	public void setObjTaskKey(String objTaskKey) {
		this.objTaskKey = objTaskKey;
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
