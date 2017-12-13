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

import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.generator.util.FileTools;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/** 
 * @ClassName: MainProjectCreateLogic 
 * @Description: 主项目创建
 * @author GOME
 * @date 2017年5月26日 下午2:49:05  
 */
@Service
public class MainProjectCreateLogic implements ILogics<TblProjectBasicInfo> {

	private Logger logger = LoggerFactory.getLogger("MainProjectCreateLogic");
	
	@Override
    public ResultEnum exec(TblProjectBasicInfo projectBasicInfo) throws LogicsException {
		try{
			String projectPath = projectBasicInfo.getProjectPath().trim();
			if(!projectPath.endsWith("//")){
				projectPath +="//";
			}
			
			File file = new File(projectBasicInfo.getProjectPath().trim());
			if(!file.exists()){
				file.mkdirs();
			}
			//创建主项目pom文件
			createMainProjectPomXml(projectPath, projectBasicInfo);

			return ResultEnum.OK;
		} catch (Exception e) {
			logger.error("创建主项目异常：projectEng=" + projectBasicInfo.getProjectEng(), e);
		}
		return ResultEnum.PART_CASE_01;
	}

	private void createMainProjectPomXml(String projectPath, TblProjectBasicInfo projectBasicInfo) throws IOException {
		String fileName = projectPath + "//pom.xml";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		sb.append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
		sb.append("	<modelVersion>4.0.0</modelVersion>\n\n");
		sb.append("	<groupId>" + projectBasicInfo.getPackages() + "</groupId>\n");
		sb.append("	<artifactId>" + projectBasicInfo.getProjectEng() + "</artifactId>\n");
		sb.append("	<version>0.0.1-SNAPSHOT</version>\n");
		sb.append("	<packaging>pom</packaging>\n\n");
		sb.append("	<name>" + projectBasicInfo.getProjectEng() + "</name>\n");
		sb.append("	<url>http://maven.apache.org</url>\n\n");
		sb.append("	<properties>\n");
		sb.append("		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n");
		sb.append("		<spring.version>3.2.9.RELEASE</spring.version>\n");
		sb.append("		<jr.commons.version>");
		sb.append(GlobalApplicationCache.getInstance().getStr("jr.commons.version"));
		sb.append("</jr.commons.version>\n");
		sb.append("		<jdk.version>1.7</jdk.version>\n");
		sb.append("	</properties>\n\n");
		sb.append("	<dependencies>\n");
		sb.append("		<dependency>\n");
		sb.append("			<groupId>junit</groupId>\n");
		sb.append("			<artifactId>junit</artifactId>\n");
		sb.append("			<version>4.12</version>\n");
		sb.append("		</dependency>\n");
		sb.append("	</dependencies>\n\n");
		sb.append("    <dependencyManagement>\n" +
				"        <dependencies>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-frame</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-log4j2</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-rpc</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-jdbc</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-quartz</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"            <dependency>\n" +
				"                <groupId>cn.com.gome</groupId>\n" +
				"                <artifactId>commons-security</artifactId>\n" +
				"                <version>${jr.commons.version}</version>\n" +
				"            </dependency>\n" +
				"        </dependencies>\n" +
				"    </dependencyManagement>\n");
		sb.append("	<distributionManagement>\n");
		sb.append("		<snapshotRepository>\n");
		sb.append("			<id>snapshots</id>\n");
		sb.append("			<url>http://maven.ds.gome.com.cn/nexus/content/repositories/snapshots</url>\n");
		sb.append("		</snapshotRepository>\n");
		sb.append("		<repository>\n");
		sb.append("			<id>releases</id>\n");
		sb.append("			<url>http://maven.ds.gome.com.cn/nexus/content/repositories/releases</url>\n");
		sb.append("		</repository>\n");
		sb.append("	</distributionManagement>\n\n");
		sb.append("	<build>\n");
		sb.append("		<plugins>\n");
		sb.append("			<plugin>\n");
		sb.append("				<artifactId>maven-source-plugin</artifactId>\n");
		sb.append("				<version>2.4</version>\n");
		sb.append("				<configuration>\n");
		sb.append("					<attach>true</attach>\n");
		sb.append("				</configuration>\n");
		sb.append("				<executions>\n");
		sb.append("					<execution>\n");
		sb.append("						<phase>compile</phase>\n");
		sb.append("						<goals>\n");
		sb.append("                            <goal>jar</goal>\n");
		sb.append("						</goals>\n");
		sb.append("					</execution>\n");
		sb.append("				</executions>\n");
		sb.append("			</plugin>\n");
		sb.append("		</plugins>\n");
		sb.append("	</build>\n\n");
		sb.append("	<modules>\n</modules>\n");
		sb.append("</project>\n");
		FileTools.writeToFile(fileName, sb.toString());
	}
}
