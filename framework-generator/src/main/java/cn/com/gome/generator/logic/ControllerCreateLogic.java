/**   
* @Title: DaoEntityVoCreateLogic.java 
* @Package cn.com.gome.generator.logic 
* @Description: 控制器层实现
* @author GOME
* @date 2017年6月1日 下午2:16:49 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.generator.util.MysqlCache;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Map;

/** 
 * @ClassName: DaoEntityVoCreateLogic 
 * @Description: 控制器层实现 
 * @author GOME
 * @date 2017年6月1日 下午2:16:49  
 */
@Service
public class ControllerCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {
	
	private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
		try{
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");
			String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");
			
			TblChildProjectInfo childProjectInfo = new TblChildProjectInfo();
			childProjectInfo.setProjectCode(projectBasicInfo.getProjectCode());
			childProjectInfo.setProjectType("060");
			childProjectInfo = tblChildProjectInfoSerMapper.queryObj(childProjectInfo);
			
			String fileName = columnToStringU(tableDaoInfo.getTableEng());
			String fileNameVo = fileName.substring(3, fileName.length());
			
			StringBuilder sb = new StringBuilder();
			sb.append("/**\n");   
			sb.append(" * @Title: "+fileNameVo+"Contorller.java\n");   
			sb.append(" * @Package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".controller\n");   
			sb.append(" * @Description: "+tableDaoInfo.getTableChan()+"的控制器\n");   
			sb.append(" * @author GOME\n");
			sb.append(" * @date "+new Date().toString()+"\n");   
			sb.append(" * @company cn.com.gome\n");  
			sb.append(" * @version V1.0\n");     
			sb.append(" */\n");   
			sb.append("\n");  
			sb.append("package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".admin.controller;\n");  
			sb.append("\n");  
			
			sb.append("import org.apache.commons.lang.StringUtils;\n");  
			sb.append("import java.text.SimpleDateFormat;\n");  
			sb.append("import java.util.Date;\n");  
			sb.append("import java.util.Arrays;\n");
			sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");  
			sb.append("import org.springframework.stereotype.Controller;\n");  
			sb.append("import org.springframework.web.bind.annotation.ModelAttribute;\n");  
			sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n"); 
			sb.append("import javax.servlet.http.HttpServletRequest;\n"); 
			sb.append("import javax.servlet.http.HttpServletResponse;\n"); 
			sb.append("import net.sf.json.JSONArray;\n"); 
			sb.append("import org.springframework.ui.Model;\n");
			sb.append("import com.gomeplus.jdbc.page.Entitys;\n"); 
			sb.append("import com.gomeplus.frame.controller.AbstractAdminController;\n");  
			sb.append("import com.gomeplus.jdbc.page.PageQueryEntitys;\n");  
			sb.append("import java.util.List;\n");
			if("020".equals(tableDaoInfo.getAddDic())){
				sb.append("import com.gomeplus.frame.cache.DictionaryVo;\n");
				sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n");
				sb.append("import com.gomeplus.frame.cache.GlobalDataDictionaryCache;\n");
			}
			sb.append("import com.gomeplus.jdbc.service.EntityPersistenceLayerService;\n"); 
			sb.append("import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;\n"); 
			sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+";\n");  
			sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".vo."+fileNameVo+"Vo;\n"); 
			sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.edit."+fileName+"EditMapper;\n");  
			sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.ser."+fileName+"SerMapper;\n"); 
			
			sb.append("\n");  
			sb.append("/**\n");  
			sb.append(" * @ClassName: "+fileNameVo+"Contorller\n");   
			sb.append(" * @Description: "+tableDaoInfo.getTableChan()+"的控制器\n");   
			sb.append(" * @author GOME\n");
			sb.append(" * @date 2015年2月10日 下午2:35:34\n");    
			sb.append(" */\n");  
			sb.append("@Controller\n");  
			sb.append("@RequestMapping(\"/"+fileNameVo.toLowerCase()+"\")\n");  
			sb.append("public class "+fileNameVo+"Controller extends AbstractAdminController {\n");  
			sb.append("\n");  
			sb.append("	@Autowired\n");  
			sb.append("	private EntityPersistenceLayerService entityPersistenceLayerService;\n\n");  
			String fileNameL = fileName.substring(0, 1).toLowerCase()+fileName.substring(1, fileName.length());
			sb.append("	@Autowired\n");  
			sb.append("	private "+fileName+"EditMapper "+fileNameL+"EditMapper;\n\n"); 
			sb.append("	@Autowired\n");  
			sb.append("	private "+fileName+"SerMapper "+fileNameL+"SerMapper;\n"); 
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/inits\")\n");  
			sb.append("	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {\n");  
			sb.append("\n");  
			sb.append("		return \""+fileNameVo.toLowerCase()+"/inits\";\n");  
			sb.append("	}\n");  
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/search\")\n");  
			sb.append("	public void search(@ModelAttribute(\"pageQueryEntitys\") PageQueryEntitys pageQueryEntitys,HttpServletRequest request, HttpServletResponse response) {\n");  
			sb.append("		try {\n");  
			sb.append("			pageQueryEntitys.setEntityClassName(\""+fileName+"\");\n");  
			String[] queryList = tableDaoInfo.getQueryColumns().split(",");
			String VIEW_COLUMNS = tableDaoInfo.getViewColumns();
			String[] colmunName = VIEW_COLUMNS.split(",");
			String[] colmunType =tableDaoInfo.getColumnType().split(",");
			if(queryList!=null){
				String beanName = "";
				/*1:文本框;2:下拉列表;3:单选按钮;4:复选框;5:文本域;6:日期文本框;7:文件上传;8:隐藏域;*/
				for(String index : queryList){
					beanName = columnToStringL((colmunName[Integer.valueOf(index)]));
					if(!"6".equals(colmunType[Integer.valueOf(index)])){
						sb.append("			String "+beanName+" = request.getParameter(\""+beanName+"\");\n");  
						sb.append("			if (StringUtils.isNotEmpty("+beanName+")) {\n");  
						sb.append("				pageQueryEntitys.addF(\""+beanName+"\", "+beanName+");\n");  
						sb.append("			}\n");  
					}else{
						sb.append("			String "+beanName+"s = request.getParameter(\""+beanName+"s\");\n");  
						sb.append("			if (StringUtils.isNotEmpty("+beanName+"s)) {\n");  
						sb.append("				pageQueryEntitys.addF(\""+beanName+"s\", "+beanName+"s);\n");  
						sb.append("			}\n");  
						sb.append("			String "+beanName+"e = request.getParameter(\""+beanName+"e\");\n");  
						sb.append("			if (StringUtils.isNotEmpty("+beanName+"e)) {\n");  
						sb.append("				pageQueryEntitys.addF(\""+beanName+"e\", "+beanName+"e);\n");  
						sb.append("			}\n");  
					}
				}
			}
			sb.append("			pageQueryEntitys = entityPersistenceLayerService.queryPageVoList((PersistenceLayerSerMapper) "+fileNameL+"SerMapper, pageQueryEntitys);\n");  
			
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			logger.error(\" "+tableDaoInfo.getTableChan()+"的控制器执行异常【search】\",e);\n");  
			sb.append("		}\n");
			sb.append("		ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(),pageQueryEntitys.getTotalCount(),response);\n");  
			sb.append("	}\n");  
			sb.append("\n");  
			
			sb.append("	@RequestMapping(\"/select\")\n");
			sb.append("	public void select(HttpServletRequest request, HttpServletResponse response) {\n");
			sb.append("		try {\n");
			sb.append("			"+fileName+" obj = new "+fileName+"();\n");
			sb.append("			List<"+fileName+"> list = "+fileNameL+"SerMapper.queryList(obj);\n");
			sb.append("			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());\n");
			sb.append("		} catch (Exception e) {\n");
			sb.append("			logger.error(\" "+tableDaoInfo.getTableChan()+"的控制器执行异常【select】\",e);\n");
			sb.append("		}\n");
			sb.append("	}\n");
			sb.append("\n");  
			
			sb.append("	@RequestMapping(\"/add\")\n");  
			sb.append("	public String add(HttpServletRequest request, HttpServletResponse response,Model model){\n");  
			sb.append("		model.addAttribute(\"resultType\", \"add\");\n");  
			sb.append("		return \""+fileNameVo.toLowerCase()+"/add\";\n");  
			sb.append("	}\n"); 
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/save\")\n");  
			sb.append("	public String save(@ModelAttribute(\"enitty\") "+fileName+" enitty,HttpServletRequest request, HttpServletResponse response,Model model) {\n");  
			sb.append("		try {\n");  
			sb.append("			model.addAttribute(\"resultStatus\", \"false\");\n");
			//0:隐藏域    1:文本框    2:下拉列表   3:单选按钮  4:复选框   5:文本域   6:日期文本框  7:文件上传  8:系统时间  9:金额文本框 10:浮点数文本框                   
			String[] PAGE_FORM = tableDaoInfo.getPageForm().split(",");
			for(int i=0;i<colmunName.length;i++){
				if("8".equals(PAGE_FORM[i])){
					sb.append("			enitty.set"+columnToStringU(colmunName[i])+"(new SimpleDateFormat(\"yyyyMMddHHmmss\").format(new Date()));\n");	
				}
				
			}
			sb.append("			"+fileNameL+"EditMapper.save(enitty);\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：添加成功！\");\n");  
			sb.append("			model.addAttribute(\"resultStatus\", \"true\");\n");  
			if("020".equals(tableDaoInfo.getAddDic())){
				sb.append("			inits();\n");  
			}
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：添加异常:\" + e.getMessage());\n");  
			sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【save】\",e);\n");  
			sb.append("		}\n");  
			sb.append("		model.addAttribute(\"ADN_ADD_URL\", \""+fileNameVo.toLowerCase()+"/add.dhtml\");\n");  
			sb.append("		return \"success\";\n");  
			sb.append("\n");  		
			sb.append("	}\n");  
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/query\")\n");  
			sb.append("	public String query(HttpServletRequest request, HttpServletResponse response,Model model) {\n");  
			sb.append("		try {\n");  
			sb.append("			model.addAttribute(\"resultType\", \"edit\");\n");
			sb.append("			model.addAttribute(\"resultStatus\", \"false\");\n");
			sb.append("			"+fileName+" enitty = new "+fileName+"();\n");
		
			
			int indexType = 0;
			for(int i=1 ; i < colmunName.length;i++){
				if(colmunName[i].equals(tableDaoInfo.getKeyText())){
					indexType = i;
				}
			}
			String a = columnToStringU(tableDaoInfo.getKeyText());
			if("INTEGER".equals(MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType()))){
				sb.append("			enitty.set"+a+"(Integer.valueOf(request.getParameter(\"id_key\")));\n");  
			}else{
				sb.append("			enitty.set"+a+"(request.getParameter(\"id_key\"));\n");  
			}
			sb.append("			enitty = ("+fileName+") "+fileNameL+"SerMapper.queryObj(enitty);\n");  
			sb.append("			if (enitty != null){\n");  
			sb.append("				model.addAttribute(\"enitty\", enitty);\n");  
			sb.append("				model.addAttribute(\"resultStatus\", \"true\");\n");  
			sb.append("				return \""+fileNameVo.toLowerCase()+"/add\";\n");  
			sb.append("			}\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：没有查询到符合条件的记录！\");\n");  
			sb.append("\n");  
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：查询异常:\" + e.getMessage());\n"); 
			sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【query】\",e);\n"); 
			sb.append("		}\n");  
			sb.append("		return \"success\";\n");  
			sb.append("	}\n");  
			sb.append("\n"); 
			sb.append("	@RequestMapping(\"/edit\")\n");  
			sb.append("	public String edit(@ModelAttribute(\"enitty\") "+fileName+" enitty,HttpServletRequest request, HttpServletResponse response,Model model) {\n");  
			sb.append("		try {\n");  
			sb.append("			model.addAttribute(\"resultStatus\", \"false\");\n");
			//0:隐藏域    1:文本框    2:下拉列表   3:单选按钮  4:复选框   5:文本域   6:日期文本框  7:文件上传  8:系统时间  9:金额文本框 10:浮点数文本框                   
			for(int i=0;i<colmunName.length;i++){
				if("8".equals(PAGE_FORM[i])){
					sb.append("			enitty.set"+columnToStringU(colmunName[i])+"(new SimpleDateFormat(\"yyyyMMddHHmmss\").format(new Date()));\n");	
				}
				
			}
			sb.append("			int count = "+fileNameL+"EditMapper.edit(enitty);\n");  
			sb.append("			if (count == 0) {\n");  
			sb.append("				model.addAttribute(\"messages\", \"温馨提示：修改失败！\");\n");  
			sb.append("			} else {\n");  
			sb.append("				model.addAttribute(\"messages\", \"温馨提示：修改成功！\");\n");  
			sb.append("				model.addAttribute(\"resultStatus\", \"true\");\n");  
			sb.append("				"+fileName+" enittyNew = new "+fileName+"();\n");  
			sb.append("				enittyNew.set"+a+"(enitty.get"+a+"());\n");  
			sb.append("				enittyNew = ("+fileName+") "+fileNameL+"SerMapper.queryObj(enittyNew);\n");  
			sb.append("				model.addAttribute(\"enitty\", enittyNew);\n");  
			sb.append("				model.addAttribute(\"resultStatus\", \"true\");\n");  
			sb.append("				model.addAttribute(\"resultType\", \"edit\");\n");
			if("020".equals(tableDaoInfo.getAddDic())){
				sb.append("				inits();\n");  
			}
			sb.append("				return \""+fileNameVo.toLowerCase()+"/add\";\n");  
			sb.append("			}\n");  
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：修改异常:\" + e.getMessage());\n");  
			sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【edit】\",e);\n");  
			sb.append("		}\n");  
			sb.append("		return \"success\";\n");  
			sb.append("	}\n");  
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/del\")\n");  
			sb.append("	public void del(HttpServletRequest request, HttpServletResponse response,Model model) {\n");  
			sb.append("		try {\n");  
			sb.append("			model.addAttribute(\"resultStatus\", \"false\");\n");
			sb.append("			int count = "+fileNameL+"EditMapper.del(Arrays.asList(request.getParameter(\"id_key\").split(\",\")));\n");  
			sb.append("			if (count == 0) {\n");  
			sb.append("				ajaxJsonMessage(response,\"0002\",\"温馨提示：没有删除到符合条件的记录！\",\"\");\n");  
			sb.append("			} else {\n");  
			sb.append("				ajaxJsonMessage(response,\"0000\",\"温馨提示：删除成功！\",\"\");\n");  
			if("020".equals(tableDaoInfo.getAddDic())){
				sb.append("				inits();\n");  
			}
			sb.append("			}\n");  
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			ajaxJsonMessage(response,\"0002\",\"温馨提示：删除失败！\",\"\");\n"); 
			sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【del】\",e);\n");
			sb.append("		}\n"); 
			sb.append("	}\n");  
			sb.append("\n");  
			sb.append("	@RequestMapping(\"/view\")\n");  
			sb.append("	public String view(HttpServletRequest request, HttpServletResponse response,Model model) {\n");  
			sb.append("		try {\n");  
			sb.append("			model.addAttribute(\"resultType\", \"view\");\n");
			sb.append("			model.addAttribute(\"resultStatus\", \"false\");\n");
			sb.append("			"+fileName+" enitty = new "+fileName+"();\n");  
			if("INTEGER".equals(MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType()))){
				sb.append("			enitty.set"+a+"(Integer.valueOf(request.getParameter(\"id_key\")));\n");  
			}else{
				sb.append("			enitty.set"+a+"(request.getParameter(\"id_key\"));\n");  
			}
			sb.append("			"+fileNameVo+"Vo vo = ("+fileNameVo+"Vo) "+fileNameL+"SerMapper.queryVo(enitty);\n");  
			sb.append("			if (vo != null){\n");  
			sb.append("				model.addAttribute(\"enitty\", vo);\n");  
			sb.append("				model.addAttribute(\"resultStatus\", \"true\");\n");  
			sb.append("			}else{\n");  
			sb.append("				model.addAttribute(\"messages\", \"温馨提示：没有查询到符合条件的记录！\");\n");  
			sb.append("			}\n");  
			sb.append("		} catch (Exception e) {\n");  
			sb.append("			model.addAttribute(\"messages\", \"温馨提示：查询异常:\" + e.getMessage());\n");
			sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【view】\",e);\n");
			sb.append("		}\n"); 
			sb.append("		return \""+fileNameVo.toLowerCase()+"/add\";\n");  
			sb.append("	}\n\n"); 
			if("020".equals(tableDaoInfo.getAddDic())){
				sb.append("	@PostConstruct\n");
				sb.append("	private void inits(){\n");
				sb.append("		try {\n");
				sb.append("			List<"+fileName+"> list = "+fileNameL+"SerMapper.queryList(new "+fileName+"());\n");
				sb.append("			if (list != null) {\n");
				sb.append("				List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();\n");
				sb.append("				DictionaryVo idval = null;\n");
				sb.append("				for ("+fileName+" info : list) {\n");
				sb.append("					GlobalApplicationCache.getInstance().put(\""+tableDaoInfo.getKeyText()+".\" + info.get"+columnToStringU(tableDaoInfo.getKeyText())+"(),info.get"+columnToStringU(tableDaoInfo.getKeyText())+"());//TODO 待修正\n");
				sb.append("					idval = new DictionaryVo(info.get"+columnToStringU(tableDaoInfo.getKeyText())+"()+\"\", info.get"+columnToStringU(tableDaoInfo.getKeyText())+"(), false);//TODO 待修正\n");
				sb.append("					ivList.add(idval);\n");
				sb.append("				}\n");
				sb.append("				GlobalDataDictionaryCache.getInstance().putIdValue(\""+tableDaoInfo.getKeyText()+"\", ivList);\n");
				sb.append("			} else {\n");
				sb.append("				logger.info(\"温馨提示：没有查询到记录！\");\n");
				sb.append("			}\n");
				sb.append("		} catch (Exception e) {\n");
				sb.append("			logger.error(\" "+fileNameVo.toLowerCase()+"的控制器执行异常【inits】\",e);\n");
				sb.append("		}\n");
				sb.append("	}\n");
			}
			
			sb.append("}\n");  
			System.out.println(sb.toString());
			File file = new File(projectBasicInfo.getProjectPath() + "//" + projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//admin//controller//");
			if(!file.exists()){
				file.mkdirs();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(projectBasicInfo.getProjectPath() + "//" + projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//admin//controller//" + fileNameVo + "Controller.java"), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(sb.toString());
			writer.flush();
			writer.close();
			
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("创建子项目异常：",e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
