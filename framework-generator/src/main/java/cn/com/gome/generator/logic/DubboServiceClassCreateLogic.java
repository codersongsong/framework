package cn.com.gome.generator.logic;

import cn.com.gome.framework.dao.entity.TblChildProjectInfo;
import cn.com.gome.framework.dao.entity.TblDubboClassCreateConfig;
import cn.com.gome.framework.dao.entity.TblInterfaceInfo;
import cn.com.gome.framework.dao.entity.TblProjectBasicInfo;
import cn.com.gome.framework.dao.mapper.ser.TblChildProjectInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblInterfaceInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblProjectBasicInfoSerMapper;
import cn.com.gome.generator.bean.*;
import com.gomeplus.frame.exception.LogicsException;
import com.gomeplus.frame.logic.ResultEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DubboServiceClassCreateLogic extends AbstractLogic {
    @Resource
    private TblChildProjectInfoSerMapper tblChildProjectInfoSerMapper;
    @Resource
    private TblProjectBasicInfoSerMapper tblProjectBasicInfoSerMapper;
    @Autowired
    private TblInterfaceInfoSerMapper tblInterfaceInfoSerMapper;

    public ResultEnum exec(TblDubboClassCreateConfig dubboClassCreateConfig) throws LogicsException {
        try {
            //查询并校验数据
            Integer CHILD_PROJECT_CODE = dubboClassCreateConfig.getChildProjectCode();
            TblChildProjectInfo queryChildProjectInfo = new TblChildProjectInfo();
            queryChildProjectInfo.setChildProjectCode(CHILD_PROJECT_CODE);
            queryChildProjectInfo = tblChildProjectInfoSerMapper.queryObj(queryChildProjectInfo);
            if (queryChildProjectInfo == null) {
                return ResultEnum.PART_CASE_01;
            }
            TblProjectBasicInfo queryProjectBasicInfo = new TblProjectBasicInfo();
            queryProjectBasicInfo.setProjectCode(queryChildProjectInfo.getProjectCode());
            queryProjectBasicInfo = tblProjectBasicInfoSerMapper.queryObj(queryProjectBasicInfo);
            if (queryProjectBasicInfo == null) {
                return ResultEnum.PART_CASE_01;
            }
            TblInterfaceInfo interfaceInfo = new TblInterfaceInfo();
            interfaceInfo.setDubboId(String.valueOf(dubboClassCreateConfig.getClassNo()));
            List<TblInterfaceInfo> interfaceInfos = tblInterfaceInfoSerMapper.queryList(interfaceInfo);
            if (interfaceInfos == null || interfaceInfos.size() == 0) {
                return ResultEnum.PART_CASE_01;
            }

            String PROJECT_ENG = queryProjectBasicInfo.getProjectEng();
            String PROJECT_PATH = queryProjectBasicInfo.getProjectPath();
            String PACHAGE_PATH = queryProjectBasicInfo.getPackages().replace(".", "//");
            //封装参数
            String engName = queryChildProjectInfo.getChildProjectEng();
            String[] t = {PROJECT_ENG, PROJECT_PATH,engName.contains("service")?engName:"service" , PACHAGE_PATH, queryProjectBasicInfo.getPackages()};
            //创建Logic
            List<LogicPo> logicPoList = Lists.newArrayList();
            LogicPo checkLogic = LogicPo.create(dubboClassCreateConfig.getServiceCalss() + "CheckParamLogic",
                    dubboClassCreateConfig.getServiceCalssMethodAsk() + "参数检查").setNeedTx(false).setInterfaceInfos(interfaceInfos);
            logicPoList.add(checkLogic);

            String[] LogicClassArr = dubboClassCreateConfig.getLogicCalss().split(";");
            String[] LogicClassAskArr = dubboClassCreateConfig.getServiceCalssMethodAsk().split(";");
            for (int i = 0; i < LogicClassArr.length; i++) {
                LogicPo givenLogic = LogicPo.create()
                        .setLogicClassName(containLogic(LogicClassArr[i]))
                        .setLogicClassAsk(i <= LogicClassAskArr.length - 1 ? LogicClassAskArr[i] : dubboClassCreateConfig.getServiceCalssMethodAsk())
                        .setNeedTx(true)
                        .setInterfaceInfos(interfaceInfos);
                logicPoList.add(givenLogic);
                createLogic(givenLogic, t, false);
            }
            ServiceMethodPo serviceMethod = ServiceMethodPo.create()
                    .setMethodName(dubboClassCreateConfig.getServiceCalssMethod())
                    .setMethodAsk(dubboClassCreateConfig.getServiceCalssMethodAsk())
                    .setLogicList(logicPoList);

            ServicePo givenService = ServicePo.create()
                    .setServiceClassName(containService(dubboClassCreateConfig.getServiceCalss()))
                    .setServiceClassAsk(dubboClassCreateConfig.getServiceCalssMethodAsk())
                    .setServiceMethodList(Lists.newArrayList(serviceMethod))
                    .setLogicList(logicPoList);

            DubboMethodPo dubboMethod = DubboMethodPo.create()
                    .setMethodName(dubboClassCreateConfig.getDubboClassMethod())
                    .setMethodAsk(dubboClassCreateConfig.getDubboClassMethodAsk())
                    .setServiceMethod(dubboClassCreateConfig.getServiceCalssMethod())
                    .setServiceMethodAsk(dubboClassCreateConfig.getServiceCalssMethodAsk())
                    .setServicePo(givenService);

            DubboPo dubboPo = DubboPo.create()
                    .setDubboClassName(containFacadeService(dubboClassCreateConfig.getDubboClass()))
                    .setDubboServiceAsk(dubboClassCreateConfig.getDubboClassMethodAsk())
                    .setDubboMethodPoList(Lists.newArrayList(dubboMethod))
                    .setServiceList(Lists.newArrayList(givenService))
                    .setTblInterfaceInfoList(interfaceInfos);

            //创建dubbo服务
            createDubboInterface(dubboPo, t);
            //创建dubbo服务实现
            createDubboClassUtils(dubboPo, t);
            createDubboClass(dubboPo, t);
            //创建业务层接口
            createServiceInterface(givenService, t);
            //创建业务层实现
            createServiceClass(givenService, t);
            //创建Logic实现
            createLogic(checkLogic, t, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultEnum.OK;
    }

    private void createLogic(LogicPo logicPo, String[] t, boolean isDefault) throws IOException {
        String fileName = lowerToUpper(logicPo.getLogicClassName());
        String path = new StringBuilder(t[1])
                .append("//")
                //.append(t[0])
                //.append("//")
                .append(t[0])
                .append("-")
                .append(t[2])
                .append("//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//logic//impl//")
                .append(fileName)
                .append(".java").toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();

        sb.append("package " + t[4] + "." + PROJECT_ENG + ".logic.impl;\n");
        sb.append("\n");
        sb.append("import org.slf4j.Logger;\n");
        sb.append("import org.slf4j.LoggerFactory;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("\n");
        sb.append("import com.gomeplus.frame.exception.LogicsException;\n");
        sb.append("import com.gomeplus.frame.logic.ILogics;\n");
        sb.append("import com.gomeplus.frame.logic.ResultEnum;\n");
        sb.append("import org.apache.commons.lang.StringUtils;\n");
        sb.append("import java.util.regex.Pattern;\n");
        if (!isDefault) {
            sb.append("import org.springframework.transaction.annotation.Propagation;\n");
            sb.append("import org.springframework.transaction.annotation.Transactional;\n");
        }
        sb.append("\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + lowerToUpper(PROJECT_ENG) + "Constants;\n");
        sb.append("\n");
        sb.append("/** \n");
        sb.append("* @Title: " + logicPo.getLogicClassName() + ".java \n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".logic.impl \n");
        sb.append("* @Description: " + logicPo.getLogicClassAsk() + " \n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + lowerToUpper(PROJECT_ENG) + " \n");
        sb.append("* @company cn.com.gome\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("@Service\n");
        if (logicPo.isNeedTx()) {
            sb.append("@Transactional\n");
        }
        sb.append("public class " + fileName + " implements ILogics<" + PROJECT_ENG_U + "ParametersVo> {\n");
        sb.append("	\n");
        sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
        sb.append("\n");
        sb.append("@Override\n");
        if (!isDefault) {
            sb.append("	@Transactional(value = \"txManager" + lowerToUpper(t[0]) + "\", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)\n");
        }
        sb.append("	public ResultEnum exec(" + PROJECT_ENG_U + "ParametersVo vo) throws LogicsException {\n");
        sb.append("		try{\n");
        if (isDefault) {
            sb.append("			if (StringUtils.isEmpty(vo.getVersion())) {\n")
                    .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                    .append("			    vo.setResDesc(\"版本号version不能为空\");\n")
                    .append("			    return ResultEnum.PART_CASE_01;\n")
                    .append("			}\n")
                    .append("			if (StringUtils.isEmpty(vo.getSources())) {\n")
                    .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                    .append("			    vo.setResDesc(\"来源sources不能为空\");\n")
                    .append("			    return ResultEnum.PART_CASE_01;\n")
                    .append("			 }\n")
                    .append("			if (StringUtils.isEmpty(vo.getLogAndKey())) {\n")
                    .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                    .append("			    vo.setResDesc(\"日志关联logAndKey不能为空\");\n")
                    .append("			    return ResultEnum.PART_CASE_01;\n")
                    .append("			}\n")
                    .append(getParamCheck(logicPo.getInterfaceInfos(),PROJECT_ENG))
                    .append("			\n");
        }
        sb.append("			\n");
        sb.append("			//TODO 业务逻辑\n");
        sb.append("			\n");
        sb.append("			return ResultEnum.OK;\n");
        sb.append("		}catch(Exception e){\n");
        sb.append("			logger.error(\"[" + logicPo.getLogicClassAsk() + "]接口调用,业务节点异常：\" , e);\n");
        sb.append("		}\n");
        sb.append("		return ResultEnum.PART_CASE_01;\n");
        sb.append("	}\n");
        sb.append("\n");
        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    private void createServiceClass(ServicePo servicePo, String[] t) throws IOException {
        String fileName = lowerToUpper(servicePo.getServiceClassName());
        String path = new StringBuilder(t[1])
                //.append("//")
                //.append(t[0])
                .append("//")
                .append(t[0])
                .append("-")
                .append(t[2])
                .append("//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//service//impl//")
                .append(fileName)
                .append("Impl.java").toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("package " + t[4] + "." + PROJECT_ENG + ".service.impl;\n");
        sb.append("\n");
        sb.append("import javax.annotation.Resource;\n");
        sb.append("import org.slf4j.Logger;\n");
        sb.append("import org.slf4j.LoggerFactory;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import com.gomeplus.frame.exception.LogicsException;\n");
        sb.append("import com.gomeplus.frame.logic.ILogics;\n");
        sb.append("import com.gomeplus.frame.logic.ResultEnum;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".service." + fileName + ";\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append("* @Title: " + fileName + "Impl.java\n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".service.impl\n");
        sb.append("* @Description: " + servicePo.getServiceClassAsk() + "\n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + getCodeGenerateTime() + " \n");
        sb.append("* @company cn.com.gome\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("@Service\n");
        sb.append("public class " + fileName + "Impl implements " + fileName + " {\n");
        sb.append("\n");
        sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
        sb.append("	\n");

        for (LogicPo logicPo : servicePo.getLogicList()) {
            sb.append("	@Resource\n");
            sb.append("	private ILogics<" + PROJECT_ENG_U + "ParametersVo> " + upperToLower(logicPo.getLogicClassName()) + ";\n");
            sb.append("	\n");
        }
        for (ServiceMethodPo serviceMethodPo : servicePo.getServiceMethodList()) {
            sb.append("	/**\n");
            sb.append("	 * @Title: " + serviceMethodPo.getMethodName() + "\n");
            sb.append("	 * @Description: " + serviceMethodPo.getMethodAsk() + "\n");
            sb.append("	 * @param vo\n");
            sb.append("	 * @return " + PROJECT_ENG_U + "ParametersVo\n");
            sb.append("	 * @throws\n");
            sb.append("	 */\n");
            sb.append("	 @Override\n");
            sb.append("	public " + PROJECT_ENG_U + "ParametersVo " + upperToLower(serviceMethodPo.getMethodName()) + "(" + PROJECT_ENG_U + "ParametersVo vo) {\n");
            sb.append("		try{\n");
            sb.append("			\n");

            for (LogicPo logicPo : serviceMethodPo.getLogicList()) {
                sb.append("			if(!ResultEnum.OK.equals(" + upperToLower(logicPo.getLogicClassName()) + ".exec(vo))){\n");
                sb.append("				logger.info(\"[" + logicPo.getLogicClassAsk() + "]接口调用,失败...\");\n");
                sb.append("				return vo;\n");
                sb.append("			}\n");
                sb.append("			\n");
            }

            sb.append("		}catch(LogicsException e){\n");
            sb.append("			logger.error(\"[" + servicePo.getServiceClassAsk() + "]接口调用异常:\",e);\n");
            sb.append("			vo.setResCode(e.getCode());\n");
            sb.append("			vo.setResDesc(e.getMessage());\n");
            sb.append("		}\n");
            sb.append("		return vo;\n");
            sb.append("	}\n");
        }

        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    private void createServiceInterface(ServicePo servicePo, String[] t) throws IOException {
        String fileName = lowerToUpper(servicePo.getServiceClassName());
        String path = new StringBuilder(t[1])
                //.append("//")
                //.append(t[0])
                .append("//")
                .append(t[0])
                .append("-")
                .append(t[2])
                .append("//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//service//")
                .append(fileName)
                .append(".java").toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();

        sb.append("package " + t[4] + "." + PROJECT_ENG + ".service;\n");
        sb.append("\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append("* @Title: " + fileName + ".java\n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".service\n");
        sb.append("* @Description: " + servicePo.getServiceClassAsk() + "\n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + getCodeGenerateTime() + " \n");
        sb.append("* @company cn.com.gome\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("public interface " + fileName + " {\n");
        sb.append("\n");
        for (ServiceMethodPo serviceMethodPo : servicePo.getServiceMethodList()) {
            sb.append("	/**\n");
            sb.append("	 * @Title: " + serviceMethodPo.getMethodName() + "\n");
            sb.append("	 * @Description: " + serviceMethodPo.getMethodAsk() + "\n");
            sb.append("	 * @param vo\n");
            sb.append("	 * @return " + PROJECT_ENG_U + "ParametersVo\n");
            sb.append("	 * @throws\n");
            sb.append("	 */\n");
            sb.append("	" + PROJECT_ENG_U + "ParametersVo " + upperToLower(serviceMethodPo.getMethodName()) + "(" + PROJECT_ENG_U + "ParametersVo vo) ;\n");
        }
        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    /**
     * 创建dubbo接口
     *
     * @param t
     * @throws IOException
     */
    private void createDubboInterface(DubboPo dubboPo, String[] t) throws IOException {
        String fileName = lowerToUpper(dubboPo.getDubboClassName());
        String ask = dubboPo.getDubboServiceAsk();
        String path = new StringBuilder(t[1])
                //.append("//")
                //.append(t[0])
                .append("//")
                .append(t[0])
                .append("-dubbo//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//dubbo//")
                .append(fileName)
                .append(".java").toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();

        sb.append("package " + t[4] + "." + PROJECT_ENG + ".dubbo;\n");
        sb.append("\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append("* @Title: " + fileName + ".java\n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".dubbo\n");
        sb.append("* @Description: " + ask + "\n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + getCodeGenerateTime() + " \n");
        sb.append("* @company " + t[4] + "\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("public interface " + fileName + " {\n");
        sb.append("\n");

        for (DubboMethodPo dubboMethodPo : dubboPo.getDubboMethodPoList()) {
            sb.append("	/**\n");
            sb.append("	 * @Title: " + dubboMethodPo.getMethodName() + "\n");
            sb.append("	 * @Description: " + dubboMethodPo.getMethodAsk() + "\n");
            sb.append("	 * @param  vo\n");
            sb.append("	 * @return " + PROJECT_ENG_U + "ParametersVo \n");
            sb.append("	 * @throws\n");
            sb.append("	 */\n");
            sb.append("	" + PROJECT_ENG_U + "ParametersVo " + upperToLower(dubboMethodPo.getMethodName()) + "(" + PROJECT_ENG_U + "ParametersVo vo) ;\n");
        }
        sb.append("\n");
        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    private void createDubboClass(DubboPo dubboPo, String[] t) throws IOException {
        String fileName = lowerToUpper(dubboPo.getDubboClassName());
        String ask = dubboPo.getDubboServiceAsk();
        String path = new StringBuilder(t[1])
                //.append("//")
                //.append(t[0])
                .append("//")
                .append(t[0])
                .append("-")
                .append(t[2])
                .append("//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//dubbo//impl//")
                .append(fileName)
                .append("Impl.java")
                .toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();

        sb.append("package " + t[4] + "." + PROJECT_ENG + ".dubbo.impl;\n");
        sb.append("\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".util.ParameterUtils;\n");
        sb.append("import org.slf4j.Logger;\n");
        sb.append("import org.slf4j.LoggerFactory;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import com.alibaba.dubbo.rpc.RpcContext;\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import com.google.common.base.Throwables;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "Constants;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo." + fileName + ";\n");
        //service引包
        for (ServicePo servicePo : dubboPo.getServiceList()) {
            sb.append("import " + t[4] + "." + PROJECT_ENG + ".service." + lowerToUpper(servicePo.getServiceClassName()) + ";\n");
        }
        sb.append("\n");
        sb.append("/**\n");
        sb.append("* @Title: " + fileName + "Impl.java\n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".dubbo.impl\n");
        sb.append("* @Description: " + ask + "\n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + getCodeGenerateTime() + " \n");
        sb.append("* @company cn.com.gome\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("@Service\n");
        sb.append("public class " + fileName + "Impl implements " + fileName + " {\n");
        sb.append("\n");
        sb.append("	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
        sb.append("	\n");
        //service注入
        for (ServicePo servicePo : dubboPo.getServiceList()) {
            sb.append("	@Autowired\n");
            sb.append("	private " + lowerToUpper(servicePo.getServiceClassName()) + " " + upperToLower(servicePo.getServiceClassName()) + ";\n");
            sb.append("	\n");
        }
        //service方法
        for (DubboMethodPo dubboMethodPo : dubboPo.getDubboMethodPoList()) {
            sb.append("	/**\n");
            sb.append("	 * @Title: " + dubboMethodPo.getMethodName() + "\n");
            sb.append("	 * @Description: " + dubboMethodPo.getMethodAsk() + "\n");
            sb.append("	 * @param  vo\n");
            sb.append("	 * @return " + PROJECT_ENG_U + "ParametersVo\n");
            sb.append("	 * @throws\n");
            sb.append("	 */\n");
            sb.append("    @Override\n");
            sb.append("    public " + PROJECT_ENG_U + "ParametersVo " + dubboMethodPo.getMethodName() + "(" + PROJECT_ENG_U + "ParametersVo vo) {\n");
            sb.append("        String clientIp = RpcContext.getContext().getRemoteHost();\n");
            sb.append("		   String logKey = vo.getLogAndKey();\n");
            sb.append("		   String voStr = ParameterUtils.paramFilter(\"" + getParamStr(dubboPo.getTblInterfaceInfoList()) + "\", vo);//过滤不许打印的参数\n");
            sb.append("        logger.info(\"[" + dubboMethodPo.getMethodAsk() + "]接口调用开始，客户端IP：{}， 日志关联KEY：{}， vo：{}\", clientIp, logKey, voStr);\n");
            sb.append("		   long startTime = System.currentTimeMillis();\n");
            sb.append("		   try{\n");
            sb.append("            vo = " + upperToLower(dubboMethodPo.getServicePo().getServiceClassName()) + "." + dubboMethodPo.getServiceMethod() + "(vo);\n");
            sb.append("		   }catch(Exception e){\n");
            sb.append("			    logger.error(\"[" + dubboMethodPo.getMethodAsk() + "]接口调用， 日志关联KEY：{}，异常：{}\" , logKey , Throwables.getStackTraceAsString(e));\n");
            sb.append("			    vo.setResCode(" + PROJECT_ENG_U + "Constants.RESULT_FAIL);\n");
            sb.append("			    vo.setResDesc(\"" + dubboMethodPo.getMethodAsk() + "服务接口调用异常\");\n");
            sb.append("		   }finally{\n");
            sb.append("		   vo = ParameterUtils.removeParams(\"" + getParamStr(dubboPo.getTblInterfaceInfoList()) + "\", vo);//删除不需要回传的入参\n");
            sb.append("			    logger.info(\"[" + dubboMethodPo.getMethodAsk() + "]接口调用,执行时长:{},返回参数：{}\" , (System.currentTimeMillis()-startTime) , vo);\n");
            sb.append("		   }\n");
            sb.append("		   return vo;\n");
            sb.append("    }\n");
            sb.append("\n");
        }
        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    private void createDubboClassUtils(DubboPo dubboPo, String[] t) throws IOException {
        String fileName = "ParameterUtils";
        String path = new StringBuilder(t[1])
                //.append("//")
                //.append(t[0])
                .append("//")
                .append(t[0])
                .append("-")
                .append(t[2])
                .append("//src//main//java//")
                .append(t[3])
                .append("//")
                .append(t[0])
                .append("//util//")
                .append(fileName)
                .append(".java")
                .toString();
        String PROJECT_ENG = t[0].toLowerCase();
        String PROJECT_ENG_U = columnToStringU(t[0].trim());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
//            return;
        }
        StringBuffer sb = new StringBuffer();

        sb.append("package " + t[4] + "." + PROJECT_ENG + ".util;\n");
        sb.append("\n");
        sb.append("import org.apache.commons.lang.StringUtils;\n");
        sb.append("import org.slf4j.Logger;\n");
        sb.append("import org.slf4j.LoggerFactory;\n");
        sb.append("import org.springframework.stereotype.Service;\n");
        sb.append("import " + t[4] + "." + PROJECT_ENG + ".dubbo.bean." + PROJECT_ENG_U + "ParametersVo;\n");
        sb.append("\n");
        sb.append("/**\n");
        sb.append("* @Title: " + fileName + ".java\n");
        sb.append("* @Package " + t[4] + "." + PROJECT_ENG + ".util\n");
        sb.append("* @Description: (参数处理)\n");
        sb.append("* @author GOME   \n");
        sb.append("* @date " + getCodeGenerateTime() + " \n");
        sb.append("* @company cn.com.gome\n");
        sb.append("* @version V1.0   \n");
        sb.append("*/ \n");
        sb.append("@Service\n");
        sb.append("public class " + fileName + " {\n");
        sb.append("\n");
        sb.append("	   private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());\n");
        sb.append("	\n");
        sb.append("	   private " + fileName + "() {\n");
        sb.append("        //防止外部类实例化使用\n");
        sb.append("	   }\n");
        sb.append("	\n");
        sb.append("	\n");
        sb.append("	   /**\n");
        sb.append("	    * 打印入参前过滤参数\n");
        sb.append("	    * @param paramStr\n");
        sb.append("	    * @param vo\n");
        sb.append("	    * @return\n");
        sb.append("	    */\n");
        sb.append("    public static String paramFilter(String paramStr," + PROJECT_ENG_U + "ParametersVo vo){\n");
        sb.append("\n");
        sb.append("    StringBuilder stringBuilder = null;\n");
        sb.append("    if (StringUtils.isEmpty(paramStr)) {\n");
        sb.append("    return null;\n");
        sb.append("    }\n");
        sb.append("    try {\n");
        sb.append("    String[] paramArr = paramStr.split(\", \");\n");
        sb.append("    stringBuilder = new StringBuilder();\n");
        sb.append("    for (int i=0;i<paramArr.length;i++) {\n");
        sb.append("    stringBuilder.append(vo.getString(paramArr[i]));\n");
        sb.append("    }\n");
        sb.append("    } catch (Exception e) {\n");
        sb.append("    logger.error(\" 过滤参数出错！logANDKey:{},错误原因:{}\",vo.getLogAndKey(),e);\n");
        sb.append("    return vo.toString();\n");
        sb.append("    }\n");
        sb.append("\n");
        sb.append("    return stringBuilder.toString();\n");
        sb.append("    }\n");
        sb.append("	\n");
        sb.append("	\n");
        sb.append("	   /**\n");
        sb.append("	    * 返回Vo时删除包含的入参\n");
        sb.append("	    * @param paramStr\n");
        sb.append("	    * @param vo\n");
        sb.append("	    * @return\n");
        sb.append("	    */\n");
        sb.append("    public static MountainParametersVo removeParams(String paramStr," + PROJECT_ENG_U + "ParametersVo vo){\n");
        sb.append("\n");
        sb.append("    try {\n");
        sb.append("    if (StringUtils.isEmpty(paramStr)) {\n");
        sb.append("    return null;\n");
        sb.append("    }\n");
        sb.append("    String[] paramArr = paramStr.split(\", \");\n");
        sb.append("    for (int i=0;i<paramArr.length;i++) {\n");
        sb.append("    vo.remove(paramArr[i]);\n");
        sb.append("    }\n");
        sb.append("    } catch (Exception e) {\n");
        sb.append("    logger.error(\" 删除入参出错！logAndKey:{},错误原因:{}\",vo.getLogAndKey(),e);\n");
        sb.append("    return vo;\n");
        sb.append("    }\n");
        sb.append("\n");
        sb.append("    return vo;\n");
        sb.append("    }\n");
        sb.append("    }\n");


        sb.append("}\n");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    public String getParamStr(List<TblInterfaceInfo> interfaceInfos) {
        if (interfaceInfos == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < interfaceInfos.size(); i++) {
            sb.append(interfaceInfos.get(i).getParamname());
            if (i < interfaceInfos.size() - 1) {
                sb.append(",");
            }
        }

        return sb != null ? sb.toString() : null;
    }

    public String containService(String str) {
        if (str.length() <= 7) {
            return str;
        }
        String nameSubStr = str.substring(str.length() - 7, str.length());
        return nameSubStr.equals("Service") ? str : str + "Service";
    }

    public String containFacadeService(String str) {
        if (str.length() <= 13) {
            return str;
        }
        String nameSubStr = str.substring(str.length() - 13, str.length());
        return nameSubStr.equals("FacadeService") ? str : str + "FacadeService";
    }

    public String containLogic(String str) {
        if (str.length() <= 5) {
            return str;
        }
        String nameSubStr = str.substring(str.length() - 5, str.length());
        return nameSubStr.equals("Logic") ? str : str + "Logic";
    }

    public StringBuilder getParamCheck(List<TblInterfaceInfo> interfaceInfos,String PROJECT_ENG){
        StringBuilder sb = new StringBuilder();
        TblInterfaceInfo interfaceInfo ;
        for (int i=0;i<interfaceInfos.size();i++) {
            interfaceInfo = interfaceInfos.get(i);
            String paramType = interfaceInfo.getParamtype();
            String paramName = interfaceInfo.getParamname();
            String isRequired = interfaceInfo.getIsRequire();
            String isLength = interfaceInfo.getIsLength();
            String isRegex = interfaceInfo.getRegex();
            if (!"String".equals(paramType)) {
                continue;
            }
            if ("version".equals(paramName)) {
                continue;
            }
            if ("sources".equals(paramName)) {
                continue;
            }
            if ("logAndKey".equals(paramName)) {
                continue;
            }

            if ("Y".equals(isRequired)) {//非空判断
                sb.append("			if (StringUtils.isEmpty(vo.getString(\""+paramName+"\"))) {\n")
                        .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                        .append("			    vo.setResDesc(\""+paramName+"不能为空\");\n")
                        .append("			    return ResultEnum.PART_CASE_01;\n")
                        .append("			 }\n");
            }
            if (StringUtils.isNotEmpty(isLength)) {//字符串长度判断
                sb.append("			if (vo.getString(\""+paramName+"\").length()"+isLength+") {\n")
                        .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                        .append("			    vo.setResDesc(\""+paramName+"长度出错\");\n")
                        .append("			    return ResultEnum.PART_CASE_01;\n")
                        .append("			 }\n");
            }
            if (StringUtils.isNotEmpty(isRegex)) {//正则格式判断
                sb.append("			if (!Pattern.compile(\""+isRegex+"\").matcher(vo.getString(\"" + paramName + "\")).find()) {\n")
                        .append("			    vo.setResCode(" + lowerToUpper(PROJECT_ENG) + "Constants.RESULT_FAIL);\n")
                        .append("			    vo.setResDesc(\""+paramName+"格式出错\");\n")
                        .append("			    return ResultEnum.PART_CASE_01;\n")
                        .append("			 }\n");
            }

        }

        return sb;

    }


}
