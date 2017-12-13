/**   
 * @Title: TaskListenerServerServiceImpl.java 
 * @Package com.gomeplus.baitiao.admin.logic.impl 
 * @Description: 服务端接收到客户端信息的业务处理 
 * @author chenmin-ds   
 * @date 2017年6月19日 下午3:29:02 
 * @company cn.com.gome
 * @version V1.0   
 */

package com.gomeplus.framework.admin.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gomeplus.commons.quartz.business.TaskListenerServerService;
import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.frame.logic.ILogics;

/**
 * @ClassName: TaskListenerServerServiceImpl
 * @Description: 服务端接收到客户端信息的业务处理
 * @author chenmin-ds
 * @date 2017年6月19日 下午3:29:02
 */
@Service
public class TaskListenerServerServiceImpl implements TaskListenerServerService {

	private Logger logger = LoggerFactory.getLogger("TaskListenerServerServiceImpl");

	@Resource
	private ILogics<NettyMessage> clientJionCutLogic;
	
	@Resource
	private ILogics<NettyMessage> taskRunLogSaveEditLogic;
	
	@Resource
	private ILogics<NettyMessage> queryTaskInfoLogic;
	
	@Resource
	private ILogics<NettyMessage> clientRegisterCheckLogic;
	
	/*
	 * (非 Javadoc) <p>Title: businessAdapter</p> <p>Description: </p>
	 * 
	 * @param nettyMessage
	 * 
	 * @see
	 * com.gomeplus.commons.quartz.business.TaskListenerServerService#businessAdapter(com.gomeplus.commons.quartz.netty
	 * .NettyMessage)
	 */
	public void businessAdapter(NettyMessage nettyMessage) {
		String businessType = nettyMessage.getBusinessType();
		try{
			if (QuartzConstants.BUSINESS_TYPE_XT.equals(businessType)) {
				clientRegisterCheckLogic.exec(nettyMessage);
			} else if (QuartzConstants.BUSINESS_TYPE_RWXXCX_INITS.equals(businessType)) {
				queryTaskInfoLogic.exec(nettyMessage);
			} else if (QuartzConstants.BUSINESS_TYPE_RWXXCX_UPDATE.equals(businessType)) {
				queryTaskInfoLogic.exec(nettyMessage);
			} else if (QuartzConstants.CLIENT_REGISTER.equals(businessType)) {
				clientRegisterCheckLogic.exec(nettyMessage);
			} else if (QuartzConstants.CLIENT_JION_CUT.equals(businessType)) {
				clientJionCutLogic.exec(nettyMessage);
			}else if (QuartzConstants.TASK_RUN_LOG.equals(businessType)) {
				taskRunLogSaveEditLogic.exec(nettyMessage);
			}
		}catch(Exception e){
			logger.error("接收客户端的信息后处理异常：requestKey=" +nettyMessage.getRequestKey() ,e);
		}
	}

}
