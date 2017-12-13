package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTaskCodeGenerator;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskCodeGeneratorSerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建定时任务Logic
 * @author zhouyongsheng-ds
 *
 */
@Service
public class CreateTaskProjectLogic {

	private Logger logger = LoggerFactory.getLogger("CreateTaskProjectLogic");
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Autowired
	private TblTaskCodeGeneratorSerMapper tblTaskCodeGeneratorSerMapper;
	
	@Autowired
	private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
	
	/**
	 * 查询生成定时代码需要的参数
	 * @param key_id
	 * @return
	 */
	public Map<String,String> getCreateObjectInfo(String key_id) throws Exception {
		
		TblTaskCodeGenerator tblTaskCodeGenerator = tblTaskCodeGeneratorSerMapper.query(key_id);
		if(null == tblTaskCodeGenerator){
			return null;
		}
		
		TblChildProjectInfo tblChildProjectInfo = new TblChildProjectInfo();
		tblChildProjectInfo.setChildProjectCode(tblTaskCodeGenerator.getChildProjectCode());	
		tblChildProjectInfo = tblChildProjectInfoSerMapper.queryObj(tblChildProjectInfo);
		
		TblProjectBasicInfo tblProjectBasicInfo = new TblProjectBasicInfo();
		tblProjectBasicInfo.setProjectCode(tblChildProjectInfo.getProjectCode());
		tblProjectBasicInfo = tblProjectBasicInfoSerMapper.queryObj(tblProjectBasicInfo);
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("projectPath", tblChildProjectInfo.getProjectPath());
		map.put("tableChan", tblTaskCodeGenerator.getTableChan());
		map.put("tableEng", tblTaskCodeGenerator.getTableEng());
		map.put("lockFlg", tblTaskCodeGenerator.getLockFlg());
		map.put("lockTm", tblTaskCodeGenerator.getLockTm());
		map.put("runCounts", tblTaskCodeGenerator.getRunCounts());
		
		map.put("projectEng", tblProjectBasicInfo.getProjectEng());
		map.put("databaseType", tblProjectBasicInfo.getDatabaseType());
		map.put("taskClassName", tblTaskCodeGenerator.getTaskClassName());
		map.put("tableKey", tblTaskCodeGenerator.getTableKey());
		map.put("taskAsk", tblTaskCodeGenerator.getTaskAsk());
		
		logger.info("查询组装生成定时任务代码map成功 map:" + map.toString());
		
		return map;
	}

}
