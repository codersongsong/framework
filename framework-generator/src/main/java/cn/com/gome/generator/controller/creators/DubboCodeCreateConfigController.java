/**
 * @Title: DubboClassCreateConfigContorller.java
 * @Package com.gome.framework.controller
 * @Description: DUBBO服务类生产配置的控制器
 * @author GOME
 * @date Fri May 05 14:26:30 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller.creators;

import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import cn.com.gome.framework.dao.mapper.edit.TblDubboClassCreateConfigEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblDubboClassCreateConfigSerMapper;
import cn.com.gome.framework.po.DubboClassCreateConfigPo;
import cn.com.gome.generator.service.ImportDocService;
import cn.com.gome.generator.service.ImportExcelService;
import cn.com.gome.generator.service.impl.ImportExcelServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author GOME
 * @ClassName: DubboClassCreateConfigContorller
 * @Description: DUBBO服务类生产配置的控制器
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/dubboclasscreateconfig")
public class DubboCodeCreateConfigController extends AbstractAdminController {

    @Autowired
    private EntityPersistenceLayerService entityPersistenceLayerService;
    @Autowired
    private ImportDocService importDocService;
    @Autowired
    private TblDubboClassCreateConfigSerMapper tblDubboClassCreateConfigSerMapper;
    @Autowired
    private TblDubboClassCreateConfigEditMapper tblDubboClassCreateConfigEditMapper;
    @Autowired
    private ImportExcelService importExcelService;

    @RequestMapping("/inits")
    public String init(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "dubboclasscreateconfig/inits";
    }

    @RequestMapping("/search")
    public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys, HttpServletRequest request, HttpServletResponse response) {
        try {
            pageQueryEntitys.setEntityClassName("TblDubboClassCreateConfig");
            String childProjectCode = request.getParameter("childProjectCode");
            if (StringUtils.isNotEmpty(childProjectCode)) {
                pageQueryEntitys.addF("childProjectCode", childProjectCode);
            }
            String createType = request.getParameter("createType");
            if (StringUtils.isNotEmpty(createType)) {
                pageQueryEntitys.addF("createType", createType);
            }
            String dubboClass = request.getParameter("dubboClass");
            if (StringUtils.isNotEmpty(dubboClass)) {
                pageQueryEntitys.addF("dubboClass", dubboClass);
            }
            String dubboClassAsk = request.getParameter("dubboClassAsk");
            if (StringUtils.isNotEmpty(dubboClassAsk)) {
                pageQueryEntitys.addF("dubboClassAsk", dubboClassAsk);
            }
            String dubboClassMethod = request.getParameter("dubboClassMethod");
            if (StringUtils.isNotEmpty(dubboClassMethod)) {
                pageQueryEntitys.addF("dubboClassMethod", dubboClassMethod);
            }
            pageQueryEntitys = entityPersistenceLayerService.queryPageVoList(tblDubboClassCreateConfigSerMapper, pageQueryEntitys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(), pageQueryEntitys.getTotalCount(), response);
    }

    @RequestMapping("/select")
    public void select(HttpServletRequest request, HttpServletResponse response) {
        List<TblDubboClassCreateConfig> list = null;
        try {
            TblDubboClassCreateConfig obj = new TblDubboClassCreateConfig();
            list = tblDubboClassCreateConfigSerMapper.queryList(obj);
            ajaxJsonMessage(response, JSONArray.fromObject(list).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultType", "add");
        return "dubboclasscreateconfig/add";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("enitty") TblDubboClassCreateConfig enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            tblDubboClassCreateConfigEditMapper.save(enitty);
            model.addAttribute("messages", "温馨提示：添加成功！");
            model.addAttribute("resultStatus", "true");
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
        }
        model.addAttribute("ADN_ADD_URL", "dubboclasscreateconfig/add.dhtml");
        return "success";

    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "edit");
            model.addAttribute("resultStatus", "false");
            TblDubboClassCreateConfig enitty = new TblDubboClassCreateConfig();
            enitty.setClassNo(Integer.valueOf(request.getParameter("id_key")));
            enitty = (TblDubboClassCreateConfig) tblDubboClassCreateConfigSerMapper.queryObj(enitty);
            if (enitty != null) {
                model.addAttribute("enitty", enitty);
                model.addAttribute("resultStatus", "true");
                return "dubboclasscreateconfig/add";
            }
            model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("enitty") TblDubboClassCreateConfig enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setCreateTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            enitty.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            int count = tblDubboClassCreateConfigEditMapper.edit(enitty);
            if (count == 0) {
                model.addAttribute("messages", "温馨提示：修改失败！");
            } else {
                model.addAttribute("messages", "温馨提示：修改成功！");
                model.addAttribute("resultStatus", "true");
                TblDubboClassCreateConfig enittyNew = new TblDubboClassCreateConfig();
                enittyNew.setClassNo(enitty.getClassNo());
                enittyNew = (TblDubboClassCreateConfig) tblDubboClassCreateConfigSerMapper.queryObj(enittyNew);
                model.addAttribute("enitty", enittyNew);
                model.addAttribute("resultStatus", "true");
                model.addAttribute("resultType", "edit");
                return "dubboclasscreateconfig/add";
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
            int count = tblDubboClassCreateConfigEditMapper.delList(Arrays.asList(request.getParameter("id_key").split(",")));
            if (count == 0) {
                ajaxJsonMessage(response, ERROR, "温馨提示：没有删除到符合条件的记录！", "");
            } else {
                ajaxJsonMessage(response, SUCCESS, "温馨提示：删除成功！", "");
                //inits();
            }
        } catch (Exception e) {
            ajaxJsonMessage(response, ERROR, "温馨提示：删除失败！", "");
        }
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "view");
            model.addAttribute("resultStatus", "false");
            TblDubboClassCreateConfig enitty = new TblDubboClassCreateConfig();
            enitty.setClassNo(Integer.valueOf(request.getParameter("id_key")));
            DubboClassCreateConfigPo po = (DubboClassCreateConfigPo) tblDubboClassCreateConfigSerMapper.queryPo(enitty);
            if (po != null) {
                model.addAttribute("enitty", po);
                model.addAttribute("resultStatus", "true");
            } else {
                model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
        }
        return "dubboclasscreateconfig/add";
    }

    //导入Excel
    @RequestMapping("/import")
    public String importURL(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultType", "import");
        return "dubboclasscreateconfig/add";
    }

    //保存Excel信息
    @RequestMapping("/saveExcel")
    public void importExcel(@RequestParam("load_file_name") MultipartFile load_file_name, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            String childProjectCode = request.getParameter("childProjectCode");
            //导入excel文件进行解析
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("childProjectCode", childProjectCode);
            paramMap.put("fileStream", load_file_name.getInputStream());
            boolean result = importExcelService.importService(paramMap);
            if (result) {
                ajaxJsonMessage(response, SUCCESS, "温馨提示：导入成功！", "");
                return;
            }
            ajaxJsonMessage(response, ERROR, "温馨提示：导入失败", "");
        } catch (Exception e) {
            ajaxJsonMessage(response, ERROR, "温馨提示：导入失败！", "");
        }
    }

}
