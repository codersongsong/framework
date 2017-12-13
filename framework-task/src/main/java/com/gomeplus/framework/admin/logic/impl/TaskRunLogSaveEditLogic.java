/**   
* @Title: TaskRunLogSaveEditLogic.java 
* @Package com.gomeplus.framework.admin.logic.impl 
* @Description: 任务运行日志的添加与修改
* @author chenmin-ds   
* @date 2017年6月27日 上午10:27:57 
* @company cn.com.gome
* @version V1.0   
*/ 


package com.gomeplus.framework.admin.logic.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;

import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;

/** 
 * @ClassName: TaskRunLogSaveEditLogic 
 * @Description: 任务运行日志的添加与修改 
 * @author chenmin-ds 
 * @date 2017年6月27日 上午10:27:57  
 */
@Service
public class TaskRunLogSaveEditLogic implements ILogics<NettyMessage> {

	private Logger logger = LoggerFactory.getLogger("TaskRunLogSaveEditLogic");
	
	@Autowired
	private TblTaskRunningLogEditMapper tblTaskRunningLogEditMapper;

	@Autowired
	private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;
	
	@SuppressWarnings("unchecked")
	public ResultEnum exec(NettyMessage nettyMessage) throws LogicsException {
		try{
			String[] ipPorts = nettyMessage.getRequestKey().split(":");
			List<JSONObject> list = (List<JSONObject>) nettyMessage.getJsonObject(QuartzConstants.TASK_RUN_LOG);
			TblTaskRunningLog entity = null;
			for(JSONObject obj : list){
				logger.info("TASK_RUN_LOG data={}",obj);
				entity = new TblTaskRunningLog();
				entity.setTaskRunServer(ipPorts[0]);
				entity.setTaskNo(obj.getString("taskNo"));
				entity.setObjectTal(obj.getString("objectTal"));
				entity.setObjTaskName(obj.getString("objTaskName"));
				entity = tblTaskRunningLogSerMapper.queryObj(entity);
				if(null == entity){
					entity = new TblTaskRunningLog();
					entity.setTaskNo(obj.getString("taskNo"));
					entity.setObjTaskName(obj.getString("objTaskName"));
					entity.setObjectTal(obj.getString("objectTal"));
					entity.setTaskRunServer(ipPorts[0]);
					entity.setTaskRunPort(Integer.valueOf(ipPorts[1]));
					entity.setPartValue(obj.getString("partValue"));
					entity.setModeValue(obj.getString("modeValue"));
					entity.setPreRunTime(obj.getString("preRunTime"));
					entity.setNextRunTime(obj.getString("nextRunTime"));
					entity.setTaskNo(obj.getString("taskNo"));
					entity.setDdlStatus(obj.getString("ddlStatus"));
					entity.setUpPerson(obj.getString("upPerson"));
					entity.setUpTime(obj.getString("upTime"));
					if(obj.containsKey(QuartzConstants.TASK_RUN_RECORD_COUNT)){
						entity.setExecuteCount(Integer.valueOf(obj.getString(QuartzConstants.TASK_RUN_RECORD_COUNT)));
					}else {
						entity.setExecuteCount(0);
					}
					tblTaskRunningLogEditMapper.save(entity);
				}else{
					entity.setUpTime(obj.getString("upTime"));
					entity.setDdlStatus(obj.getString("ddlStatus"));
					entity.setPreRunTime(obj.getString("preRunTime"));
					entity.setNextRunTime(obj.getString("nextRunTime"));
					entity.setTaskRunPort(Integer.valueOf(obj.getString("taskRunPort")));
					entity.setPartValue(obj.getString("partValue"));
					entity.setModeValue(obj.getString("modeValue"));
					if(obj.containsKey(QuartzConstants.TASK_RUN_RECORD_COUNT)){
						entity.setExecuteCount(Integer.valueOf(obj.getString(QuartzConstants.TASK_RUN_RECORD_COUNT)));
					}else {
						entity.setExecuteCount(0);
					}
					tblTaskRunningLogEditMapper.edit(entity);
				}
			}
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("任务异常：requestKey=" +nettyMessage.getRequestKey() ,e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
