package cn.com.gome.generator.util;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * <Description> </Description>
 * <ClassName> CodeGenHelper</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月06日 17:06
 */
public class CodeGenHelper {

    /**
     * 创建spring xml
     *
     * @param projectPath
     * @param projectBasicInfo
     * @param childProjectInfo
     * @param configFileMap
     * @throws IOException
     */
    public static void createSpringXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        String filePath = projectPath + "//src//main//resources//spring.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:util=\"http://www.springframework.org/schema/util\"\n" +
                "       xmlns:aop=\"http://www.springframework.org/schema/aop\"\n" +
                "       xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/aop\n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/aop/spring-aop.xsd \n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/beans \n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/context \n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/context/spring-context.xsd \n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/util\n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/util/spring-util-3.1.xsd\n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/mvc\n" +
                "\t\t\t\t\t\t\t\thttp://www.springframework.org/schema/mvc/spring-mvc.xsd\">\n\n");

        sb.append("    <util:properties id=\"");
        sb.append(childProjectInfo.getChildProjectEng().toUpperCase());
        sb.append("\" location=\"classpath:props/");
        String configFileName = childProjectInfo.getConfigFileName();
        if (StringUtils.isBlank(configFileName)) {
            configFileName = projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + ".properties";
        }
        sb.append(configFileName);
        sb.append("\"/>\n");
        sb.append("   <aop:aspectj-autoproxy proxy-target-class=\"true\" /> \n");
        sb.append("   <context:annotation-config /> \n");
        if ("com.gomeplus".equals(projectBasicInfo.getPackages())) {
            sb.append("   <context:component-scan base-package=\"com.gomeplus\" /> \n");
        } else {
            sb.append("   <context:component-scan base-package=\"" + projectBasicInfo.getPackages() + ",com.gomeplus\" /> \n");
        }
        sb.append("  \n");
        if ("020,030,060".contains(childProjectInfo.getProjectType())) {
            //后台、前台web、api接口
            sb.append("   <import resource=\"classpath:/spring/spring-mvc-interceptor.xml\" /> \n");
        }
        if ("040,050,060".contains(childProjectInfo.getProjectType())) {
            sb.append("   <import resource=\"classpath*:/spring/" + projectBasicInfo.getProjectEng() + "-jdbc-props.xml\" /> \n");
            sb.append("   <import resource=\"classpath*:/spring/" + projectBasicInfo.getProjectEng() + "-dataSource.xml\" /> \n");
            sb.append("  \n");
        }
        sb.append("   <import resource=\"classpath*:/frame/ftl-frame-servlet.xml\" /> \n");
        if ("050".equals(childProjectInfo.getProjectType())) {
            //定时器
            sb.append("   <import resource=\"spring/spring-quartz.xml\" /> \n");
        }
        sb.append("  \n");
        if (configFileMap != null && !configFileMap.isEmpty()) {
            for (String fileName : configFileMap.keySet()) {
                if (fileName.contains("log4j")) {
                    continue;
                }
                sb.append("   <import resource=\"classpath*:");
                sb.append(fileName);
                sb.append("\" /> \n");
            }
        }
        if ("040".equals(childProjectInfo.getProjectType())) {
            sb.append("   <import resource=\"classpath:/spring/" + projectBasicInfo.getProjectEng() + "-dubbo-provider.xml\" /> \n");
        } else {
            sb.append("   <!--<import resource=\"classpath*:/spring/consumer/" + projectBasicInfo.getProjectEng() + "-dubbo-consumer.xml\" />--> \n");
        }
        sb.append("   <bean id=\"springBeanFactory\" class=\"com.gomeplus.frame.factory.SpringBeanFactory\" /> \n");
        sb.append("</beans>\n");
        FileTools.writeToFile(filePath, sb.toString());
    }

    /**
     * 创建dubbo服务端demo配置文件
     *
     * @param projectPath
     * @param projectBasicInfo
     * @param childProjectInfo
     * @throws IOException
     */
    public static void createDemoDubboConsumer(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        String demoConsumerFilePath = projectPath + "//src//main//resources//spring//consumer//" + projectBasicInfo.getProjectEng() + "-dubbo-consumer.xml";
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:dubbo=\"http://code.alibabatech.com/schema/dubbo\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd\n" +
                "\thttp://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd\">\n" +
                "    <!--<dubbo:registry protocol=\"zookeeper\" address=\"${demo.run.registryAddress}\" id=\"demoDubboProvider\"/>-->\n" +
                "    <!--<dubbo:reference interface=\"com.liuzhao.demo.dubbo.DemoFacadeService\" id=\"demoFacadeService\"-->\n" +
                "                     <!--group=\"${demo.dubbo.group}\" loadbalance=\"${dubbo.loadbalance}\" timeout=\"${dubbo.run.timeout}\"-->\n" +
                "                     <!--registry=\"demoDubboProvider\" check=\"${dubbo.check}\" retries=\"0\"/>-->\n" +
                "</beans>");
        FileTools.writeToFile(demoConsumerFilePath, sb.toString());
    }

    /**
     * 创建项目配置文件
     *
     * @param projectPath
     * @param configFileMap
     * @throws IOException
     */
    public static void createProjectConfigFiles(String projectPath, Map<String, String> configFileMap) throws IOException {
        StringBuilder sb;
        //创建配置文件
        if (configFileMap != null && !configFileMap.isEmpty()) {
            String filePath;
            for (Map.Entry<String, String> entry : configFileMap.entrySet()) {
                filePath = projectPath + "//src//main//resources" + entry.getKey();
                sb = new StringBuilder(Optional.fromNullable(entry.getValue()).or("没有配置，所以是空的"));
                FileTools.writeToFile(filePath, sb.toString());
            }
        }
    }

    /**
     * <Title>createSpringMvcInterceptorXml</Title>
     * <Description> 创建拦截器配置文件</Description>
     *
     * @param projectPath, projectBasicInfo, childProjectInfo
     * @return void
     * @throws IOException
     */
    public static void createSpringMvcInterceptorXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        String interceptorFilePath = projectPath + "//src//main//resources//spring//spring-mvc-interceptor.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append(" <beans xmlns=\"http://www.springframework.org/schema/beans\"\n");
        sb.append("	 xmlns:util=\"http://www.springframework.org/schema/util\" \n");
        sb.append("	 xmlns:aop=\"http://www.springframework.org/schema/aop\" \n");
        sb.append("	 xmlns:context=\"http://www.springframework.org/schema/context\" \n");
        sb.append("	 xmlns:tx=\"http://www.springframework.org/schema/tx\" \n");
        sb.append("	 xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
        sb.append("	 xmlns:mongo=\"http://www.springframework.org/schema/data/mongo\" \n");
        sb.append("	 xmlns:task=\"http://www.springframework.org/schema/task\" \n");
        sb.append("	 xmlns:mvc=\"http://www.springframework.org/schema/mvc\" \n");
        sb.append("	 xsi:schemaLocation=\"http://www.springframework.org/schema/aop \n");
        sb.append("		 http://www.springframework.org/schema/aop/spring-aop.xsd \n");
        sb.append("		 http://www.springframework.org/schema/beans \n");
        sb.append("		 http://www.springframework.org/schema/beans/spring-beans.xsd \n");
        sb.append("		 http://www.springframework.org/schema/context \n");
        sb.append("		 http://www.springframework.org/schema/context/spring-context.xsd \n");
        sb.append("		 http://www.springframework.org/schema/tx \n");
        sb.append("		 http://www.springframework.org/schema/tx/spring-tx.xsd \n");
        sb.append("		 http://www.springframework.org/schema/task \n");
        sb.append("		 http://www.springframework.org/schema/task/spring-task-3.1.xsd \n");
        sb.append("		 http://www.springframework.org/schema/util \n");
        sb.append("		 http://www.springframework.org/schema/util/spring-util-3.1.xsd \n");
        sb.append("		 http://www.springframework.org/schema/mvc \n");
        sb.append("		 http://www.springframework.org/schema/mvc/spring-mvc-3.1.xsd\">\n");
        sb.append("  <mvc:interceptors>\n");
        sb.append("  	<mvc:interceptor>\n");
        sb.append("  		<mvc:mapping path=\"/**/*\" /> \n");
        sb.append("  		<bean class=\"" + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".interceptor.GomeUserLoginInterceptor\">\n");
        sb.append("  			<property name=\"allowUrls\">\n");
        sb.append("  				<list>\n");
        sb.append("  					<value>result/error404.dhtml</value> \n");
        sb.append("  					<value>result/error500.dhtml</value> \n");
        sb.append("  					<value>tests/tests.dhtml</value> \n");
        sb.append("  				</list>\n");
        sb.append("  			</property>\n");
        sb.append("  		</bean>\n");
        sb.append("  	</mvc:interceptor>\n");
        sb.append("  </mvc:interceptors>\n");
        sb.append("</beans>\n");
        FileTools.writeToFile(interceptorFilePath, sb.toString());
    }


    public static void createWebXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        String configFileName = childProjectInfo.getConfigFileName();
        if (StringUtils.isBlank(configFileName)) {
            configFileName = projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + ".properties";
        }
        //创建web.xml文件
        String webXmlPath = projectPath + "//src//main//webapp//WEB-INF//web.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
        sb.append("	xmlns=\"http://java.sun.com/xml/ns/javaee\" \n");
        sb.append("	xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" \n");
        sb.append("	version=\"2.5\" metadata-complete=\"true\">\n");
        sb.append("  <context-param>\n");
        sb.append("  	<param-name>contextConfigLocation</param-name> \n");
        sb.append("  	<param-value>classpath:spring.xml</param-value> \n");
        sb.append("  </context-param>\n");
        sb.append("  <context-param>\n");
        sb.append("  	<param-name>propertiesConfigLocation</param-name> \n");
        sb.append("  	<param-value>/props/" + configFileName + "</param-value>\n");
        sb.append("  </context-param>\n");
        sb.append("  <listener>\n");
        sb.append("  	<listener-class>org.springframework.web.util.IntrospectorCleanupListener</listener-class> \n");
        sb.append("  </listener>\n");
        sb.append("  <listener>\n");
        sb.append("  	<listener-class>com.gomeplus.frame.listener.PropertiesConfigListener</listener-class> \n");
        sb.append("  </listener>\n");
        if (!"020,030".contains(childProjectInfo.getProjectType())) {
            sb.append("  <listener>\n");
            sb.append("  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class> \n");
            sb.append("  </listener>\n");
        }
        if ("020,030".contains(childProjectInfo.getProjectType())) {
            //web项目
            sb.append("  <servlet>\n");
            sb.append("  	<servlet-name>springmvc</servlet-name> \n");
            sb.append("  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\n");
            sb.append("  	<init-param>\n");
            sb.append("  		<param-name>publishContext</param-name> \n");
            sb.append("  		<param-value>false</param-value> \n");
            sb.append("  	</init-param>\n");
            sb.append("  	<init-param>\n");
            sb.append("  		<param-name>contextConfigLocation</param-name> \n");
            sb.append("  		<param-value>classpath:spring.xml</param-value> \n");
            sb.append("  	</init-param>\n");
            sb.append("  	<load-on-startup>1</load-on-startup> \n");
            sb.append("  </servlet>\n");
            sb.append("  <servlet-mapping>\n");
            sb.append("  	<servlet-name>springmvc</servlet-name> \n");
            sb.append("  	<url-pattern>*.dhtml</url-pattern> \n");
            sb.append(" 	 <url-pattern>*.json</url-pattern> \n");
            sb.append("  </servlet-mapping>\n");
            sb.append("  <filter>\n");
            sb.append("  	<filter-name>HiddenHttpMethodFilter</filter-name> \n");
            sb.append("  	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class> \n");
            sb.append("  	<async-supported>true</async-supported> \n");
            sb.append("  </filter>\n");
            sb.append("  <filter-mapping>\n");
            sb.append("  	<filter-name>HiddenHttpMethodFilter</filter-name> \n");
            sb.append("  	<servlet-name>springmvc</servlet-name> \n");
            sb.append("  </filter-mapping>\n");
            sb.append("  <filter>\n");
            sb.append("  	<filter-name>EncodingFilter</filter-name> \n");
            sb.append("  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class> \n");
            sb.append("  	<async-supported>true</async-supported> \n");
            sb.append("  	<init-param>\n");
            sb.append("  		<param-name>encoding</param-name> \n");
            sb.append("  		<param-value>UTF-8</param-value> \n");
            sb.append(" 	</init-param>\n");
            sb.append("  </filter>\n");
            sb.append("  <filter-mapping>\n");
            sb.append("  	<filter-name>EncodingFilter</filter-name> \n");
            sb.append("  	<url-pattern>/*</url-pattern> \n");
            sb.append("  </filter-mapping>\n");
            sb.append("  <error-page>\n");
            sb.append("  	<error-code>404</error-code> \n");
            sb.append("  	<location>/result/404error.dhtml</location> \n");
            sb.append("  </error-page>\n");
            sb.append("  <error-page>\n");
            sb.append("  	<error-code>500</error-code> \n");
            sb.append("  	<location>/result/500error.dhtml</location> \n");
            sb.append("  </error-page>\n");
        }
        sb.append("</web-app>\n");
        FileTools.writeToFile(webXmlPath, sb.toString());
    }
}
