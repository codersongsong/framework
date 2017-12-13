package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Map;

/**
 * <Description> </Description>
 * <ClassName> MainProjectCreateCheckLogic</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月05日 13:37
 */
public class MainProjectCreateCheckLogic implements ILogics<Map<String, Object>> {
    @Autowired
    private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
    @Autowired
    private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;


    @Override
    public ResultEnum exec(Map<String, Object> map) throws LogicsException {
        String id = (String) map.get("childProjectId");
        TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(id);
        map.put("projectId", childProjectInfo.getProjectCode() + "");

        TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(childProjectInfo.getProjectCode() + "");
        String projectPath = projectBasicInfo.getProjectPath();
        File file = new File(projectPath);
        if (file.exists()) {
            return ResultEnum.OK;
        } else {
            return ResultEnum.PART_CASE_01;
        }
    }
}
