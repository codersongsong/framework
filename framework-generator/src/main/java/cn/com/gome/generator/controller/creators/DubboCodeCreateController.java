/**
 * @Title: DubboClassCreateController.java
 * @Package com.gome.generator.controller
 * @Description: dubbo服务类的创建
 * @author GOME
 * @date 2016年6月30日 下午3:32:08
 * @company cn.com.gome
 * @version V1.0
 */


package cn.com.gome.generator.controller.creators;

import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import cn.com.gome.framework.dao.mapper.ser.TblDubboClassCreateConfigSerMapper;
import cn.com.gome.generator.logic.DubboServiceClassCreateLogic;
import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.frame.logic.ResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GOME
 * @ClassName: DubboClassCreateController
 * @Description: dubbo服务类的创建
 * @date 2016年6月30日 下午3:32:08
 */
@Controller
@RequestMapping("dubboclasscreate")
public class DubboCodeCreateController extends AbstractAdminController {
    @Resource
    private DubboServiceClassCreateLogic dubboServiceClassCreateLogic;
    @Resource
    private TblDubboClassCreateConfigSerMapper tblDubboClassCreateConfigSerMapper;

    @RequestMapping("/createclass")
    public void createClass(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id_key");
            TblDubboClassCreateConfig queryDubboClassCreateConfig = tblDubboClassCreateConfigSerMapper.query(id);
            if (queryDubboClassCreateConfig == null) {
                ajaxJsonMessage(response, ERROR, "温馨提示：项目创建异常！", "");
                return;
            }
            if (ResultEnum.PART_CASE_01 == dubboServiceClassCreateLogic.exec(queryDubboClassCreateConfig)) {
                ajaxJsonMessage(response, ERROR, "温馨提示：项目创建异常！", "");
                return;
            }
            ajaxJsonMessage(response, SUCCESS, "温馨提示：项目创建成功！", "");
        } catch (Exception e) {
            ajaxJsonMessage(response, ERROR, "温馨提示：项目创建异常！", "");
        }
    }


    @RequestMapping("/getInterfaceExample")
    public void getInterfaceExample(HttpServletRequest request,HttpServletResponse response){
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String filePaths = request.getSession().getServletContext().getRealPath("/file/InterfaceExample.xlsx");
        try {
            InputStream inputStream = new FileInputStream(new File(filePaths));
            OutputStream outputStream = response.getOutputStream();
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostConstruct
    private void inits() {
        try {
            List<DictionaryVo> ivList = new ArrayList<DictionaryVo>();
            DictionaryVo idval = null;
            GlobalApplicationCache.getInstance().put("CREATE_TYPE." + "010", "类型1");
            idval = new DictionaryVo("010", "类型1", false);
            ivList.add(idval);
            GlobalDataDictionaryCache.getInstance().putIdValue("CREATE_TYPE", ivList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
