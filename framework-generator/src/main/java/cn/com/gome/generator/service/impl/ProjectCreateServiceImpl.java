/**   
* @Title: ProjectCreateServiceImpl.java 
* @Package cn.com.gome.generator.service.impl 
* @Description: 创建项目
* @author GOME
* @date 2017年5月26日 下午2:40:42 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.service.impl;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.mapper.edit.TblChildProjectInfoEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.generator.service.ProjectCreateService;
import com.gomeplus.frame.logic.ILogics;
import com.gomeplus.frame.logic.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: ProjectCreateServiceImpl 
 * @Description: 创建项目
 * @author GOME
 * @date 2017年5月26日 下午2:40:42  
 */
@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {
	
	private Logger logger = LoggerFactory.getLogger("ProjectCreateServiceImpl");
	
	@Autowired
	private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
	
	@Autowired
	private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
	
	@Autowired
	private TblChildProjectInfoEditMapper tblChildProjectInfoEditMapper;
	
	@Autowired
	private ILogics<TblProjectBasicInfo> mainProjectCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectBaseCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectDaoCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectAdminCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectDubboCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectServiceCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectApiCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectTaskCreateLogic;
	
	@Resource
	private ILogics<Map<String , Object>> childProjectWebCreateLogic;

	@Resource
	private ILogics<Map<String , Object>> childProjectOtherCreateLogic;

	/* (非 Javadoc)
	 * <p>Title: mainProjectCreate</p> 
	 * <p>Description: 创建主项目</p> 
	 * @param id
	 * @see cn.com.gome.generator.service.ProjectCreateService#mainProjectCreate(java.lang.String) 
	 */
	@Override
	public boolean mainProjectCreate(String from, String id) {
		try{
			TblProjectBasicInfo projectBasicInfo;
			if ("child".equals(from)) {
				TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(id);
				projectBasicInfo = tblProjectBasicInfoSerMapper.query(childProjectInfo.getProjectCode() + "");
				File file = new File(projectBasicInfo.getProjectPath()+"/pom.xml");
				if (file.exists()) {
					return true;
				}
			} else {
				projectBasicInfo = tblProjectBasicInfoSerMapper.query(id);
			}
			ResultEnum resultEnum = mainProjectCreateLogic.exec(projectBasicInfo);
			if(resultEnum == ResultEnum.OK) {
				return true;
			}
		}catch(Exception e){
			logger.error("主项目创建异常：" , e);
		}
		return false;
	}

	/* (非 Javadoc) 
	 * <p>Title: childProjectCreate</p> 
	 * <p>Description: 创建子项目</p> 
	 * @param id
	 * @see cn.com.gome.generator.service.ProjectCreateService#childProjectCreate(java.lang.String) 
	 */
	@Override
	public boolean childProjectCreate(String id) {
		try{
			TblChildProjectInfo childProjectInfo = tblChildProjectInfoSerMapper.query(id);
			TblProjectBasicInfo projectBasicInfo = tblProjectBasicInfoSerMapper.query(childProjectInfo.getProjectCode().toString());
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("childProjectInfo", childProjectInfo);
			map.put("projectBasicInfo", projectBasicInfo);
			//projectType  {label: '持久层',value: '010'},{label: '前端WEB',value: '020'},{label: 'API接口',value: '030'},{label: 'DUBBO服务',value: '040'},{label: '定时任务',value: '050'},{label: '后台管理',value: '060'}
			//packageType  {label: 'war',value: 'war'},{label: 'jar',value: 'jar'},{label: 'pom',value: 'pom'}
			if(ResultEnum.OK != childProjectBaseCreateLogic.exec(map)){
				return false;
			}
			String projectType = childProjectInfo.getProjectType();
			if("010".equals(projectType)){
				if(ResultEnum.OK != childProjectDaoCreateLogic.exec(map)){
					return false;
				}
			}else if("020".equals(projectType)){
				if(ResultEnum.OK != childProjectWebCreateLogic.exec(map)){
					return false;
				}
			}else if("030".equals(projectType)){
				if(ResultEnum.OK != childProjectApiCreateLogic.exec(map)){
					return false;
				}
			}else if("040".equals(projectType)){
				if(ResultEnum.OK != childProjectDubboCreateLogic.exec(map)){
					return false;
				}
				if(ResultEnum.OK != childProjectServiceCreateLogic.exec(map)){
					return false;
				}
			}else if("050".equals(projectType)){
				if(ResultEnum.OK != childProjectTaskCreateLogic.exec(map)){
					return false;
				}
			}else if("060".equals(projectType)){
				if(ResultEnum.OK != childProjectAdminCreateLogic.exec(map)){
					return false;
				}
			} else if ("999".equals(projectType)) {
				if(ResultEnum.OK != childProjectOtherCreateLogic.exec(map)){
					return false;
				}
			}
			tblChildProjectInfoEditMapper.edit(childProjectInfo);
			return true;
		}catch(Exception e){
			logger.error("子项目创建异常：" , e);
		}
		return false;
	}
}
