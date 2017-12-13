/**   
* @Title: ClientJionCutLogic.java 
* @Package com.gomeplus.framework.admin.logic.impl 
* @Description: 客户端断开连接的后续处理
* @author chenmin-ds   
* @date 2017年6月27日 上午10:25:00 
* @company cn.com.gome
* @version V1.0   
*/ 


package com.gomeplus.framework.admin.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;
import cn.com.gome.framework.dao.mapper.edit.TblTaskObjectRegisterEditMapper;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskObjectRegisterSerMapper;

import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;

/** 
 * @ClassName: ClientJionCutLogic 
 * @Description: 客户端断开连接的后续处理 
 * @author chenmin-ds 
 * @date 2017年6月27日 上午10:25:00  
 */
@Service
public class ClientJionCutLogic implements ILogics<NettyMessage> {

	private Logger logger = LoggerFactory.getLogger("ClientJionCutLogic");
	
	@Autowired
	private TblTaskObjectRegisterEditMapper tblTaskObjectRegisterEditMapper;
	
	@Autowired
	private TblTaskObjectRegisterSerMapper tblTaskObjectRegisterSerMapper;
	
	@Autowired
	private TblTaskRunningLogEditMapper tblTaskRunningLogEditMapper;

	public ResultEnum exec(NettyMessage nettyMessage) throws LogicsException {
		try{
			String[] ipPorts = nettyMessage.getRequestKey().split(":");
			TblTaskObjectRegister entity = new TblTaskObjectRegister();
			entity.setTaskRunServer(ipPorts[0]);
			entity.setTaskRunPort(Integer.valueOf(ipPorts[1]));
			entity.setObjectStatus("010");
			entity = tblTaskObjectRegisterSerMapper.queryObj(entity);
			if(null != entity){
				String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				entity.setObjectStatus(QuartzConstants.DDL_STATUS_020);
				entity.setUpTime(timeStr);
				tblTaskObjectRegisterEditMapper.edit(entity);
				
				Map<String , String> reqMap = new HashMap<String, String>();
				reqMap.put("taskRunServer", ipPorts[0]);
				reqMap.put("taskRunPort", ipPorts[1]);
				reqMap.put("ddlStatusNew", QuartzConstants.DDL_STATUS_040);
				reqMap.put("upTimeNew", timeStr);
				//修改运行日志表
				tblTaskRunningLogEditMapper.editInfo(reqMap);
				logger.info("客户端与服务端连接断开,taskRunServer={},taskRunPort={}",ipPorts[0] ,ipPorts[1]);
			}
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("客户端与服务端连接断开业务处理异常：requestKey=" +nettyMessage.getRequestKey() ,e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
