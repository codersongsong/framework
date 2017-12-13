/**   
* @Title: FilesCreateServiceImpl.java 
* @Package cn.com.gome.generator.service.impl 
* @Description: 持久层、控制器层、页面、定时任务代码生成
* @author GOME
* @date 2017年6月1日 下午2:05:07 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.service.impl;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.entity.TblTableDaoInfo;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTableDaoInfoSerMapper;
import cn.com.gome.generator.service.FilesCreateService;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: FilesCreateServiceImpl 
 * @Description: 持久层、控制器层、页面、定时任务代码生成
 * @author GOME
 * @date 2017年6月1日 下午2:05:07  
 */
@Service
public class FilesCreateServiceImpl implements FilesCreateService {
	
	private Logger logger = LoggerFactory.getLogger("FilesCreateServiceImpl");
	
	@Autowired
	private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Autowired
	private TblTableDaoInfoSerMapper tblTableDaoInfoSerMapper;

	@Resource
	private ILogics<Map<String , Object>> daoEntityVoCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> daoSerEditMapperCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> daoSerEditXmlCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> controllerCreateLogic;

	@Resource
	private ILogics<Map<String , Object>> daoSerEditXmlCompCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> pageFltCreateLogic;
	
	/* (非 Javadoc) 
	 * <p>Title: daoControllerPage</p> 
	 * <p>Description: 持久层、控制器层、页面</p> 
	 * @param id
	 * @see cn.com.gome.generator.service.FilesCreateService#daoControllerPage(java.lang.String) 
	 */
	@Override
    public boolean daoControllerPage(String id,String flag) {
		try{
			TblTableDaoInfo tblTableDaoInfo = tblTableDaoInfoSerMapper.query(id);
			if(null == tblTableDaoInfo){
				tblTableDaoInfo = new TblTableDaoInfo();
				tblTableDaoInfo.setChildProjectCode(Integer.parseInt(id));
				tblTableDaoInfo = tblTableDaoInfoSerMapper.queryObj(tblTableDaoInfo);
			}
			TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(tblTableDaoInfo.getChildProjectCode().toString());
			TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(childProjectInfo.getProjectCode().toString());
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("childProjectInfo", childProjectInfo);
			map.put("projectBasicInfo", projectBasicInfo);
			map.put("tblTableDaoInfo", tblTableDaoInfo);
			String createFiles = tblTableDaoInfo.getCreateFiles();
			if(StringUtils.isNotEmpty(createFiles)){
				for(String temp : createFiles.split(",")){
					if("1".equals(temp)){
						if(ResultEnum.OK != daoEntityVoCreateLogic.exec(map)){
							return false;
						}
						if(ResultEnum.OK != daoSerEditMapperCreateLogic.exec(map)){
							return false;
						}
						if("010".equals(flag)){// 对比创建XML
							if(ResultEnum.OK != daoSerEditXmlCompCreateLogic.exec(map)){
								return false;
							}
						}else{
							if(ResultEnum.OK != daoSerEditXmlCreateLogic.exec(map)){ // 覆盖创建XML
								return false;
							}
						}
					}else if("2".equals(temp)){
						if(ResultEnum.OK != controllerCreateLogic.exec(map)){
							return false;
						}
					}else if("3".equals(temp)){
						if(ResultEnum.OK != pageFltCreateLogic.exec(map)){
							return false;
						}
					}
				}
				return true;
			}
		}catch(Exception e){
			logger.error("子项目创建异常：" , e);
		}
		return false;
	}

	/* (非 Javadoc) 
	 * <p>Title: task</p> 
	 * <p>Description: 定时任务代码生成</p> 
	 * @param id
	 * @see cn.com.gome.generator.service.FilesCreateService#task(java.lang.String) 
	 */
	@Override
    public boolean task(String id) {
		return false;
	}

}
