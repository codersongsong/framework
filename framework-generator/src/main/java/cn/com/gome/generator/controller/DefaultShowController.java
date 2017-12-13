/**   
* @Title: DefaultShowController.java 
* @Package com.gome.login.admin.controller 
* @Description: 登录后的页面展示
* @author GOME
* @date 2017年1月7日 上午10:19:57 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gomeplus.frame.controller.AbstractAdminController;

/** 
 * @ClassName: DefaultShowController 
 * @Description: 登录后的页面展示
 * @author GOME
 * @date 2017年1月7日 上午10:19:57  
 */
@Controller
@RequestMapping("/defaultshow")
public class DefaultShowController extends AbstractAdminController{

	@RequestMapping("/show")
	public String init(Model model,HttpServletRequest request, HttpServletResponse response) {
		return "show";
	}
}
