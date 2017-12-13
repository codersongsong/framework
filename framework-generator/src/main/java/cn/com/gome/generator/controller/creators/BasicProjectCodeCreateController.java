/**
 * @Title: ProjectBasicInfoContorller.java
 * @Package com.gome.framework.controller
 * @Description: 项目基本信息表的控制器
 * @author GOME
 * @date Fri May 05 14:26:15 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller.creators;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.impl.QueryTableName;
import cn.com.gome.framework.dao.mapper.edit.TblProjectBasicInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.framework.po.ProjectBasicInfoPo;
import cn.com.gome.generator.service.ProjectCreateService;
import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProjectBasicInfoContorller
 * @Description: 项目基本信息表的控制器
 * @author GOME
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/projectbasicinfo")
public class BasicProjectCodeCreateController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
	
	@Autowired
	private TblProjectBasicInfoEditMapper tblProjectBasicInfoEditMapper;

	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Autowired
	private ProjectCreateService projectCreateService;

	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "projectbasicinfo/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblProjectBasicInfo");
			String projectName = request.getParameter("projectName");
			if (StringUtils.isNotEmpty(projectName)) {
				pageQueryEntitys.addF("projectName", projectName);
			}
			String projectEng = request.getParameter("projectEng");
			if (StringUtils.isNotEmpty(projectEng)) {
				pageQueryEntitys.addF("projectEng", projectEng);
			}
			String databaseType = request.getParameter("databaseType");
			if (StringUtils.isNotEmpty(databaseType)) {
				pageQueryEntitys.addF("databaseType", databaseType);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblProjectBasicInfoSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblProjectBasicInfo> list = null;
		try {
			TblProjectBasicInfo obj = new TblProjectBasicInfo();
			list = tblProjectBasicInfoSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		return "projectbasicinfo/add";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblProjectBasicInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			tblProjectBasicInfoEditMapper.save(enitty);
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
			inits();
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "projectbasicinfo/add.dhtml");
		return "success";

	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			TblProjectBasicInfo enitty = new TblProjectBasicInfo();
			enitty.setProjectCode(Integer.valueOf(request.getParameter("id_key")));
			enitty = (TblProjectBasicInfo) tblProjectBasicInfoSerMapper.queryObj(enitty);
			if (enitty != null){
				model.addAttribute("enitty", enitty);
				model.addAttribute("resultStatus", "true");
				return "projectbasicinfo/add";
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblProjectBasicInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			int count = tblProjectBasicInfoEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblProjectBasicInfo enittyNew = new TblProjectBasicInfo();
				enittyNew.setProjectCode(enitty.getProjectCode());
				enittyNew = (TblProjectBasicInfo) tblProjectBasicInfoSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
				inits();
				return "projectbasicinfo/add";
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
			int count = tblProjectBasicInfoEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
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
			String id = request.getParameter("id_key");
			boolean flag = projectCreateService.mainProjectCreate("main", id);
			if(flag){
				ajaxJsonMessage(response,"0000","温馨提示：项目创建成功！","");
			}else{
				ajaxJsonMessage(response,"0002","温馨提示：项目创建失败！","");
			}
		} catch (Exception e) {
			ajaxJsonMessage(response,"0002","温馨提示：项目创建异常！","");
		}
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "view");
			model.addAttribute("resultStatus", "false");
			TblProjectBasicInfo enitty = new TblProjectBasicInfo();
			enitty.setProjectCode(Integer.valueOf(request.getParameter("id_key")));
			ProjectBasicInfoPo po = (ProjectBasicInfoPo) tblProjectBasicInfoSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "projectbasicinfo/add";
	}
	/**
	 *	查询出所有的表名
	 * @param request
	 * @param response
	 * @author wangheming 2017 12 04
	 */
	@RequestMapping("/queryTblName")
	public void queryTblName(HttpServletRequest request, HttpServletResponse response) {
		List<String> list = null;
		try {
			// JDBC 代码动态访问数据库
			String childProjectCode = request.getParameter("childProjectCode");
			// 步骤一：查询出子表所有信息
			TblChildProjectInfo childProjectInfo = new TblChildProjectInfo();
			childProjectInfo.setChildProjectCode(Integer.parseInt(childProjectCode));
			childProjectInfo = tblChildProjectInfoSerMapper.queryObj(childProjectInfo);
			// 步骤二：查询出对应主项目信息
			TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(String.valueOf(childProjectInfo.getProjectCode()));

			QueryTableName queryTableName = new QueryTableName();
			// 区分数据库类型（oracle， mysql）
			String databaseDriver = projectBasicInfo.getDatabaseDriver();
			List names = new ArrayList();
			if(databaseDriver.indexOf("mysql") != -1){
				names = queryTableName.mysqlTableInfo(databaseDriver, projectBasicInfo.getDatabaseUrl(), projectBasicInfo.getDatabaseAccount(), projectBasicInfo.getDatabasePassword(), projectBasicInfo.getProjectEng());
			}
			if(databaseDriver.indexOf("oracle") != -1){
				names = queryTableName.oracleTableInfo(databaseDriver, projectBasicInfo.getDatabaseUrl(), projectBasicInfo.getDatabaseAccount(), projectBasicInfo.getDatabasePassword(), projectBasicInfo.getProjectEng());
			}
			ajaxJsonMessage(response,JSONArray.fromObject(names).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	private void inits(){
		try {
			List<TblProjectBasicInfo> list = tblProjectBasicInfoSerMapper.queryList(new TblProjectBasicInfo());
			if (list != null) {
				List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();
				DictionaryVo idval = null;
				for (TblProjectBasicInfo tblProjectBasicInfo : list) {
					GlobalApplicationCache.getInstance().put("PROJECT_CODE." + tblProjectBasicInfo.getProjectCode(),
							tblProjectBasicInfo.getProjectName());
					idval = new DictionaryVo(tblProjectBasicInfo.getProjectCode()+"", tblProjectBasicInfo.getProjectName(), false);
					ivList.add(idval);
				}
				GlobalDataDictionaryCache.getInstance().putIdValue("PROJECT_CODE", ivList);
			} else {
				logger.info("温馨提示：没有查询到系统注册信息的记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
