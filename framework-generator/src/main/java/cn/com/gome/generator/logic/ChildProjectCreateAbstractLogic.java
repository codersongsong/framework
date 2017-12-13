package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.generator.util.CodeGenHelper;
import cn.com.gome.generator.util.FileTools;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <Description> 工程创建抽象类</Description>
 * <ClassName> ChildProjectCreateAbstractLogic</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月04日 14:33
 */
public abstract class ChildProjectCreateAbstractLogic extends AbstractLogic implements ILogics<Map<String, Object>> {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    /**
     * 是否创建spring.xml
     */
    protected boolean isCreateSpringXml = false;
    /**
     * 是否创建spring-mvc-interceptor.xml
     */
    protected boolean isCreateSpringMvcInterceptor = false;
    /**
     * shi否创建web.xml
     */
    protected boolean isCreateWebXml = false;
    /**
     * 是否创建依赖的配置文件
     */
    protected boolean isCreateXmlConfigFiles = false;

    /**
     * 是否创建dubbo consumer示例
     */
    protected boolean isCreateDemoDubboConsumerXml = false;


    @Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
        TblChildProjectInfo childProjectInfo = (TblChildProjectInfo) map.get("childProjectInfo");
        TblProjectBasicInfo projectBasicInfo = (TblProjectBasicInfo) map.get("projectBasicInfo");
        String pachagesPath = projectBasicInfo.getPackages().replace(".", "//");
        String projectPath = projectBasicInfo.getProjectPath().trim();
        if (!projectPath.endsWith("//")) {
            projectPath += "//";
            projectBasicInfo.setProjectPath(projectPath);
        }
        projectPath += projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng();
        childProjectInfo.setProjectPath(projectPath);
        //依赖配置文件
        Map<String, String> configFileMap = (Map<String, String>) map.get("configFiles");
        //属性配置列表
        List<TblPomProfileInfo> propertieConfigs = (List<TblPomProfileInfo>) map.get("propertieConfigs");
        logger.info("开始创建：{}", childProjectInfo.getChildProjectName());
        try {
            logger.info("开始创建应用必备文件夹目录");
            //1.创建应用必备文件夹目录
            createNecessaryFolder(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
            logger.info("结束创建应用必备文件夹目录");
            logger.info("开始创建spring配置文件");
            //2.创建spring配置文件
            createSpringXml(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
            logger.info("结束创建spring配置文件");
            logger.info("开始创建xml配置文件");
            //3.创建xml配置文件
            createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
            logger.info("结束创建xml配置文件");

            logger.info("开始创建属性文件");
            //4.创建属性文件
            createPropertieFiles(projectPath, projectBasicInfo, childProjectInfo, propertieConfigs);
            logger.info("结束创建属性文件");
            logger.info("开始创建webxml文件");
            //5.创建webxml文件
            createWebXml(projectPath, projectBasicInfo, childProjectInfo);
            logger.info("结束创建webxml文件");
            logger.info("开始创建必要的java文件");
            //6.创建必要的java文件
            createDefaultJavaFiles(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
            logger.info("结束创建必要的java文件");
            logger.info("开始个性化模块创建");
            //7.个性化模块创建
            selfModuleCreate(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
            logger.info("结束个性化模块创建");
            logger.info("结束创建：{}", childProjectInfo.getChildProjectName());
            return ResultEnum.OK;
        } catch (Exception e) {
            logger.error("创建子项目异常：" + childProjectInfo.getChildProjectName(), e);
        }
        return ResultEnum.PART_CASE_01;
    }

    /**
     * <Title>initConfig</Title>
     * <Description> 加载初始化配置</Description>
     *
     * @param
     * @return
     * @throws
     */
    protected abstract void initConfig();

    /**
     * <Title>createNecessaryFolder</Title>
     * <Description> 创建必备文件夹</Description>
     *
     * @param projectPath, pachagesPath, projectBasicInfo, childProjectInfo
     * @return void
     * @throws
     */
    protected void createNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        List<String> folders = getNecessaryFolder(projectPath, pachagesPath, projectBasicInfo, childProjectInfo);
        if (CollectionUtils.isNotEmpty(folders)) {
            for (int i = 0; i < folders.size(); i++) {
                FileTools.mkdirs(folders.get(i));
            }
        }
    }

    /**
     * <Title>getNecessaryFolder</Title>
     * <Description> 获取必备文件夹</Description>
     *
     * @param
     * @return
     * @throws
     */
    protected abstract List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo);

    /**
     * <Title>createSpringXml</Title>
     * <Description> 创建spring xml文件</Description>
     *
     * @param projectPath, projectBasicInfo, childProjectInfo, configFileMap
     * @return void
     * @throws
     */
    protected void createSpringXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        if (isCreateSpringXml) {
            CodeGenHelper.createSpringXml(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
        }
    }

    /**
     * <Title>createXmlConfigFiles</Title>
     * <Description> 创建xml配置文件</Description>
     *
     * @param projectPath, projectBasicInfo, childProjectInfo, configFileMap
     * @return void
     * @throws
     */
    protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        if (isCreateXmlConfigFiles) {
            CodeGenHelper.createProjectConfigFiles(projectPath, configFileMap);
        }
        if (isCreateSpringMvcInterceptor) {
            CodeGenHelper.createSpringMvcInterceptorXml(projectPath, projectBasicInfo, childProjectInfo);
        }
        if (isCreateDemoDubboConsumerXml) {
            CodeGenHelper.createDemoDubboConsumer(projectPath, projectBasicInfo, childProjectInfo);
        }
    }

    /**
     * <Title>createPropertieFiles</Title>
     * <Description> 创建属性配置文件</Description>
     *
     * @param projectPath, projectBasicInfo, childProjectInfo, propertieConfigs
     * @return void
     * @throws
     */
    protected void createPropertieFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, List<TblPomProfileInfo> propertieConfigs) throws IOException {
        String configFileName = childProjectInfo.getConfigFileName();
        if (StringUtils.isBlank(configFileName)) {
            configFileName = projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + ".properties";
        }
        StringBuilder devProperties = new StringBuilder();
        StringBuilder uatProperties = new StringBuilder();
        StringBuilder preProperties = new StringBuilder();
        StringBuilder liveProperties = new StringBuilder();
        StringBuilder properties = new StringBuilder();
        String devPropertieFile = projectPath + "//src//main//resources//props//dev//" + configFileName;
        String uatPropertieFile = projectPath + "//src//main//resources//props//uat//" + configFileName;
        String prePropertieFile = projectPath + "//src//main//resources//props//pre//" + configFileName;
        String livePropertieFile = projectPath + "//src//main//resources//props//live//" + configFileName;
        String propertieFile = projectPath + "//src//main//resources//props//" + configFileName;
        //将配置数据写入配置文件
        if (CollectionUtils.isNotEmpty(propertieConfigs) && propertieConfigs.size() > 0) {
            for (int i = 0; i < propertieConfigs.size(); i++) {
                devProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n").append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvDev()).append("\n");
                uatProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n").append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvUat()).append("\n");
                preProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n").append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvPre()).append("\n");
                liveProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n").append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvLive()).append("\n");
                properties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n").append(propertieConfigs.get(i).getConfigName()).append("=${").append(propertieConfigs.get(i).getConfigName()).append("}\n");
            }
        }
        FileTools.writeToFile(devPropertieFile, devProperties.toString());
        FileTools.writeToFile(uatPropertieFile, uatProperties.toString());
        FileTools.writeToFile(prePropertieFile, preProperties.toString());
        FileTools.writeToFile(livePropertieFile, liveProperties.toString());
        FileTools.writeToFile(propertieFile, properties.toString());
    }
    /**
     * <Title>createWebXml</Title>
     * <Description> 创建webxml文件</Description>
     *
     * @param projectPath, projectBasicInfo, childProjectInfo
     * @return void
     * @throws
     */
    protected void createWebXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        if (isCreateWebXml) {
            CodeGenHelper.createWebXml(projectPath, projectBasicInfo, childProjectInfo);
        }
    }

    /**
     * <Title>createDefaultJavaFiles</Title>
     * <Description> 创建默认java代码</Description>
     *
     * @param
     * @return
     * @throws
     */
    protected abstract void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException;

    /**
     * <Title>selfModuleCreate</Title>
     * <Description> 自定义模块</Description>
     *
     * @param
     * @return
     * @throws
     */
    protected abstract void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException;
}
