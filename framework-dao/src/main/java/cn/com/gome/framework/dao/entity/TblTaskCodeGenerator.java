/**
* @Title: TblTaskCodeGenerator.java
* @Package com.gome.framework.dao.entity
* @Description: 定时任务代码生成表实体类
* @author renhanxiang
* @date Fri May 05 14:26:27 CST 2017
* @company cn.com.gome
* @version V1.0
*/

package cn.com.gome.framework.dao.entity;

import com.gomeplus.jdbc.page.Entitys;

/** 
 * @ClassName: TblTaskCodeGenerator
 * @Description: 定时任务代码生成表实体类
 * @author renhanxiang
 * @date 2015年2月10日 下午2:01:26
 * <br>tableName = TBL_TASK_CODE_GENERATOR
 */
public class TblTaskCodeGenerator extends Entitys {

	private static final long serialVersionUID = 1L;

	/**表DAO序号:TABLE_NUMBERS*/
	private Integer tableNumbers;

	/**子项目编码:CHILD_PROJECT_CODE*/
	private Integer childProjectCode;

	/**表中文名:TABLE_CHAN*/
	private String tableChan;

	/**表英文名:TABLE_ENG*/
	private String tableEng;

	/**锁定位:LOCK_FLG*/
	private String lockFlg;

	/**锁定时间:LOCK_TM*/
	private String lockTm;

	/**执行次数:RUN_COUNTS*/
	private String runCounts;

	/**表主键:TABLE_KEY*/
	private String tableKey;

	/**任务说明:TASK_ASK*/
	private String taskAsk;

	/**任务类名:TASK_CLASS_NAME*/
	private String taskClassName;

	/**添加时间:CREATE_TIME*/
	private String createTime;

	/**表DAO序号:TABLE_NUMBERS*/
	public Integer getTableNumbers() {
		return tableNumbers;
	}

	/**表DAO序号:TABLE_NUMBERS*/
	public void setTableNumbers(Integer tableNumbers) {
		this.tableNumbers = tableNumbers;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public Integer getChildProjectCode() {
		return childProjectCode;
	}

	/**子项目编码:CHILD_PROJECT_CODE*/
	public void setChildProjectCode(Integer childProjectCode) {
		this.childProjectCode = childProjectCode;
	}

	/**表中文名:TABLE_CHAN*/
	public String getTableChan() {
		return tableChan;
	}

	/**表中文名:TABLE_CHAN*/
	public void setTableChan(String tableChan) {
		this.tableChan = tableChan;
	}

	/**表英文名:TABLE_ENG*/
	public String getTableEng() {
		return tableEng;
	}

	/**表英文名:TABLE_ENG*/
	public void setTableEng(String tableEng) {
		this.tableEng = tableEng;
	}

	/**锁定位:LOCK_FLG*/
	public String getLockFlg() {
		return lockFlg;
	}

	/**锁定位:LOCK_FLG*/
	public void setLockFlg(String lockFlg) {
		this.lockFlg = lockFlg;
	}

	/**锁定时间:LOCK_TM*/
	public String getLockTm() {
		return lockTm;
	}

	/**锁定时间:LOCK_TM*/
	public void setLockTm(String lockTm) {
		this.lockTm = lockTm;
	}

	/**执行次数:RUN_COUNTS*/
	public String getRunCounts() {
		return runCounts;
	}

	/**执行次数:RUN_COUNTS*/
	public void setRunCounts(String runCounts) {
		this.runCounts = runCounts;
	}

	/**表主键:TABLE_KEY*/
	public String getTableKey() {
		return tableKey;
	}

	/**表主键:TABLE_KEY*/
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}

	/**任务说明:TASK_ASK*/
	public String getTaskAsk() {
		return taskAsk;
	}

	/**任务说明:TASK_ASK*/
	public void setTaskAsk(String taskAsk) {
		this.taskAsk = taskAsk;
	}

	/**任务类名:TASK_CLASS_NAME*/
	public String getTaskClassName() {
		return taskClassName;
	}

	/**任务类名:TASK_CLASS_NAME*/
	public void setTaskClassName(String taskClassName) {
		this.taskClassName = taskClassName;
	}

	/**添加时间:CREATE_TIME*/
	public String getCreateTime() {
		return createTime;
	}

	/**添加时间:CREATE_TIME*/
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
