
package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.generator.util.FileTools;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author GOME
 * @ClassName: MainProjectCreateLogic
 * @Description: 主项目创建
 * @date 2017年5月26日 下午2:49:05
 */
@Service
public class ChildProjectServiceCreateLogic extends ChildProjectCreateAbstractLogic {
    private Logger logger = LoggerFactory.getLogger(ChildProjectServiceCreateLogic.class);

    @Override
    @PostConstruct
    protected void initConfig() {
        this.isCreateSpringXml = true;
        this.isCreateSpringMvcInterceptor = false;
        this.isCreateWebXml = true;
        this.isCreateXmlConfigFiles = true;
        this.isCreateDemoDubboConsumerXml = false;
    }

    @Override
    protected List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) {
        List<String> folders = Lists.newArrayListWithExpectedSize(12);
        folders.add(projectPath + "//src//main//resources//spring");
        folders.add(projectPath + "//src//main//resources//props//dev");
        folders.add(projectPath + "//src//main//resources//props//uat");
        folders.add(projectPath + "//src//main//resources//props//pre");
        folders.add(projectPath + "//src//main//resources//props//live");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//dubbo//impl");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//logic//impl");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//service//impl");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//util");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//cache");
        folders.add(projectPath + "//src//main//webapp//WEB-INF");
        folders.add(projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//test");
        return folders;
    }

    @Override
    protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        super.createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);

        String providerXmlPath = projectPath + "//src//main//resources//spring//" + projectBasicInfo.getProjectEng() + "-dubbo-provider.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n");
        sb.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" \n");
        sb.append("			xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
        sb.append("			xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\" \n");
        sb.append("			xsi:schemaLocation=\"http://www.springframework.org/schema/beans \n");
        sb.append("				http://www.springframework.org/schema/beans/spring-beans-2.5.xsd \n");
        sb.append("				http://code.alibabatech.com/schema/dubbo \n");
        sb.append("				http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\n");
        sb.append("  <dubbo:consumer check=\"false\" /> \n");
        sb.append("  <dubbo:registry protocol=\"zookeeper\" address=\"${dubbo.run.registryAddress}\" id=\"" + projectBasicInfo.getProjectEng() + "DubboProvider\" /> \n");
        sb.append("  <dubbo:protocol name=\"dubbo\" port=\"-1\" /> \n");
        sb.append("  \n");
        sb.append("  <!--dubbo:service timeout=\"6000\" group=\"${dubbo.group}\" ref=\"demoFacadeServiceImpl\" interface=\"com.gome.mammon.dubbo.DemoFacadeService\" registry=\"" + projectBasicInfo.getProjectEng() + "DubboProvider\" /-->\n");
        sb.append("   \n");
        sb.append("</beans>\n");
        FileTools.writeToFile(providerXmlPath, sb.toString());
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
        String sCacheServiceImplFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//service//impl//SCacheServiceImpl.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".service.impl;\n");
        sb.append("\n");
        sb.append("import java.util.ArrayList;\n");
        sb.append("import java.util.List;\n");
        sb.append("\n");
        sb.append("import javax.annotation.PostConstruct;\n");
        sb.append("\n");
        sb.append("import org.slf4j.Logger;\n");
        sb.append("import org.slf4j.LoggerFactory;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("\n");
        sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n");
        sb.append("import com.gomeplus.frame.cache.GlobalDataDictionaryCache;\n");
        sb.append("import com.gomeplus.frame.cache.DictionaryVo;\n");
        sb.append("\n");
        sb.append("import " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".service.SCacheService;\n");
        sb.append("\n");
        sb.append(getClassDescription("SCacheServiceImpl", "缓存参数初始化接口实现"));
        sb.append("@Service\n");
        sb.append("public class SCacheServiceImpl implements SCacheService {\n");
        sb.append("\n");
        sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
        sb.append("	\n");
        sb.append("	@PostConstruct\n");
        sb.append("	private void inits() {\n");
        sb.append("		logger.info(\"系统启动时初始化静态参数开始...\");\n");
        sb.append("		setSysDic();\n");
        sb.append("		logger.info(\"系统启动时初始化静态参数成功\");\n");
        sb.append("	}\n");
        sb.append("	\n");
        sb.append("	public void cacheUpdate() {\n");
        sb.append("		logger.info(\"系统更新初始化静态参数开始...\");\n");
        sb.append("		setSysDic();\n");
        sb.append("		logger.info(\"系统更新初始化静态参数成功\");\n");
        sb.append("	}\n");
        sb.append("\n");
        sb.append("	private void setSysDic(){\n");
        sb.append("		List<DictionaryVo> list = new ArrayList<DictionaryVo>();\n");
        sb.append("		list.add(new DictionaryVo(\"010\",\"测试1\"));\n");
        sb.append("		list.add(new DictionaryVo(\"020\",\"测试2\"));\n");
        sb.append("		list.add(new DictionaryVo(\"030\",\"测试3\"));\n");
        sb.append("		list.add(new DictionaryVo(\"040\",\"测试4\"));\n");
        sb.append("		GlobalDataDictionaryCache.getInstance().putIdValue(\"TEST_TEST\", list);\n");
        sb.append("	}\n");
        sb.append("}\n");
        FileTools.writeToFile(sCacheServiceImplFilePath, sb.toString());

        String sCacheServiceFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//service//SCacheService.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".service;\n");
        sb.append("\n");
        sb.append(getClassDescription("SCacheService", "缓存参数初始化接口"));
        sb.append("public interface SCacheService {\n");
        sb.append("\n");
        sb.append("	public void cacheUpdate();\n");
        sb.append("}\n");
        FileTools.writeToFile(sCacheServiceFilePath, sb.toString());

        String PROJECT_ENG_U = columnToStringU(projectBasicInfo.getProjectEng().trim());
        String filePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//cache//" + PROJECT_ENG_U + "Cache.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".cache;\n");
        sb.append("\n");
        sb.append("import java.util.concurrent.ConcurrentMap;\n");
        sb.append("import java.util.concurrent.ConcurrentHashMap;\n");
        sb.append("\n");
        sb.append(getClassDescription(PROJECT_ENG_U + "Cache", "应用级缓存"));
        sb.append("public class " + PROJECT_ENG_U + "Cache {\n");
        sb.append("\n");
        sb.append("	public static ConcurrentMap<String, String> business = new ConcurrentHashMap<String,String>();\n");
        sb.append("	\n");
        sb.append("}\n");
        FileTools.writeToFile(filePath, sb.toString());
    }

    @Override
    protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {

    }
}
