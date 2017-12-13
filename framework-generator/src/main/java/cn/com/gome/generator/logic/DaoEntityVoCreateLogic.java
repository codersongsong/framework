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

import java.util.Date;
import java.util.Map;

import cn.com.gome.generator.util.FileTools;
import cn.com.gome.generator.util.MysqlCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;

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
public class DaoEntityVoCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {
	
	private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");
	
	@Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
		try{
			TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");
			String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");
			
			String tableCode = tableDaoInfo.getTableEng();
			
			String tableName = tableDaoInfo.getTableChan();
			
			String className = columnToStringU(tableCode);
			
			StringBuffer sb = new StringBuffer();
			sb.append("/**\n");   
			sb.append("* @Title: "+className+".java\n"); 
			sb.append("* @Package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity\n"); 
			sb.append("* @Description: "+tableName+"实体类\n"); 
			sb.append("* @author GOME\n");   
			sb.append("* @date "+new Date().toString()+"\n"); 
			sb.append("* @company "+projectBasicInfo.getPackages()+"\n");
			sb.append("* @version V1.0\n");   
			sb.append("*/\n"); 
			sb.append("\n");
			sb.append("package "+projectBasicInfo.getPackages()+"." + projectBasicInfo.getProjectEng() + ".dao.entity;\n");
			sb.append("\n");
			sb.append("import java.math.BigDecimal;\n");
			sb.append("import java.util.Date;\n");
			sb.append("import com.gomeplus.jdbc.page.Entitys;\n");
			sb.append("\n");
			sb.append("/** \n");
			sb.append(" * @ClassName: "+className+"\n");
			sb.append(" * @Description: "+tableName+"实体类\n"); 
			sb.append(" * @author GOME\n"); 
			sb.append(" * @date "+new Date().toString()+"\n");  
			sb.append(" * <br>tableName = "+tableCode+"\n");
			sb.append(" */\n");
			sb.append("public class "+className+" extends Entitys {\n");
			sb.append("\n");
			sb.append("	private static final long serialVersionUID = 1L;");
			sb.append("\n\n");
			String[] propertyName = tableDaoInfo.getColumnsName().split(",");
			String[] colmunName = tableDaoInfo.getViewColumns().split(",");
			String[] colmunType = tableDaoInfo.getColumnType().split(",");
			
			for(int i=0;i<propertyName.length;i++){
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	private "+ MysqlCache.getJavaType(colmunType[i],projectBasicInfo.getDatabaseType())+" "+columnToStringL(colmunName[i])+";\n");
				sb.append("\n");
			}
			
			for(int i=0;i<propertyName.length;i++){
				String a = MysqlCache.getJavaType(colmunType[i],projectBasicInfo.getDatabaseType());
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	public "+a+" get"+(columnToStringU(colmunName[i]))+"() {\n");
				sb.append("		return "+columnToStringL(colmunName[i])+";\n");
				sb.append("	}\n");
				sb.append("\n");
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	public void set"+(columnToStringU(colmunName[i]))+"("+a+" "+columnToStringL(colmunName[i])+") {\n");
				sb.append("		this."+columnToStringL(colmunName[i])+" = "+columnToStringL(colmunName[i])+";\n");
				sb.append("	}\n\n");
			}
			sb.append("}\n");
			
			System.out.println(sb.toString());
			new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//dao//entity//",className+".java",sb.toString());
			
			String fileName = columnToStringU(tableCode);
			fileName = fileName.substring(3, fileName.length());
			sb = new StringBuffer();
			sb.append("/**\n");   
			sb.append("* @Title: "+fileName+"Vo.java\n"); 
			sb.append("* @Package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".vo\n"); 
			sb.append("* @Description: "+tableName+"实体类\n"); 
			sb.append("* @author GOME\n");   
			sb.append("* @date "+new Date().toString()+"\n"); 
			sb.append("* @company cn.com.gome\n");
			sb.append("* @version V1.0\n");   
			sb.append("*/\n"); 
			sb.append("\n");
			sb.append("package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".vo;\n");
			sb.append("\n");
			sb.append("import com.gomeplus.jdbc.page.Entitys;\n");
			sb.append("import java.math.BigDecimal;\n");
			if(tableDaoInfo.getColumnType().indexOf("decimal") >=0){
				sb.append("import java.math.BigDecimal;\n");
				sb.append("import java.text.SimpleDateFormat;\n");
			}
			if(tableDaoInfo.getColumnType().indexOf("datetime") >=0){
				sb.append("import java.util.Date;\n");
			}
			if(tableDaoInfo.getColumnType().indexOf("char3") >=0){
				sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n");
			}
			sb.append("import com.gomeplus.frame.utils.MoneyFormatUtils;\n");
			sb.append("\n");
			sb.append("/** \n");
			sb.append(" * @ClassName: "+fileName+"Vo\n");
			sb.append(" * @Description: "+tableName+"实体类\n"); 
			sb.append(" * @author GOME\n"); 
			sb.append(" * @date "+new Date().toString()+"\n");  
			sb.append(" * <br>tableName = "+tableCode+"\n");
			sb.append(" */\n");
			sb.append("@SuppressWarnings(\"unused\")\n");
			sb.append("public class "+fileName+"Vo extends Entitys {\n");
			sb.append("\n");
			sb.append("	private static final long serialVersionUID = 1L;");
			sb.append("\n\n");
			
			for(int i=0;i<colmunName.length;i++){
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	private "+MysqlCache.getJavaType(colmunType[i],projectBasicInfo.getDatabaseType())+" "+ columnToStringL(colmunName[i]) +";\n");
				sb.append("\n");
				
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	private String "+ columnToStringL(colmunName[i]) +"_colmun;\n");
				sb.append("\n");
			}
			for(int i=0;i<colmunName.length;i++){
				String a = MysqlCache.getJavaType(colmunType[i],projectBasicInfo.getDatabaseType());
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	public "+a+" get"+( columnToStringU(colmunName[i]) )+"() {\n");
				sb.append("		return "+ columnToStringL(colmunName[i]) +";\n");
				sb.append("	}\n");
				sb.append("\n");
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	public String get"+( columnToStringU(colmunName[i]) )+"_colmun() {\n");
				if("BigDecimal".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = MoneyFormatUtils.format("+ columnToStringL(colmunName[i]) +", \"#,##0.00#\", \"f2y\");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("Integer".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = String.valueOf("+ columnToStringL(colmunName[i]) +");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("Double".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = String.valueOf("+ columnToStringL(colmunName[i]) +");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("Float".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = String.valueOf("+ columnToStringL(colmunName[i]) +");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("Long".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = String.valueOf("+ columnToStringL(colmunName[i]) +");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("Date".equals(a)){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp = new SimpleDateFormat(\"yyyyMMddHHmmss\").format("+ columnToStringL(colmunName[i]) +");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else if("String".equals(a) && "char3".equals(colmunType[i])){
					sb.append("		String temp = \"\";\n");
					sb.append("		if(null != "+ columnToStringL(colmunName[i]) +"){\n");
					sb.append("			temp =  GlobalApplicationCache.getInstance().getStr(\""+colmunName[i]+".\""+"+"+ columnToStringL(colmunName[i])+");\n");
					sb.append("		}\n");
					sb.append("		return temp;\n");
				}else{
					sb.append("		return "+ columnToStringL(colmunName[i]) +";\n");
				}
				sb.append("	}\n");
				sb.append("\n");
				sb.append("	/**"+propertyName[i]+":"+colmunName[i]+"*/\n");
				sb.append("	public void set"+( columnToStringU(colmunName[i]) )+"("+a+" "+ columnToStringL(colmunName[i]) +") {\n");
				sb.append("		this."+ columnToStringL(colmunName[i]) +" = "+ columnToStringL(colmunName[i]) +";\n");
				sb.append("	}\n\n");
			}
			sb.append("}\n");
			
			System.out.println(sb.toString());
			new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//vo//",fileName+"Vo.java",sb.toString());
			
			
			return ResultEnum.OK;
			
		}catch(Exception e){
			logger.error("实体层和展现层代码生成异常：",e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
