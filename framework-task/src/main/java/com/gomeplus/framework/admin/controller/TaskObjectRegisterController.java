/**
 * @Title: TaskObjectRegisterContorller.java
 * @Package com.gome.framework.controller
 * @Description: 定时任务实例启动日志表的控制器
 * @author chenmin-ds
 * @date Thu Jun 15 10:05:44 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package com.gomeplus.framework.admin.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;
import cn.com.gome.framework.dao.mapper.edit.TblTaskObjectRegisterEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskObjectRegisterSerMapper;
import cn.com.gome.framework.po.TaskObjectRegisterPo;

import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;

/**
 * @ClassName: TaskObjectRegisterContorller
 * @Description: 定时任务实例启动日志表的控制器
 * @author chenmin-ds
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/taskobjectregister")
public class TaskObjectRegisterController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblTaskObjectRegisterEditMapper tblTaskObjectRegisterEditMapper;
	
	@Autowired
	private TblTaskObjectRegisterSerMapper tblTaskObjectRegisterSerMapper;
	
	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "taskobjectregister/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblTaskObjectRegister");
			String registerNo = request.getParameter("registerNo");
			if (StringUtils.isNotEmpty(registerNo)) {
				pageQueryEntitys.addF("registerNo", registerNo);
			}
			String objectTal = request.getParameter("objectTal");
			if (StringUtils.isNotEmpty(objectTal)) {
				pageQueryEntitys.addF("objectTal", objectTal);
			}
			String taskRunServer = request.getParameter("taskRunServer");
			if (StringUtils.isNotEmpty(taskRunServer)) {
				pageQueryEntitys.addF("taskRunServer", taskRunServer);
			}
			String taskRunPort = request.getParameter("taskRunPort");
			if (StringUtils.isNotEmpty(taskRunPort)) {
				pageQueryEntitys.addF("taskRunPort", taskRunPort);
			}
			String registerTime = request.getParameter("registerTime");
			if (StringUtils.isNotEmpty(registerTime)) {
				pageQueryEntitys.addF("registerTime", registerTime);
			}
			String objectStatus = request.getParameter("objectStatus");
			if (StringUtils.isNotEmpty(objectStatus)) {
				pageQueryEntitys.addF("objectStatus", objectStatus);
			}
			String upTime = request.getParameter("upTime");
			if (StringUtils.isNotEmpty(upTime)) {
				pageQueryEntitys.addF("upTime", upTime);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTaskObjectRegisterSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblTaskObjectRegister> list = null;
		try {
			TblTaskObjectRegister obj = new TblTaskObjectRegister();
			list = tblTaskObjectRegisterSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "taskobjectregister/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblTaskObjectRegister enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			tblTaskObjectRegisterEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "taskobjectregister/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblTaskObjectRegister enitty = new TblTaskObjectRegister();
			enitty.setRegisterNo(request.getParameter("id_key"));
			enitty = (TblTaskObjectRegister) tblTaskObjectRegisterSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "taskobjectregister/add";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblTaskObjectRegister enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			int count = tblTaskObjectRegisterEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblTaskObjectRegister enittyNew = new TblTaskObjectRegister();
				enittyNew.setRegisterNo(enitty.getRegisterNo());
				enittyNew = (TblTaskObjectRegister) tblTaskObjectRegisterSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
				return "taskobjectregister/add";
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
			int count = tblTaskObjectRegisterEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
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
			TblTaskObjectRegister enitty = new TblTaskObjectRegister();
			enitty.setRegisterNo(request.getParameter("id_key"));
			TaskObjectRegisterPo po = (TaskObjectRegisterPo) tblTaskObjectRegisterSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "taskobjectregister/add";
	}

}
