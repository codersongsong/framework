package com.gomeplus.framework.admin.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import redis.Gcache;

public class GomeUserLoginInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger("GomeUserLoginInterceptor");
	
	private String[] allowUrls = null;
	
	@Resource
    private Gcache jjcsGcache;
	
	
	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
		request.setAttribute("basePath", basePath);
		String uri = request.getRequestURI();
		logger.info("权限校验,uri:{}",uri);
		for(String url : allowUrls){
			if (uri.contains(url))
				return true;
		}
		
		UserLoginBean userLoginBean = CookiesUtils.getCookie(request, response);
		if (userLoginBean == null) {
			logger.info("未登录");
			response.sendRedirect(basePath + "login/login_show.dhtml");
			return false;
		}
		
		//校验是否登录
		if(!"admin".equals(userLoginBean.getUserAccount()) && !check(uri,userLoginBean)){
			response.sendRedirect(basePath + "login/limit_show.dhtml");
			return false;
		}
		
		request.setAttribute(CookiesUtils.USER_LOGIN_BEAN, userLoginBean);
		return true;
	}
	
	private boolean check(String uri,UserLoginBean userLoginBean){
		try{
			//校验是否登录
			String userMenuLimit = jjcsGcache.get("USER_MENU_LIMIT_" + userLoginBean.getUserId());
			
			JSONArray jas = JSONArray.fromObject(userMenuLimit);
			JSONObject jo = null;
			String reqUrl = uri.split("\\.")[0];
			String[] reqUrls = reqUrl.split("\\/");
			reqUrl = reqUrls[reqUrls.length-2] + "/" + reqUrls[reqUrls.length-1];
			for(int i =0;i<jas.size();i++){
				jo = (JSONObject) jas.get(i);
				if(jo.getString("limitTal").indexOf(reqUrl) > -1){//有权限
					return true;
				}
			}
			return false;		
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
