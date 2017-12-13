/**   
 * @Title: UserLoginController.java 
 * @Package com.gome.mammon.admin.controller 
 * @Description: 用户登录控制器 
 * @author GOME
 * @date 2016年2月4日 上午10:50:13 
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
 * @ClassName: UserLoginController
 * @Description: 用户登录控制器
 * @author GOME
 * @date 2016年2月4日 上午10:50:13
 */
@Controller
@RequestMapping("login")
public class UserLoginController extends AbstractAdminController {

	@RequestMapping("/login")
	public String init(Model model, HttpServletRequest request, HttpServletResponse response){
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return "login";
	}

	@RequestMapping("/loginCheck")
	public String defaults(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "default";
	}

}
