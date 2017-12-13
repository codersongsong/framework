/**   
* @Title: TaskCodeGeneratorServiceImpl.java 
* @Package com.gome.generator.service.impl 
* @Description: 定时任务代码生成成功
* @author GOME
* @date 2016年7月7日 下午2:03:40 
* @company cn.com.gome
* @version V1.0   
*/ 


package cn.com.gome.generator.service.impl;

import cn.com.gome.generator.logic.AbstractLogic;
import cn.com.gome.generator.util.FileTools;
import cn.com.gome.generator.service.TaskCodeGeneratorService;
import org.springframework.stereotype.Service;

import java.util.Map;


/** 
 * @ClassName: TaskCodeGeneratorServiceImpl 
 * @Description: 定时任务代码生成成功 
 * @author GOME
 * @date 2016年7月7日 下午2:03:40  
 */
@Service
public class TaskCodeGeneratorServiceImpl extends AbstractLogic implements TaskCodeGeneratorService {
	
	@Override
	public String service(Map<String,String> map) {
		
		String projectPath = map.get("projectPath");
		String projectEng = map.get("projectEng");
		String tableEng = map.get("tableEng");
		String tableEng_U = columnToStringU(tableEng);
		String tableEng_L = columnToStringL(tableEng);
		projectPath = projectPath + "//src//main//java//com//gome//" + projectEng;
		String taskClassName = map.get("taskClassName");
		String taskClassName_L = taskClassName.substring(0,1).toLowerCase() + taskClassName.substring(1, taskClassName.length());
		String tableKey = map.get("tableKey");
		String tableKey_L = columnToStringL(tableKey);
		String tableKey_U = columnToStringU(tableKey);
		String taskAsk = map.get("taskAsk");
		
		String lockFlg = map.get("lockFlg");
		String lockFlg_L = columnToStringL(lockFlg);
		String lockFlg_U = columnToStringU(lockFlg);
		String lockTm = map.get("lockTm");
		String lockTm_L = columnToStringL(lockTm);
		String lockTm_U = columnToStringU(lockTm);
		String runCounts = map.get("runCounts");
		String runCounts_L = columnToStringL(runCounts);
		String runCounts_U = columnToStringU(runCounts);
		
		//主任务类
		StringBuffer sb = new StringBuffer();
		sb.append("/**\n");   
		sb.append("* @Title: " + taskClassName + "Task.java \n");
		sb.append("* @Package com.gome." + projectEng + ".step.impl \n");
		sb.append("* @Description:  " + taskAsk + "\n");
		sb.append("* @author GOME   \n");
		sb.append("* @date 2015年2月10日 上午11:59:03 \n");
		sb.append("* @company GOME\n");
		sb.append("* @version V.1.0  \n"); 
		sb.append("*/\n");
		sb.append("package com.gome." + projectEng + ".step.impl;\n");
		sb.append("\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import java.util.Date;\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("import cn.com.gome.frame.exception.GomeFinanceException;\n");
		sb.append("import cn.com.gome.frame.logic.ILogics;\n");
		sb.append("import cn.com.gome.frame.util.DateFormatUtils;\n");
		sb.append("import cn.com.gome.frame.logic.ResultEnum;\n");
		sb.append("import com.gome." + projectEng + ".dao.entity." + tableEng_U + ";\n");
		sb.append("import com.gome." + projectEng + ".dao.mapper.ser." + tableEng_U + "SerMapper;\n");
		sb.append("import com.gome.task.bean.TaskBasicConfig;\n");
		sb.append("import com.gome.task.step.IStep;\n");
		sb.append("import com.gome.task.utils.TaskConstants;\n");
		sb.append("import com.gomeplus.commons.quartz.task.SpartITask;\n");
		sb.append("import net.sf.json.JSONObject;\n");
		sb.append("/** \n");
		sb.append(" * @ClassName: " + taskClassName + "Task \n"); 
		sb.append(" * @Description: " + taskAsk );
		sb.append(" * @author GOME\n");
		sb.append(" * @date 2015年7月10日 上午9:52:11  \n");
		sb.append(" */\n");
		sb.append("@Service\n");
		sb.append("public class " + taskClassName + "Task implements IStep<" + tableEng_U + ">, SpartITask<" + tableEng_U + ">{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("	\n");
		sb.append("	@Autowired\n");
		sb.append("	private " + tableEng_U + "SerMapper " + tableEng_L + "SerMapper;\n");
		sb.append("	\n");
		sb.append("	@Resource\n");
		sb.append("	private ILogics<Map<String, Object>> " + taskClassName_L + "LockLogic;\n");
		sb.append("	\n");
		sb.append("	@Resource\n");
		sb.append("	private ILogics<Map<String, Object>> " + taskClassName_L + "UnLockLogic;\n");
		sb.append("	\n");
		sb.append("	@Resource\n");
		sb.append("	private ILogics<Map<String, Object>> " + taskClassName_L + "Logic;\n");
		sb.append("	\n");
		sb.append("	public List<" + tableEng_U + "> queryTaskInfo(TaskBasicConfig config) {\n");
		sb.append("		" + tableEng_U + " entity = new " + tableEng_U + "();\n");
		sb.append("		entity.set" + lockFlg_U + "(TaskConstants.LOCK_FLG_NO);\n");
		sb.append("		String timeStr = DateFormatUtils.format(new Date(), \"yyyyMMddHHmmss\");\n");
		sb.append("		entity.set" + lockTm_U + "(timeStr);\n");
		sb.append("		List<" + tableEng_U + "> taskList = " + tableEng_L + "SerMapper.queryList(entity);\n");
		sb.append("		return taskList;\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void runTask(" + tableEng_U + " task) {\n");
		sb.append("		Map<String, Object> map = new HashMap<String, Object>();\n");
		sb.append("		map.put(\"task\", task);\n");
		sb.append("		map.put(\"isUnlock\", \"error\");\n");
		sb.append("		try{\n");
		sb.append("			//任务锁定\n");
		sb.append("			if(!ResultEnum.PartOK.equals(" + taskClassName_L + "LockLogic.exec(map))){\n");
		sb.append("				return;\n");
		sb.append("			}\n");
		sb.append("			map.put(\"isUnlock\", \"true\");\n");
		sb.append("			\n");
		sb.append("			//业务处理\n");
		sb.append("			if(!ResultEnum.PartOK.equals(" + taskClassName_L + "Logic.exec(map))){\n");
		sb.append("				return;\n");
		sb.append("			}\n");
		sb.append("			//TODO 业务\n");
		sb.append("			\n");
		sb.append("		}catch(Exception e){\n");
		sb.append("			logger.error(\"" + taskAsk + "任务执行异常：" + tableKey_L + "=\"+task.get" + tableKey_U + "(),e);\n");
		sb.append("		}finally{\n");
		sb.append("			try {\n");
		sb.append("				/*9.逻辑解锁子任务*/\n");
		sb.append("				if(\"true\".equals(map.get(\"isUnlock\").toString())){\n");
		sb.append("					" + taskClassName_L + "UnLockLogic.exec(map);\n");
		sb.append("				}\n");
		sb.append("			} catch (GomeFinanceException e) {\n");
		sb.append("				e.printStackTrace();\n");
		sb.append("			}\n");
		sb.append("		}\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public boolean editTaskInfo(List<" + tableEng_U + "> list) {return false;}\n");
		sb.append("	\n");
		sb.append("	public boolean spart(" + tableEng_U + " taskConfig,JSONObject config) {return true;}\n");
		sb.append("	\n");
		sb.append("}\n");
		new FileTools().fileCreate(projectPath+"//step//impl//", taskClassName + "Task.java",sb.toString());
		
		//任务加锁类
		sb.setLength(0);
		sb.append("/**   \n");
		sb.append("* @Title: " + taskClassName + "LockLogic.java \n");
		sb.append("* @Package com.gome." + projectEng + ".step.impl \n");
		sb.append("* @Description:   " + taskAsk + "加锁");
		sb.append("* @author GOME   \n");
		sb.append("* @date 2015年2月10日 上午11:59:03 \n");
		sb.append("* @company GOME\n");
		sb.append("* @version V.1.0   \n");
		sb.append("*/\n");
		sb.append("package com.gome." + projectEng + ".logic.impl;\n");
		sb.append("\n");
		sb.append("import java.util.Date;\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("import cn.com.gome.frame.exception.GomeFinanceException;\n");
		sb.append("import cn.com.gome.frame.logic.ILogics;\n");
		sb.append("import cn.com.gome.frame.logic.ResultEnum;\n");
		sb.append("import cn.com.gome.frame.util.DateFormatUtils;\n");
		sb.append("import com.gome." + projectEng + ".dao.entity." + tableEng_U + ";\n");
		sb.append("import com.gome." + projectEng + ".dao.mapper.edit." + tableEng_U + "EditMapper;\n");
		sb.append("import com.gome.task.utils.TaskConstants;\n");
		sb.append("/** \n");
		sb.append(" * @ClassName: " + taskClassName + "LockLogic \n");
		sb.append(" * @Description:  " + taskAsk + "加锁\n");
		sb.append(" * @author GOME\n");
		sb.append(" * @date 2015年7月10日 上午9:52:11  \n");
		sb.append(" */\n");
		sb.append("@Service\n");
		sb.append("public class " + taskClassName + "LockLogic implements ILogics<Map<String, Object>>{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("	\n");
		sb.append("	@Autowired\n");
		sb.append("	private " + tableEng_U + "EditMapper " + tableEng_L + "EditMapper;\n");
		sb.append("	\n");
		sb.append("	public ResultEnum exec(Map<String, Object> map) throws GomeFinanceException {\n");
		sb.append("		" + tableEng_U + " task = (" + tableEng_U + ") map.get(\"task\");\n");
		sb.append("		try{\n");
		sb.append("			logger.info(\"" + taskAsk + "锁定开始," + tableKey_L + "=\"+task.get" + tableKey_U + "());\n");
		sb.append("			Map<String, String> lockMap = new HashMap<String, String>();\n");
		sb.append("			lockMap.put(\"" + tableKey_L + "\", String.valueOf(task.get" + tableKey_U + "()));\n");
		sb.append("			lockMap.put(\"" + lockFlg_L+ "\", TaskConstants.LOCK_FLG_NO);\n");
		sb.append("			lockMap.put(\"" + lockFlg_L+ "New\", TaskConstants.LOCK_FLG_ING);\n");
		sb.append("			String timeStr = DateFormatUtils.format(new Date(), \"yyyyMMddHHmmss\");\n");
		sb.append("			lockMap.put(\"" + lockTm_L + "\", timeStr);\n");
		sb.append("			int count = " + tableEng_L + "EditMapper.editInfo(lockMap);\n");
		sb.append("			if(count == 1){\n");
		sb.append("				logger.info(\"" + taskAsk + "锁定成功," + tableKey_L + "=\"+task.get" + tableKey_U + "());\n");
		sb.append("				return ResultEnum.PartOK;\n");
		sb.append("			}\n");
		sb.append("		}catch(Exception e){\n");
		sb.append("			logger.error(\"" + taskAsk + "加锁异常：" + tableKey_L + "=\"+task.get" + tableKey_U + "(),e);\n");
		sb.append("		}\n");
		sb.append("		return ResultEnum.PartCase01;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("}\n");
		new FileTools().fileCreate( projectPath + "//logic//impl//", taskClassName + "LockLogic.java",sb.toString());
				
		//任务解锁类
		sb.setLength(0);
		sb.append("/**    \n");
		sb.append("* @Title: " + taskClassName + "UnLockLogic.java \n");
		sb.append("* @Package com.gome." + projectEng + ".logic.impl \n");
		sb.append("* @Description:  " + taskAsk + "解锁\n");
		sb.append("* @author GOME   \n");
		sb.append("* @date 2015年2月10日 上午11:59:03 \n");
		sb.append("* @company GOME\n");
		sb.append("* @version V.1.0   \n");
		sb.append("*/\n");
		sb.append("package com.gome." + projectEng + ".logic.impl;\n");
		sb.append("\n");
		sb.append("import java.util.Date;\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("\n");
		sb.append("import cn.com.gome.frame.exception.GomeFinanceException;\n");
		sb.append("import cn.com.gome.frame.logic.ILogics;\n");
		sb.append("import cn.com.gome.frame.logic.ResultEnum;\n");
		sb.append("import cn.com.gome.frame.util.DateFormatUtils;\n");
		sb.append("import cn.com.gome.frame.util.DateUtils;\n");
		sb.append("\n");
		sb.append("import com.gome." + projectEng + ".dao.entity." + tableEng_U + ";\n");  
		sb.append("import com.gome." + projectEng + ".dao.mapper.edit." + tableEng_U + "EditMapper;\n");
		sb.append("import com.gome.task.utils.TaskConstants;\n");
		sb.append("\n");
		sb.append("/** \n");
		sb.append(" * @ClassName: " + taskClassName + "UnLockLogic \n");
		sb.append(" * @Description:  " + taskAsk + "解锁\n");
		sb.append(" * @author GOME\n");
		sb.append(" * @date 2015年7月10日 上午9:52:11  \n");
		sb.append(" */\n");
		sb.append("@Service\n");
		sb.append("public class " + taskClassName + "UnLockLogic implements ILogics<Map<String, Object>>{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("	\n");
		sb.append("	@Autowired\n");
		sb.append("	private " + tableEng_U + "EditMapper " + tableEng_L + "EditMapper;\n");
		sb.append("	\n");
		sb.append("	public ResultEnum exec(Map<String, Object> map) throws GomeFinanceException {\n");
		sb.append("		" + tableEng_U + " task = (" + tableEng_U + ") map.get(\"task\");\n");
		sb.append("		try{\n");
		sb.append("			logger.info(\"" + taskAsk + "任务解锁开始," + tableKey_L+ "=\"+task.get" + tableKey_U + "());\n");
		sb.append("			Map<String, String> lockMap = new HashMap<String, String>();\n");
		sb.append("			lockMap.put(\"" + tableKey_L + "\", String.valueOf(task.get" + tableKey_U + "()));\n");
		sb.append("			int runCount = task.get" + runCounts_U + "() + 1;\n");
		sb.append("			lockMap.put(\"" + runCounts_L + "New\", runCount+\"\");\n");
		sb.append("			String timeStr = DateFormatUtils.format(new Date(), \"yyyyMMddHHmmss\");\n");
		sb.append("			String " + lockTm_L + " = timeStr;\n");
		sb.append("			if(TaskConstants.LOCK_FLG_SUCC.equals(task.get" + lockFlg_U + "())){\n");
		sb.append("				lockMap.put(\"" + lockFlg_L + "New\", TaskConstants.LOCK_FLG_SUCC);\n");
		sb.append("			}else{\n");
		sb.append("				if(runCount <= 7){\n");
		sb.append("					lockMap.put(\"" + lockFlg_L + "New\", TaskConstants.LOCK_FLG_NO);\n");
		sb.append("					//当前时间\n");
		sb.append("					String currDateTime = DateUtils.getCurrDateTime();\n");
		sb.append("					// 推迟锁定时间为  执行次数*执行次数(分钟)\n");
		sb.append("					if(runCount < 4){\n");
		sb.append("						" + lockTm_L + " = DateUtils.countTime(currDateTime, runCount * runCount * 60/2);\n");
		sb.append("					}else{\n");
		sb.append("						" + lockTm_L + " = DateUtils.countTime(currDateTime, runCount * runCount * 60);\n");
		sb.append("					}\n");
		sb.append("				}else{\n");
		sb.append("					lockMap.put(\"" + lockFlg_L + "New\", TaskConstants.LOCK_FLG_FAIL);\n");
		sb.append("				}\n");
		sb.append("			}\n");
		sb.append("			lockMap.put(\"" + lockTm_L + "New\", lockTm);\n");
		sb.append("			lockMap.put(\"" + lockFlg_L + "\", TaskConstants.LOCK_FLG_ING);\n");
		sb.append("			\n");
		sb.append("			int count = " + tableEng_L + "EditMapper.editInfo(lockMap);\n");
		sb.append("			if(count == 1){\n");
		sb.append("				logger.info(\"" + taskAsk + "解锁成功," + tableKey_L + "=\"+task.get" + tableKey_U + "());\n");
		sb.append("				return ResultEnum.PartOK;\n");
		sb.append("			}\n");
		sb.append("		}catch(Exception e){\n");
		sb.append("			logger.error(\"" + taskAsk + "解锁异常：" + tableKey_L + "=\" + task.get" + tableKey_U + "(),e);\n");
		sb.append("		}\n");
		sb.append("		return ResultEnum.PartCase01;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("}\n");
		new FileTools().fileCreate( projectPath + "//logic//impl//", taskClassName + "UnLockLogic.java",sb.toString());
		
		//主业务处理类
		sb.setLength(0);
		sb.append("/**   \n");
		sb.append("* @Title: " + taskClassName + "Logic.java \n");
		sb.append("* @Package com.gome." + projectEng + ".step.impl \n");
		sb.append("* @Description:   " + taskAsk + "加锁");
		sb.append("* @author GOME   \n");
		sb.append("* @date 2015年2月10日 上午11:59:03 \n");
		sb.append("* @company GOME\n");
		sb.append("* @version V.1.0   \n");
		sb.append("*/\n");
		sb.append("package com.gome." + projectEng + ".logic.impl;\n");
		sb.append("\n");
		sb.append("import java.util.Date;\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import org.slf4j.Logger;\n");
		sb.append("import org.springframework.transaction.annotation.Transactional;\n");
		sb.append("import org.slf4j.LoggerFactory;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("import cn.com.gome.frame.exception.GomeFinanceException;\n");
		sb.append("import cn.com.gome.frame.logic.ILogics;\n");
		sb.append("import cn.com.gome.frame.logic.ResultEnum;\n");
		sb.append("import cn.com.gome.frame.util.DateFormatUtils;\n");
		sb.append("import com.gome." + projectEng + ".dao.entity." + tableEng_U + ";\n");
		sb.append("import com.gome." + projectEng + ".dao.mapper.edit." + tableEng_U + "EditMapper;\n");
		sb.append("import com.gome.task.utils.TaskConstants;\n");
		sb.append("/** \n");
		sb.append(" * @ClassName: " + taskClassName + "Logic \n");
		sb.append(" * @Description:  " + taskAsk + "业务处理\n");
		sb.append(" * @author GOME\n");
		sb.append(" * @date 2015年7月10日 上午9:52:11  \n");
		sb.append(" */\n");
		sb.append("@Service\n");
		sb.append("@Transactional\n");
		sb.append("public class " + taskClassName + "Logic implements ILogics<Map<String, Object>>{\n");
		sb.append("\n");
		sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
		sb.append("	\n");
		sb.append("	@Transactional(value = \"txManagercashier\", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)");
		sb.append("	\n");
		sb.append("	public ResultEnum exec(Map<String, Object> map) throws GomeFinanceException {\n");
		sb.append("		" + tableEng_U + " task = (" + tableEng_U + ") map.get(\"task\");\n");
		sb.append("		logger.info(\"" + taskAsk + "业务处理开始," + tableKey_L + "=\"+task.get" + tableKey_U + "());\n");
		sb.append("		return ResultEnum.PartCase01;\n");
		sb.append("	}\n");
		sb.append("	\n");
		sb.append("}\n");
		new FileTools().fileCreate( projectPath + "//logic//impl//", taskClassName + "Logic.java",sb.toString());
		
		return projectPath;
	}

}
