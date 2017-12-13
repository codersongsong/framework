/**
 * @Title: DependencyInfoContorller.java
 * @Package cn.com.gome.framework.controller
 * @Description: 依赖管理表的控制器
 * @author GOME
 * @date Thu Nov 30 15:59:16 CST 2017
 * @company cn.com.gome
 * @version V1.0
 */

package cn.com.gome.generator.controller;

import cn.com.gome.framework.dao.entity.TblDependencyInfo;
import cn.com.gome.framework.dao.mapper.edit.TblDependencyInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblDependencyInfoSerMapper;
import cn.com.gome.framework.po.DependencyInfoPo;
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
 * @ClassName: DependencyInfoContorller
 * @Description: 依赖管理表的控制器
 * @date 2015年2月10日 下午2:35:34
 */
@Controller
@RequestMapping("/dependencyinfo")
public class DependencyInfoController extends AbstractAdminController {

    @Autowired
    private EntityPersistenceLayerService entityPersistenceLayerService;

    @Autowired
    private TblDependencyInfoEditMapper tblDependencyInfoEditMapper;

    @Autowired
    private TblDependencyInfoSerMapper tblDependencyInfoSerMapper;

    @RequestMapping("/inits")
    public String init(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "dependencyinfo/inits";
    }

    @RequestMapping("/search")
    public void search(@ModelAttribute("pageQueryEntitys") PageQueryEntitys pageQueryEntitys, HttpServletRequest request, HttpServletResponse response) {
        try {
            pageQueryEntitys.setEntityClassName("TblDependencyInfo");
            String groupId = request.getParameter("groupId");
            if (StringUtils.isNotEmpty(groupId)) {
                pageQueryEntitys.addF("groupId", groupId);
            }
            String artifactId = request.getParameter("artifactId");
            if (StringUtils.isNotEmpty(artifactId)) {
                pageQueryEntitys.addF("artifactId", artifactId);
            }
            String dependencyName = request.getParameter("dependencyName");
            if (StringUtils.isNotEmpty(dependencyName)) {
                pageQueryEntitys.addF("dependencyName", dependencyName);
            }
            pageQueryEntitys = entityPersistenceLayerService.queryPageVoList((PersistenceLayerSerMapper) tblDependencyInfoSerMapper, pageQueryEntitys);
        } catch (Exception e) {
            logger.error(" 依赖管理表的控制器执行异常【search】", e);
        }
        ajaxJsonPage(JSONArray.fromObject(pageQueryEntitys.getList()).toString(), pageQueryEntitys.getTotalCount(), response);
    }

    @RequestMapping("/select")
    public void select(HttpServletRequest request, HttpServletResponse response) {
        try {
            TblDependencyInfo obj = new TblDependencyInfo();
            List<TblDependencyInfo> list = tblDependencyInfoSerMapper.queryList(obj);
            ajaxJsonMessage(response, JSONArray.fromObject(list).toString());
        } catch (Exception e) {
            logger.error(" 依赖管理表的控制器执行异常【select】", e);
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultType", "add");
        return "dependencyinfo/add";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("enitty") TblDependencyInfo enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setId(UUIDGenerator.getUUID());
            enitty.setCreateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            enitty.setUpdateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            tblDependencyInfoEditMapper.save(enitty);
            model.addAttribute("messages", "温馨提示：添加成功！");
            model.addAttribute("resultStatus", "true");
            init();
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：添加异常:" + e.getMessage());
            logger.error(" dependencyinfo的控制器执行异常【save】", e);
        }
        model.addAttribute("ADN_ADD_URL", "dependencyinfo/add.dhtml");
        return "success";

    }

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "edit");
            model.addAttribute("resultStatus", "false");
            TblDependencyInfo enitty = new TblDependencyInfo();
            enitty.setId(request.getParameter("id_key"));
            enitty = (TblDependencyInfo) tblDependencyInfoSerMapper.queryObj(enitty);
            if (enitty != null) {
                model.addAttribute("enitty", enitty);
                model.addAttribute("resultStatus", "true");
                return "dependencyinfo/edit";
            }
            model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");

        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
            logger.error(" dependencyinfo的控制器执行异常【query】", e);
        }
        return "success";
    }

    @RequestMapping("/edit")
    public String edit(@ModelAttribute("enitty") TblDependencyInfo enitty, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            enitty.setUpdateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            int count = tblDependencyInfoEditMapper.edit(enitty);
            if (count == 0) {
                model.addAttribute("messages", "温馨提示：修改失败！");
            } else {
                model.addAttribute("messages", "温馨提示：修改成功！");
                model.addAttribute("resultStatus", "true");
                TblDependencyInfo enittyNew = new TblDependencyInfo();
                enittyNew.setId(enitty.getId());
                enittyNew = (TblDependencyInfo) tblDependencyInfoSerMapper.queryObj(enittyNew);
                model.addAttribute("enitty", enittyNew);
                model.addAttribute("resultStatus", "true");
                model.addAttribute("resultType", "edit");
                init();
                return "dependencyinfo/edit";
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：修改异常:" + e.getMessage());
            logger.error(" dependencyinfo的控制器执行异常【edit】", e);
        }
        return "success";
    }

    @RequestMapping("/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultStatus", "false");
            int count = tblDependencyInfoEditMapper.del(Arrays.asList(request.getParameter("id_key").split(",")));
            if (count == 0) {
                ajaxJsonMessage(response, "0002", "温馨提示：没有删除到符合条件的记录！", "");
            } else {
                ajaxJsonMessage(response, "0000", "温馨提示：删除成功！", "");
                init();
            }
        } catch (Exception e) {
            ajaxJsonMessage(response, "0002", "温馨提示：删除失败！", "");
            logger.error(" dependencyinfo的控制器执行异常【del】", e);
        }
    }

    @RequestMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            model.addAttribute("resultType", "view");
            model.addAttribute("resultStatus", "false");
            TblDependencyInfo enitty = new TblDependencyInfo();
            enitty.setId(request.getParameter("id_key"));
            DependencyInfoPo vo = (DependencyInfoPo) tblDependencyInfoSerMapper.queryVo(enitty);
            if (vo != null) {
                model.addAttribute("enitty", vo);
                model.addAttribute("resultStatus", "true");
            } else {
                model.addAttribute("messages", "温馨提示：没有查询到符合条件的记录！");
            }
        } catch (Exception e) {
            model.addAttribute("messages", "温馨提示：查询异常:" + e.getMessage());
            logger.error(" dependencyinfo的控制器执行异常【view】", e);
        }
        return "dependencyinfo/view";
    }

    //初始化部分信息到缓存
    @PostConstruct
    private void init() {
        List<TblDependencyInfo> infoList = tblDependencyInfoSerMapper.queryList(null);
        if (CollectionUtils.isNotEmpty(infoList) && infoList.size() != 0) {
            TblDependencyInfo info = null;
            List<DictionaryVo> list = new ArrayList<>();
            for (int i = 0; i < infoList.size(); i++) {
                info = infoList.get(i);
                GlobalApplicationCache.getInstance().put("DEPENDENCY." + info.getId(), info.getArtifactId() + "|" + info.getGroupId());
                list.add(new DictionaryVo(info.getId(), info.getArtifactId() + "|" + info.getGroupId()));
            }
            GlobalDataDictionaryCache.getInstance().putIdValue("DEPENDENCY", list);
        }
    }
}
