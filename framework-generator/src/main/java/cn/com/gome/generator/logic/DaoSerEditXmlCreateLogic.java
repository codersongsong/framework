/**   
* @Title: DaoEntityVoCreateLogic.java 
* @Package cn.com.gome.generator.logic 
* @Description: 持久层XML代码生成 
* @author GOME
* @date 2017年6月1日 下午2:16:49 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.logic;

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
 * @Description: 持久层XML代码生成 
 * @author GOME
 * @date 2017年6月1日 下午2:16:49  
 */
@Service
public class DaoSerEditXmlCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {
	
	private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");
	
	@Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
		try{
			TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");
			
			String fileName = columnToStringU(tableDaoInfo.getTableEng());
			String fileNameVo = fileName.substring(3, fileName.length());
			
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n"); 
			sb.append("	<mapper namespace=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.edit."+fileName+"EditMapper\">\n"); 
			sb.append("	\n"); 
			sb.append("	<insert id=\"save\" parameterType=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+"\">\n");
			sb.append("		insert into "+tableDaoInfo.getTableEng()+"(\n		");
			
			String VIEW_COLUMNS = tableDaoInfo.getViewColumns();
			String[] colmunName = VIEW_COLUMNS.split(",");
			String[] colmunType = tableDaoInfo.getColumnType().split(",");
			
			for(int i=0 ; i < colmunName.length;i++){
				if(i == colmunName.length-1){
					sb.append(colmunName[i]+""); 
				}else{
					sb.append(colmunName[i]+","); 
				}
				
			}
			sb.append("\n		)\n"); 
			sb.append("		values(\n");
			for(int i=0 ; i < colmunName.length;i++){
				if(i == colmunName.length-1){
					if("TIMESTAMP".equals(MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType()))){
						sb.append("			sysdate\n");
					}else{
						sb.append("			#{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}\n"); 
					}
					
				}else{
					if("TIMESTAMP".equals(MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType()))){
						sb.append("			sysdate,\n");
					}else{
						sb.append("			#{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"},\n");
					}
				}
			}
			sb.append("		)\n"); 
			sb.append("	</insert>\n"); 
			sb.append("	\n"); 
			sb.append("	<update id=\"edit\" parameterType=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+"\" >\n"); 
			sb.append("		update "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<set>\n"); 
			int indexType = 0;
			for(int i=1 ; i < colmunName.length;i++){
				if(!colmunName[i].equals(tableDaoInfo.getKeyText())){
					sb.append("			 <if test=\""+columnToStringL(colmunName[i])+" != null\">\n"); 
					sb.append("		     	"+colmunName[i]+" = #{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"},\n"); 
					sb.append("		     </if>\n"); 
				}else{
					indexType = i;
				}
			}
			sb.append("		</set>\n"); 
			sb.append("		where "+tableDaoInfo.getKeyText()+"=#{"+columnToStringL(colmunName[indexType])+",jdbcType=" + MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType()) + "}\n"); 
			sb.append("	</update>\n"); 
			sb.append("\n"); 
			sb.append("	<update id=\"editNull\" parameterType=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+"\" >\n"); 
			sb.append("		update "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<set>\n"); 
			indexType = 0;
			for(int i=1 ; i < colmunName.length;i++){
				if(!colmunName[i].equals(tableDaoInfo.getKeyText())){
					sb.append("		     	"+colmunName[i]+" = #{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"},\n"); 
				}else{
					indexType = i;
				}
			}
			sb.append("		</set>\n"); 
			sb.append("		where "+tableDaoInfo.getKeyText()+"=#{"+columnToStringL(colmunName[indexType])+",jdbcType=" + MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType()) + "}\n"); 
			sb.append("	</update>\n"); 
			sb.append("\n");
			sb.append("	<update id=\"editInfo\" parameterType=\"java.util.Map\" >\n"); 
			sb.append("		update "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<set>\n");
			for(int i=1 ; i < colmunName.length;i++){
				if(!colmunName[i].equals(tableDaoInfo.getKeyText())){
					sb.append("			 <if test=\""+columnToStringL(colmunName[i])+"New != null\">\n"); 
					sb.append("		     	"+colmunName[i]+" = #{"+columnToStringL(colmunName[i])+"New,jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"},\n"); 
					sb.append("		     </if>\n");
				}
			}
			sb.append("		</set>\n"); 
			sb.append("		<where>\n");
			for(int i=0;i<colmunName.length;i++){
				sb.append("		<if test=\""+columnToStringL(colmunName[i])+" != null  and "+columnToStringL(colmunName[i])+" !='' \">\n"); 
				sb.append("			AND "+colmunName[i]+" = #{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}\n"); 
				sb.append("		</if>\n"); 
			}
			sb.append("		</where>\n");
			sb.append("	</update>\n"); 
			sb.append("\n");
			sb.append("	<delete id=\"del\" parameterType=\"java.util.List\">\n"); 
			sb.append("		delete from "+tableDaoInfo.getTableEng()+" where "+tableDaoInfo.getKeyText()+" in (\n"); 
			sb.append("			<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n"); 
			sb.append("				 #{item,jdbcType=" + MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType()) + "}\n"); 
			sb.append("			</foreach>\n"); 
			sb.append("		)\n"); 
			sb.append("	</delete>\n"); 
			sb.append("</mapper>\n"); 
			System.out.println(sb.toString());
			new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//resources//props//"+projectBasicInfo.getProjectEng()+"//mapper//edit//",fileName+"EditMapper.xml",sb.toString());
			
			
			sb.setLength(0);
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n"); 
			sb.append("<mapper namespace=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.mapper.ser."+fileName+"SerMapper\">\n"); 
			sb.append("	<resultMap id=\"BaseResultMap\"\n"); 
			sb.append("		type=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+"\">\n");
			
			for(int i=0;i<colmunName.length;i++){
				if(colmunName[i].equals(tableDaoInfo.getKeyText())){
				  sb.append("		<id column=\""+colmunName[i]+"\" property=\""+columnToStringL(colmunName[i])+"\" jdbcType=\""+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"\" />\n");
				}else{
				  sb.append("		<result column=\""+colmunName[i]+"\" property=\""+columnToStringL(colmunName[i])+"\" jdbcType=\""+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"\" />\n");
				}
			}
			sb.append("	</resultMap>\n");
			
			sb.append("	<resultMap id=\"BasePoResultMap\"\n"); 
			sb.append("		type=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".vo."+fileNameVo+"Vo\">\n"); 
			indexType = 0;
			for(int i=0;i<colmunName.length;i++){
				if(colmunName[i].equals(tableDaoInfo.getKeyText())){
					indexType = i;
				  sb.append("		<id column=\""+colmunName[i]+"\" property=\""+columnToStringL(colmunName[i])+"\" jdbcType=\""+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"\" />\n");
				}else{
				  sb.append("		<result column=\""+colmunName[i]+"\" property=\""+columnToStringL(colmunName[i])+"\" jdbcType=\""+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"\" />\n");
				}
			}
			sb.append("	</resultMap>\n"); 
			
			StringBuffer sbColumn = new StringBuffer();
			sb.append("	<sql id=\"Base_Column_List\">\n	"); 
			for(int i=0;i<colmunName.length;i++){
				if(i == colmunName.length -1){
					sb.append(colmunName[i]);
					sbColumn.append("AA."+colmunName[i]);
				}else{
					sb.append(colmunName[i]+",");
					sbColumn.append("AA."+colmunName[i]+",");
				}
			}
			sb.append("\n	</sql>\n"); 
			
			sb.append("	<select id=\"query\" resultMap=\"BaseResultMap\" parameterType=\"java.lang.String\">\n"); 
			sb.append("		select\n"); 
			sb.append("		<include refid=\"Base_Column_List\" />\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		where "+tableDaoInfo.getKeyText()+" = #{"+columnToStringL(tableDaoInfo.getKeyText())+",jdbcType="+MysqlCache.getXmlType(colmunType[indexType],projectBasicInfo.getDatabaseType())+"}\n"); 
			sb.append("	</select>\n");
			
			sb.append("	<sql id=\"comm_where\">"); 
			sb.append("		<where>");
			for(int i=0;i<colmunName.length;i++){
				sb.append("		<if test=\""+columnToStringL(colmunName[i])+" != null and "+columnToStringL(colmunName[i])+" !='' \">\n"); 
				sb.append("			AND "+colmunName[i]+" = #{"+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}\n"); 
				sb.append("		</if>\n"); 
			}
			sb.append("		</where>\n");
			sb.append("\n	</sql>\n"); 
			
			sb.append("	<select id=\"queryObj\" resultMap=\"BaseResultMap\" parameterType=\""+projectBasicInfo.getPackages()+"."+projectBasicInfo.getProjectEng()+".dao.entity."+fileName+"\">\n"); 
			sb.append("		select\n"); 
			sb.append("		<include refid=\"Base_Column_List\" />\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<include refid=\"comm_where\"></include>\n"); 
			sb.append("	</select>\n");
			
			sb.append("	<select id=\"queryVo\" resultMap=\"BasePoResultMap\">\n"); 
			sb.append("		select\n"); 
			sb.append("		<include refid=\"Base_Column_List\" />\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<include refid=\"comm_where\"></include>\n"); 
			sb.append("	</select>\n"); 
			
			sb.append("	<select id=\"queryList\" resultMap=\"BaseResultMap\">\n"); 
			sb.append("		select\n"); 
			sb.append("		<include refid=\"Base_Column_List\" />\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<include refid=\"comm_where\"></include>\n"); 
			sb.append("	</select>\n"); 
			
			sb.append("	<sql id=\"page_where\">"); 
			sb.append("		<where>"); 
			/*1:文本框;2:下拉列表;3:单选按钮;4:复选框;5:文本域;6:日期文本框;7:文件上传;8:隐藏域;*/
			for(int i=0;i<colmunName.length;i++){
				if(!"6".equals(colmunType[i])){
					sb.append("		<if test=\"f."+columnToStringL(colmunName[i])+" != null  and f."+columnToStringL(colmunName[i])+" !=''  \">\n"); 
					sb.append("			AND "+colmunName[i]+" = #{f."+columnToStringL(colmunName[i])+",jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}\n"); 
					sb.append("		</if>\n"); 
				}else{
					sb.append("		<if test=\"f."+columnToStringL(colmunName[i])+"s != null  and f."+columnToStringL(colmunName[i])+" !=''  \">\n"); 
					sb.append("			AND <![CDATA[ "+colmunName[i]+" >= #{f."+columnToStringL(colmunName[i])+"s,jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}]]>\n"); 
					sb.append("		</if>\n"); 
					sb.append("		<if test=\"f."+columnToStringL(colmunName[i])+"e != null  and f."+columnToStringL(colmunName[i])+" !=''  \">\n"); 
					sb.append("			AND <![CDATA[ "+colmunName[i]+" <= #{f."+columnToStringL(colmunName[i])+"e,jdbcType="+MysqlCache.getXmlType(colmunType[i],projectBasicInfo.getDatabaseType())+"}]]>\n"); 
					sb.append("		</if>\n"); 
				}
			}
			sb.append("		</where>\n");
			sb.append("	</sql>\n");
			
			sb.append("	<select id=\"queryPageListCount\" resultType=\"java.lang.Integer\">\n"); 
			sb.append("		select count(1) from "+tableDaoInfo.getTableEng()+"\n"); 
			sb.append("		<include refid=\"page_where\"></include>\n"); 
			sb.append("	</select>\n"); 
			
			sb.append("	<select id=\"queryPageList\" resultMap=\"BaseResultMap\">\n"); 
			sb.append("        <![CDATA[ select * from ( select interTable.*,rownum rn from ( ]]>\n"); 
			sb.append("		select\n"); 
			sb.append("		"+sbColumn+"\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+" AA \n"); 
			sb.append("		<include refid=\"page_where\"></include>\n"); 
			sb.append("			) interTable where \n"); 
			sb.append("	    <![CDATA[ rownum <= #{limit}+#{offset} ]]>\n"); 
			sb.append("			) outerTable where \n"); 
			sb.append("	    <![CDATA[ outerTable.rn > #{limit} ]]>\n"); 
			sb.append("	</select>\n");
			
			sb.append("	<select id=\"queryPageVoList\" resultMap=\"BasePoResultMap\">\n"); 
			sb.append("        <![CDATA[ select * from ( select interTable.*,rownum rn from ( ]]>\n"); 
			sb.append("		select\n"); 
			sb.append("		"+sbColumn+"\n"); 
			sb.append("		from "+tableDaoInfo.getTableEng()+" AA \n"); 
			sb.append("		<include refid=\"page_where\"></include>\n"); 
			sb.append("			) interTable where \n"); 
			sb.append("	    <![CDATA[ rownum <= #{limit}+#{offset} ]]>\n"); 
			sb.append("			) outerTable where \n"); 
			sb.append("	    <![CDATA[ outerTable.rn > #{limit} ]]>\n"); 
			sb.append("	</select>\n"); 
			
			sb.append("</mapper>\n"); 
			
			System.out.println(sb.toString());
			new FileTools().fileCreate(projectBasicInfo.getProjectPath()+"//"+projectBasicInfo.getProjectEng()+"-"+childProjectInfo.getChildProjectEng()+"//src//main//resources//props//"+projectBasicInfo.getProjectEng()+"//mapper//ser//",fileName+"SerMapper.xml",sb.toString());
			
			return ResultEnum.OK;
		}catch(Exception e){
			logger.error("持久层XML代码生成 异常：",e);
		}
		return ResultEnum.PART_CASE_01;
	}

}
