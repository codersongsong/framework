/**   
 * @Title: UserLoginInterceptor.java 
 * @Package cn.com.gome.frame.interceptor 
 * @Description: 用户登录校验拦截器 
 * @author GOME
 * @date 2015年2月26日 下午4:38:50 
 * @company cn.com.gome
 * @version V1.0   
 */

package cn.com.gome.generator.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: UserLoginInterceptor
 * @Description: 用户登录校验拦截器
 * @author GOME
 * @date 2015年2月26日 下午4:38:50
 */
public class UserLoginInterceptor implements HandlerInterceptor {

	private String[] allowUrls;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String basePath = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
		request.setAttribute("basePath", basePath);
		return true;
	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

}
