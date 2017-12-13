/**
 * @Title: TaskRunningLogContorller.java
 * @Package com.gome.login.controller
 * @Description: 任务运行日志表的控制器
 * @author chenmin-ds
 * @date Fri Mar 24 15:43:27 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package com.gomeplus.framework.admin.controller;

import com.gomeplus.commons.quartz.netty.client.NettyClientStartup;
import io.netty.channel.Channel;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.entity.TblTaskPartConfig;
import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskPartConfigEditMapper;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskPartConfigSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;
import cn.com.gome.framework.po.TaskRunningLogPo;

import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.cache.QuartzPropertiesCache;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;


/**
 * @ClassName: TaskRunningLogContorller
 * @Description: 任务运行日志表的控制器
 * @author chenmin-ds
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/taskrunninglog")
public class TaskRunningLogController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblTaskConfigInfoSerMapper tblTaskConfigInfoSerMapper;
	
	@Autowired
	private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;
	
	@Autowired
	private TblTaskRunningLogEditMapper tblTaskRunningLogEditMapper;
	
	@Autowired
	private TblTaskPartConfigSerMapper tblTaskPartConfigSerMapper;
	
	@Autowired
	private TblTaskPartConfigEditMapper tblTaskPartConfigEditMapper;
	
	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "taskrunninglog/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblTaskRunningLog");
			pageQueryEntitys.setRows(100);
			String taskNo = request.getParameter("taskNo");
			if (StringUtils.isNotEmpty(taskNo)) {
				pageQueryEntitys.addF("taskNo", taskNo);
				pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTaskRunningLogSerMapper,pageQueryEntitys);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}
	
	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblTaskRunningLog> list = null;
		try {
			TblTaskRunningLog obj = new TblTaskRunningLog();
			list = tblTaskRunningLogSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/executeOnceNow")
	public void executeOnceNow(HttpServletRequest request, HttpServletResponse response,Model model){
		try {
			String runNo = request.getParameter("runNo");
			String taskNo = request.getParameter("taskNo");
			String objTaskName = request.getParameter("objTaskName");
			String objectTal = request.getParameter("objectTal");
			String taskRunServer = request.getParameter("taskRunServer");
			String taskRunPort = request.getParameter("taskRunPort");
			TblTaskConfigInfo tblTaskConfigInfo = new TblTaskConfigInfo();
			tblTaskConfigInfo.setTaskNo(taskNo);
			tblTaskConfigInfo = tblTaskConfigInfoSerMapper.queryObj(tblTaskConfigInfo);
			NettyMessage nettyMessage = new NettyMessage(QuartzConstants.TASK_EXECUTE_ONCE_NOW);
			nettyMessage.setBusinessType(QuartzConstants.TASK_EXECUTE_ONCE_NOW);
			nettyMessage.setJsonObject("businessObjName",tblTaskConfigInfo.getBusinessObjName());
			//发送客户端
			boolean res = NettyServerStartup.getInstance().sendMsg(taskRunServer, Integer.valueOf(taskRunPort), nettyMessage);
			logger.info("executeOnceNow_taskName=" + tblTaskConfigInfo.getBusinessObjName() +";ip="+taskRunServer +";port="+ taskRunPort );
			ajaxJsonMessage(response,"0000","温馨提示：修改成功！","");
		} catch (Exception e) {
			logger.error("温馨提示：修改失败！" , e);
			ajaxJsonMessage(response,"0002","温馨提示：修改失败！","");
		}
	}


	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "taskrunninglog/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblTaskRunningLog enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			tblTaskRunningLogEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "taskrunninglog/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblTaskRunningLog enitty = new TblTaskRunningLog();
			enitty.setRunNo(request.getParameter("id_key"));
			enitty = (TblTaskRunningLog) tblTaskRunningLogSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "taskrunninglog/add";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public void edit(@ModelAttribute("enitty") TblTaskRunningLog enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			NettyMessage nettyMessage = new NettyMessage(QuartzConstants.BUSINESS_TYPE_RWXXCX_INITS);
			nettyMessage.setJsonObject("BUSINESS_CODE", QuartzPropertiesCache.getBusinessCode());
			TblTaskRunningLog tblTaskRunningLog = tblTaskRunningLogSerMapper.query(enitty.getRunNo());
			String ip = tblTaskRunningLog.getTaskRunServer();
			int port = tblTaskRunningLog.getTaskRunPort();
			
			if(StringUtils.isNotEmpty(enitty.getModeValue())){
				nettyMessage.setJsonObject("setTypes", "true");
			}else{
				enitty.setPartValue("");
				enitty.setModeValue("");
				nettyMessage.setJsonObject("setTypes", "true");
			}

			if(null != tblTaskRunningLog){
				//String[] objTaskNames = tblTaskRunningLog.getObjTaskName().split("_");
				//nettyMessage.setJsonObject("businessObjName", objTaskNames[0] + "_" + objTaskNames[1]);
				nettyMessage.setBusinessType(QuartzConstants.TASK_PART_SET);
				nettyMessage.setJsonObject("businessObjName",tblTaskRunningLog.getObjTaskName());
				nettyMessage.setJsonObject("partValue", enitty.getPartValue());
				nettyMessage.setJsonObject("modeValue", enitty.getModeValue());
				//发送客户端
				boolean res = NettyServerStartup.getInstance().sendMsg(ip, port, nettyMessage);
				
				enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				int count = tblTaskRunningLogEditMapper.edit(enitty);
				if (count == 0) {
					ajaxJsonMessage(response,"0002","温馨提示：修改失败！","");
				} else {
					TblTaskPartConfig partConfig = new TblTaskPartConfig();
					partConfig.setTaskNo(tblTaskRunningLog.getTaskNo());
					//partConfig.setObjTaskKey(objTaskNames[0] + "_" + objTaskNames[1]);
					partConfig.setObjTaskKey(tblTaskRunningLog.getObjTaskName());
					partConfig.setTaskRunServer(tblTaskRunningLog.getTaskRunServer());
					partConfig.setTaskRunPort(tblTaskRunningLog.getTaskRunPort());
					partConfig = tblTaskPartConfigSerMapper.queryObj(partConfig);
					if(partConfig == null){
						partConfig = new TblTaskPartConfig();
						partConfig.setTaskNo(tblTaskRunningLog.getTaskNo());
						//partConfig.setObjTaskKey(objTaskNames[0] + "_" + objTaskNames[1]);
						partConfig.setObjTaskKey(tblTaskRunningLog.getObjTaskName());
						partConfig.setTaskRunServer(tblTaskRunningLog.getTaskRunServer());
						partConfig.setTaskRunPort(tblTaskRunningLog.getTaskRunPort());
						partConfig.setModeValue(enitty.getModeValue());
						partConfig.setPartValue(enitty.getPartValue());
						partConfig.setObjectTal(tblTaskRunningLog.getObjectTal());
						partConfig.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
						tblTaskPartConfigEditMapper.save(partConfig);
					}else{
						partConfig.setObjectTal(tblTaskRunningLog.getObjectTal());
						partConfig.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
						partConfig.setModeValue(enitty.getModeValue());
						partConfig.setPartValue(enitty.getPartValue());
						tblTaskPartConfigEditMapper.edit(partConfig);
					}
					ajaxJsonMessage(response,"0000","温馨提示：修改成功！","");
				}
			}else{
				ajaxJsonMessage(response,"0002","温馨提示：修改失败！","");
			}

		} catch (Exception e) {
			logger.error("温馨提示：修改失败！" , e);
			ajaxJsonMessage(response,"0002","温馨提示：修改失败！","");
		}
	}

	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			int count = tblTaskRunningLogEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
			if (count == 0) {
				ajaxJsonMessage(response,"0002","温馨提示：没有删除到符合条件的记录！","");
			} else {
				ajaxJsonMessage(response,"0000","温馨提示：删除成功！","");
				//inits();
			}
		} catch (Exception e) {
			ajaxJsonMessage(response,"0002","温馨提示：删除失败！","");
		}
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "view");
			model.addAttribute("resultStatus", "false");
			TblTaskRunningLog enitty = new TblTaskRunningLog();
			enitty.setRunNo(request.getParameter("id_key"));
			TaskRunningLogPo po = (TaskRunningLogPo) tblTaskRunningLogSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "taskrunninglog/add";
	}

	@RequestMapping("/closerun")
	public void closerun(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			String type = request.getParameter("type");
			TblTaskRunningLog enitty = new TblTaskRunningLog();
			enitty.setRunNo(request.getParameter("id_key"));
			enitty = (TblTaskRunningLog) tblTaskRunningLogSerMapper.queryObj(enitty);

			//判断是否为全局唯一性任务
			TblTaskConfigInfo taskConfigInfo = tblTaskConfigInfoSerMapper.query(enitty.getTaskNo());
			if(null == taskConfigInfo){
				ajaxJsonMessage(response,"0000","温馨提示：操作失败，无任务配置信息！","");
				return ;
			}
			JSONObject taskConfigInfoJson  = JSONObject.fromObject(taskConfigInfo);
			NettyMessage nettyMessage = new NettyMessage();
			if("CLOSE_TASK".equals(type)){//关闭任务
				nettyMessage.setBusinessType(QuartzConstants.TASK_STOP_RUN);
				nettyMessage.setJsonObject(QuartzConstants.TASK_STOP_RUN,taskConfigInfoJson);
				nettyMessage.setJsonObject("stopOrRun","stop");
				nettyMessage.setJsonObject("objTaskName",enitty.getObjTaskName());
				nettyMessage.setJsonObject("taskNo",enitty.getTaskNo());
				boolean successFlg = NettyServerStartup.getInstance().sendMsg(enitty.getTaskRunServer(),enitty.getTaskRunPort() ,nettyMessage);
				ajaxJsonMessage(response,"0000","温馨提示：操作成功！","");
			}else if("RUN_TASK".equals(type)){//运行任务
				if(enitty.getDdlStatus().equals(QuartzConstants.DDL_STATUS_010)){
					ajaxJsonMessage(response,"0000","温馨提示：操作失败，任务实例正在运行！","");
					return ;
				}else {
					nettyMessage.setBusinessType(QuartzConstants.TASK_STOP_RUN);
					nettyMessage.setJsonObject(QuartzConstants.TASK_STOP_RUN,taskConfigInfoJson);
					nettyMessage.setJsonObject("stopOrRun","run");
					nettyMessage.setJsonObject("objTaskName",enitty.getObjTaskName());
					nettyMessage.setJsonObject("taskNo",enitty.getTaskNo());
					boolean successFlg = NettyServerStartup.getInstance().sendMsg(enitty.getTaskRunServer(),enitty.getTaskRunPort() ,nettyMessage);
					ajaxJsonMessage(response,"0000","温馨提示：操作成功！","");
				}
			}
			if(taskConfigInfo.getFullTaskCount() == 1 && "ONE_TASK_SERVER_RUN".equals(type) ){//先关闭再运行 // TODO: 2017/6/27  
				nettyMessage.setJsonObject("oneTaskRunType","false");
				Map param = new HashMap();
				param.put("taskNo",enitty.getTaskNo());
				param.put("ddlStatus",new String[]{QuartzConstants.DDL_STATUS_010 , QuartzConstants.DDL_STATUS_020 , QuartzConstants.DDL_STATUS_030});
				List<TblTaskRunningLog>  addrList = tblTaskRunningLogSerMapper.queryAddrList(param);
				for(TblTaskRunningLog log : addrList){
					NettyServerStartup.getInstance().sendMsg(log.getTaskRunServer(),log.getTaskRunPort(),nettyMessage);//先关闭所有任务实例
				}
				TblTaskRunningLog taskRunningLog = new TblTaskRunningLog();
				taskRunningLog.setTaskNo(enitty.getTaskNo());
				taskRunningLog.setDdlStatus(QuartzConstants.DDL_STATUS_040);//作废所有实例
				tblTaskRunningLogEditMapper.edit(taskRunningLog);
				nettyMessage.setJsonObject("oneTaskRunType","true");
				NettyServerStartup.getInstance().sendMsg(enitty.getTaskRunServer(),enitty.getTaskRunPort() ,nettyMessage);
				enitty.setDdlStatus(QuartzConstants.DDL_STATUS_010);
				if (1 == tblTaskRunningLogEditMapper.edit(enitty)) {
					ajaxJsonMessage(response,"0000","温馨提示：操作成功！","");
				}else {
					ajaxJsonMessage(response,"0002","温馨提示：操作失败！","");
				}
			}
		} catch (Exception e) {
			ajaxJsonMessage(response,"0002","温馨提示：删除失败！","");
		}
	}
}
