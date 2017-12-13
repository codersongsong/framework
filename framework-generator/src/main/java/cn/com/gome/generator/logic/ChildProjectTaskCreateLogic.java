/**   
* @Title: MainProjectCreateLogic.java 
* @Package cn.com.gome.generator.logic 
* @Description: 主项目创建
* @author GOME
* @date 2017年5月26日 下午2:49:05 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.generator.util.FileTools;
import com.google.common.collect.Lists;
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
public class ChildProjectTaskCreateLogic extends ChildProjectCreateAbstractLogic {

	@Override
	@PostConstruct
	protected void initConfig() {
		this.isCreateSpringXml = true;
		this.isCreateSpringMvcInterceptor = false;
		this.isCreateWebXml = true;
		this.isCreateXmlConfigFiles = true;
		this.isCreateDemoDubboConsumerXml = true;
	}

	@Override
	protected List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) {
		List<String> folders = Lists.newArrayListWithExpectedSize(9);
		folders.add(projectPath + "//src//main//resources//spring/consumer");
		folders.add(projectPath + "//src//main//resources//props//dev");
		folders.add(projectPath + "//src//main//resources//props//uat");
		folders.add(projectPath + "//src//main//resources//props//pre");
		folders.add(projectPath + "//src//main//resources//props//live");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//task");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//logic//impl");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//step//impl");
		folders.add(projectPath + "//src//main//webapp//WEB-INF");
		folders.add(projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//test");
		return folders;
	}

	@Override
	protected void createPropertieFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, List<TblPomProfileInfo> propertieConfigs) throws IOException {
		super.createPropertieFiles(projectPath, projectBasicInfo, childProjectInfo, propertieConfigs);
		//创建quartz-client.properties
		String basicPath = projectPath + "//src//main//resources//props//";
		String devFile = basicPath + "//dev//quartz-client.properties";
		String uatFile = basicPath + "//uat//quartz-client.properties";
		String preFile = basicPath + "//pre//quartz-client.properties";
		String liveFile = basicPath + "//live//quartz-client.properties";
		String file = projectPath + "//src//main//resources//quartz-client.properties";

		StringBuilder sb = new StringBuilder();
		sb.append("remoteAddress=10.112.179.41:10440\n");
		sb.append("businessCode=xxx\n");
		sb.append("taskStatus=1\n");
		sb.append("taskUpdateTimeLong=45\n");
		sb.append("readerIdleTimeSeconds=15\n");
		sb.append("writerIdleTimeSeconds=20\n");
		sb.append("allIdleTimeSeconds=25\n");
		sb.append("\n");
		FileTools.writeToFile(devFile, sb.toString());
		FileTools.writeToFile(uatFile, sb.toString());
		FileTools.writeToFile(preFile, sb.toString());
		FileTools.writeToFile(liveFile, sb.toString());

		sb = new StringBuilder();
		sb.append("taskInfoLoadType=remote\n");
		sb.append("remoteAddress=${remoteAddress}\n");
		sb.append("taskUpdateColumn=upTime\n");
		sb.append("businessCode=${businessCode}\n");
		sb.append("taskStatus=${taskStatus}");
		sb.append("taskUpdateTimeLong=${taskUpdateTimeLong}\n");
		sb.append("readerIdleTimeSeconds=${readerIdleTimeSeconds}\n");
		sb.append("writerIdleTimeSeconds=${writerIdleTimeSeconds}\n");
		sb.append("allIdleTimeSeconds=${allIdleTimeSeconds}\n");
		sb.append("\n");
		FileTools.writeToFile(file, sb.toString());
	}

	@Override
	protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
		super.createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
	}

	@Override
	protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo) throws IOException {
		String filePath1 = projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//package-info.java";
		StringBuilder sb = new StringBuilder("package ")
				.append(pachagesPath.replaceAll("//","."))
				.append(".")
				.append(projectBasicInfo.getProjectEng())
				.append(".")
				.append(childProjectInfo.getChildProjectEng())
				.append(";");
		FileTools.writeToFile(filePath1, sb.toString());
	}

	@Override
	protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {

	}
}
