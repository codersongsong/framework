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

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.generator.util.FileTools;
import cn.com.gome.generator.util.MysqlCache;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/** 
 * @ClassName: DaoEntityVoCreateLogic 
 * @Description: 持久层XML代码生成 
 * @author GOME
 * @date 2017年6月1日 下午2:16:49  
 */
@Service
public class DaoSerEditXmlCompCreateLogic extends AbstractLogic implements ILogics<Map<String , Object>> {

    private Logger logger = LoggerFactory.getLogger("DaoEntityVoCreateLogic");

    @Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
        try {
            TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
            TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
            TblTableDaoInfo tableDaoInfo = (TblTableDaoInfo) map.get("tblTableDaoInfo");

            String fileName = columnToStringU(tableDaoInfo.getTableEng());
            String fileNameVo = fileName.substring(3, fileName.length());
            // 先判断是否已经有原有文件，如果有则读取，只更改原没有的文件。如果没有则生成
            String baseXmlPath = childProjectInfo.getProjectPath()+"//src//main//resources//props//";
            /* Edit 文件的对比创建 */
            String xmlEditPath = baseXmlPath + projectBasicInfo.getProjectEng() + "//mapper//edit//"+fileName + "EditMapper.xml";
            String yourCreate ="";
            File file1 = new File(xmlEditPath);
            if (file1.exists()||file1.isDirectory()) {
                yourCreate = editPomXml(xmlEditPath);
            }
            // 处理 yourCreate 截取前面字符串
            if(!"".equals(yourCreate)){
                String sign = fileName + "EditMapper";
                int index = yourCreate.indexOf(fileName + "EditMapper");
                if(-1 != index){
                    yourCreate = yourCreate.substring(index+sign.length()+3);
                }else {
                    logger.error("对比创建创建异常：异常位置 DaoSerEditXmlCompCreateLogic（line ：73）");
                    throw new Exception("对比创建创建异常");
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
            sb.append("	<mapper namespace=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.mapper.edit." + fileName + "EditMapper\">\n");
            sb.append("	\n");
            sb.append("	<insert id=\"save\" parameterType=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.entity." + fileName + "\">\n");
            sb.append("		insert into " + tableDaoInfo.getTableEng() + "(\n		");

            String VIEW_COLUMNS = tableDaoInfo.getViewColumns();
            String[] colmunName = VIEW_COLUMNS.split(",");
            String[] colmunType = tableDaoInfo.getColumnType().split(",");

            for (int i = 0; i < colmunName.length; i++) {
                if (i == colmunName.length - 1) {
                    sb.append(colmunName[i] + "");
                } else {
                    sb.append(colmunName[i] + ",");
                }
            }
            sb.append("\n		)\n");
            sb.append("		values(\n");
            for (int i = 0; i < colmunName.length; i++) {
                if (i == colmunName.length - 1) {
                    if ("TIMESTAMP".equals(MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()))) {
                        sb.append("			sysdate\n");
                    } else {
                        sb.append("			#{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}\n");
                    }

                } else {
                    if ("TIMESTAMP".equals(MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()))) {
                        sb.append("			sysdate,\n");
                    } else {
                        sb.append("			#{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "},\n");
                    }
                }
            }
            sb.append("		)\n");
            sb.append("	</insert>\n");
            sb.append("	\n");
            sb.append("	<update id=\"edit\" parameterType=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.entity." + fileName + "\" >\n");
            sb.append("		update " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<set>\n");
            int indexType = 0;
            for (int i = 1; i < colmunName.length; i++) {
                if (!colmunName[i].equals(tableDaoInfo.getKeyText())) {
                    sb.append("			 <if test=\"" + columnToStringL(colmunName[i]) + " != null\">\n");
                    sb.append("		     	" + colmunName[i] + " = #{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "},\n");
                    sb.append("		     </if>\n");
                } else {
                    indexType = i;
                }
            }
            sb.append("		</set>\n");
            sb.append("		where " + tableDaoInfo.getKeyText() + "=#{" + columnToStringL(colmunName[indexType]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[indexType], projectBasicInfo.getDatabaseType()) + "}\n");
            sb.append("	</update>\n");
            sb.append("\n");
            sb.append("	<update id=\"editNull\" parameterType=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.entity." + fileName + "\" >\n");
            sb.append("		update " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<set>\n");
            indexType = 0;
            for (int i = 1; i < colmunName.length; i++) {
                if (!colmunName[i].equals(tableDaoInfo.getKeyText())) {
                    sb.append("		     	" + colmunName[i] + " = #{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "},\n");
                } else {
                    indexType = i;
                }
            }
            sb.append("		</set>\n");
            sb.append("		where " + tableDaoInfo.getKeyText() + "=#{" + columnToStringL(colmunName[indexType]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[indexType], projectBasicInfo.getDatabaseType()) + "}\n");
            sb.append("	</update>\n");
            sb.append("\n");
            sb.append("	<update id=\"editInfo\" parameterType=\"java.util.Map\" >\n");
            sb.append("		update " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<set>\n");
            for (int i = 1; i < colmunName.length; i++) {
                if (!colmunName[i].equals(tableDaoInfo.getKeyText())) {
                    sb.append("			 <if test=\"" + columnToStringL(colmunName[i]) + "New != null\">\n");
                    sb.append("		     	" + colmunName[i] + " = #{" + columnToStringL(colmunName[i]) + "New,jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "},\n");
                    sb.append("		     </if>\n");
                }
            }
            sb.append("		</set>\n");
            sb.append("		<where>\n");
            for (int i = 0; i < colmunName.length; i++) {
                sb.append("		<if test=\"" + columnToStringL(colmunName[i]) + " != null  and " + columnToStringL(colmunName[i]) + " !='' \">\n");
                sb.append("			AND " + colmunName[i] + " = #{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}\n");
                sb.append("		</if>\n");
            }
            sb.append("		</where>\n");
            sb.append("	</update>\n");
            sb.append("\n");
            sb.append("	<delete id=\"del\" parameterType=\"java.util.List\">\n");
            sb.append("		delete from " + tableDaoInfo.getTableEng() + " where " + tableDaoInfo.getKeyText() + " in (\n");
            sb.append("			<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">\n");
            sb.append("				 #{item,jdbcType=" + MysqlCache.getXmlType(colmunType[indexType], projectBasicInfo.getDatabaseType()) + "}\n");
            sb.append("			</foreach>\n");
            sb.append("		)\n");
            sb.append("	</delete>\n");
            if("".equals(yourCreate)){
                sb.append("</mapper>\n");
            }else{
                sb.append(yourCreate+"\n");
            }
            if (file1.exists()||file1.isDirectory()) {
                file1.delete();
            }
            FileTools.fileCreate(childProjectInfo.getProjectPath() + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//mapper//edit//",
                    fileName + "EditMapper.xml", sb.toString());


            /* ser 文件的对比创建 */
            String xmlSerPath = baseXmlPath + projectBasicInfo.getProjectEng() + "//mapper//ser//"+fileName + "SerMapper.xml";
            String yourCreateSer ="";
            File file2 = new File(xmlSerPath);
            if (file2.exists()||file2.isDirectory()) {
                yourCreateSer = serPomXml(xmlSerPath);
            }
            // 处理 yourCreateSer 截取前面字符串
            if(!"".equals(yourCreateSer)){
                String sign = fileName + "SerMapper";
                int index = yourCreateSer.indexOf(fileName + "SerMapper");
                if(-1 != index){
                    yourCreateSer = yourCreateSer.substring(index+sign.length()+3);
                }else {
                    logger.error("对比创建创建异常：异常位置 DaoSerEditXmlCompCreateLogic（line ：73）");
                    throw new Exception("对比创建创建异常");
                }
            }
            System.out.println(yourCreateSer);
            
            sb.setLength(0);
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
            sb.append("<mapper namespace=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.mapper.ser." + fileName + "SerMapper\">\n");
            sb.append("	<resultMap id=\"BaseResultMap\"\n");
            sb.append("		type=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.entity." + fileName + "\">\n");

            for (int i = 0; i < colmunName.length; i++) {
                if (colmunName[i].equals(tableDaoInfo.getKeyText())) {
                    sb.append("		<id column=\"" + colmunName[i] + "\" property=\"" + columnToStringL(colmunName[i]) + "\" jdbcType=\"" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "\" />\n");
                } else {
                    sb.append("		<result column=\"" + colmunName[i] + "\" property=\"" + columnToStringL(colmunName[i]) + "\" jdbcType=\"" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "\" />\n");
                }
            }
            sb.append("	</resultMap>\n");
            sb.append("	<resultMap id=\"BasePoResultMap\"\n");
            sb.append("		type=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".vo." + fileNameVo + "Vo\">\n");
            indexType = 0;
            for (int i = 0; i < colmunName.length; i++) {
                if (colmunName[i].equals(tableDaoInfo.getKeyText())) {
                    indexType = i;
                    sb.append("		<id column=\"" + colmunName[i] + "\" property=\"" + columnToStringL(colmunName[i]) + "\" jdbcType=\"" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "\" />\n");
                } else {
                    sb.append("		<result column=\"" + colmunName[i] + "\" property=\"" + columnToStringL(colmunName[i]) + "\" jdbcType=\"" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "\" />\n");
                }
            }
            sb.append("	</resultMap>\n");

            StringBuffer sbColumn = new StringBuffer();
            sb.append("	<sql id=\"Base_Column_List\">\n	");
            for (int i = 0; i < colmunName.length; i++) {
                if (i == colmunName.length - 1) {
                    sb.append(colmunName[i]);
                    sbColumn.append("AA." + colmunName[i]);
                } else {
                    sb.append(colmunName[i] + ",");
                    sbColumn.append("AA." + colmunName[i] + ",");
                }
            }
            sb.append("\n	</sql>\n");

            sb.append("	<select id=\"query\" resultMap=\"BaseResultMap\" parameterType=\"java.lang.String\">\n");
            sb.append("		select\n");
            sb.append("		<include refid=\"Base_Column_List\" />\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		where " + tableDaoInfo.getKeyText() + " = #{" + columnToStringL(tableDaoInfo.getKeyText()) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[indexType], projectBasicInfo.getDatabaseType()) + "}\n");
            sb.append("	</select>\n");

            sb.append("	<sql id=\"comm_where\">");
            sb.append("		<where>");
            for (int i = 0; i < colmunName.length; i++) {
                sb.append("		<if test=\"" + columnToStringL(colmunName[i]) + " != null and " + columnToStringL(colmunName[i]) + " !='' \">\n");
                sb.append("			AND " + colmunName[i] + " = #{" + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}\n");
                sb.append("		</if>\n");
            }
            sb.append("		</where>\n");
            sb.append("\n	</sql>\n");

            sb.append("	<select id=\"queryObj\" resultMap=\"BaseResultMap\" parameterType=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.entity." + fileName + "\">\n");
            sb.append("		select\n");
            sb.append("		<include refid=\"Base_Column_List\" />\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<include refid=\"comm_where\"></include>\n");
            sb.append("	</select>\n");

            sb.append("	<select id=\"queryVo\" resultMap=\"BasePoResultMap\">\n");
            sb.append("		select\n");
            sb.append("		<include refid=\"Base_Column_List\" />\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<include refid=\"comm_where\"></include>\n");
            sb.append("	</select>\n");

            sb.append("	<select id=\"queryList\" resultMap=\"BaseResultMap\">\n");
            sb.append("		select\n");
            sb.append("		<include refid=\"Base_Column_List\" />\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<include refid=\"comm_where\"></include>\n");
            sb.append("	</select>\n");

            sb.append("	<sql id=\"page_where\">");
            sb.append("		<where>");
			/*1:文本框;2:下拉列表;3:单选按钮;4:复选框;5:文本域;6:日期文本框;7:文件上传;8:隐藏域;*/
            for (int i = 0; i < colmunName.length; i++) {
                if (!"6".equals(colmunType[i])) {
                    sb.append("		<if test=\"f." + columnToStringL(colmunName[i]) + " != null  and f." + columnToStringL(colmunName[i]) + " !=''  \">\n");
                    sb.append("			AND " + colmunName[i] + " = #{f." + columnToStringL(colmunName[i]) + ",jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}\n");
                    sb.append("		</if>\n");
                } else {
                    sb.append("		<if test=\"f." + columnToStringL(colmunName[i]) + "s != null  and f." + columnToStringL(colmunName[i]) + " !=''  \">\n");
                    sb.append("			AND <![CDATA[ " + colmunName[i] + " >= #{f." + columnToStringL(colmunName[i]) + "s,jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}]]>\n");
                    sb.append("		</if>\n");
                    sb.append("		<if test=\"f." + columnToStringL(colmunName[i]) + "e != null  and f." + columnToStringL(colmunName[i]) + " !=''  \">\n");
                    sb.append("			AND <![CDATA[ " + colmunName[i] + " <= #{f." + columnToStringL(colmunName[i]) + "e,jdbcType=" + MysqlCache.getXmlType(colmunType[i], projectBasicInfo.getDatabaseType()) + "}]]>\n");
                    sb.append("		</if>\n");
                }
            }
            sb.append("		</where>\n");
            sb.append("	</sql>\n");

            sb.append("	<select id=\"queryPageListCount\" resultType=\"java.lang.Integer\">\n");
            sb.append("		select count(1) from " + tableDaoInfo.getTableEng() + "\n");
            sb.append("		<include refid=\"page_where\"></include>\n");
            sb.append("	</select>\n");

            sb.append("	<select id=\"queryPageList\" resultMap=\"BaseResultMap\">\n");
            sb.append("        <![CDATA[ select * from ( select interTable.*,rownum rn from ( ]]>\n");
            sb.append("		select\n");
            sb.append("		" + sbColumn + "\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + " AA \n");
            sb.append("		<include refid=\"page_where\"></include>\n");
            sb.append("			) interTable where \n");
            sb.append("	    <![CDATA[ rownum <= #{limit}+#{offset} ]]>\n");
            sb.append("			) outerTable where \n");
            sb.append("	    <![CDATA[ outerTable.rn > #{limit} ]]>\n");
            sb.append("	</select>\n");

            sb.append("	<select id=\"queryPageVoList\" resultMap=\"BasePoResultMap\">\n");
            sb.append("        <![CDATA[ select * from ( select interTable.*,rownum rn from ( ]]>\n");
            sb.append("		select\n");
            sb.append("		" + sbColumn + "\n");
            sb.append("		from " + tableDaoInfo.getTableEng() + " AA \n");
            sb.append("		<include refid=\"page_where\"></include>\n");
            sb.append("			) interTable where \n");
            sb.append("	    <![CDATA[ rownum <= #{limit}+#{offset} ]]>\n");
            sb.append("			) outerTable where \n");
            sb.append("	    <![CDATA[ outerTable.rn > #{limit} ]]>\n");
            sb.append("	</select>\n");
            if("".equals(yourCreateSer)){
                sb.append("</mapper>\n");
            }else{
                sb.append(yourCreateSer+"\n");
            }
            if (file2.exists()||file2.isDirectory()) {
                file2.delete();
            }
            // System.out.println(sb.toString());
            FileTools.fileCreate(childProjectInfo.getProjectPath() + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//mapper//ser//", fileName + "SerMapper.xml", sb.toString());

            return ResultEnum.OK;
        } catch (Exception e) {
            logger.error("持久层XML代码生成 异常：", e);
        }
        return ResultEnum.PART_CASE_01;
    }

    /**
     * 读取原有的 *Edit*.xml文件并解析
     * @param path
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public String editPomXml(String path) throws IOException {
        String stringFromDoc= "";
        // 文件创建工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(path); // 加载xml文件到当前项目
            // 获取update节点
            NodeList nodeList = document.getElementsByTagName("update");
            int length = nodeList.getLength();
            for(int i = 0 ; i < length; i++){
                Node node = nodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("editNull".equals(nodeValue)||"editInfo".equals(nodeValue)||"edit".equals(nodeValue)){
                    // 删除当前节点
                    node.getParentNode().removeChild(node);
                    length--;
                    i--;
                }
            }

            NodeList insertNodeList = document.getElementsByTagName("insert"); // 获取insert节点
            int insertLength = insertNodeList.getLength();
            for(int i = 0 ; i < insertLength; i++){
                Node node = insertNodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("save".equals(nodeValue)){
                    node.getParentNode().removeChild(node);// 删除当前节点
                    insertLength--;
                    i--;
                }
            }

            NodeList deleteNodeList = document.getElementsByTagName("delete"); // 获取delete节点
            int deleteLength = deleteNodeList.getLength();
            for(int i = 0 ; i < deleteLength; i++){
                Node node = deleteNodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("del".equals(nodeValue)){
                    node.getParentNode().removeChild(node);// 删除当前节点
                    deleteLength--;
                    i--;
                }
            }
            // dom 转为字符串
            stringFromDoc = toStringFromDoc(document);
        } catch (Exception e) {
            logger.debug("调用EditPomXml文件出错");
        }
        return stringFromDoc;
    }


    /**
     * 读取原有的 *ser*.xml文件并解析
     * @param path
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public String serPomXml(String path) throws IOException {
        String stringFromDoc= "";
        // 文件创建工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(path); // 加载xml文件到当前项目

            // 需要删除的id 有 BaseResultMap  BasePoResultMap  Base_Column_List
            // query comm_where  queryObj queryVo queryList page_where  queryPageListCount
            // queryPageList queryPageVoList

            // 获取 select 节点
            NodeList selectList = document.getElementsByTagName("select");
            int selectLen= selectList.getLength();
            for(int i = 0 ; i < selectLen; i++){
                Node node = selectList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("query".equals(nodeValue)||"queryObj".equals(nodeValue)||"queryVo".equals(nodeValue)
                        ||"queryList".equals(nodeValue)||"queryPageListCount".equals(nodeValue)
                        ||"queryPageList".equals(nodeValue)||"queryPageVoList".equals(nodeValue)){
                    // 删除当前节点
                    node.getParentNode().removeChild(node);
                    selectLen--;
                    i--;
                }
            }


            // 获取 sql 节点
            NodeList sqlNodeList = document.getElementsByTagName("sql");
            int sqlLen= sqlNodeList.getLength();
            for(int i = 0 ; i < sqlLen; i++){
                Node node = sqlNodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("Base_Column_List".equals(nodeValue)||"comm_where".equals(nodeValue)||"page_where".equals(nodeValue)){
                    // 删除当前节点
                    node.getParentNode().removeChild(node);
                    sqlLen--;
                    i--;
                }
            }

            // 获取 resultMap 节点
            NodeList resultMapNodeList = document.getElementsByTagName("resultMap");
            int resultMapLen= resultMapNodeList.getLength();
            for(int i = 0 ; i < resultMapLen; i++){
                Node node = resultMapNodeList.item(i);
                NamedNodeMap attributes = node.getAttributes();
                Node attr = attributes.item(0); // 获取第一个属性 为ID属性
                String nodeValue = attr.getNodeValue(); // 获取属性值
                if("BaseResultMap".equals(nodeValue)||"BasePoResultMap".equals(nodeValue)){
                    // 删除当前节点
                    node.getParentNode().removeChild(node);
                    resultMapLen--;
                    i--;
                }
            }

            // dom 转为字符串
            stringFromDoc = toStringFromDoc(document);
        } catch (Exception e) {
            logger.debug("调用serPomXml文件出错");
        }
        return stringFromDoc;
    }

    @SuppressWarnings("rawtypes")
    public boolean compare(String sourcePath, String targetPath, String CHILD_PROJECT_ENG) {
        return false;
    }
    /*
     * 把dom文件转换为xml字符串
     */
    public String toStringFromDoc(Document document) {
        String result = null;

        if (document != null) {
            StringWriter strWtr = new StringWriter();
            StreamResult strResult = new StreamResult(strWtr);
            TransformerFactory tfac = TransformerFactory.newInstance();
            try {
                Transformer t = tfac.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");// text
                t.transform(new DOMSource(document.getDocumentElement()),strResult);
            } catch (Exception e) {
                System.err.println("XML.toString(Document): " + e);
            }
            result = strResult.getWriter().toString();
            try {
                strWtr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
