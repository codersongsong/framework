package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblDependencyInfo;
import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.mapper.ser.TblDependencyInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblPomProfileInfoSerMapper;
import cn.com.gome.generator.util.FileTools;
import cn.com.gome.generator.util.PomUtils;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MainProjectCreateLogic
 * @Description: 主项目创建
 */
@Service
public class ChildProjectBaseCreateLogic implements ILogics<Map<String, Object>> {

    private Logger logger = LoggerFactory.getLogger("ChildProjectBaseCreateLogic");

    @Autowired
    private TblDependencyInfoSerMapper tblDependencyInfoSerMapper;
    @Autowired
    private TblPomProfileInfoSerMapper tblPomProfileInfoSerMapper;

    @Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
        try {
            TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
            TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
            String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");

            String projectPath = projectBasicInfo.getProjectPath().trim();
            if (!projectPath.endsWith("//")) {
                projectPath += "//";
                projectBasicInfo.setProjectPath(projectPath);
            }
//            projectPath += projectBasicInfo.getProjectEng() + "//";
            projectPath += projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng();
            childProjectInfo.setProjectPath(projectPath);
            File file = new File(projectPath);
            if (!file.exists()) {
                file.mkdir();
            }

            file = new File(projectPath + "//src//main//resources");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(projectPath + "//src//main//java");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng());
            if (!file.exists()) {
                file.mkdirs();
            }
            String basicJar = childProjectInfo.getBasicJar();
            List<TblDependencyInfo> dependencyList = null;
            if (StringUtils.isNotBlank(basicJar)) {
                List<String> dependencyIds = Splitter.on(',').splitToList(basicJar);
                dependencyList = tblDependencyInfoSerMapper.queryListByIds(dependencyIds);
            }

            //创建pom文件：jar包依赖，属性文件
            projectPath += "//pom.xml";
            file = new File(projectPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            StringBuffer sb = new StringBuffer();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            sb.append("	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
            sb.append("	<modelVersion>4.0.0</modelVersion>\n");
            sb.append("	<parent>\n");
            sb.append("		<groupId>" + projectBasicInfo.getPackages() + "</groupId>\n");
            sb.append("		<artifactId>" + projectBasicInfo.getProjectEng() + "</artifactId>\n");
            sb.append("		<version>0.0.1-SNAPSHOT</version>\n");
            sb.append("	</parent>\n");
            sb.append("	<artifactId>" + projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "</artifactId>\n");
            sb.append("	<packaging>" + childProjectInfo.getPackageType() + "</packaging>\n");
            sb.append("	<name>" + projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "</name>\n");
            sb.append("	<url>http://maven.apache.org</url>\n");
            sb.append("	<properties>");
            sb.append("		<profiles.dir>src/main/resources/props</profiles.dir>\n");
            sb.append("	</properties>\n");
            sb.append("	<dependencies>\n");
            //拼装依赖列表，并组装生成依赖xmll配置文件参数
            Map<String, String> configFileMap = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(dependencyList)) {
                for (int i = 0; i < dependencyList.size(); i++) {
                    sb.append("		<!-- ");
                    sb.append(dependencyList.get(i).getDependencyName());
                    sb.append(" -->\n");
                    sb.append("		<dependency>\n");
                    sb.append("			<groupId>");
                    sb.append(dependencyList.get(i).getGroupId());
                    sb.append("</groupId>\n");
                    sb.append("			<artifactId>");
                    sb.append(dependencyList.get(i).getArtifactId());
                    sb.append("</artifactId>\n");
                    sb.append("			<version>");
                    sb.append(dependencyList.get(i).getVersion());
                    sb.append("</version>\n");
                    if (StringUtils.isNotBlank(dependencyList.get(i).getScope())) {
                        sb.append("			<scope>");
                        sb.append(dependencyList.get(i).getScope());
                        sb.append("</scope>\n");
                    }
                    sb.append("		</dependency>\n");
                    if (StringUtils.isNotBlank(dependencyList.get(i).getConfigFileName())) {
                        configFileMap.put(dependencyList.get(i).getConfigFileName(), dependencyList.get(i).getConfigFileContent());
                    }
                }
            }
            map.put("configFiles", configFileMap);
            if ("040,050,060".contains(childProjectInfo.getProjectType())) {
                sb.append("		<!-- ");
                sb.append(projectBasicInfo.getProjectName());
                sb.append("持久层");
                sb.append(" -->\n");
                sb.append("		<dependency>\n");
                sb.append("			<groupId>");
                sb.append(projectBasicInfo.getPackages());
                sb.append("</groupId>\n");
                sb.append("			<artifactId>");
                sb.append(projectBasicInfo.getProjectEng());
                sb.append("-dao");
                sb.append("</artifactId>\n");
                sb.append("		</dependency>\n");
            }
            if ("040".equals(childProjectInfo.getProjectType())) {
                sb.append("		<!-- ");
                sb.append(childProjectInfo.getChildProjectName());
                sb.append("dubbo接口");
                sb.append(" -->\n");
                sb.append("		<dependency>\n");
                sb.append("			<groupId>");
                sb.append(projectBasicInfo.getPackages());
                sb.append("</groupId>\n");
                sb.append("			<artifactId>");
                sb.append(projectBasicInfo.getProjectEng());
                if (!"service".equals(childProjectInfo.getChildProjectEng())) {
                    sb.append("-");
                    sb.append(childProjectInfo.getChildProjectEng());
                }
                sb.append("-dubbo");
                sb.append("</artifactId>\n");
                sb.append("		</dependency>\n");
            }
            sb.append("	</dependencies>\n");

            if ("pom".equals(childProjectInfo.getPackageType())) {
                sb.append("	<modules></modules>\n");
            } else {
                sb.append("	<build>\n");
                if ("war".equals(childProjectInfo.getPackageType())) {
                    sb.append("		<finalName>" + projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + "</finalName>\n");
                }
                if (StringUtils.isNotEmpty(childProjectInfo.getConfigFileName())) {
                    sb.append("		<filters>\n");
                    if (childProjectInfo.getChildProjectEng().contains("dao") && childProjectInfo.getConfigFileName().contains("jdbc")) {
                        sb.append("<!-- 注意：dao层，不过滤jdbc配置文件  如果需要过滤其他文件，自己添加配置 -->\n");
                    } else {
                        sb.append("			<filter>${profiles.dir}\\${env}\\" + childProjectInfo.getConfigFileName() + "</filter>\n");
                    }
                    sb.append("		</filters>\n");
                }
                sb.append("		<resources>\n");
                sb.append("			<resource>\n");
                sb.append("				<directory>src/main/resources</directory>\n");
                sb.append("				<filtering>true</filtering>\n");
                sb.append("			</resource>\n");
                sb.append("		</resources>\n");
                sb.append("<plugins>\n" +
                        "            <plugin>\n" +
                        "                <groupId>org.apache.maven.plugins</groupId>\n" +
                        "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                        "                <version>3.7.0</version>\n" +
                        "                <configuration>\n" +
                        "                    <source>${jdk.version}</source>\n" +
                        "                    <target>${jdk.version}</target>\n" +
                        "                </configuration>\n" +
                        "            </plugin>\n");
                if (!"010".equals(childProjectInfo.getProjectType()) && !"999".equals(childProjectInfo.getProjectType())) {
                    sb.append("            <plugin>\n" +
                            "                <groupId>org.eclipse.jetty</groupId>\n" +
                            "                <artifactId>jetty-maven-plugin</artifactId>\n" +
                            "                <version>9.0.7.v20131107</version>\n" +
                            "                <configuration>\n" +
                            "                    <webApp>\n" +
                            "                        <contextPath>/</contextPath>\n" +
                            "                        <!--<defaultsDescriptor>src/main/resources/webdefault.xml</defaultsDescriptor>-->\n" +
                            "                    </webApp>\n" +
                            "                    <!--<contextXml>${project.basedir}/src/main/resources/jetty-context.xml</contextXml>-->\n" +
                            "                    <scanIntervalSeconds>10</scanIntervalSeconds>\n" +
                            "                    <stopPort>9005</stopPort>\n" +
                            "                    <stopKey>shutdown</stopKey>\n" +
                            "                    <stopWait>10</stopWait>\n" +
                            "                    <systemProperties>\n" +
                            "                        <systemProperty>\n" +
                            "                            <name>tapestry.execution-mode</name>\n" +
                            "                            <value>development</value>\n" +
                            "                        </systemProperty>\n" +
                            "                        <systemProperty>\n" +
                            "                            <name>jetty.port</name>\n" +
                            "                            <value>10143</value>\n" +
                            "                        </systemProperty>\n" +
                            "                    </systemProperties>\n" +
                            "                </configuration>\n" +
                            "            </plugin>\n");
                }
                sb.append("        </plugins>");
                sb.append("	</build>\n");
                String versionConfig = childProjectInfo.getVersionConfig();
                List<TblPomProfileInfo> profileInfoList = null;
                if (StringUtils.isNotBlank(versionConfig)) {
                    List<String> profileInfoIds = Splitter.on(',').splitToList(versionConfig);
                    profileInfoList = tblPomProfileInfoSerMapper.queryListByIds(profileInfoIds);
                }
                List<TblPomProfileInfo> propertiesProfileInfoList = new ArrayList<>();
                List<TblPomProfileInfo> pomProfileInfoList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(profileInfoList)) {
                    for (int i = 0; i < profileInfoList.size(); i++) {
                        if ("010".equals(profileInfoList.get(i).getType())) {
                            //pom配置
                            pomProfileInfoList.add(profileInfoList.get(i));
                        } else {
                            //属性配置
                            propertiesProfileInfoList.add(profileInfoList.get(i));
                        }
                    }
                }
                map.put("propertieConfigs", propertiesProfileInfoList);
                if (CollectionUtils.isNotEmpty(pomProfileInfoList)) {
                    sb.append("	<profiles>\n");
                    StringBuilder devprofile = new StringBuilder();
                    StringBuilder uatprofile = new StringBuilder();
                    StringBuilder preprofile = new StringBuilder();
                    StringBuilder liveprofile = new StringBuilder();
                    for (int i = 0; i < pomProfileInfoList.size(); i++) {
                        devprofile.append("			<");
                        devprofile.append(pomProfileInfoList.get(i).getConfigName());
                        devprofile.append(">");
                        devprofile.append(pomProfileInfoList.get(i).getEnvDev());
                        devprofile.append("</");
                        devprofile.append(pomProfileInfoList.get(i).getConfigName());
                        devprofile.append(">\n");

                        uatprofile.append("		<!-- ");
                        uatprofile.append(pomProfileInfoList.get(i).getConfigDescription());
                        uatprofile.append(" -->\n");
                        uatprofile.append("			<");
                        uatprofile.append(pomProfileInfoList.get(i).getConfigName());
                        uatprofile.append(">");
                        uatprofile.append(pomProfileInfoList.get(i).getEnvUat());
                        uatprofile.append("</");
                        uatprofile.append(pomProfileInfoList.get(i).getConfigName());
                        uatprofile.append(">\n");

                        preprofile.append("			<");
                        preprofile.append(pomProfileInfoList.get(i).getConfigName());
                        preprofile.append(">");
                        preprofile.append(pomProfileInfoList.get(i).getEnvPre());
                        preprofile.append("</");
                        preprofile.append(pomProfileInfoList.get(i).getConfigName());
                        preprofile.append(">\n");

                        liveprofile.append("			<");
                        liveprofile.append(pomProfileInfoList.get(i).getConfigName());
                        liveprofile.append(">");
                        liveprofile.append(pomProfileInfoList.get(i).getEnvLive());
                        liveprofile.append("</");
                        liveprofile.append(pomProfileInfoList.get(i).getConfigName());
                        liveprofile.append(">\n");
                    }
                    sb.append("	<profile>\n		<id>dev</id>\n		<properties>\n			<env>dev</env>\n");
                    sb.append(devprofile);
                    sb.append("		</properties>\n");
                    sb.append("	</profile>\n");
                    sb.append("	<profile>\n		<id>uat</id>\n		<properties>\n			<env>uat</env>\n");
                    sb.append(uatprofile);
                    sb.append("		</properties>\n");
                    sb.append("	</profile>\n");
                    sb.append("	<profile>\n		<id>pre</id>\n		<properties>\n			<env>pre</env>\n");
                    sb.append(preprofile);
                    sb.append("		</properties>\n");
                    sb.append("	</profile>\n");
                    sb.append("	<profile>\n		<id>live</id>\n		<properties>\n			<env>live</env>\n");
                    sb.append(liveprofile);
                    sb.append("		</properties>\n");
                    sb.append("	</profile>\n");
                    sb.append("	<profile>\n		<id>drill</id>\n		<properties>\n			<env>live</env>\n");
                    sb.append(liveprofile);
                    sb.append("		</properties>\n");
                    sb.append("	</profile>\n");
                    sb.append("	</profiles>\n");
                } else {
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
                }
            }

            sb.append("</project>");
            FileTools.writeToFile(projectPath, sb.toString());
            PomUtils.editPomXml(projectBasicInfo.getProjectPath(), projectBasicInfo.getProjectEng(), childProjectInfo.getChildProjectEng(), projectBasicInfo.getPackages());
            return ResultEnum.OK;
        } catch (Exception e) {
            logger.error("创建子项目异常：", e);
        }
        return ResultEnum.PART_CASE_01;
    }
}
