/**
 * @Title: ChildProjectInfoContorller.java
 * @Package com.gome.framework.controller
 * @Description: 子项目信息配置表的控制器
 * @author GOME
 * @date Fri May 05 14:26:19 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller.creators;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import cn.com.gome.framework.dao.mapper.edit.TblChildProjectInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblPomProfileInfoSerMapper;
import cn.com.gome.framework.po.ChildProjectInfoPo;
import cn.com.gome.generator.service.ProjectCreateService;
import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;
import com.google.common.base.Splitter;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ChildProjectInfoContorller
 * @Description: 子项目信息配置表的控制器
 * @author GOME
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/childprojectinfo")
public class ChildProjectCodeCreateController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private ProjectCreateService projectCreateService;
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;

	@Autowired
	private TblChildProjectInfoEditMapper tblChildProjectInfoEditMapper;

	@Autowired
	private TblPomProfileInfoSerMapper tblPomProfileInfoSerMapper;
	
	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "childprojectinfo/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblChildProjectInfo");
			String projectCode = request.getParameter("projectCode");
			if (StringUtils.isNotEmpty(projectCode)) {
				pageQueryEntitys.addF("projectCode", projectCode);
			}
			String childProjectName = request.getParameter("childProjectName");
			if (StringUtils.isNotEmpty(childProjectName)) {
				pageQueryEntitys.addF("childProjectName", childProjectName);
			}
			String childProjectEng = request.getParameter("childProjectEng");
			if (StringUtils.isNotEmpty(childProjectEng)) {
				pageQueryEntitys.addF("childProjectEng", childProjectEng);
			}
			String projectType = request.getParameter("projectType");
			if (StringUtils.isNotEmpty(projectType)) {
				pageQueryEntitys.addF("projectType", projectType);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblChildProjectInfoSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblChildProjectInfo> list = null;
		try {
			TblChildProjectInfo obj = new TblChildProjectInfo();
			String projectCode = request.getParameter("projectCode");
			if(StringUtils.isNotEmpty(projectCode)) {
				obj.setProjectCode(Integer.valueOf(projectCode));
			}
			
			String type = request.getParameter("type");
			if(StringUtils.isNotEmpty(type)) {
				obj.setProjectType(type);
			}
			
			list = tblChildProjectInfoSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "childprojectinfo/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblChildProjectInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			if ("dubbo".equals(enitty.getChildProjectEng())) {
				enitty.setChildProjectEng("service");
			}
			tblChildProjectInfoEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
			inits();
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "childprojectinfo/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblChildProjectInfo enitty = new TblChildProjectInfo();
			enitty.setChildProjectCode(Integer.valueOf(request.getParameter("id_key")));
			enitty = (TblChildProjectInfo) tblChildProjectInfoSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "childprojectinfo/edit";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblChildProjectInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			int count = tblChildProjectInfoEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblChildProjectInfo enittyNew = new TblChildProjectInfo();
				enittyNew.setChildProjectCode(enitty.getChildProjectCode());
				if ("dubbo".equals(enittyNew.getChildProjectEng())) {
					enittyNew.setChildProjectEng("service");
				}
				enittyNew = (TblChildProjectInfo) tblChildProjectInfoSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
                inits();
				return "childprojectinfo/edit";
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
			int count = tblChildProjectInfoEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
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
	
	@RequestMapping("/create")
	public void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("id_key");
			List<String> idList = Splitter.on(",").splitToList(ids);
			StringBuilder sb = new StringBuilder("温馨提示：");
			//没有创建主项目直接创建
			projectCreateService.mainProjectCreate("child", idList.get(0));

			for (int i = 0; i < idList.size(); i++) {
				sb.append((i + 1));
				boolean flag = projectCreateService.childProjectCreate(idList.get(i));
				if (flag) {
					sb.append("成功;");
				} else {
					sb.append("失败;");
				}
			}
			ajaxJsonMessage(response, "0000", sb.toString(), "");
		} catch (Exception e) {
			ajaxJsonMessage(response,"0002","温馨提示：项目创建异常！","");
		}
	}
	@RequestMapping("/selectByType")
	public void selectByType(HttpServletRequest request, HttpServletResponse response) {
		List<TblChildProjectInfo> list = null;
		try {
			TblChildProjectInfo obj = new TblChildProjectInfo();
			String childProjectEng = request.getParameter("childProjectEng");
			if(StringUtils.isNotEmpty(childProjectEng)) {
				obj.setChildProjectEng(childProjectEng);
			}

			list = tblChildProjectInfoSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "view");
			model.addAttribute("resultStatus", "false");
			TblChildProjectInfo enitty = new TblChildProjectInfo();
			enitty.setChildProjectCode(Integer.valueOf(request.getParameter("id_key")));
			ChildProjectInfoPo po = (ChildProjectInfoPo) tblChildProjectInfoSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "childprojectinfo/view";
	}

	@RequestMapping("queryPomProfiles")
	public void queryPomProfiles(@RequestParam(value = "projectCode") String projectCode, HttpServletResponse response) {
		List<TblPomProfileInfo> profileInfoList = tblPomProfileInfoSerMapper.queryPomProfiles(projectCode);
		if (!CollectionUtils.isEmpty(profileInfoList)) {
			for (int i = 0; i < profileInfoList.size(); i++) {
				profileInfoList.get(i).setConfigDescription(profileInfoList.get(i).getConfigDescription() + "|" + profileInfoList.get(i).getConfigName());
			}
		}
		ajaxJsonMessage(response, JSONArray.fromObject(profileInfoList).toString());
	}
	@PostConstruct
	private void inits(){
		try {
			List<TblChildProjectInfo> list = tblChildProjectInfoSerMapper.queryList(new TblChildProjectInfo());
			if (list != null) {
				List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();
				DictionaryVo idval = null;
				for (TblChildProjectInfo tblChildProjectInfo : list) {
					GlobalApplicationCache.getInstance().put("CHILD_PROJECT_CODE." + tblChildProjectInfo.getChildProjectCode(),
							tblChildProjectInfo.getChildProjectName());
					idval = new DictionaryVo(tblChildProjectInfo.getChildProjectCode()+"", tblChildProjectInfo.getChildProjectName(), false);
					ivList.add(idval);
				}
				GlobalDataDictionaryCache.getInstance().putIdValue("CHILD_PROJECT_CODE", ivList);
			} else {
				logger.info("温馨提示：没有查询到系统注册信息的记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
