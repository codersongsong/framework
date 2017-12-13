/**   
* @Title: QueryTaskInfoLogic.java 
* @Package com.gomeplus.framework.admin.logic.impl 
* @Description: 查询任务基本配置信息
* @author chenmin-ds   
* @date 2017年6月27日 上午10:18:32 
* @company cn.com.gome
* @version V1.0   
*/ 


package com.gomeplus.framework.admin.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;

import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;

/** 
 * @ClassName: QueryTaskInfoLogic 
 * @Description: 查询任务基本配置信息 
 * @author chenmin-ds 
 * @date 2017年6月27日 上午10:18:32  
 */
@Service
public class QueryTaskInfoLogic implements ILogics<NettyMessage> {
	
	private Logger logger = LoggerFactory.getLogger("QueryTaskInfoLogic");
	
	@Autowired
	private TblTaskConfigInfoSerMapper tblTaskConfigInfoSerMapper;

	public ResultEnum exec(NettyMessage nettyMessage) throws LogicsException {
		try {
			TblTaskConfigInfo taskConfigInfo = new TblTaskConfigInfo();
			taskConfigInfo.setSysNo(Integer.valueOf(nettyMessage.getJsonObject(QuartzConstants.BUSINESS_CODE).toString()));
			List<TblTaskConfigInfo> list = tblTaskConfigInfoSerMapper.queryList(taskConfigInfo);
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			for (TblTaskConfigInfo info : list) {
				listJson.add(JSONObject.fromObject(info));
			}
			nettyMessage.setJsonObject(QuartzConstants.TASK_CONFIG_INFO_ARRAYS, listJson);
			NettyServerStartup.getInstance().getChannel(nettyMessage.getRequestKey()).writeAndFlush(nettyMessage);
			logger.info("服务端接收到客户端信息的业务处理，获取到的任务配置信息【{}】条", listJson.size());
			return ResultEnum.OK;
		} catch (Exception e) {
			logger.error("服务端接收到客户端信息的业务处理异常", e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
