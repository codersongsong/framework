package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
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
public class ChildProjectDaoCreateLogic extends ChildProjectCreateAbstractLogic {
    private Logger logger = LoggerFactory.getLogger(ChildProjectDaoCreateLogic.class);

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
        List<String> folders = Lists.newArrayListWithExpectedSize(12);
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//util");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//vo");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//entity");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//mapper//ser");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//mapper//edit");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//jdbc//dev");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//jdbc//uat");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//jdbc//pre");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//jdbc//live");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//mapper//ser");
        folders.add(projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//mapper//edit");
        folders.add(projectPath + "//src//main//resources//spring");
        return folders;
    }

    @Override
    protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        super.createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
        //1.创建属性引入文件
        createPropsInferXml(projectPath, projectBasicInfo);
        //2.创建mybatis 配置
        createMybatisConfigurationXml(projectPath);
        //3.创建DataSource配置文件
        createDataSourceXml(projectPath, projectBasicInfo);
    }


    @Override
    protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo) throws IOException {

    }

    @Override
    protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {
    }

    @Override
    protected void createPropertieFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, List<TblPomProfileInfo> propertieConfigs) throws IOException {
        createJdbcPropertiesFiles(projectPath, projectBasicInfo);
    }

    /**
     * <Title>createJdbcPropertiesFiles</Title>
     * <Description> 创建jdbc配置文件</Description>
     *
     * @param projectPath
     * @param projectBasicInfo
     * @return void
     * @throws
     */
    private void createJdbcPropertiesFiles(String projectPath, TblProjectBasicInfo projectBasicInfo) throws IOException {
        String basicPath = projectPath + "//src//main//resources//props//" + projectBasicInfo.getProjectEng() + "//jdbc//";
        String devFile = basicPath + "//dev//" + projectBasicInfo.getProjectEng() + "-jdbc.properties";
        String uatFile = basicPath + "//uat//" + projectBasicInfo.getProjectEng() + "-jdbc.properties";
        String preFile = basicPath + "//pre//" + projectBasicInfo.getProjectEng() + "-jdbc.properties";
        String liveFile = basicPath + "//live//" + projectBasicInfo.getProjectEng() + "-jdbc.properties";

        StringBuilder sb = new StringBuilder();
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.checkoutTimeout=5000\n");
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.minPoolSize=10\n");
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.maxPoolSize=50\n");
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.maxIdleTime=7200\n");
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.acquireIncrement=3\n");
        sb.append(projectBasicInfo.getProjectEng() + ".cpool.maxIdleTimeExcessConnections=3000\n");
        sb.append("\n");
        sb.append(projectBasicInfo.getProjectEng() + ".jdbc.driverClassName=" + projectBasicInfo.getDatabaseDriver() + "\n");
        sb.append(projectBasicInfo.getProjectEng() + ".jdbc.url=" + projectBasicInfo.getDatabaseUrl() + "\n");
        sb.append(projectBasicInfo.getProjectEng() + ".jdbc.username=" + projectBasicInfo.getDatabaseAccount() + "\n");
        sb.append(projectBasicInfo.getProjectEng() + ".jdbc.password=" + projectBasicInfo.getDatabasePassword() + "\n");

        FileTools.writeToFile(devFile, sb.toString());
        FileTools.writeToFile(uatFile, sb.toString());
        FileTools.writeToFile(preFile, sb.toString());
        FileTools.writeToFile(liveFile, sb.toString());
    }

    /**
     * <Title>createPropsInferXml</Title>
     * <Description> 创建jdbc配置引用xml</Description>
     *
     * @param projectPath
     * @param projectBasicInfo
     * @return void
     * @throws
     */
    private void createPropsInferXml(String projectPath, TblProjectBasicInfo projectBasicInfo) throws IOException {
        String propsInferXmlFilePath = projectPath + "//src//main//resources//spring//" + projectBasicInfo.getProjectEng() + "-jdbc-props.xml";
        StringBuilder sbDataSource = new StringBuilder();
        sbDataSource.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sbDataSource.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n");
        sbDataSource.append("	xmlns:context=\"http://www.springframework.org/schema/context\"\n");
        sbDataSource.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        sbDataSource.append("	xmlns:mongo=\"http://www.springframework.org/schema/data/mongo\"\n");
        sbDataSource.append("	xsi:schemaLocation=\"http://www.springframework.org/schema/beans  \n");
        sbDataSource.append("        http://www.springframework.org/schema/beans/spring-beans.xsd \n");
        sbDataSource.append("        http://www.springframework.org/schema/context              \n");
        sbDataSource.append("        http://www.springframework.org/schema/context/spring-context.xsd\">\n");
        sbDataSource.append("	\n");
        sbDataSource.append("	<bean class=\"com.gomeplus.security.aes.PropertyEncryptConfig\">\n");
        sbDataSource.append("		<property name=\"locations\">\n");
        sbDataSource.append("			<list>\n");
        sbDataSource.append("				<value>classpath*:/props/" + projectBasicInfo.getProjectEng() + "/jdbc/${env}/" + projectBasicInfo.getProjectEng() + "-jdbc.properties</value>\n");
        sbDataSource.append("			</list>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("	</bean>\n");
        sbDataSource.append("	\n");
        sbDataSource.append("</beans>\n");
        FileTools.writeToFile(propsInferXmlFilePath, sbDataSource.toString());
    }

    /**
     * <Title>createMybatisConfigurationXml</Title>
     * <Description> 创建mybatisConfigurationXml</Description>
     *
     * @param projectPath
     * @return void
     * @throws
     */
    private void createMybatisConfigurationXml(String projectPath) throws IOException {
        String mybatisConfigurationXmlFilePath = projectPath + "//src//main//resources//spring//mybatis-config.xml";
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
                "<configuration>\n" +
                "    <settings>\n" +
                "        <setting name=\"callSettersOnNulls\" value=\"true\"/>\n" +
                "    </settings>\n" +
                "</configuration>");
        FileTools.writeToFile(mybatisConfigurationXmlFilePath, sb.toString());
    }

    /**
     * <Title>createDataSourceXml</Title>
     * <Description> 创建DataSourceXml</Description>
     *
     * @param projectPath
     * @param projectBasicInfo
     * @return void
     * @throws
     */
    private void createDataSourceXml(String projectPath, TblProjectBasicInfo projectBasicInfo) throws IOException {
        String dataSourceFilePath = projectPath + "//src//main//resources//spring//" + projectBasicInfo.getProjectEng() + "-dataSource.xml";
        StringBuilder sbDataSource = new StringBuilder();
        sbDataSource.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sbDataSource.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n");
        sbDataSource.append("	xmlns:aop=\"http://www.springframework.org/schema/aop\" xmlns:context=\"http://www.springframework.org/schema/context\"\n");
        sbDataSource.append("	xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        sbDataSource.append("	xmlns:mongo=\"http://www.springframework.org/schema/data/mongo\"\n");
        sbDataSource.append("	xsi:schemaLocation=\"http://www.springframework.org/schema/aop\n");
        sbDataSource.append("        http://www.springframework.org/schema/aop/spring-aop.xsd\n");
        sbDataSource.append("        http://www.springframework.org/schema/beans\n");
        sbDataSource.append("        http://www.springframework.org/schema/beans/spring-beans.xsd\n");
        sbDataSource.append("        http://www.springframework.org/schema/context \n");
        sbDataSource.append("        http://www.springframework.org/schema/context/spring-context.xsd\n");
        sbDataSource.append("        http://www.springframework.org/schema/tx  \n");
        sbDataSource.append("        http://www.springframework.org/schema/tx/spring-tx.xsd\">\n");
        sbDataSource.append("\n");
        sbDataSource.append("	<bean id=\"" + projectBasicInfo.getProjectEng() + "DataSource\" class=\"org.apache.commons.dbcp.BasicDataSource\">\n");
        sbDataSource.append("		<property name=\"driverClassName\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".jdbc.driverClassName}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"url\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".jdbc.url}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"username\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".jdbc.username}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"password\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".jdbc.password}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"initialSize\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".cpool.minPoolSize}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"maxActive\">\n");
        sbDataSource.append("			<value>${" + projectBasicInfo.getProjectEng() + ".cpool.maxPoolSize}</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"maxIdle\">\n");
        sbDataSource.append("			<value>15</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"minIdle\">\n");
        sbDataSource.append("			<value>5</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"maxWait\">\n");
        sbDataSource.append("			<value>60000</value>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("		<property name=\"testOnBorrow\" value=\"true\"></property>\n");
        sbDataSource.append("		<property name=\"testOnReturn\" value=\"true\"></property>\n");
        sbDataSource.append("		<property name=\"testWhileIdle\" value=\"true\"></property>\n");
        sbDataSource.append("		<property name=\"timeBetweenEvictionRunsMillis\" value=\"36000\"></property>\n");
        sbDataSource.append("	</bean>\n");
        sbDataSource.append("\n");
        sbDataSource.append("	<bean id=\"" + projectBasicInfo.getProjectEng() + "SqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">\n");
        sbDataSource.append("		<property name=\"dataSource\" ref=\"" + projectBasicInfo.getProjectEng() + "DataSource\" />\n");
        sbDataSource.append("     <property name=\"configLocation\" value=\"classpath:spring/mybatis-config.xml\"/>\n");
        sbDataSource.append("		<property name=\"mapperLocations\">\n");
        sbDataSource.append("			<list>\n");
        sbDataSource.append("				<value>classpath*:/props/" + projectBasicInfo.getProjectEng() + "/mapper/*/*.xml</value>\n");
        sbDataSource.append("			</list>\n");
        sbDataSource.append("		</property>\n");
        sbDataSource.append("	</bean>\n");
        sbDataSource.append("	<bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">\n");
        sbDataSource.append("		<property name=\"basePackage\" value=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + ".dao.mapper\" />\n");
        sbDataSource.append("		<property name=\"sqlSessionFactoryBeanName\" value=\"" + projectBasicInfo.getProjectEng() + "SqlSessionFactory\" />\n");
        sbDataSource.append("		<property name=\"annotationClass\" value=\"com.gomeplus.jdbc.mybatis.MybatisRepository\" />\n");
        sbDataSource.append("	</bean>\n");
        sbDataSource.append("	<bean id=\"txManager" + lowerToUpper(projectBasicInfo.getProjectEng()) + "\"	class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">\n");
        sbDataSource.append("		<property name=\"dataSource\" ref=\"" + projectBasicInfo.getProjectEng() + "DataSource\"></property>\n");
        sbDataSource.append("	</bean>\n");
        sbDataSource.append("	<tx:annotation-driven transaction-manager=\"txManager" + lowerToUpper(projectBasicInfo.getProjectEng()) + "\" proxy-target-class=\"true\" />\n");
        sbDataSource.append("\n");
        sbDataSource.append("</beans>\n");
        FileTools.writeToFile(dataSourceFilePath, sbDataSource.toString());
    }
}
