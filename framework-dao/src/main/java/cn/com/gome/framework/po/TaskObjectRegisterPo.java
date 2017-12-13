/**
* @Title: TaskObjectRegisterPo.java
* @Package com.gome.framework.po
* @Description: 定时任务实例启动日志表实体类
* @author renhanxiang
* @date Thu Jun 15 10:00:02 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.po;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TaskObjectRegisterPo
 * @Description: 定时任务实例启动日志表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TASK_OBJECT_REGISTER
 */
@SuppressWarnings("unused")
public class TaskObjectRegisterPo extends Entitys {

	private static final long serialVersionUID = 1L;

	/**注册编号:REGISTER_NO*/
	private String registerNo;

	/**注册编号:REGISTER_NO*/
	private String registerNo_colmun;

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal;

	/**实例唯一标示:OBJECT_TAL*/
	private String objectTal_colmun;

	/**服务器IP:TASK_RUN_SERVER*/
	private String taskRunServer;

	/**服务器IP:TASK_RUN_SERVER*/
	private String taskRunServer_colmun;

	/**服务器端口:TASK_RUN_PORT*/
	private Integer taskRunPort;

	/**服务器端口:TASK_RUN_PORT*/
	private String taskRunPort_colmun;

	/**注册时间:REGISTER_TIME*/
	private String registerTime;

	/**注册时间:REGISTER_TIME*/
	private String registerTime_colmun;

	/**实例状态:OBJECT_STATUS*/
	private String objectStatus;

	/**实例状态:OBJECT_STATUS*/
	private String objectStatus_colmun;

	/**修改时间:UP_TIME*/
	private String upTime;

	/**修改时间:UP_TIME*/
	private String upTime_colmun;

	/**注册编号:REGISTER_NO*/
	public String getRegisterNo() {
		return registerNo;
	}

	/**注册编号:REGISTER_NO*/
	public String getRegisterNo_colmun() {
		return registerNo;
	}

	/**注册编号:REGISTER_NO*/
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
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

	/**服务器IP:TASK_RUN_SERVER*/
	public String getTaskRunServer() {
		return taskRunServer;
	}

	/**服务器IP:TASK_RUN_SERVER*/
	public String getTaskRunServer_colmun() {
		return taskRunServer;
	}

	/**服务器IP:TASK_RUN_SERVER*/
	public void setTaskRunServer(String taskRunServer) {
		this.taskRunServer = taskRunServer;
	}

	/**服务器端口:TASK_RUN_PORT*/
	public Integer getTaskRunPort() {
		return taskRunPort;
	}

	/**服务器端口:TASK_RUN_PORT*/
	public String getTaskRunPort_colmun() {
		String temp = "";
		if(null != taskRunPort){
			temp = String.valueOf(taskRunPort);
		}
		return temp;
	}

	/**服务器端口:TASK_RUN_PORT*/
	public void setTaskRunPort(Integer taskRunPort) {
		this.taskRunPort = taskRunPort;
	}

	/**注册时间:REGISTER_TIME*/
	public String getRegisterTime() {
		return registerTime;
	}

	/**注册时间:REGISTER_TIME*/
	public String getRegisterTime_colmun() {
		return registerTime;
	}

	/**注册时间:REGISTER_TIME*/
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	/**实例状态:OBJECT_STATUS*/
	public String getObjectStatus() {
		return objectStatus;
	}

	/**实例状态:OBJECT_STATUS*/
	public String getObjectStatus_colmun() {
		return objectStatus;
	}

	/**实例状态:OBJECT_STATUS*/
	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
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
