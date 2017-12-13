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

/**
 * <Description> 其他项目创建</Description>
 * <ClassName> ChildProjectOtherCreateLogic</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月04日 18:42
 */
@Service
public class ChildProjectOtherCreateLogic extends ChildProjectCreateAbstractLogic {

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
    protected List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) {
        List<String> folders = Lists.newArrayListWithExpectedSize(3);
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//");
        folders.add(projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//test");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//util");
        return folders;
    }


    @Override
    protected void createPropertieFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, List<TblPomProfileInfo> propertieConfigs) throws IOException {

    }

    @Override
    protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        String filePath = projectPath + "//src//test//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//package-info.java";
        StringBuilder sb = new StringBuilder("package ")
                .append(pachagesPath.replaceAll("//","."))
                .append(".")
                .append(projectBasicInfo.getProjectEng())
                .append(".")
                .append(childProjectInfo.getChildProjectEng())
                .append(";");
        FileTools.writeToFile(filePath, sb.toString());
        filePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//package-info.java";
        sb = new StringBuilder("package ")
                .append(pachagesPath.replaceAll("//", "."))
                .append(".")
                .append(projectBasicInfo.getProjectEng())
                .append(".")
                .append(childProjectInfo.getChildProjectEng())
                .append(";");
        FileTools.writeToFile(filePath, sb.toString());
    }

    @Override
    protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {

    }
}
