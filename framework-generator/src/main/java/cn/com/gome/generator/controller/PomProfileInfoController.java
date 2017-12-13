/**
 * @Title: PomProfileInfoContorller.java
 * @Package cn.com.gome.framework.controller
 * @Description: pom文件profile信息管理的控制器
 * @author GOME
 * @date Thu Nov 30 15:59:20 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller;

import cn.com.gome.framework.dao.entity.TblPomProfileInfo;
import cn.com.gome.framework.dao.mapper.edit.TblPomProfileInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblPomProfileInfoSerMapper;
import cn.com.gome.framework.po.PomProfileInfoPo;
import com.gomeplus.frame.cache.DictionaryVo;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gomeplus.frame.cache.GlobalDataDictionaryCache;
import com.gomeplus.frame.controller.AbstractAdminController;
import com.gomeplus.frame.utils.UUIDGenerator;
import com.gomeplus.jdbc.mapper.PersistenceLayerSerMapper;
import com.gomeplus.jdbc.page.PageQueryEntitys;
import com.gomeplus.jdbc.service.EntityPersistenceLayerService;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author GOME
 * @ClassName: PomProfileInfoContorller
 * @Description: pom文件profile信息管理的控制器
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/pomprofileinfo")
public class PomProfileInfoController extends AbstractAdminController {

    @Autowired
    private EntityPersistenceLayerService entityPersistenceLayerService;

    @Autowired
    private TblPomProfileInfoEditMapper tblPomProfileInfoEditMapper;

    @Autowired
    private TblPomProfileInfoSerMapper tblPomProfileInfoSerMapper;

    @RequestMapping("/inits")
    public String init(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "pomprofileinfo/inits";
    }

    @RequestMapping("/search")
    public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys, HttpServletRequest request, HttpServletResponse response) {
        try {
            pageQueryEntitys.setEntityClassName("TblPomProfileInfo");
            String configDescription = request.getParameter("configDescription");
            if (StringUtils.isNotEmpty(configDescription)) {
                pageQueryEntitys.addF("configDescription", configDescription);
            }
            String configName = request.getParameter("configName");
            if (StringUtils.isNotEmpty(configName)) {
                pageQueryEntitys.addF("configName", configName);
            }
            String type = request.getParameter("type");
            if (StringUtils.isNotEmpty(type)) {
                pageQueryEntitys.addF("type", type);
            }
            String andDependencyId = request.getParameter("andDependencyId");
            if (StringUtils.isNotEmpty(andDependencyId)) {
                pageQueryEntitys.addF("andDependencyId", andDependencyId);
            }
            String andProjectId = request.getParameter("andProjectId");
            if (StringUtils.isNotEmpty(andProjectId)) {
                pageQueryEntitys.addF("andProjectId", andProjectId);
            }
            String groupInfo = request.getParameter("groupInfo");
            if (StringUtils.isNotEmpty(groupInfo)) {
                pageQueryEntitys.addF("groupInfo", groupInfo);
            }
            pageQueryEntitys = entityPersistenceLayerService.queryPageVoList((PersistenceLayerSerMapper) tblPomProfileInfoSerMapper, pageQueryEntitys);
        } catch (Exception e) {
            logger.error(" pom文件profile信息管理的控制器执行异常【search】", e);
        }
        ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(), pageQueryEntitys.getTotalCount(), response);
    }

    @RequestMapping("/select")
    public void select(HttpServletRequest request, HttpServletResponse response) {
        try {
            TblPomProfileInfo obj = new TblPomProfileInfo();
            List<TblPomProfileInfo> list = tblPomProfileInfoSerMapper.queryList(obj);
            ajaxJsonMessage(response, JSONArray.fromObject(list).toString());
        } catch (Exception e) {
            logger.error(" pom文件profile信息管理的控制器执行异常【select】", e);
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultType", "add");
        return "pomprofileinfo/add";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("enitty") TblPomProfileInfo enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setId(UUIDGenerator.getUUID());
            enitty.setCreateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            enitty.setUpdateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            tblPomProfileInfoEditMapper.save(enitty);
            model.addAttribute("messages", "温馨提示：添加成功！");
            model.addAttribute("resultStatus", "true");
            init();
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
            logger.error(" pomprofileinfo的控制器执行异常【save】", e);
        }
        model.addAttribute("ADN_ADD_URL", "pomprofileinfo/add.dhtml");
        return "success";

    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "edit");
            model.addAttribute("resultStatus", "false");
            TblPomProfileInfo enitty = new TblPomProfileInfo();
            enitty.setId(request.getParameter("id_key"));
            enitty = (TblPomProfileInfo) tblPomProfileInfoSerMapper.queryObj(enitty);
            if (enitty != null) {
                model.addAttribute("enitty", enitty);
                model.addAttribute("resultStatus", "true");
                return "pomprofileinfo/edit";
            }
            model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
            logger.error(" pomprofileinfo的控制器执行异常【query】", e);
        }
        return "success";
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("enitty") TblPomProfileInfo enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setUpdateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            int count = tblPomProfileInfoEditMapper.edit(enitty);
            if (count == 0) {
                model.addAttribute("messages", "温馨提示：修改失败！");
            } else {
                model.addAttribute("messages", "温馨提示：修改成功！");
                model.addAttribute("resultStatus", "true");
                TblPomProfileInfo enittyNew = new TblPomProfileInfo();
                enittyNew.setId(enitty.getId());
                enittyNew = (TblPomProfileInfo) tblPomProfileInfoSerMapper.queryObj(enittyNew);
                model.addAttribute("enitty", enittyNew);
                model.addAttribute("resultStatus", "true");
                model.addAttribute("resultType", "edit");
                init();
                return "pomprofileinfo/edit";
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：修改异常:" + e.getMessage());
            logger.error(" pomprofileinfo的控制器执行异常【edit】", e);
        }
        return "success";
    }

    @RequestMapping("/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            int count = tblPomProfileInfoEditMapper.del(Arrays.asList(request.getParameter("id_key").split(",")));
            if (count == 0) {
                ajaxJsonMessage(response, "0002", "温馨提示：没有删除到符合条件的记录！", "");
            } else {
                ajaxJsonMessage(response, "0000", "温馨提示：删除成功！", "");
                init();
            }
        } catch (Exception e) {
            ajaxJsonMessage(response, "0002", "温馨提示：删除失败！", "");
            logger.error(" pomprofileinfo的控制器执行异常【del】", e);
        }
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "view");
            model.addAttribute("resultStatus", "false");
            TblPomProfileInfo enitty = new TblPomProfileInfo();
            enitty.setId(request.getParameter("id_key"));
            PomProfileInfoPo vo = (PomProfileInfoPo) tblPomProfileInfoSerMapper.queryVo(enitty);
            if (vo != null) {
                model.addAttribute("enitty", vo);
                model.addAttribute("resultStatus", "true");
            } else {
                model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
            logger.error(" pomprofileinfo的控制器执行异常【view】", e);
        }
        return "pomprofileinfo/view";
    }

    //初始化部分信息到缓存
    @PostConstruct
    private void init() {
        GlobalApplicationCache.getInstance().put("PROFILE_TYPE.010", "pom配置");
        GlobalApplicationCache.getInstance().put("PROFILE_TYPE.020", "properties配置");
        DictionaryVo vo1 = new DictionaryVo("010", "pom配置");
        DictionaryVo vo2 = new DictionaryVo("020", "properties配置");
        List<DictionaryVo> list = new ArrayList<>();
        list.add(vo1);
        list.add(vo2);
        GlobalDataDictionaryCache.getInstance().putIdValue("PROFILE_TYPE", list);

        List<TblPomProfileInfo> infoList = tblPomProfileInfoSerMapper.queryList(null);
        if (CollectionUtils.isNotEmpty(infoList) && infoList.size() != 0) {
            TblPomProfileInfo info = null;
            List<DictionaryVo> list1 = new ArrayList<>();
            for (int i = 0; i < infoList.size(); i++) {
                info = infoList.get(i);
                GlobalApplicationCache.getInstance().put("PROFILE." + info.getId(), info.getConfigDescription() + "|" + info.getConfigName());
                list1.add(new DictionaryVo(info.getId(), info.getConfigDescription() + "|" + info.getConfigName()));
            }
            GlobalDataDictionaryCache.getInstance().putIdValue("PROFILE", list1);
        }
    }
}
