/**
 * @Title: TableDaoInfoContorller.java
 * @Package com.gome.framework.controller
 * @Description: 表持久化信息表的控制器
 * @author GOME
 * @date Fri May 05 14:26:22 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller.creators;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.framework.dao.mapper.edit.TblTableDaoInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTableDaoInfoSerMapper;
import cn.com.gome.framework.po.TableDaoInfoPo;
import cn.com.gome.generator.service.DatabaseTypeService;
import cn.com.gome.generator.service.FilesCreateService;

import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;

/**
 * @ClassName: TableDaoInfoContorller
 * @Description: 表持久化信息表的控制器
 * @author GOME
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/tabledaoinfo")
public class DaoCodeCreateController extends AbstractAdminController {

	@Autowired
	private EntityPersistenceLayerService entityPersistenceLayerService;
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Autowired
	private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
	
	@Autowired
	private DatabaseTypeService databaseTypeService;
	
	@Autowired
	private TblTableDaoInfoEditMapper tblTableDaoInfoEditMapper;
	
	@Autowired
	private TblTableDaoInfoSerMapper tblTableDaoInfoSerMapper;
	
	@Autowired
	private FilesCreateService filesCreateService;

	@RequestMapping("/inits")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {

		return "tabledaoinfo/inits";
	}

	@RequestMapping("/search")
	public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {
		try {
			pageQueryEntitys.setEntityClassName("TblTableDaoInfo");
			String childProjectCode = request.getParameter("childProjectCode");
			if (StringUtils.isNotEmpty(childProjectCode)) {
				pageQueryEntitys.addF("childProjectCode", childProjectCode);
			}
			String tableChan = request.getParameter("tableChan");
			if (StringUtils.isNotEmpty(tableChan)) {
				pageQueryEntitys.addF("tableChan", tableChan);
			}
			String tableEng = request.getParameter("tableEng");
			if (StringUtils.isNotEmpty(tableEng)) {
				pageQueryEntitys.addF("tableEng", tableEng);
			}
			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTableDaoInfoSerMapper,pageQueryEntitys);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);
	}

	@RequestMapping("/select")
	public void select(HttpServletRequest request, HttpServletResponse response) {
		List<TblTableDaoInfo> list = null;
		try {
			TblTableDaoInfo obj = new TblTableDaoInfo();
			list = tblTableDaoInfoSerMapper.queryList(obj);
			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("resultType", "add");
		String childProjectCode = request.getParameter("childProjectCode");
		String tableEng = request.getParameter("tableEng");
		tableEng=tableEng.toUpperCase();
		TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(childProjectCode);
		if(null != childProjectInfo){
			TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(String.valueOf(childProjectInfo.getProjectCode()));
			if(null != projectBasicInfo){
				Map<String, String> map = databaseTypeService.service(projectBasicInfo.getDatabaseType(),projectBasicInfo.getDatabaseDriver(),projectBasicInfo.getDatabaseUrl()
						,projectBasicInfo.getDatabaseAccount(),projectBasicInfo.getDatabasePassword(),tableEng);
				model.addAttribute("COLUMNS_TYPE", map.get("COLUMNS_TYPE"));
				model.addAttribute("COLUMNS_CODE", map.get("COLUMNS_CODE"));
				model.addAttribute("COLUMNS_NAME", map.get("COLUMNS_NAME"));
				model.addAttribute("COLUMNS_TYPE_LIST", map.get("COLUMNS_TYPE").split(","));
				model.addAttribute("COLUMNS_CODE_LIST", map.get("COLUMNS_CODE").split(","));
				model.addAttribute("COLUMNS_NAME_LIST", map.get("COLUMNS_NAME").split(","));
				model.addAttribute("KEY", map.get("KEY"));
				model.addAttribute("SHOW_COLUMNS", "SHOW_COLUMNS");
				model.addAttribute("count", map.get("COLUMNS_CODE").split(",").length);
				model.addAttribute("TABLE_NAME", map.get("TABLE_NAME"));
				model.addAttribute("TABLE_ENG", tableEng);
				model.addAttribute("childProjectCode", childProjectCode);
				return "tabledaoinfo/add";
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到主项目记录！");
				return "tabledaoinfo/query";
			}
		}else{
			model.addAttribute("messages", "温馨提示：没有查询到子项目记录！");
			return "tabledaoinfo/query";
		}
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enitty") TblTableDaoInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			String childProjectCode = request.getParameter("childProjectCode");
			String TABLE_CHAN = request.getParameter("TABLE_NAME");
			String TABLE_ENG = request.getParameter("TABLE_ENG");
			String KEY = request.getParameter("KEY");
			String COLUMNS_TYPE = request.getParameter("COLUMNS_TYPE");
			String COLUMNS_CODE = request.getParameter("COLUMNS_CODE");
			String COLUMNS_NAME = request.getParameter("COLUMNS_NAME");
			
			int count = COLUMNS_CODE.split(",").length;
			StringBuffer sb = new StringBuffer();
			StringBuffer sb_check = new StringBuffer();
			String type_v = "";
			for(int i=0;i<count ;i ++){
				type_v = request.getParameter("type_" + i);
				sb.append(type_v + ",");
				
				String[] checkVs = request.getParameterValues("check_" + i);
				if(null != checkVs && checkVs.length > 0){
					String a = "";
					for(int g=0;g<checkVs.length;g++){
						a = checkVs[g];
						if("1".equals(a)){
							sb_check.append("required: true,");
						}
						if("3".equals(a)){
							if(StringUtils.isNotEmpty(request.getParameter("check_" + i+"_c3"))) {
                                sb_check.append("validType:'length[0," + request.getParameter("check_" + i + "_c3") + "]',");
                            }
						}
						if("4".equals(a)){
							if(StringUtils.isNotEmpty(request.getParameter("check_" + i+"_c4_1"))) {
                                sb_check.append("min:0,");
                            }
							if(StringUtils.isNotEmpty(request.getParameter("check_" + i+"_c4_2"))) {
                                sb_check.append("max:0,");
                            }
							if(StringUtils.isNotEmpty(request.getParameter("check_" + i+"_c4_3"))) {
                                sb_check.append("precision:2,");
                            }
						}
					}
				}else{
					sb_check.append("TEST");
				}
				sb_check.append("|");
			}
			String PAGE_FORM = sb.toString();
			PAGE_FORM = PAGE_FORM.substring(0, PAGE_FORM.length()-1);
			String[] showList = request.getParameterValues("showList");
			String[] queryList = request.getParameterValues("queryList");
			String[] addList = request.getParameterValues("addList");
			String[] upList = request.getParameterValues("upList");
			
			String sb_check_str = sb_check.toString();
			if(StringUtils.isNotEmpty(sb_check_str)) {
                sb_check_str = sb_check_str.substring(0, sb_check_str.length() - 1);
            }
			
			
			TblTableDaoInfo entity = new TblTableDaoInfo();
			entity.setChildProjectCode(Integer.valueOf(childProjectCode));
			entity.setTableEng(TABLE_ENG);
			tblTableDaoInfoEditMapper.del(entity);
			
			entity.setTableChan(TABLE_CHAN);
			entity.setShowColumns(to(showList));
			entity.setQueryColumns(to(queryList));
			entity.setAddColumns(to(addList));
			entity.setEditColumns(to(upList));
			entity.setPageForm( PAGE_FORM);
			entity.setViewColumns(COLUMNS_CODE);
			entity.setColumnType(COLUMNS_TYPE);
			entity.setColumnsName( COLUMNS_NAME);
			entity.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			entity.setKeyText(KEY);
			entity.setValidateName(sb_check_str);
			entity.setAddDic(request.getParameter("addDic"));
			entity.setCreateStatus("010");
			String[] createFiless = request.getParameterValues("createFiles");
			if(createFiless != null && createFiless.length > 0){
				StringBuffer sbb = new StringBuffer();
				sbb.append(createFiless[0]);
				for(int i=1;i<createFiless.length;i++){
					if(StringUtils.isNotEmpty(createFiless[i])) {
                        sbb.append("," + createFiless[i]);
                    }
				}
				entity.setCreateFiles(sbb.toString());
			}
			tblTableDaoInfoEditMapper.save(entity);
			
			model.addAttribute("messages", "温馨提示：添加成功！");
			model.addAttribute("resultStatus", "true");
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
		}
		model.addAttribute("ADN_ADD_URL", "colse");
		return "success";

	}
	
	private String to(String[] COLUMNS){
		if(null != COLUMNS && COLUMNS.length > 0){
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<COLUMNS.length;i++){
				sb.append(COLUMNS[i] + ",");
			}
			String a = sb.toString();
			a = a.substring(0, a.length()-1);
			return a;
		}else{
			return "";
		}
	}
	
	@RequestMapping("/queryTable")
	public String queryTable(HttpServletRequest request, HttpServletResponse response,Model model) {
		return "tabledaoinfo/query";
	}

	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "edit");
			model.addAttribute("resultStatus", "false");
			
			
			TblTableDaoInfo enittyOld = new TblTableDaoInfo();
			enittyOld.setTableNumbers(Integer.valueOf(request.getParameter("id_key")));
			enittyOld = (TblTableDaoInfo) tblTableDaoInfoSerMapper.queryObj(enittyOld);
			if (enittyOld != null){
				model.addAttribute("resultStatus", "true");
				
				TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(enittyOld.getChildProjectCode().toString());
				if(null != childProjectInfo){
					TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(String.valueOf(childProjectInfo.getProjectCode()));
					if(null != projectBasicInfo){
						Map<String, String> map = databaseTypeService.service(projectBasicInfo.getDatabaseType(),projectBasicInfo.getDatabaseDriver(),projectBasicInfo.getDatabaseUrl()
								,projectBasicInfo.getDatabaseAccount(),projectBasicInfo.getDatabasePassword(),enittyOld.getTableEng());
						model.addAttribute("COLUMNS_TYPE", map.get("COLUMNS_TYPE"));
						model.addAttribute("COLUMNS_CODE", map.get("COLUMNS_CODE"));
						model.addAttribute("COLUMNS_NAME", map.get("COLUMNS_NAME"));
						model.addAttribute("COLUMNS_TYPE_LIST", map.get("COLUMNS_TYPE").split(","));
						model.addAttribute("COLUMNS_CODE_LIST", map.get("COLUMNS_CODE").split(","));
						model.addAttribute("COLUMNS_NAME_LIST", map.get("COLUMNS_NAME").split(","));
						model.addAttribute("KEY", map.get("KEY"));
						model.addAttribute("SHOW_COLUMNS", "SHOW_COLUMNS");
						model.addAttribute("count", map.get("COLUMNS_CODE").split(",").length);
						model.addAttribute("TABLE_NAME", map.get("TABLE_NAME"));
						model.addAttribute("TABLE_ENG", enittyOld.getTableEng());
						model.addAttribute("childProjectCode", childProjectInfo.getChildProjectCode().toString());
						return "tabledaoinfo/add";
					}else{
						model.addAttribute("messages", "温馨提示：没有查询到主项目记录！");
						return "tabledaoinfo/query";
					}
				}else{
					model.addAttribute("messages", "温馨提示：没有查询到子项目记录！");
					return "tabledaoinfo/query";
				}
				
			}
			model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "success";
	}

	@RequestMapping("/edit")
	public String edit(@ModelAttribute("enitty") TblTableDaoInfo enitty,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultStatus", "false");
			enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
			int count = tblTableDaoInfoEditMapper.edit(enitty);
			if (count == 0) {
				model.addAttribute("messages", "温馨提示：修改失败！");
			} else {
				model.addAttribute("messages", "温馨提示：修改成功！");
				model.addAttribute("resultStatus", "true");
				TblTableDaoInfo enittyNew = new TblTableDaoInfo();
				enittyNew.setTableNumbers(enitty.getTableNumbers());
				enittyNew = (TblTableDaoInfo) tblTableDaoInfoSerMapper.queryObj(enittyNew);
				model.addAttribute("enitty", enittyNew);
				model.addAttribute("resultStatus", "true");
				model.addAttribute("resultType", "edit");
				return "tabledaoinfo/add";
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
			int count = tblTableDaoInfoEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
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
	//
	@RequestMapping("/create")
	public void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id_key");
			String createFlag = request.getParameter("flag");
			boolean flag = false;
			if(StringUtils.isEmpty(createFlag)){
				flag = filesCreateService.daoControllerPage(id,"020");// 覆盖创建
			}else{
				flag = filesCreateService.daoControllerPage(id,createFlag);// 对比创建
			}
			if(flag){
				ajaxJsonMessage(response,"0000","温馨提示：持久层创建成功！","");
			}else{
				ajaxJsonMessage(response,"0002","温馨提示：持久层创建失败！","");
			}
		} catch (Exception e) {
			ajaxJsonMessage(response,"0002","温馨提示：持久层创建异常！","");
		}
	}

	@RequestMapping("/view")
	public String view(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("resultType", "view");
			model.addAttribute("resultStatus", "false");
			TblTableDaoInfo enitty = new TblTableDaoInfo();
			enitty.setTableNumbers(Integer.valueOf(request.getParameter("id_key")));
			TableDaoInfoPo po = (TableDaoInfoPo) tblTableDaoInfoSerMapper.queryPo(enitty);
			if (po != null){
				model.addAttribute("enitty", po);
				model.addAttribute("resultStatus", "true");
			}else{
				model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
			}
		} catch (Exception e) {
			model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
		}
		return "tabledaoinfo/view";
	}

}
