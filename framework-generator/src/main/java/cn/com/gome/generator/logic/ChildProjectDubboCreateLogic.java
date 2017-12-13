package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.generator.util.FileTools;
import cn.com.gome.generator.util.PomUtils;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ResultEnum;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: MainProjectCreateLogic 
 * @Description: 主项目创建
 * @author GOME
 * @date 2017年5月26日 下午2:49:05  
 */
@Service
public class ChildProjectDubboCreateLogic extends ChildProjectCreateAbstractLogic {
	private Logger logger = LoggerFactory.getLogger(ChildProjectDubboCreateLogic.class);

	@Override
	@PostConstruct
	protected void initConfig() {
		this.isCreateSpringXml = false;
		this.isCreateSpringMvcInterceptor = false;
		this.isCreateWebXml = false;
		this.isCreateXmlConfigFiles = false;
		this.isCreateDemoDubboConsumerXml = false;
	}

	@Override
	public ResultEnum exec(Map<String , Object> map) throws LogicsException {
		try{
			TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
			TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
			String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");

			String projectPath = projectBasicInfo.getProjectPath().trim();
			if(!projectPath.endsWith("//")){
				projectPath +="//";
				projectBasicInfo.setProjectPath(projectPath);
			}
			if ("service".equals(childProjectInfo.getChildProjectEng())) {
				projectPath += projectBasicInfo.getProjectEng() + "-dubbo";
			} else {
				projectPath += projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "-dubbo";
			}


			//依赖配置文件
			Map<String, String> configFileMap = (Map<String, String>) map.get("configFiles");
			//1.创建应用必备文件夹目录
			createNecessaryFolder(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
			//3.创建xml配置文件
			createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
			//6.创建必要的java文件
			createDefaultJavaFiles(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
			//7.个性化模块创建
			selfModuleCreate(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);

			return ResultEnum.OK;
		} catch (Exception e) {
			logger.error("创建子项目异常：", e);
		}
		return ResultEnum.PART_CASE_01;
	}

	@Override
	protected List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) {
		List<String> folders = Lists.newArrayListWithExpectedSize(3);
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//dubbo//bean");
		folders.add(projectPath + "//src//main//resources//" + projectBasicInfo.getProjectEng());
		folders.add(projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//dubbo");
		return folders;
	}

	@Override
	protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
		String fileName = projectPath + "//src//main//resources//" + projectBasicInfo.getProjectEng() + "//" + projectBasicInfo.getProjectEng() + "-dubbo-consumer.xml";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" \n");
		sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
		sb.append("	xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\" \n");
		sb.append("	xsi:schemaLocation=\"http://www.springframework.org/schema/beans \n");
		sb.append("			http://www.springframework.org/schema/beans/spring-beans-2.5.xsd \n");
		sb.append("			http://code.alibabatech.com/schema/dubbo \n");
		sb.append("			http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\n");
		sb.append("  \n");
		sb.append("  <dubbo:consumer check=\"false\" /> \n");
		sb.append("  <dubbo:registry protocol=\"zookeeper\" address=\"${dubbo.run.registryAddress}\" id=\"" + projectBasicInfo.getProjectEng() + "DubboConsumer\" /> \n");
		sb.append("  \n");
		sb.append("  <!--dubbo:reference timeout=\"${dubbo.run.timeout}\" group=\"${dubbo.group}\" id=\"sysDicQueryFacedeService\" interface=\"com.gomeplus.framework.dubbo.SysDicQueryFacedeService\" registry=\"" + projectBasicInfo.getProjectEng() + "DubboConsumer\" retries=\"0\" check=\"false\" /-->\n");
		sb.append("  \n");
		sb.append("</beans>\n");
		FileTools.writeToFile(fileName, sb.toString());
	}

	@Override
	protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
		String tfn = "";
		if ("service".equals(childProjectInfo.getChildProjectEng())) {
			tfn = lowerToUpper(projectBasicInfo.getProjectEng());
		} else {
			tfn = lowerToUpper(projectBasicInfo.getProjectEng()) + lowerToUpper(childProjectInfo.getChildProjectEng());
		}
		String constantsFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//dubbo//bean//" + tfn + "Constants.java";
		StringBuilder sb = new StringBuilder();
		sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dubbo.bean;\n");
		sb.append("\n");
		sb.append(getClassDescription(tfn + "Constants", childProjectInfo.getChildProjectName() + "常量类"));
		sb.append("public class " + tfn + "Constants {\n");
		sb.append("	\n");
		sb.append("	/**返回结果码：0000成功；*/\n");
		sb.append("	public final static String RESULT_SUCCESS = \"0000\";\n");
		sb.append("	\n");
		sb.append("	/**返回结果码：0001，重复入库；*/\n");
		sb.append("	public final static String RESULT_REPEAT = \"0001\";\n");
		sb.append("	\n");
		sb.append("	/**返回结果码：0003，过期。*/\n");
		sb.append("	public final static String RESULT_EXPIRE = \"0003\"; \n");
		sb.append("	\n");
		sb.append("	/**返回结果码：0002，失败。*/ \n");
		sb.append("	public final static String RESULT_FAIL = \"0002\"; \n");
		sb.append("\n");
		sb.append("	/**返回结果码：5555,接口调用权限限制*/\n");
		sb.append("	public final static String CHANNEL_LIMIT = \"5555\"; \n");
		sb.append("	\n");
		sb.append("}\n");
		FileTools.writeToFile(constantsFilePath, sb.toString());
		String voFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//dubbo//bean//" + tfn + "ParametersVo.java";
		sb = new StringBuilder();
		sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dubbo.bean; \n");
		sb.append("\n");
		sb.append("import java.io.Serializable;\n");
		sb.append("import java.math.BigDecimal;\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.Map;\n");
		sb.append("\n");
		sb.append("import org.apache.commons.lang.StringUtils;\n");
		sb.append(getClassDescription(tfn + "ParametersVo", childProjectInfo.getChildProjectName() + " dubbo服务对象传输Object Value对象"));
		sb.append("public class " + tfn + "ParametersVo implements Serializable{\n");
		sb.append("\n");
		sb.append("	private static final long serialVersionUID = -8886267161275214875L;\n");
		sb.append("\n");
		sb.append("	/**业务来源：010，PC端；020，移动端*/\n");
		sb.append("	private String sources;\n");
		sb.append("	\n");
		sb.append("	/**业务版本号，默认:0.1*/\n");
		sb.append("	private String version = \"0.1\";\n");
		sb.append("	\n");
		sb.append("	/**业务类型，与服务层服务类方法同名*/\n");
		sb.append("	private String businessType;\n");
		sb.append("	\n");
		sb.append("	/**日志关联key*/\n");
		sb.append("	private String logAndKey;\n");
		sb.append("	\n");
		sb.append("	/**业务参数集合*/\n");
		sb.append("	private Map<String , Object> map = null;\n");
		sb.append("	\n");
		sb.append("	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/\n");
		sb.append("	private String resCode;\n");
		sb.append("	\n");
		sb.append("	/**错误信息，默认为“成功”*/\n");
		sb.append("	private String resDesc;\n");
		sb.append("	\n");
		sb.append("	public " + tfn + "ParametersVo(){\n");
		sb.append("		map = new HashMap<String , Object>();\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	public " + tfn + "ParametersVo(String sources){\n");
		sb.append("		this.sources = sources;\n");
		sb.append("		map = new HashMap<String , Object>();\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	public " + tfn + "ParametersVo(String sources , String businessType){\n");
		sb.append("		this.sources = sources;\n");
		sb.append("		this.businessType = businessType;\n");
		sb.append("		map = new HashMap<String , Object>();\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务来源：010，PC端；020，移动端*/\n");
		sb.append("	public String getSources() {\n");
		sb.append("		return sources;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务来源：01，PC端；02，移动端*/\n");
		sb.append("	public void setSources(String sources) {\n");
		sb.append("		this.sources = sources;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**日志关联key*/\n");
		sb.append("	public String getLogAndKey() {\n");
		sb.append("		return logAndKey;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**日志关联key*/\n");
		sb.append("	public void setLogAndKey(String logAndKey) {\n");
		sb.append("		this.logAndKey = logAndKey;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务版本号，默认:0.1*/\n");
		sb.append("	public String getVersion() {\n");
		sb.append("		return version;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务版本号，默认:0.1*/\n");
		sb.append("	public void setVersion(String version) {\n");
		sb.append("		this.version = version;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务类型，与服务层服务类方法同名*/\n");
		sb.append("	public String getBusinessType() {\n");
		sb.append("		return businessType;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**业务类型，与服务层服务类方法同名*/\n");
		sb.append("	public void setBusinessType(String businessType) {\n");
		sb.append("		this.businessType = businessType;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	/**业务参数*/\n");
		sb.append("	public void setInfo(String key , Object value){\n");
		sb.append("		map.put(key, value);\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	/**业务参数*/\n");
		sb.append("	public Object getInfo(String key){\n");
		sb.append("		return map.get(key);\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	/**业务参数*/\n");
		sb.append("	public String getString(String key){\n");
		sb.append("		Object obj = map.get(key);\n");
		sb.append("		String returnValue = \"\";\n");
		sb.append("		if(obj != null){\n");
		sb.append("			if(obj.getClass().getSimpleName().equals(\"BigDecimal\")){\n");
		sb.append("				returnValue =((BigDecimal) obj).toPlainString();\n");
		sb.append("			}else{\n");
		sb.append("				returnValue = String.valueOf(obj);\n");
		sb.append("			}\n");
		sb.append("		}\n");
		sb.append("		return returnValue;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	/**业务参数*/\n");
		sb.append("	public BigDecimal getBigDecimal(String key){\n");
		sb.append("		Object obj = map.get(key);\n");
		sb.append("		if(obj != null){\n");
		sb.append("			if(obj.getClass().getSimpleName().equals(\"BigDecimal\")){\n");
		sb.append("				return ((BigDecimal) obj);\n");
		sb.append("			}else{\n");
		sb.append("				return new BigDecimal(String.valueOf(obj));\n");
		sb.append("			}\n");
		sb.append("		}\n");
		sb.append("		return null;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	/**业务参数*/\n");
		sb.append("	public Integer getInt(String key){\n");
		sb.append("		Object obj = map.get(key);\n");
		sb.append("		if(obj != null){\n");
		sb.append("			if(obj.getClass().getSimpleName().equals(\"Integer\")){\n");
		sb.append("				return (Integer) obj;\n");
		sb.append("			}else{\n");
		sb.append("				return Integer.valueOf(String.valueOf(obj));\n");
		sb.append("			}\n");
		sb.append("		}\n");
		sb.append("		return null;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	@Override\n");
		sb.append("	public String toString() {\n");
		sb.append("		StringBuilder resultStr = new StringBuilder();\n");
		sb.append("		resultStr.append(\" businessType: \" + this.getBusinessType());\n");
		sb.append("		resultStr.append(\" sources: \" + this.getSources());\n");
		sb.append("		resultStr.append(\" version: \" + this.getVersion());\n");
		sb.append("		resultStr.append(\" logAndKey: \" + this.getLogAndKey());\n");
		sb.append("		if(!StringUtils.isEmpty(this.getResCode())){\n");
		sb.append("			resultStr.append(\" resCode: \" + this.getResCode());\n");
		sb.append("			resultStr.append(\" resDesc: \" + this.getResDesc());\n");
		sb.append("		}\n");
		sb.append("		resultStr.append(\" map: \" + this.map.toString());\n");
		sb.append("		return resultStr.toString();\n");
		sb.append("		\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	public void remove(String key){\n");
		sb.append("		map.remove(key);\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/\n");
		sb.append("	public String getResCode() {\n");
		sb.append("		return resCode;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/\n");
		sb.append("	public void setResCode(String resCode) {\n");
		sb.append("		this.resCode = resCode;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**错误信息，默认为“成功”*/\n");
		sb.append("	public String getResDesc() {\n");
		sb.append("		return resDesc;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	/**错误信息，默认为“成功”*/\n");
		sb.append("	public void setResDesc(String resDesc) {\n");
		sb.append("		this.resDesc = resDesc;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public Map<String, Object> getMap() {\n");
		sb.append("		return map;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void setMap(Map<String, Object> map) {\n");
		sb.append("		this.map = map;\n");
		sb.append("	}\n");
		sb.append("}\n");
		FileTools.writeToFile(voFilePath, sb.toString());
	}

	@Override
	protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
		String appName = "";
		if ("service".equals(childProjectInfo.getChildProjectEng())) {
			appName = "dubbo";
		} else {
			appName = childProjectInfo.getChildProjectEng() + "-dubbo";
		}
		createPomXml(projectPath, appName, projectBasicInfo);
		PomUtils.editPomXml(projectBasicInfo.getProjectPath(), projectBasicInfo.getProjectEng(), appName, projectBasicInfo.getPackages());
	}

	private void createPomXml(String projectPath, String appName, TblProjectBasicInfo projectBasicInfo) throws IOException {
		String pomXmlFilePath = projectPath + "//pom.xml";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		sb.append("	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
		sb.append("	<modelVersion>4.0.0</modelVersion>\n");
		sb.append("	<parent>\n");
		sb.append("		<groupId>" + projectBasicInfo.getPackages() + "</groupId>\n");
		sb.append("		<artifactId>" + projectBasicInfo.getProjectEng() + "</artifactId>\n");
		sb.append("		<version>0.0.1-SNAPSHOT</version>\n");
		sb.append("	</parent>\n");
		sb.append("	<groupId>" + projectBasicInfo.getPackages() + "</groupId>\n");
		sb.append("	<artifactId>" + projectBasicInfo.getProjectEng() + "-" + appName + "</artifactId>\n");
		sb.append("	<version>0.0.1-SNAPSHOT</version>\n");
		sb.append("	<packaging>jar</packaging>\n");
		sb.append("	<name>" + projectBasicInfo.getProjectEng() + "-dubbo</name>\n");
		sb.append("	<url>http://maven.apache.org</url>\n");

		sb.append("	<dependencies>\n");
		sb.append("	<dependency>\n");
		sb.append("		<groupId>commons-lang</groupId>\n");
		sb.append("		<artifactId>commons-lang</artifactId>\n");
		sb.append("		<version>2.6</version>\n");
		sb.append("	</dependency>\n");
		sb.append("	</dependencies>\n");
		sb.append("	<build>\n");
		sb.append("		<resources>\n");
		sb.append("			<resource>\n");
		sb.append("				<directory>src/main/resources</directory>\n");
		sb.append("				<filtering>true</filtering>\n");
		sb.append("			</resource>\n");
		sb.append("		</resources>\n");
		sb.append("	</build>\n");

		sb.append("<profiles>\n");
		sb.append("	<profile>\n");
		sb.append("		<id>dev</id>\n");
		sb.append("		<properties>\n");
		sb.append("			<env>dev</env>\n");
		sb.append("		</properties>\n");
		sb.append("	</profile>\n");
		sb.append("	<profile>\n");
		sb.append("		<id>uat</id>\n");
		sb.append("		<activation>\n");
		sb.append("			<activeByDefault>true</activeByDefault>\n");
		sb.append("		</activation>\n");
		sb.append("		<properties>\n");
		sb.append("			<env>uat</env>\n");
		sb.append("		</properties>\n");
		sb.append("	</profile>\n");
		sb.append("	<profile>\n");
		sb.append("		<id>pre</id>\n");
		sb.append("		<properties>\n");
		sb.append("			<env>pre</env>\n");
		sb.append("		</properties>\n");
		sb.append("	</profile>\n");
		sb.append("	<profile>\n");
		sb.append("		<id>live</id>\n");
		sb.append("		<properties>\n");
		sb.append("			<env>live</env>\n");
		sb.append("		</properties>\n");
		sb.append("	</profile>\n");
		sb.append("	<profile>\n");
		sb.append("		<id>drill</id>\n");
		sb.append("		<properties>\n");
		sb.append("			<env>live</env>\n");
		sb.append("		</properties>\n");
		sb.append("	</profile>\n");
		sb.append("</profiles>\n");

		sb.append("</project>");
		FileTools.writeToFile(pomXmlFilePath, sb.toString());
	}
}
