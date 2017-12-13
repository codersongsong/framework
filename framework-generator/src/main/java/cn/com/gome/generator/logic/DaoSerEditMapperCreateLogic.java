/**   
* @Title: DaoEntityVoCreateLogic.java 
* @Package cn.com.gome.generator.logic 
* @Description: 持久层接口代码生成
* @author GOME
* @date 2017年6月1日 下午2:16:49 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.logic;

import java.io.File;
import java.util.Date;
import java.util.Map;

import cn.com.gome.generator.util.FileTools;
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
 * @Description: 持久层接口代码生成
 * @author GOME
 * @date 2017年6月1日 下午2:16:49  
 */
@Service
public class DaoSerEditMapperCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {
	
	private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");
	
	@Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
		try{
			// 先判断是否已经创建 然后没有则创建  如果已经存在则不做修改
			TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");
			String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");
			
			String tableName = tableDaoInfo.getTableChan();
			String fileName = columnToStringU(tableDaoInfo.getTableEng());

			String serfilePath = childProjectInfo.getProjectPath()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//dao//mapper//ser//";
			String editFilepath = childProjectInfo.getProjectPath()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//dao//mapper//edit//";
			File f = new File(editFilepath+fileName+"EditMapper.java");
			StringBuilder sb = new StringBuilder();
			if(!f.exists()  && !f.isDirectory()){
				sb.append("/**\n");
				sb.append(" * @Title: "+fileName+"EditMapper.java\n");
				sb.append(" * @Package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.edit\n");
				sb.append(" * @Description: "+tableName+"的实体增、删、改接口\n");
				sb.append(" * @author GOME\n");
				sb.append(" * @date 2015年2月12日 下午3:47:57\n");
				sb.append(" * @company com.gomepulus\n");
				sb.append(" * @version V1.0\n");
				sb.append(" */\n");
				sb.append("\n");
				sb.append("\n");
				sb.append("package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.edit;\n");
				sb.append("\n");
				sb.append("import java.util.Map;\n");
				sb.append("import com.gomeplus.jdbc.mybatis.MybatisRepository;\n");
				sb.append("import com.gomeplus.jdbc.mapper.PersistenceLayerEditMapper;\n");
				sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+";\n");
				sb.append("\n");
				sb.append("/** \n");
				sb.append(" * @ClassName: "+fileName+"EditMapper\n");
				sb.append(" * @Description: "+tableName+"的实体增、删、改接口\n");
				sb.append(" * @author GOME\n");
				sb.append(" * @date 2015年2月12日 下午3:47:57\n");
				sb.append(" */\n");
				sb.append("@MybatisRepository\n");
				sb.append("public interface "+fileName+"EditMapper extends PersistenceLayerEditMapper<"+fileName+">{\n");
				sb.append("\n");
				sb.append("	/**\n");
				sb.append("	* @Title: edit\n");
				sb.append("	* @Description: 实体对象持久化修改-"+tableName+"\n");
				sb.append("	* @param  Map<String , String>\n");
				sb.append("	* @return int    返回类型\n");
				sb.append("	* @throws\n");
				sb.append("	 */\n");
				sb.append("	public int editInfo(Map<String , String> reqMap);\n");
				sb.append("\n");
				sb.append("}\n");
				// System.out.println(sb.toString());
				new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//dao//mapper//edit//",fileName+"EditMapper.java",sb.toString());
			}
			File f1 = new File(serfilePath+fileName+"SerMapper.java");
			if(!f1.exists()  && !f1.isDirectory()){
				sb.setLength(0);
				sb.append("/**\n");
				sb.append("* @Title: "+fileName+"SerMapper.java\n");
				sb.append("* @Package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.ser\n");
				sb.append("* @Description: "+tableName+"查询接口类\n");
				sb.append("* @author GOME\n");
				sb.append("* @date "+new Date().toString()+"\n");
				sb.append("* @company com.gomeplus\n");
				sb.append("* @version V1.0\n");
				sb.append("*/\n");
				sb.append("package "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.ser;\n");
				sb.append("\n");
				sb.append("import com.gomeplus.jdbc.mybatis.MybatisRepository;\n");
				sb.append("import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;\n");
				sb.append("import "+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+";\n");
				sb.append("/**\n");
				sb.append("* @ClassName: "+fileName+"SerMapper\n");
				sb.append("* @Description: "+tableName+"查询接口类\n");
				sb.append("* @author GOME\n");
				sb.append("* @date 2015年2月10日 下午5:11:39\n");
				sb.append(" */\n");
				sb.append("@MybatisRepository\n");
				sb.append("public interface "+fileName+"SerMapper extends PersistenceLayerSerMapper<"+fileName+">{\n");
				sb.append("\n");
				sb.append("}\n");
				// System.out.println(sb.toString());
				new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//java//"+pachagesPath+"//"+projectBasicInfo.getProjectEng()+"//dao//mapper//ser//",fileName+"SerMapper.java",sb.toString());
			}
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("持久层接口代码生成异常：",e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
