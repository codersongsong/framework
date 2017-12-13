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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author GOME
 * @ClassName: MainProjectCreateLogic
 * @Description: 主项目创建
 * @date 2017年5月26日 下午2:49:05
 */
@Service
public class ChildProjectAdminCreateLogic extends ChildProjectCreateAbstractLogic {
    private Logger logger = LoggerFactory.getLogger(ChildProjectAdminCreateLogic.class);

    @Override
    @PostConstruct
    protected void initConfig() {
        this.isCreateSpringXml = true;
        this.isCreateSpringMvcInterceptor = true;
        this.isCreateWebXml = true;
        this.isCreateXmlConfigFiles = true;
        this.isCreateDemoDubboConsumerXml = true;
    }

    @Override
    protected List<String> getNecessaryFolder(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) {
        List<String> folders = Lists.newArrayListWithExpectedSize(14);
        folders.add(projectPath + "//src//main//resources//spring");
        folders.add(projectPath + "//src//main//resources//spring//consumer");
        folders.add(projectPath + "//src//main//webapp//js");
        folders.add(projectPath + "//src//main//webapp//css");
        folders.add(projectPath + "//src//main//webapp//images");
        folders.add(projectPath + "//src//main//webapp//WEB-INF//page//error");
        folders.add(projectPath + "//src//main//resources//props//dev");
        folders.add(projectPath + "//src//main//resources//props//uat");
        folders.add(projectPath + "//src//main//resources//props//pre");
        folders.add(projectPath + "//src//main//resources//props//live");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//interceptor");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//util");
        folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//logic//impl");
        return folders;
    }

    @Override
    protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
        super.createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
    }

    @Override
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
        devProperties.append("systemDomain=uatgfs.com.cn\n" +
                "login_show=http://login.uatgfs.com.cn:7047/login/login_show.dhtml\n" +
                "limit_show=http://login.uatgfs.com.cn:7047/login/limit_show.dhtml\n");
        uatProperties.append("systemDomain=uatgfs.com.cn\n" +
                "login_show=http://login.uatgfs.com.cn:7047/login/login_show.dhtml\n" +
                "limit_show=http://login.uatgfs.com.cn:7047/login/limit_show.dhtml\n");
        preProperties.append("systemDomain=pregfs.com.cn\n" +
                "login_show=http://login.pregfs.com.cn:7047/login/login_show.dhtml\n" +
                "limit_show=http://login.pregfs.com.cn:7047/login/limit_show.dhtml\n");
        liveProperties.append("systemDomain=livegfs.com.cn\n" +
                "login_show=http://login.livegfs.com.cn:6805/login/login_show.dhtml\n" +
                "limit_show=http://login.livegfs.com.cn:6805/login/limit_show.dhtml\n");
        properties.append("cookieOverdue=1800000\n" +
                "systemDomain=${systemDomain}\n" +
                "login_show=${login_show}\n" +
                "limit_show=${limit_show}\n");
        if (CollectionUtils.isNotEmpty(propertieConfigs) && propertieConfigs.size() > 0) {
            for (int i = 0; i < propertieConfigs.size(); i++) {
                devProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n")
                        .append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvDev()).append("\n");
                uatProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n")
                        .append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvUat()).append("\n");
                preProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n")
                        .append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvPre()).append("\n");
                liveProperties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n")
                        .append(propertieConfigs.get(i).getConfigName()).append("=").append(propertieConfigs.get(i).getEnvLive()).append("\n");
                properties.append("#").append(propertieConfigs.get(i).getConfigDescription()).append("\n")
                        .append(propertieConfigs.get(i).getConfigName()).append("=${").append(propertieConfigs.get(i).getConfigName()).append("}\n");
            }
        }
        FileTools.writeToFile(devPropertieFile, devProperties.toString());
        FileTools.writeToFile(uatPropertieFile, uatProperties.toString());
        FileTools.writeToFile(prePropertieFile, preProperties.toString());
        FileTools.writeToFile(livePropertieFile, liveProperties.toString());
        FileTools.writeToFile(propertieFile, properties.toString());
    }

    @Override
    protected void createWebXml(String projectPath, TblProjectBasicInfo projectBasicInfo, TblChildProjectInfo childProjectInfo) throws IOException {
        String configFileName = childProjectInfo.getConfigFileName();
        if (StringUtils.isBlank(configFileName)) {
            configFileName = projectBasicInfo.getProjectEng() + "-" + childProjectInfo.getChildProjectEng() + ".properties";
        }
        String webXmlPath = projectPath + "//src//main//webapp//WEB-INF//web.xml";
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
        sb.append("	xmlns=\"http://java.sun.com/xml/ns/javaee\" \n");
        sb.append("	xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" \n");
        sb.append("	version=\"2.5\" metadata-complete=\"true\">\n");
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
        sb.append("  </filter>\n");
        sb.append("  <filter-mapping>\n");
        sb.append("  	<filter-name>HiddenHttpMethodFilter</filter-name> \n");
        sb.append("  	<servlet-name>springmvc</servlet-name> \n");
        sb.append("  </filter-mapping>\n");
        sb.append("  <filter>\n");
        sb.append("  	<filter-name>EncodingFilter</filter-name> \n");
        sb.append("  	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class> \n");
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
        sb.append("</web-app>\n");
        FileTools.writeToFile(webXmlPath, sb.toString());
    }

    @Override
    protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo) throws IOException {
        String gomeUserLoginInterceptorFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//interceptor//GomeUserLoginInterceptor.java";
        StringBuilder sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".interceptor;\n\n");
        sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n");
        sb.append("import " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".util.CookiesUtils;\n");
        sb.append("import net.sf.json.JSONArray;\n" +
                "import net.sf.json.JSONObject;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.web.servlet.HandlerInterceptor;\n" +
                "import org.springframework.web.servlet.ModelAndView;\n" +
                "import redis.Gcache;\n" +
                "\n" +
                "import javax.annotation.Resource;\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "\n");
        sb.append(getClassDescription("GomeUserLoginInterceptor", "登陆拦截器"));
        sb.append("public class GomeUserLoginInterceptor implements HandlerInterceptor {\n" +
                "\n" +
                "    private Logger logger = LoggerFactory.getLogger(GomeUserLoginInterceptor.class);\n" +
                "\n" +
                "    private String[] allowUrls = null;\n" +
                "\n" +
                "    @Resource\n" +
                "    private Gcache fundGcache;\n" +
                "\n" +
                "\n" +
                "    public String[] getAllowUrls() {\n" +
                "        return allowUrls;\n" +
                "    }\n" +
                "\n" +
                "    public void setAllowUrls(String[] allowUrls) {\n" +
                "        this.allowUrls = allowUrls;\n" +
                "    }\n" +
                "\n" +
                "    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n" +
                "        String basePath = request.getScheme() + \"://\" + request.getServerName() + \":\" + request.getServerPort() + request.getContextPath() + \"/\";\n" +
                "        request.setAttribute(\"basePath\", basePath);\n" +
                "        String uri = request.getRequestURI();\n" +
                "        logger.info(\"权限校验,uri:{}\", uri);\n" +
                "        for (String url : allowUrls) {\n" +
                "            if (uri.contains(url)) {\n" +
                "                return true;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        UserLoginBean userLoginBean = CookiesUtils.getCookie(request, response);\n" +
                "        if (userLoginBean == null) {\n" +
                "            logger.info(\"未登录\");\n" +
                "            //response.sendRedirect(basePath + \"login/login_show.dhtml\");\n" +
                "            response.sendRedirect(GlobalApplicationCache.getInstance().getStr(\"login_show\"));\n" +
                "            return false;\n" +
                "        }else{\n" +
                "             logger.info(\"当前操作用户：{}，校验权限：{}\",userLoginBean.getUserId(),uri);\n" +
                "\t\t\t\n" +
                "\t\t\t//校验是否登录\n" +
                "\t\t\tif(!\"admin\".equals(userLoginBean.getUserAccount()) && !check(uri,userLoginBean)){\n" +
                "\t\t\t\tresponse.sendRedirect(GlobalApplicationCache.getInstance().getStr(\"limit_show\"));\n" +
                "\t\t\t\treturn false;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\trequest.setAttribute(CookiesUtils.USER_LOGIN_BEAN, userLoginBean);\n" +
                "\t\t\treturn true;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    private boolean check(String uri, UserLoginBean userLoginBean) {\n" +
                "        try {\n" +
                "            //校验是否登录\n" +
                "            String userMenuLimit = fundGcache.get(\"USER_MENU_LIMIT_\" + userLoginBean.getUserId());\n" +
                "\n" +
                "            JSONArray jas = JSONArray.fromObject(userMenuLimit);\n" +
                "            JSONObject jo = null;\n" +
                "            String reqUrl = uri.split(\"\\\\.\")[0];\n" +
                "            String[] reqUrls = reqUrl.split(\"\\\\/\");\n" +
                "            reqUrl = reqUrls[reqUrls.length - 2] + \"/\" + reqUrls[reqUrls.length - 1];\n" +
                "            for (int i = 0; i < jas.size(); i++) {\n" +
                "                jo = (JSONObject) jas.get(i);\n" +
                "                if (jo.getString(\"limitTal\").indexOf(reqUrl) > -1) {//有权限\n" +
                "                    return true;\n" +
                "                }\n" +
                "            }\n" +
                "            return false;\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "            return true;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,\n" +
                "                           ModelAndView modelAndView) throws Exception {\n" +
                "    }\n" +
                "\n" +
                "    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)\n" +
                "            throws Exception {\n" +
                "    }\n" +
                "\n" +
                "}\n");
        FileTools.writeToFile(gomeUserLoginInterceptorFilePath, sb.toString());
        String userLoginControllerFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller//UserLoginController.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "."+childProjectInfo.getChildProjectEng()+".controller;\n");
        sb.append("\n");
        sb.append("import javax.servlet.http.HttpServletRequest;\n");
        sb.append("import javax.servlet.http.HttpServletResponse;\n");
        sb.append("\n");
        sb.append("import org.springframework.stereotype.Controller;\n");
        sb.append("import org.springframework.ui.Model;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("import com.gomeplus.frame.controller.AbstractAdminController;\n");
        sb.append("\n");
        sb.append(getClassDescription("UserLoginController", "登陆控制器"));
        sb.append("@Controller\n");
        sb.append("@RequestMapping(\"/login\")\n");
        sb.append("public class UserLoginController extends AbstractAdminController{\n");
        sb.append("\n");
        sb.append("	@RequestMapping(\"/login\")\n");
        sb.append("	public String login(Model model,HttpServletRequest request, HttpServletResponse response) {\n");
        sb.append("		\n");
        sb.append("		return \"login\";\n");
        sb.append("	}\n");
        sb.append("\n");
        sb.append("	@RequestMapping(\"/loginCheck\")\n");
        sb.append("	public String loginCheck(Model model,HttpServletRequest request, HttpServletResponse response) {\n");
        sb.append("		\n");
        sb.append("		return \"default\";\n");
        sb.append("	}\n");
        sb.append("\n");
        sb.append("}\n");
        FileTools.writeToFile(userLoginControllerFilePath, sb.toString());
        String adminDictionaryControllerFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller//AdminDictionaryController.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "."+childProjectInfo.getChildProjectEng()+".controller;\n");
        sb.append("\n");
        sb.append("import java.util.ArrayList;\n");
        sb.append("import java.util.Collections;\n");
        sb.append("import java.util.List;\n");
        sb.append("\n");
        sb.append("import javax.servlet.http.HttpServletRequest;\n");
        sb.append("import javax.servlet.http.HttpServletResponse;\n");
        sb.append("\n");
        sb.append("import net.sf.json.JSONArray;\n");
        sb.append("\n");
        sb.append("import org.springframework.stereotype.Controller;\n");
        sb.append("import org.springframework.web.bind.annotation.ModelAttribute;\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        sb.append("\n");
        sb.append("import com.gomeplus.frame.cache.DictionaryVo;\n");
        sb.append("import com.gomeplus.frame.cache.GlobalDataDictionaryCache;\n");
        sb.append("import com.gomeplus.frame.controller.AbstractAdminController;\n");
        sb.append("\n");
        sb.append(getClassDescription("AdminDictionaryController", "数据字典获取控制器"));
        sb.append("@Controller\n");
        sb.append("@RequestMapping(\"/dictionary\")\n");
        sb.append("public class AdminDictionaryController extends AbstractAdminController{\n");
        sb.append("\n");
        sb.append("	@RequestMapping(\"/select\")\n");
        sb.append("	public void selects(HttpServletRequest request, HttpServletResponse response) {\n");
        sb.append("		List<DictionaryVo> list = new ArrayList<DictionaryVo>();\n");
        sb.append("		try {\n");
        sb.append("			String key = request.getParameter(\"key\");\n");
        sb.append("			List<DictionaryVo> obj = GlobalDataDictionaryCache.getInstance().getIdValue(key);\n");
        sb.append("			if (obj != null) {\n");
        sb.append("				Collections.addAll(list,  new  DictionaryVo[obj.size()]);   \n");
        sb.append("				Collections.copy(list, obj);   \n");
        sb.append("				DictionaryVo idvalue = new DictionaryVo(null,\"全部\",true);\n");
        sb.append("				list.add(0, idvalue);\n");
        sb.append("			}\n");
        sb.append("			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");
        sb.append("	}\n");
        sb.append("	\n");
        sb.append("	@RequestMapping(\"/selectv\")\n");
        sb.append("	public void selectsv(HttpServletRequest request, HttpServletResponse response) {\n");
        sb.append("		try {\n");
        sb.append("			String key = request.getParameter(\"key\");\n");
        sb.append("			List<DictionaryVo> obj = GlobalDataDictionaryCache.getInstance().getIdValue(key);\n");
        sb.append("			ajaxJsonMessage(response,JSONArray.fromObject(obj).toString());\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");
        sb.append("	}\n");
        sb.append("	\n");
        sb.append("	@RequestMapping(\"/selectl\")\n");
        sb.append("	public void selectl(HttpServletRequest request, HttpServletResponse response) {\n");
        sb.append("		List<DictionaryVo> list = new ArrayList<DictionaryVo>();\n");
        sb.append("		try {\n");
        sb.append("			String key = request.getParameter(\"key\");\n");
        sb.append("			List<DictionaryVo> obj = GlobalDataDictionaryCache.getInstance().getIdValue(key);\n");
        sb.append("			if (obj != null) {\n");
        sb.append("				Collections.addAll(list,  new  DictionaryVo[obj.size()]);   \n");
        sb.append("				Collections.copy(list, obj);   \n");
        sb.append("				DictionaryVo idvalue = new DictionaryVo(null,\"无\",true);\n");
        sb.append("				list.add(0, idvalue);\n");
        sb.append("			}\n");
        sb.append("			ajaxJsonMessage(response,JSONArray.fromObject(list).toString());\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");
        sb.append("	}\n");
        sb.append("}\n");
        FileTools.writeToFile(adminDictionaryControllerFilePath, sb.toString());
        String cacheServiceImplFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//logic//impl//CacheServiceImpl.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "."+childProjectInfo.getChildProjectEng()+".logic.impl;\n");
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
        sb.append("import com.gomeplus.frame.cache.DictionaryVo;\n");
        sb.append("import com.gomeplus.frame.cache.GlobalDataDictionaryCache;\n");
        sb.append("\n");
        sb.append("import " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "."+childProjectInfo.getChildProjectEng()+".logic.CacheService;\n");
        sb.append("\n");
        sb.append(getClassDescription("CacheServiceImpl", "缓存参数初始化接口实现"));
        sb.append("@Service\n");
        sb.append("public class CacheServiceImpl implements CacheService {\n");
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
        FileTools.writeToFile(cacheServiceImplFilePath, sb.toString());

        String cacheServiceFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//logic//CacheService.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "."+childProjectInfo.getChildProjectEng()+".logic;\n");
        sb.append("\n");
        sb.append(getClassDescription("CacheService", "缓存参数初始化接口"));
        sb.append("public interface CacheService {\n");
        sb.append("\n");
        sb.append("	public void cacheUpdate();\n");
        sb.append("}\n");
        FileTools.writeToFile(cacheServiceFilePath, sb.toString());
        FileTools.writeToFile(projectPath + "//src//main//webapp//WEB-INF//page//error//404.ftl", "404");
        FileTools.writeToFile(projectPath + "//src//main//webapp//WEB-INF//page//error//500.ftl", "500");
        String resultControllerFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller//ResultController.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".controller;\n");
        sb.append("\n" +
                "import com.gomeplus.frame.controller.AbstractAdminController;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.ui.Model;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "\n");
        sb.append(getClassDescription("ResultController", "异常结果控制器"));
        sb.append("@Controller\n" +
                "@RequestMapping(\"/result\")\n" +
                "public class ResultController extends AbstractAdminController {\n" +
                "\n" +
                "    @RequestMapping(\"/404error\")\n" +
                "    public String error404(Model model, HttpServletRequest request, HttpServletResponse response) {\n" +
                "\n" +
                "        return \"error/404\";\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(\"/500error\")\n" +
                "    public String error500(Model model, HttpServletRequest request, HttpServletResponse response) {\n" +
                "\n" +
                "        return \"error/500\";\n" +
                "    }\n" +
                "\n" +
                "}");
        FileTools.writeToFile(resultControllerFilePath, sb.toString());
        String cookieUtilsFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//util//CookiesUtils.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".util;\n\n");
        sb.append("import " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".interceptor.UserLoginBean;\n");
        sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n" +
                "import com.gomeplus.security.base64.Base64Util;\n" +
                "import net.sf.json.JSONObject;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "\n" +
                "import javax.servlet.http.Cookie;\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "import java.io.UnsupportedEncodingException;\n" +
                "\n");
        sb.append(getClassDescription("CookiesUtils", "cookie添加、删除、查询操作类"));
        sb.append("public class CookiesUtils {\n" +
                "\tprivate static final Logger logger = LoggerFactory.getLogger(CookiesUtils.class);\n" +
                "\n" +
                "\tpublic static String key = \"finance_user\";\n" +
                "\n" +
                "\tpublic static String charset = \"UTF-8\";\n" +
                "\n" +
                "\tpublic static String USER_LOGIN_BEAN = \"userLoginBean\";\n" +
                "\n" +
                "\tpublic static int cookieOverdue = Integer.parseInt((String) GlobalApplicationCache.getInstance().get(\"cookieOverdue\")) ;\n" +
                "\t\n" +
                "\tpublic static String systemDomain = GlobalApplicationCache.getInstance().getStr(\"systemDomain\");\n" +
                "\n" +
                "\tpublic static boolean addCookie(UserLoginBean userLoginBean, HttpServletResponse response) {\n" +
                "\t\ttry {\n" +
                "\t\t\tJSONObject json = JSONObject.fromObject(userLoginBean);\n" +
                "\t\t\tString content = Base64Util.encodeMessage(json.toString().getBytes(charset));\n" +
                "\t\t\tCookie cookie = new Cookie(key, content);\n" +
                "\t\t\tcookie.setPath(\"/\");\n" +
                "\t\t\tcookie.setDomain(systemDomain);\n" +
                "\t\t\tcookie.setMaxAge(cookieOverdue);\n" +
                "\t\t\tresponse.addCookie(cookie);\n" +
                "\t\t\treturn true;\n" +
                "\t\t} catch (UnsupportedEncodingException e) {\n" +
                "\t\t\te.printStackTrace();\n" +
                "\t\t}\n" +
                "\t\treturn false;\n" +
                "\t}\n" +
                "\n" +
                "\tpublic static UserLoginBean getCookie(HttpServletRequest request, HttpServletResponse response) {\n" +
                "\t\ttry {\n" +
                "\t\t\tCookie[] cookies = request.getCookies();\n" +
                "\t\t\tif (cookies != null) {\n" +
                "\t\t\t\tCookie userCookie = null;\n" +
                "\t\t\t\tfor (Cookie cookie : cookies) {\n" +
                "\t\t\t\t\tif (cookie.getName().equals(key)) {\n" +
                "\t\t\t\t\t\tuserCookie = cookie;\n" +
                "\t\t\t\t\t\tif (request.getServerName().equals(cookie.getDomain())) {\n" +
                "\t\t\t\t\t\t\tbreak;\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\tif (userCookie != null) {\n" +
                "\t\t\t\t\tString value = userCookie.getValue();\n" +
                "\t\t\t\t\tvalue = new String(Base64Util.decodeMessage(value), charset);\n" +
                "\t\t\t\t\tuserCookie.setPath(\"/\");\n" +
                "\t\t\t\t\tuserCookie.setMaxAge(cookieOverdue);\n" +
                "\t\t\t\t\tuserCookie.setDomain(systemDomain);\n" +
                "\t\t\t\t\tresponse.addCookie(userCookie);\n" +
                "\t\t\t\t\tJSONObject jSONObject = JSONObject.fromObject(value);\n" +
                "\t\t\t\t\tUserLoginBean userLoginBean = (UserLoginBean) JSONObject.toBean(jSONObject, UserLoginBean.class);\n" +
                "\t\t\t\t\treturn userLoginBean;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t} catch (UnsupportedEncodingException e) {\n" +
                "\t\t\tlogger.error(\"登陆获取cookie异常\", e);\n" +
                "\t\t}\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "\n" +
                "\tpublic static boolean delCookie(HttpServletRequest request, HttpServletResponse response) {\n" +
                "\t\tCookie[] cookies = request.getCookies();\n" +
                "\t\tif (cookies != null) {\n" +
                "\t\t\tCookie userCookie = null;\n" +
                "\t\t\tfor (Cookie cookie : cookies) {\n" +
                "\t\t\t\tif (cookie.getName().equals(key)) {\n" +
                "\t\t\t\t\tuserCookie = cookie;\n" +
                "\t\t\t\t\tif (request.getServerName().equals(cookie.getDomain())) {\n" +
                "\t\t\t\t\t\tbreak;\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t\tif (userCookie != null) {\n" +
                "\t\t\t\tuserCookie.setPath(\"/\");\n" +
                "\t\t\t\tuserCookie.setMaxAge(0);\n" +
                "\t\t\t\tresponse.addCookie(userCookie);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\treturn false;\n" +
                "\t}\n" +
                "\n" +
                "}\n");
        FileTools.writeToFile(cookieUtilsFilePath, sb.toString());
        String userLoginBeanFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//interceptor//UserLoginBean.java";
        sb = new StringBuilder();
        sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".interceptor;\n\n");
        sb.append("import java.io.Serializable;\n\n");
        sb.append(getClassDescription("UserLoginBean", "用户登录信息bean"));
        sb.append("public class UserLoginBean implements Serializable {\n" +
                "\n" +
                "\tprivate static final long serialVersionUID = 1047625413715279856L;\n" +
                "\n" +
                "\t/** 用户Id */\n" +
                "\tprivate String userId;\n" +
                "\n" +
                "\t/** 登录账户 */\n" +
                "\tprivate String userAccount;\n" +
                "\n" +
                "\t/** 呢称 */\n" +
                "\tprivate String userName;\n" +
                "\n" +
                "\t/** 真实姓名 */\n" +
                "\tprivate String trueName;\n" +
                "\n" +
                "\tpublic UserLoginBean() {\n" +
                "\t\tsuper();\n" +
                "\t}\n" +
                "\n" +
                "\t/**\n" +
                "\t * <p>\n" +
                "\t * Title: UserLoginBean\n" +
                "\t * </p>\n" +
                "\t * <p>\n" +
                "\t * Description: 用户登录信息实例化构造函数\n" +
                "\t * </p>\n" +
                "\t * \n" +
                "\t * @param userId 用户Id\n" +
                "\t * @param userAccount 登录账户\n" +
                "\t * @param userName 呢称\n" +
                "\t * @param trueName 真实姓名\n" +
                "\t */\n" +
                "\tpublic UserLoginBean(String userId, String userAccount, String userName, String trueName) {\n" +
                "\t\tsuper();\n" +
                "\t\tthis.userId = userId;\n" +
                "\t\tthis.userAccount = userAccount;\n" +
                "\t\tthis.userName = userName;\n" +
                "\t\tthis.trueName = trueName;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 登录账户 */\n" +
                "\tpublic String getUserAccount() {\n" +
                "\t\treturn userAccount;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 登录账户 */\n" +
                "\tpublic void setUserAccount(String userAccount) {\n" +
                "\t\tthis.userAccount = userAccount;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 用户Id */\n" +
                "\tpublic String getUserId() {\n" +
                "\t\treturn userId;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 用户Id */\n" +
                "\tpublic void setUserId(String userId) {\n" +
                "\t\tthis.userId = userId;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 呢称 */\n" +
                "\tpublic String getUserName() {\n" +
                "\t\treturn userName;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 呢称 */\n" +
                "\tpublic void setUserName(String userName) {\n" +
                "\t\tthis.userName = userName;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 真实姓名 */\n" +
                "\tpublic String getTrueName() {\n" +
                "\t\treturn trueName;\n" +
                "\t}\n" +
                "\n" +
                "\t/** 真实姓名 */\n" +
                "\tpublic void setTrueName(String trueName) {\n" +
                "\t\tthis.trueName = trueName;\n" +
                "\t}\n" +
                "\n" +
                "}\n");
        FileTools.writeToFile(userLoginBeanFilePath, sb.toString());
    }

    @Override
    protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {
        createStaticResourceFiles(projectPath);
    }

    /**
     * <Title>createStaticResourceFiles</Title>
     * <Description> 创建静态资源</Description>
     *
     * @param projectPath
     * @return void
     * @throws
     */
    private void createStaticResourceFiles(String projectPath) {
        URL resource = this.getClass().getResource("");
        String path = resource.getPath();
        String _projectPath = path.replace("cn/com/gome/generator/logic/", "template").replaceAll("/", "//");
        String filePath = _projectPath + "//css//";
        String copyPath = projectPath + "//src//main//webapp//";
        FileTools.copyFolderWithSelf(filePath, copyPath);

        filePath = _projectPath + "//js//";
        copyPath = projectPath + "//src//main//webapp//";
        FileTools.copyFolderWithSelf(filePath, copyPath);

        filePath = _projectPath + "//images//";
        copyPath = projectPath + "//src//main//webapp//";
        FileTools.copyFolderWithSelf(filePath, copyPath);

        filePath = _projectPath + "//page//login.ftl";
        copyPath = projectPath + "//src//main//webapp//WEB-INF//page//login.ftl";
        FileTools.CopySingleFile(filePath, copyPath);

        filePath = _projectPath + "//page//default.ftl";
        copyPath = projectPath + "//src//main//webapp//WEB-INF//page//default.ftl";
        FileTools.CopySingleFile(filePath, copyPath);

        filePath = _projectPath + "//page//success.ftl";
        copyPath = projectPath + "//src//main//webapp//WEB-INF//page//success.ftl";
        FileTools.CopySingleFile(filePath, copyPath);
    }
}
