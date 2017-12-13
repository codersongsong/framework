/**
 * @Title: TaskConfigInfoContorller.java
 * @Package com.gome.login.controller
 * @Description: 任务基本信息配置表的控制器
 * @author chenmin-ds
 * @date Fri Mar 24 15:43:30 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package com.gomeplus.framework.admin.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;
import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.mapper.edit.TblTaskConfigInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;
import cn.com.gome.framework.po.TaskConfigInfoPo;

import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.framework.admin.interceptor.CookiesUtils;
import com.gomeplus.framework.admin.interceptor.UserLoginBean;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;
/**
 * @ClassName: TaskConfigInfoContorller
 * @Description: 任务基本信息配置表的控制器
 * @author chenmin-ds
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/taskconfiginfo")
public class TaskConfigInfoController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblTaskConfigInfoSerMapper tblTaskConfigInfoSerMapper;
	
	@Autowired
	private TblTaskConfigInfoEditMapper tblTaskConfigInfoEditMapper;

	@Autowired
	private TblTaskRunningLogEditMapper tblTaskRunningLogEditMapper;

	@Autowired
	private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;

	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "taskconfiginfo/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblTaskConfigInfo");
			String sysNo = request.getParameter("sysNo");
			if (StringUtils.isNotEmpty(sysNo)) {
				pageQueryEntitys.addF("sysNo", sysNo);
			}
			String taskName = request.getParameter("taskName");
			if (StringUtils.isNotEmpty(taskName)) {
				pageQueryEntitys.addF("taskName", taskName);
			}
			String businessObjName = request.getParameter("businessObjName");
			if (StringUtils.isNotEmpty(businessObjName)) {
				pageQueryEntitys.addF("businessObjName", businessObjName);
			}
			String groupTal = request.getParameter("groupTal");
			if (StringUtils.isNotEmpty(groupTal)) {
				pageQueryEntitys.addF("groupTal", groupTal);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTaskConfigInfoSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblTaskConfigInfo> list = null;
		try {
			TblTaskConfigInfo obj = new TblTaskConfigInfo();
			list = tblTaskConfigInfoSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/selectv")
	public void selectV(HttpServletRequest request, HttpServletResponse response) {
		List<TblTaskConfigInfo> list = null;
		try {
			TblTaskConfigInfo obj = new TblTaskConfigInfo();
			String sysNo = request.getParameter("sysNo");
			obj.setSysNo(Integer.valueOf(sysNo));
			list = tblTaskConfigInfoSerMapper.queryList(obj);
			JSONObject jo = new JSONObject();
			jo.put("rows", JSONArray.fromObject(list).toString());
			ajaxJsonMessage(response,jo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "taskconfiginfo/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblTaskConfigInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setConfigTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			UserLoginBean userLoginBean = (UserLoginBean) request.getAttribute(CookiesUtils.USER_LOGIN_BEAN);
			enitty.setUpPerson(userLoginBean.getTrueName());
			tblTaskConfigInfoEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
			inits();
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "taskconfiginfo/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblTaskConfigInfo enitty = new TblTaskConfigInfo();
			enitty.setTaskNo(request.getParameter("id_key"));
			enitty = (TblTaskConfigInfo) tblTaskConfigInfoSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "taskconfiginfo/add";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblTaskConfigInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			UserLoginBean userLoginBean = (UserLoginBean) request.getAttribute(CookiesUtils.USER_LOGIN_BEAN);
			enitty.setUpPerson(userLoginBean.getTrueName());
			if(enitty.getFullTaskCount() == 1){
				JSONObject taskConfigInfoJson  = JSONObject.fromObject(enitty);
				NettyMessage nettyMessage = new NettyMessage();
				nettyMessage.setBusinessType(QuartzConstants.BUSINESS_TYPE_HOLD_ONE);
				nettyMessage.setJsonObject(QuartzConstants.BUSINESS_TYPE_HOLD_ONE,taskConfigInfoJson);
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
				taskRunningLog.setDdlStatus(QuartzConstants.DDL_STATUS_010);
				taskRunningLog.setDdlStatus(QuartzConstants.DDL_STATUS_040);//作废所有实例
				tblTaskRunningLogEditMapper.edit(taskRunningLog);
				nettyMessage.setJsonObject("oneTaskRunType","true");
				if(null!= addrList  && addrList.size() >=1 ){
					TblTaskRunningLog log = addrList.get(new Random().nextInt(addrList.size()));
					NettyServerStartup.getInstance().sendMsg(log.getTaskRunServer(),log.getTaskRunPort(),nettyMessage);//再随机启动一个任务实例
				}
			}
			int count = tblTaskConfigInfoEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblTaskConfigInfo enittyNew = new TblTaskConfigInfo();
				enittyNew.setTaskNo(enitty.getTaskNo());
				enittyNew = (TblTaskConfigInfo) tblTaskConfigInfoSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
				inits();
				return "taskconfiginfo/add";
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：修改异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			int count = tblTaskConfigInfoEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
			if (count == 0) {
				ajaxJsonMessage(response,"0002","温馨提示：没有删除到符合条件的记录！","");
			} else {
				ajaxJsonMessage(response,"0000","温馨提示：删除成功！","");
				inits();
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
			TblTaskConfigInfo enitty = new TblTaskConfigInfo();
			enitty.setTaskNo(request.getParameter("id_key"));
			TaskConfigInfoPo po = (TaskConfigInfoPo) tblTaskConfigInfoSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "taskconfiginfo/add";
	}

	@PostConstruct
	private void inits(){
		try {
			List<TblTaskConfigInfo> list = tblTaskConfigInfoSerMapper.queryList(new TblTaskConfigInfo());
			if (list != null) {
				List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();
				DictionaryVo idval = null;
				for (TblTaskConfigInfo tblTaskConfigInfo : list) {
					GlobalApplicationCache.getInstance().put("TASK_NO." + tblTaskConfigInfo.getTaskNo(),
							tblTaskConfigInfo.getTaskName());
					idval = new DictionaryVo(tblTaskConfigInfo.getTaskNo()+"", tblTaskConfigInfo.getTaskName(), false);
					ivList.add(idval);
				}
				GlobalDataDictionaryCache.getInstance().putIdValue("TASK_NO", ivList);
			} else {
				logger.info("没有查询到数据字典的记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
