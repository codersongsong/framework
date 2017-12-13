package cn.com.gome.generator.controller;

import cn.com.gome.generator.util.SendEmailUtil;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuxianzhao
 * @ClassName BugUploadController
 * @Description
 * @date 2017年09月26日 18:13
 */
@Controller
@RequestMapping("/bugUpload")
public class BugUploadController extends AbstractAdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping("/inits")
    public String init(Model model, HttpServletRequest request, HttpServletResponse response) {
        return "bugupload/inits";
    }

    @RequestMapping("commit")
    public String commit(HttpServletRequest request, Model model) {
        final String bugStr = request.getParameter("requestStr");
        if (!Strings.isNullOrEmpty(bugStr)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SendEmailUtil.sendSimpleEmail("mail.gomeplus.com", "DS\\liuxianzhao", "Lxz.4263156", "liuxianzhao@gomeplus.com", "liuxianzhao@gomeholdings.com,huhailong@gomeholdings.com", "代码生成BUG反馈", bugStr);
                }
            }).start();
        }
        model.addAttribute("messages", "温馨提示：添加成功！");
        model.addAttribute("resultStatus", "true");
        model.addAttribute("ADN_ADD_URL", "bugUpload/inits.dhtml");
        return "success";
    }


}
