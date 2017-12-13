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
 * @ClassName: MainProjectCreateLogic 
 * @Description: 主项目创建
 * @author GOME
 * @date 2017年5月26日 下午2:49:05  
 */
@Service
public class ChildProjectApiCreateLogic extends ChildProjectCreateAbstractLogic {
	private Logger logger = LoggerFactory.getLogger(ChildProjectApiCreateLogic.class);

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
		List<String> folders = Lists.newArrayListWithExpectedSize(8);
		folders.add(projectPath + "//src//main//resources//spring/consumer");
		folders.add(projectPath + "//src//main//resources//props//dev");
		folders.add(projectPath + "//src//main//resources//props//uat");
		folders.add(projectPath + "//src//main//resources//props//live");
		folders.add(projectPath + "//src//main//resources//props//pre");
		folders.add(projectPath + "//src//main//webapp//WEB-INF//page");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//util");
		folders.add(projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//interceptor");
		return folders;
	}

	@Override
	protected void createXmlConfigFiles(String projectPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo, Map<String, String> configFileMap) throws IOException {
		super.createXmlConfigFiles(projectPath, projectBasicInfo, childProjectInfo, configFileMap);
	}

	@Override
	protected void createDefaultJavaFiles(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo) throws IOException {
		StringBuilder sb = new StringBuilder();
		//创建登录拦截器
		String userLoginInterceptorFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//interceptor//GomeUserLoginInterceptor.java";
		sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".interceptor;\n");
		sb.append("\n");
		sb.append("import java.io.IOException;\n");
		sb.append("import javax.servlet.http.HttpServletRequest;\n");
		sb.append("import javax.servlet.http.HttpServletResponse;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.web.servlet.HandlerInterceptor;\n");
		sb.append("import org.springframework.web.servlet.ModelAndView;\n");
		sb.append("import com.gomeplus.frame.cache.GlobalApplicationCache;\n");
		sb.append("import com.gome.memberCore.lang.model.UserResult;\n");
		sb.append("import com.gome.sso.common.tools.SsoUserCookieTools;\n");
		sb.append("import com.gome.sso.model.UserCookie;\n");
		sb.append("\n");
		sb.append(getClassDescription("GomeUserLoginInterceptor", "登陆拦截器"));
		sb.append("public class GomeUserLoginInterceptor implements HandlerInterceptor{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("	\n");
		sb.append("	private String[] allowUrls = null;\n");
		sb.append("	\n");
		sb.append("	public String[] getAllowUrls() {\n");
		sb.append("		return allowUrls;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void setAllowUrls(String[] allowUrls) {\n");
		sb.append("		this.allowUrls = allowUrls;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n");
		sb.append("		try {\n");
		sb.append("			logger.info(\"校验登录信息\");\n");
		sb.append("			String requestURI = request.getRequestURI();\n");
		sb.append("			if(allowUrls != null && allowUrls.length > 0){\n");
		sb.append("				for(String temp : allowUrls){\n");
		sb.append("					if(requestURI.indexOf(temp) >= 0 )\n");
		sb.append("						return true;\n");
		sb.append("				}\n");
		sb.append("			}\n");
		sb.append("			UserResult<UserCookie> userResult = SsoUserCookieTools.checkIsLoginByCookie(request, response);\n");
		sb.append("			if(!userResult.isSuccess()){\n");
		sb.append("				//如果没有登录，跳转到登录页面\n");
		sb.append("				return false;\n");
		sb.append("			}else{\n");
		sb.append("				String userId = userResult.getBuessObj().getId();//用户ID\n");
		sb.append("				String userLogin = userResult.getBuessObj().getLogin();\n");
		sb.append("				String userNm = userLogin;//默认值为用户账户\n");
		sb.append("				request.setAttribute(\"userId\", userId);\n");
		sb.append("				request.setAttribute(\"userLogin\", userLogin);\n");
		sb.append("				request.setAttribute(\"userNm\", userNm);\n");
		sb.append("				logger.info(\"用户登录成功:userId=\" + userId + \",userLogin=\" + userLogin);\n");
		sb.append("				return true;\n");
		sb.append("			}\n");
		sb.append("		} catch (Exception e) {\n");
		sb.append("			logger.error(\"未登录，重定向异常\",e);\n");
		sb.append("			try {\n");
		sb.append("				response.sendRedirect((String)GlobalApplicationCache.getInstance().get(\"GOME_PAGE_URL\"));\n");
		sb.append("			} catch (IOException e1) {\n");
		sb.append("				logger.error(\"未登录，再次重定向异常\",e1);\n");
		sb.append("			}\n");
		sb.append("		}\n");
		sb.append("		logger.info(\"用户未登录\");\n");
		sb.append("		return false;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,\n");
		sb.append("			ModelAndView modelAndView) throws Exception {}\n");
		sb.append("\n");
		sb.append("	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)\n");
		sb.append("			throws Exception {}\n");
		sb.append("\n");
		sb.append("}\n");
		FileTools.writeToFile(userLoginInterceptorFilePath, sb.toString());
		//创建测试控制器和页面ftl
		String testsControllerFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller//TestsController.java";
		sb = new StringBuilder();
		sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".controller;\n");
		sb.append("\n");
		sb.append("import javax.servlet.http.HttpServletRequest;\n");
		sb.append("import javax.servlet.http.HttpServletResponse;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.stereotype.Controller;\n");
		sb.append("import org.springframework.ui.Model;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		sb.append("import com.gomeplus.frame.controller.AbstractApiController;\n");
		sb.append("\n");
		sb.append(getClassDescription("TestsController", "测试控制器"));
		sb.append("@Controller\n");
		sb.append("@RequestMapping(\"/login\")\n");
		sb.append("public class TestsController extends AbstractApiController{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("\n");
		sb.append("	@RequestMapping(\"/login\")\n");
		sb.append("	public void login(Model model,HttpServletRequest request, HttpServletResponse response) {\n");
		sb.append("		try {\n");
		sb.append("			ajaxJsonMessage(response,\"测试成功\");\n");
		sb.append("		} catch (Exception e) {\n");
		sb.append("			logger.error(\"测试接口访问异常常\", e);\n");
		sb.append("		}\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("}\n");
		FileTools.writeToFile(testsControllerFilePath, sb.toString());
		String resultControllerFilePath = projectPath + "//src//main//java//" + pachagesPath + "//" + projectBasicInfo.getProjectEng() + "//" + childProjectInfo.getChildProjectEng() + "//controller//ResultController.java";
		sb = new StringBuilder();
		sb.append("package " + projectBasicInfo.getPackages() + "." + projectBasicInfo.getProjectEng() + "." + childProjectInfo.getChildProjectEng() + ".controller;\n");
		sb.append("\n" +
				"import com.alibaba.fastjson.JSONObject;\n" +
				"import com.gomeplus.frame.controller.AbstractApiController;\n" +
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
				"public class ResultController extends AbstractApiController {\n" +
				"\n" +
				"    @RequestMapping(\"/404error\")\n" +
				"    public void error404(Model model, HttpServletRequest request, HttpServletResponse response) {\n" +
				"        JSONObject jsonObject = new JSONObject();\n" +
				"        jsonObject.put(\"code\", ERROR);\n" +
				"        jsonObject.put(\"msg\", \"404\");\n" +
				"        ajaxJsonMessage(response, jsonObject.toString());\n" +
				"    }\n" +
				"\n" +
				"    @RequestMapping(\"/500error\")\n" +
				"    public void error500(Model model, HttpServletRequest request, HttpServletResponse response) {\n" +
				"        JSONObject jsonObject = new JSONObject();\n" +
				"        jsonObject.put(\"code\", ERROR);\n" +
				"        jsonObject.put(\"msg\", \"500\");\n" +
				"        ajaxJsonMessage(response, jsonObject.toString());\n" +
				"    }\n" +
				"\n" +
				"}");
		FileTools.writeToFile(resultControllerFilePath, sb.toString());
	}

	@Override
	protected void selfModuleCreate(String projectPath, String pachagesPath, TblProjectBasicInfo projectBasicInfo,TblChildProjectInfo childProjectInfo)throws IOException {

	}
}
