/**
 * @Title: TaskCodeGeneratorContorller.java
 * @Package com.gome.framework.controller
 * @Description: 定时任务代码生成表的控制器
 * @author GOME
 * @date Fri May 05 14:26:27 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller.creators;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblTaskCodeGenerator;
import cn.com.gome.framework.dao.mapper.edit.TblTaskCodeGeneratorEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskCodeGeneratorSerMapper;
import cn.com.gome.framework.po.ChildProjectInfoPo;
import cn.com.gome.framework.po.TaskCodeGeneratorPo;
import cn.com.gome.generator.logic.CreateTaskProjectLogic;
import cn.com.gome.generator.service.TaskCodeGeneratorService;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.jdbc.page.Entitys;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GOME
 * @ClassName: TaskCodeGeneratorContorller
 * @Description: 定时任务代码生成表的控制器
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/taskcodegenerator")
public class TaskCodeCreateController extends AbstractAdminController {

    @Autowired
    private EntityPersistenceLayerService entityPersistenceLayerService;

    @Autowired
    private CreateTaskProjectLogic createTaskProjectLogic;

    @Resource
    private TaskCodeGeneratorService taskCodeGeneratorService;

    @Autowired
    private TblTaskCodeGeneratorEditMapper tblTaskCodeGeneratorEditMapper;

    @Autowired
    private TblTaskCodeGeneratorSerMapper tblTaskCodeGeneratorSerMapper;

    @RequestMapping("/inits")
    public String init(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "taskcodegenerator/inits";
    }

    @RequestMapping("/search")
    public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys, HttpServletRequest request, HttpServletResponse response) {
        try {
            pageQueryEntitys.setEntityClassName("TblTaskCodeGenerator");
            String childProjectCode = request.getParameter("childProjectCode");
            if (StringUtils.isNotEmpty(childProjectCode)) {
                pageQueryEntitys.addF("childProjectCode", childProjectCode);
            }
            String tableChan = request.getParameter("tableChan");
            if (StringUtils.isNotEmpty(tableChan)) {
                pageQueryEntitys.addF("tableChan", tableChan);
            }
            String tableEng = request.getParameter("tableEng");
            if (StringUtils.isNotEmpty(tableEng)) {
                pageQueryEntitys.addF("tableEng", tableEng);
            }
            pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblTaskCodeGeneratorSerMapper, pageQueryEntitys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(), pageQueryEntitys.getTotalCount(), response);

    }

    @RequestMapping("/select")
    public void select(HttpServletRequest request, HttpServletResponse response) {
        List<TblTaskCodeGenerator> list = null;
        try {
            TblTaskCodeGenerator obj = new TblTaskCodeGenerator();
            list = tblTaskCodeGeneratorSerMapper.queryList(obj);
            ajaxJsonMessage(response, JSONArray.fromObject(list).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultType", "add");
        return "taskcodegenerator/add";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("enitty") TblTaskCodeGenerator enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            tblTaskCodeGeneratorEditMapper.save(enitty);
            model.addAttribute("messages", "温馨提示：添加成功！");
            model.addAttribute("resultStatus", "true");
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
        }
        model.addAttribute("ADN_ADD_URL", "taskcodegenerator/add.dhtml");
        return "success";

    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "edit");
            model.addAttribute("resultStatus", "false");
            TblTaskCodeGenerator enitty = new TblTaskCodeGenerator();
            enitty.setTableNumbers(Integer.valueOf(request.getParameter("id_key")));
            enitty = (TblTaskCodeGenerator) tblTaskCodeGeneratorSerMapper.queryObj(enitty);
            if (enitty != null) {
                model.addAttribute("enitty", enitty);
                model.addAttribute("resultStatus", "true");
                return "taskcodegenerator/add";
            }
            model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("enitty") TblTaskCodeGenerator enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            int count = tblTaskCodeGeneratorEditMapper.edit(enitty);
            if (count == 0) {
                model.addAttribute("messages", "温馨提示：修改失败！");
            } else {
                model.addAttribute("messages", "温馨提示：修改成功！");
                model.addAttribute("resultStatus", "true");
                TblTaskCodeGenerator enittyNew = new TblTaskCodeGenerator();
                enittyNew.setTableNumbers(enitty.getTableNumbers());
                enittyNew = (TblTaskCodeGenerator) tblTaskCodeGeneratorSerMapper.queryObj(enittyNew);
                model.addAttribute("enitty", enittyNew);
                model.addAttribute("resultStatus", "true");
                model.addAttribute("resultType", "edit");
                return "taskcodegenerator/add";
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：修改异常:" + e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            int count = tblTaskCodeGeneratorEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
            if (count == 0) {
                ajaxJsonMessage(response, "0002", "温馨提示：没有删除到符合条件的记录！", "");
            } else {
                ajaxJsonMessage(response, "0000", "温馨提示：删除成功！", "");
                //inits();
            }
        } catch (Exception e) {
            ajaxJsonMessage(response, "0002", "温馨提示：删除失败！", "");
        }
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "view");
            model.addAttribute("resultStatus", "false");
            TblTaskCodeGenerator enitty = new TblTaskCodeGenerator();
            enitty.setTableNumbers(Integer.valueOf(request.getParameter("id_key")));
            TaskCodeGeneratorPo po = (TaskCodeGeneratorPo) tblTaskCodeGeneratorSerMapper.queryPo(enitty);
            if (po != null) {
                model.addAttribute("enitty", po);
                model.addAttribute("resultStatus", "true");
            } else {
                model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
        }
        return "taskcodegenerator/add";
    }

    @RequestMapping("/createProject")
    private void createProject(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {

            Map<String, String> map = createTaskProjectLogic.getCreateObjectInfo(request.getParameter("id_key"));
            taskCodeGeneratorService.service(map);

            ajaxJsonMessage(response, "0000", "定时任务代码生成成功！", "");
        } catch (Exception e) {
            ajaxJsonMessage(response, "0002", "温馨提示：项目创建异常！", "");
        }
    }
}
