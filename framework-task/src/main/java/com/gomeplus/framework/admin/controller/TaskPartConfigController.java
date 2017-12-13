/**
 * @Title: TaskPartConfigContorller.java
 * @Package com.gome.login.controller
 * @Description: 任务分片配置表的控制器
 * @author chenmin-ds
 * @date Sat Apr 08 10:30:54 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package com.gomeplus.framework.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

import cn.com.gome.framework.dao.entity.TblTaskPartConfig;
import cn.com.gome.framework.dao.mapper.edit.TblTaskPartConfigEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskPartConfigSerMapper;
import cn.com.gome.framework.po.TaskPartConfigPo;

import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;

/**
 * @ClassName: TaskPartConfigContorller
 * @Description: 任务分片配置表的控制器
 * @author chenmin-ds
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/taskpartconfig")
public class TaskPartConfigController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblTaskPartConfigSerMapper tblTaskPartConfigSerMapper;
	
	@Autowired
	private TblTaskPartConfigEditMapper tblTaskPartConfigEditMapper;

	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "taskpartconfig/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblTaskPartConfig");
			String taskNo = request.getParameter("taskNo");
			if (StringUtils.isNotEmpty(taskNo)) {
				pageQueryEntitys.addF("taskNo", taskNo);
			}
			String objTaskKey = request.getParameter("objTaskKey");
			if (StringUtils.isNotEmpty(objTaskKey)) {
				pageQueryEntitys.addF("objTaskKey", objTaskKey);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTaskPartConfigSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblTaskPartConfig> list = null;
		try {
			TblTaskPartConfig obj = new TblTaskPartConfig();
			list = tblTaskPartConfigSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "taskpartconfig/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblTaskPartConfig enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			tblTaskPartConfigEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "taskpartconfig/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblTaskPartConfig enitty = new TblTaskPartConfig();
			enitty.setPartNo(request.getParameter("id_key"));
			enitty = (TblTaskPartConfig) tblTaskPartConfigSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "taskpartconfig/add";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblTaskPartConfig enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			int count = tblTaskPartConfigEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblTaskPartConfig enittyNew = new TblTaskPartConfig();
				enittyNew.setPartNo(enitty.getPartNo());
				enittyNew = (TblTaskPartConfig) tblTaskPartConfigSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
				return "taskpartconfig/add";
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
			int count = tblTaskPartConfigEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
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
			TblTaskPartConfig enitty = new TblTaskPartConfig();
			enitty.setPartNo(request.getParameter("id_key"));
			TaskPartConfigPo po = (TaskPartConfigPo) tblTaskPartConfigSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "taskpartconfig/add";
	}

}
