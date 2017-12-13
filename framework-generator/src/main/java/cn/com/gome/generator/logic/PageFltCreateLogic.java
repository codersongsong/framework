/**   
* @Title: DaoEntityVoCreateLogic.java 
* @Package cn.com.gome.generator.logic 
* @Description: 实体层和展现层代码生成
* @author GOME
* @date 2017年6月1日 下午2:16:49 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.gome.generator.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;

import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;

/** 
 * @ClassName: DaoEntityVoCreateLogic 
 * @Description: 实体层和展现层代码生成 
 * @author GOME
 * @date 2017年6月1日 下午2:16:49  
 */
@Service
public class PageFltCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {
	
	private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Override
	public ResultEnum exec(Map<String, Object> map) throws LogicsException {
		try{
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");
			
			TblChildProjectInfo childProjectInfoSix = new TblChildProjectInfo();
			childProjectInfoSix.setProjectCode(projectBasicInfo.getProjectCode());
			childProjectInfoSix.setProjectType("060");
			childProjectInfoSix = tblChildProjectInfoSerMapper.queryObj(childProjectInfoSix);
			
			String fileName = columnToStringU(tableDaoInfo.getTableEng());
			String fileNameVo = fileName.substring(3, fileName.length());
			
			StringBuilder sb = new StringBuilder();
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
			sb.append("<head>\n");
			sb.append("	<base href='${Request[\"basePath\"] ! \"\"}' />\n");
			sb.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
			sb.append("	<title>"+tableDaoInfo.getTableChan()+"管理</title>\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/metro/easyui.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/icon.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/inits.css\">\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\n");
			sb.append("</head>\n");
			sb.append("<body class=\"easyui-layout\">\n");
			sb.append("<div data-options=\"region:'west',border:false,width:'220px'\" id=\"west_h\" style=\"background-color:#e2e5ed;\">\n");
			sb.append("	<div class=\"search-form\" id=\"searchClear\">\n");
			String[] queryList = tableDaoInfo.getQueryColumns().split(",");
			String[] colmunName = tableDaoInfo.getViewColumns().split(",");
			String[] colmunComment = tableDaoInfo.getColumnsName().split(",");
			String[] colmunCode = tableDaoInfo.getViewColumns().split(",");
			String[] PAGE_FORM = tableDaoInfo.getPageForm().split(",");
			String[] colmunType = tableDaoInfo.getColumnType().split(",");
			
			//0:隐藏域    1:文本框    2:下拉列表   3:单选按钮  4:复选框   5:文本域   6:日期文本框  7:文件上传  8:系统时间  9:金额文本框 10:浮点数文本框         
			int a = 0;
			for(int i=0;i<queryList.length;i++){
				a = Integer.valueOf(queryList[i]);
				if("2".equals(PAGE_FORM[a]) || "3".equals(PAGE_FORM[a]) || "4".equals(PAGE_FORM[a])){
					sb.append("		<input type=\"text\" id=\""+columnToStringL(colmunCode[a])+"\"  name=\""+columnToStringL(colmunCode[a])+"\" placeholder=\"请输入"+colmunComment[a]+"\" class=\"easyui-combobox search-input\" data-options=\"inputId:'"+columnToStringL(colmunCode[a])+"',editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key="+colmunCode[a]+"'\">\n");	
				}else if("6".equals(PAGE_FORM[a])){
					sb.append("		<input type=\"text\"  id=\""+columnToStringL(colmunCode[a])+"\" name=\""+columnToStringL(colmunCode[a])+"\" placeholder=\"请输入"+colmunComment[a]+"\" class=\"easyui-datetimebox search-input\">\n");
				}else{
					sb.append("		<input type=\"text\"  id=\""+columnToStringL(colmunCode[a])+"\" name=\""+columnToStringL(colmunCode[a])+"\" placeholder=\"请输入"+colmunComment[a]+"\" class=\"easyui-textbox search-input\">\n");
				}
			}
			sb.append("		<div class=\"search-opera\">\n");
			sb.append("			<span id=\"search_button\" class=\"easyui-linkbutton\" iconCls=\"icon-search\">想要什么？</span>\n");
			sb.append("			<span id=\"search_clear_button\" class=\"easyui-linkbutton\" iconCls=\"icon-clear\">清理一下？</span>\n");
			sb.append("		</div>\n");
			sb.append("	</div>\n");
			sb.append("</div>\n");
			sb.append("\n");
			sb.append("<div data-options=\"region:'center',border:false\" style=\"background-color:#ededed\">\n");
			sb.append("	<div class=\"center-con\">\n");
			sb.append("		<ul class=\"oper-item \" id=\"menus_divs\">\n");
			sb.append("	    	<li class=\"oper-btn\" id=\"ssjg_div\"><i class=\"oper-search-btn\"></i><span>搜索结果</span></li>\n");
			sb.append("	    	<li class=\"oper-btn\" id=\"tjsj_div\"><i class=\"oper-add-btn\"></i><span>添加数据</span></li>\n");
			sb.append("	    </ul>\n");
			sb.append("		<div id=\"alls_divs\">\n");
			sb.append("			<div id=\"ssjg\">\n");
			
			sb.append("				<table title=\"业务系统表数据列表\" id=\"tt\"  style=\"width:100%;height:615px;\" url=\""+fileNameVo.toLowerCase()+"/search.dhtml\"\n");
			sb.append("			         data-options=\"striped:true,rownumbers:true,pagination:true\">\n");
			sb.append("					<thead>\n");
			sb.append("		        		<tr>\n");
			sb.append("	            		<th field=\""+columnToStringL(tableDaoInfo.getKeyText())+"\" checkbox=\"true\" width=\"40\" align=\"center\">序号</th>\n");
			String [] showList =  tableDaoInfo.getShowColumns().split(",");
			if(showList !=null){
				int index = 0;
				for(int i=0;i<showList.length;i++){
					index = Integer.valueOf(showList[i]);
					if(!colmunName[index].equals(tableDaoInfo.getKeyText())){
						sb.append("	            		<th field=\""+columnToStringL(colmunName[index])+"_colmun\" width=\"150\" align=\"left\">"+colmunComment[index]+"</th>\n");
					}
				}
			}
			sb.append("		        		</tr>\n");
			sb.append("				    </thead>\n");
			sb.append("				</table>\n");
			sb.append("			</div>\n");
			sb.append("			<div id=\"tjsj\" style=\"height:600px;\">\n");
			sb.append("				<iframe scrolling=\"no\" id=\"tjsj_add\" frameborder=\"no\" frameborder=\"0\" marginwidth=\"0\" marginheight=\"0\"  src=\""+fileNameVo.toLowerCase()+"/add.dhtml\" style=\"width:100%;height:600px;\"></iframe>\n");
			sb.append("			</div>\n");
			sb.append("			<div id=\"xgsj\" style=\"height:600px;\"></div>\n");
			sb.append("			<div id=\"llsj\" style=\"height:600px;\"></div>\n");
			sb.append("		</div>\n");
			sb.append("	</div>\n");
			sb.append("</div>\n");
			sb.append("<div id=\"mm\" class=\"easyui-menu\" data-options=\"onClick:menuHandler\" style=\"width:120px;\">\n");
			sb.append("    <div data-options=\"name:'add',iconCls:'icon-save'\" add_div_id=\"tjsj\">添加数据</div>\n");
			sb.append("    <div data-options=\"name:'query',iconCls:'icon-edit'\" query_div_id=\"xgsj\">修改数据</div>\n");
			sb.append("    <div data-options=\"name:'del',iconCls:'icon-no'\" del_div_id=\"sqsj\">删除数据</div>\n");
			sb.append("</div>\n");
			sb.append("<input type=\"hidden\" id=\"url\" value=\""+fileNameVo.toLowerCase()+"/\" />\n");
			sb.append("<input type=\"hidden\" id=\"pri_key\" value=\""+columnToStringL(tableDaoInfo.getKeyText())+"\"/>\n");
			sb.append("<input type=\"hidden\" id=\"menu_name_v\" value=\""+columnToStringL(tableDaoInfo.getKeyText())+"\"/>\n");
			sb.append("<input type=\"text\" id=\"id_key\" value=\"\"/>\n");
			sb.append("<input type=\"text\" id=\"id_name\" value=\"\"/>\n");
			sb.append("</body>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function params_str(queryParams){\n");
			//0:隐藏域    1:文本框    2:下拉列表   3:单选按钮  4:复选框   5:文本域   6:日期文本框  7:文件上传  8:系统时间  9:金额文本框 10:浮点数文本框         
			for(int i=0;i<queryList.length;i++){
				a = Integer.valueOf(queryList[i]);
				if("2".equals(PAGE_FORM[a]) || "3".equals(PAGE_FORM[a]) || "4".equals(PAGE_FORM[a])){
					sb.append("	queryParams."+columnToStringL(colmunCode[a])+"= $(\"#"+columnToStringL(colmunCode[a])+"\").combobox(\"getValue\");\n");
				}else if("6".equals(PAGE_FORM[a])){
					sb.append("	queryParams."+columnToStringL(colmunCode[a])+"= $(\"#"+columnToStringL(colmunCode[a])+"\").datetimebox(\"getValue\");\n");
				}else{
					sb.append("	queryParams."+columnToStringL(colmunCode[a])+"= $(\"#"+columnToStringL(colmunCode[a])+"\").textbox(\"getValue\");\n");
				}
			}
			sb.append("}\n");
			sb.append("function ddl_other(item){//扩展\n");
			sb.append("	//TODO\n");
			sb.append("}\n");
			sb.append("document.oncontextmenu=function(){return false;}\n");
			sb.append("</script>\n");
			sb.append("<script type=\"text/javascript\" src=\"js/inits.js\"></script>\n");
			sb.append("</html>\n");
			System.out.println(sb.toString());
			new FileTools().fileCreate(childProjectInfoSix.getProjectPath()+"//src//main//webapp//WEB-INF//page//"+fileNameVo.toLowerCase()+"//","inits.ftl",sb.toString());
			
			
			//##############################  add  
			sb.setLength(0);
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
			sb.append("<head>\n");
			sb.append("	<base href='${Request[\"basePath\"] ! \"\"}' />\n");
			sb.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
			sb.append("	<title>"+tableDaoInfo.getTableChan()+" 添加</title>\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/metro/easyui.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/icon.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui-lang-zh_CN.js\"></script>\n");
			sb.append("</head>\n");
			sb.append("<body>\n");
			sb.append("<div class=\"div_content_heah\">添加数据</div>\n");
			sb.append("	<form id=\"ff\" class=\"easyui-form\" action='"+fileNameVo.toLowerCase()+"/save.dhtml' method=\"POST\" data-options=\"novalidate:true\">\n");
			sb.append("	<table class=\"hovertable\" style=\"background-color:rgb(226, 229, 237)\" width=\"100%\">\n");
			
			String type_c = "!''";
			for(int h=0;h<colmunCode.length;h++){
				if(colmunCode[h].equals(tableDaoInfo.getKeyText())){
					if("number9".equals(colmunType[h])){
						type_c = "?c";
					}
				}
			}
			String[] checkStr = tableDaoInfo.getValidateName().split("\\|");
			//0:隐藏域    1:文本框    2:下拉列表   3:单选按钮  4:复选框   5:文本域   6:日期文本框  7:文件上传  8:系统时间  9:金额文本框 10:浮点数文本框   
			String test = "";
			for(int i=0;i<colmunCode.length;i++){
				if("0".equals(PAGE_FORM[i]) || "8".equals(PAGE_FORM[i])) {
					continue;
				}
				sb.append("		<tr>\n");
				sb.append("			<td width='100'>"+colmunComment[i]+"：</td>\n");
				sb.append("			<td>\n");
				
				
				if(!"TEST".equals(checkStr[i])){
					test = checkStr[i];
				}else{
					test = "";
				}
				
				if("1".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"\" class=\"easyui-textbox\"  data-options=\""+test+"\">\n");	
				}else if("2".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\" id=\""+columnToStringL(colmunCode[i])+"\"  name=\""+columnToStringL(colmunCode[i])+"\" class=\"easyui-combobox\" data-options=\"inputId:'"+columnToStringL(colmunCode[i])+"',"+test+"editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key="+colmunCode[i]+"'\" >\n");	
				}else if("3".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"radio\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" checked='checked'  value=\"\" class=\"easyui-radio\">说明&nbsp;\n");	
				}else if("4".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"checkbox\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" checked='checked'  value=\"\" class=\"easyui-radio\">说明&nbsp;\n");
				}else if("5".equals(PAGE_FORM[i])){
					sb.append("				<textarea class=\"textarea\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" style=\"height:100px;\"></textarea>\n");
				}else if("6".equals(PAGE_FORM[i])){
				sb.append("					<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"\" class=\"easyui-datetimebox\"  data-options=\""+test+",editable:false\">\n");	
				}else if("7".equals(PAGE_FORM[i])){
				sb.append("					<input type=\"file\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}else if("9".equals(PAGE_FORM[i])){
				sb.append("					<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}else if("10".equals(PAGE_FORM[i])){
				sb.append("					<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}
				sb.append("			</td>\n");
				sb.append("		</tr>\n");
			}
			sb.append("	<tr>\n");
			sb.append("		<td colspan=\"2\">\n");
			sb.append("			<span onclick=\"if($('#ff').form('enableValidation').form('validate')){ff.submit()}\" id=\"add_buttons\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-add'\">&nbsp;需&nbsp;要&nbsp;保&nbsp;存&nbsp;吗&nbsp;？&nbsp; </span>&nbsp;\n");
			sb.append("			<span onclick=\"javascript:parent.go_lists()\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-no'\">&nbsp;去&nbsp;列&nbsp;表&nbsp;页&nbsp;？&nbsp;</span>\n");
			sb.append("		</td>\n");
			sb.append("	</tr>\n");
			sb.append("</table>\n");
			sb.append("</form>\n");
			sb.append("</body>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})\n");
			sb.append("function colse_win(id){	\n");
			sb.append("	parent.colse_win(id);\n");
			sb.append("}\n");
			sb.append("</script>\n");
			sb.append("</html>\n");
			System.out.println(sb.toString());
			new FileTools().fileCreate(childProjectInfoSix.getProjectPath()+"//src//main//webapp//WEB-INF//page//"+fileNameVo.toLowerCase()+"//","add.ftl",sb.toString());
			
			
			
			//##############################  edit  
			sb.setLength(0);
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
			sb.append("<head>\n");
			sb.append("	<base href='${Request[\"basePath\"] ! \"\"}' />\n");
			sb.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
			sb.append("	<title>"+tableDaoInfo.getTableChan()+" 修改</title>\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/metro/easyui.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/icon.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui-lang-zh_CN.js\"></script>\n");
			sb.append("</head>\n");
			sb.append("<body>\n");
			sb.append("<div class=\"div_content_heah\">修改数据--${enitty."+columnToStringL(tableDaoInfo.getKeyText())+"!''}</div>\n");
			sb.append("<form id=\"ff\" class=\"easyui-form\" action='"+fileNameVo.toLowerCase()+"/edit.dhtml' method=\"POST\" data-options=\"novalidate:true\">\n");
			sb.append("	<input type=\"hidden\" id=\""+columnToStringL(tableDaoInfo.getKeyText())+"\" name=\""+columnToStringL(tableDaoInfo.getKeyText())+"\" value=\"${enitty."+columnToStringL(tableDaoInfo.getKeyText())+type_c+"}\">\n");
			sb.append("	<table class=\"hovertable\"  width=\"100%\">\n");
			List<String> listRadio = new ArrayList<String>();
			List<String> checkbox = new ArrayList<String>();
			for(int i=0;i<colmunCode.length;i++){
				if("0".equals(PAGE_FORM[i]) || "8".equals(PAGE_FORM[i])) {
					continue;
				}
				
				if(!"TEST".equals(checkStr[i])){
					test = checkStr[i];
				}else{
					test = "";
				}
				
				sb.append("		<tr>\n");
				sb.append("			<td  width='100'>"+colmunComment[i]+"：</td>\n");
				sb.append("			<td>\n");
				if("1".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\" class=\"easyui-textbox\"  data-options=\""+test+"\">\n");	
				}else if("2".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\" id=\""+columnToStringL(colmunCode[i])+"\"  value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\"  name=\""+columnToStringL(colmunCode[i])+"\" class=\"easyui-combobox\" data-options=\"inputId:'"+columnToStringL(colmunCode[i])+"',"+test+"editable:false,valueField:'id',textField:'value',url:'dictionary/selectv.dhtml?key="+colmunCode[i]+"'\" >\n");	
				}else if("3".equals(PAGE_FORM[i])){
					listRadio.add(columnToStringL(colmunCode[i]));
					sb.append("			<input type=\"radio\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" checked='checked'  value=\"\" class=\"easyui-radio\">说明&nbsp;\n");	
				}else if("4".equals(PAGE_FORM[i])){
					checkbox.add(columnToStringL(colmunCode[i]));
					sb.append("			<input type=\"checkbox\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" checked='checked'  value=\"\" class=\"easyui-radio\">说明&nbsp;\n");
				}else if("5".equals(PAGE_FORM[i])){
					sb.append("			<textarea class=\"textarea\" name=\""+columnToStringL(colmunCode[i])+"\" id=\""+columnToStringL(colmunCode[i])+"\" style=\"height:100px;\">${"+columnToStringL(colmunCode[i])+"!''}</textarea>\n");
				}else if("6".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\" class=\"easyui-datetimebox\"  data-options=\""+test+",editable:false\">\n");	
				}else if("7".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"file\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}else if("9".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}else if("10".equals(PAGE_FORM[i])){
					sb.append("				<input type=\"text\"  id=\""+columnToStringL(colmunCode[i])+"\" name=\""+columnToStringL(colmunCode[i])+"\" value=\"${enitty."+columnToStringL(colmunCode[i])+"!''}\" class=\"easyui-numberbox\"  data-options=\""+test+"\">\n");	
				}
				sb.append("			</td>\n");
				sb.append("		</tr>\n");
			}
			sb.append("	<tr>\n");
			sb.append("		<td colspan=\"2\"><span onclick=\"if($('#ff').form('enableValidation').form('validate')){ff.submit()}\" id=\"edit_buttons\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-edit'\">&nbsp;真&nbsp;要&nbsp;改&nbsp;？&nbsp;&nbsp;</span>\n");
			sb.append("		<span onclick=\"colse_win('xgsj_${enitty."+columnToStringL(tableDaoInfo.getKeyText())+type_c+"}')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-no'\">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span>\n");
			sb.append("		&nbsp;&nbsp;<font color=\"red\">${messages!\"\"}</font>\n");
			sb.append("		</td>\n");
			sb.append("	</tr>\n");
			sb.append("</table>\n");
			sb.append("</body>\n");
			sb.append("<script type=\"text/javascript\">\n");
			for(int i=0;i<listRadio.size();i++){
				sb.append("	$(\"input[name='"+listRadio.get(i)+"'][value='${enitty."+listRadio.get(i)+"}']\").click();\n");
			}
			sb.append("$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})\n");
			sb.append("function colse_win(id){	\n");
			sb.append("	parent.colse_win(id);\n");
			sb.append("}\n");
			sb.append("</script>\n");
			sb.append("</html>\n");
			System.out.println(sb.toString());
			new FileTools().fileCreate(childProjectInfoSix.getProjectPath()+"//src//main//webapp//WEB-INF//page//"+fileNameVo.toLowerCase()+"//","edit.ftl",sb.toString());
			
			
			//##############################  view  
			sb.setLength(0);
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
			sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
			sb.append("<head>\n");
			sb.append("	<base href='${Request[\"basePath\"] ! \"\"}' />\n");
			sb.append("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
			sb.append("	<title>"+tableDaoInfo.getTableChan()+" 浏览</title>\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/metro/easyui.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/themes/icon.css\">\n");
			sb.append("	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/basic.css\">\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui.min.js\"></script>\n");
			sb.append("	<script type=\"text/javascript\" src=\"js/jquery.easyui-lang-zh_CN.js\"></script>\n");
			sb.append("</head>\n");
			sb.append("<body>\n");
			sb.append("<div class=\"div_content_heah\">浏览数据--${enitty."+columnToStringL(tableDaoInfo.getKeyText())+"!''}</div>\n");
			sb.append("	<table class=\"hovertable\"  width=\"100%\">\n");
			for(int i=0;i<colmunCode.length;i++){
				sb.append("		<tr>\n");
				sb.append("			<td  width='100'>"+colmunComment[i]+"：</td>\n");
				sb.append("			<td>${enitty."+columnToStringL(colmunCode[i])+"!''}</td>\n");
				sb.append("		</tr>\n");
			}
			sb.append("	<tr>\n");
			sb.append("		<td colspan=\"2\"><span onclick=\"colse_win('llsj_${enitty."+columnToStringL(tableDaoInfo.getKeyText())+type_c+"}')\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-no'\">&nbsp;还&nbsp;是&nbsp;关&nbsp;了&nbsp;吧&nbsp;？&nbsp;</span></td>\n");
			sb.append("	</tr>\n");
			sb.append("</table>\n");
			sb.append("</body>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("$(function(){$('.validatebox-text').bind('blur', function(){$(this).validatebox('enableValidation').validatebox('validate');});})\n");
			sb.append("function colse_win(id){	\n");
			sb.append("	parent.colse_win(id);\n");
			sb.append("}\n");
			sb.append("</script>\n");
			sb.append("</html>\n");
			System.out.println(sb.toString());
			new FileTools().fileCreate(childProjectInfoSix.getProjectPath()+"//src//main//webapp//WEB-INF//page//"+fileNameVo.toLowerCase()+"//","view.ftl",sb.toString());
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("创建子项目异常：",e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
