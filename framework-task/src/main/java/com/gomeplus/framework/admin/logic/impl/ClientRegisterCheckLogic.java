/**   
* @Title: ClientRegisterCheckLogic.java 
* @Package com.gomeplus.framework.admin.logic.impl 
* @Description: 客户端注册服务的连接可用性检查
* @author chenmin-ds   
* @date 2017年6月27日 上午10:21:27 
* @company cn.com.gome
* @version V1.0   
*/ 


package com.gomeplus.framework.admin.logic.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;
import cn.com.gome.framework.dao.mapper.edit.TblTaskObjectRegisterEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskObjectRegisterSerMapper;

import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;

/** 
 * @ClassName: ClientRegisterCheckLogic 
 * @Description: 客户端注册服务的连接可用性检查
 * @author chenmin-ds 
 * @date 2017年6月27日 上午10:21:27  
 */
@Service
public class ClientRegisterCheckLogic implements ILogics<NettyMessage>{

	private Logger logger = LoggerFactory.getLogger("ClientRegisterCheckLogic");
	
	@Autowired
	private TblTaskObjectRegisterEditMapper tblTaskObjectRegisterEditMapper;
	
	@Autowired
	private TblTaskObjectRegisterSerMapper tblTaskObjectRegisterSerMapper;
	

	public ResultEnum exec(NettyMessage nettyMessage) throws LogicsException {
		try{
			String[] ipPorts = nettyMessage.getRequestKey().split(":");
			TblTaskObjectRegister entity = new TblTaskObjectRegister();
			entity.setObjectTal(nettyMessage.getHoldKey());
			entity.setTaskRunServer(ipPorts[0]);
			entity = tblTaskObjectRegisterSerMapper.queryObj(entity);
			String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(nettyMessage.getRequestTime());
			if(null == entity){
				entity = new TblTaskObjectRegister();
				entity.setObjectTal(nettyMessage.getHoldKey());
				entity.setTaskRunServer(ipPorts[0]);
				entity.setTaskRunPort(Integer.valueOf(ipPorts[1]));
				entity.setObjectStatus(QuartzConstants.DDL_STATUS_010);
				entity.setRegisterTime(timeStr);
				entity.setUpTime(timeStr);
				tblTaskObjectRegisterEditMapper.save(entity);
				logger.info("客户端向服务端注册插入结束，ip={},port={}",ipPorts[0],ipPorts[1]);
			}else{
				//服务端重启 客户端断开或者重启 都不修改运行日志表 20170922 zhumenglong
				if(!entity.getTaskRunPort().equals(Integer.valueOf(ipPorts[1])) ){
					entity.setTaskRunPort(Integer.valueOf(ipPorts[1]));
				}
				entity.setObjectStatus(QuartzConstants.DDL_STATUS_010);
				entity.setUpTime(timeStr);
				tblTaskObjectRegisterEditMapper.edit(entity);
				logger.info("客户端向服务端注册修改结束,ip={},port={}",ipPorts[0],ipPorts[1]);
			}
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("客户端注册与心跳检查业务信息处理异常：requestKey=" +nettyMessage.getRequestKey() ,e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
