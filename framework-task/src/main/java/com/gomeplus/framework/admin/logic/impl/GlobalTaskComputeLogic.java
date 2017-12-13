package com.gomeplus.framework.admin.logic.impl;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;
import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhumenglong on 2017/6/28.
 */
@Service
public class GlobalTaskComputeLogic implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger("GlobalTaskComputeLogic");
    @Autowired
    private TblTaskConfigInfoSerMapper tblTaskConfigInfoSerMapper;
    @Autowired
    private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;


    private Random random= new Random();

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        public Thread newThread(Runnable r) {
            return new Thread(r, "GlobalTaskComputePool"  );
        }
    });
    public void afterPropertiesSet() throws Exception {

      //  scheduledExecutorService.scheduleWithFixedDelay(new GlobalTask(), 60*2 , 60 , TimeUnit.SECONDS );
        //scheduledExecutorService.scheduleWithFixedDelay(new GlobalTask(), 10 , 30 , TimeUnit.SECONDS );
        scheduledExecutorService.scheduleAtFixedRate(new GlobalTask(), 10 , 30 , TimeUnit.SECONDS );
    }


    class  GlobalTask implements Runnable{

        public void run() {

            try{
                TblTaskConfigInfo taskConfigInfo = new TblTaskConfigInfo();
                taskConfigInfo.setStatus(1);//1启用状态
                taskConfigInfo.setFullTaskCount(1);
                List<TblTaskConfigInfo> tblTaskConfigInfoList = tblTaskConfigInfoSerMapper.queryList(taskConfigInfo);
                for(TblTaskConfigInfo tblTaskConfigInfo : tblTaskConfigInfoList){
                    TblTaskRunningLog  tblTaskRunningLog = new TblTaskRunningLog();
                    tblTaskRunningLog.setTaskNo(tblTaskConfigInfo.getTaskNo());
                    List<TblTaskRunningLog> tblTaskRunningLogList =  tblTaskRunningLogSerMapper.queryList(tblTaskRunningLog);
                    if(CollectionUtils.isEmpty(tblTaskRunningLogList)){//还没有当前任务的实例日志，跳过等待下次执行
                        logger.info("全局唯一性任务,还没有当前任务的实例日志，跳过等待下次执行");
                        continue;
                    }
                    tblTaskRunningLog.setDdlStatus(QuartzConstants.DDL_STATUS_010);//查询运行中实例
                    List<TblTaskRunningLog> oneList =  tblTaskRunningLogSerMapper.queryList(tblTaskRunningLog);
                    JSONObject tblTaskConfigInfoJson =  JSONObject.fromObject(tblTaskConfigInfo);
                    NettyMessage nettyMessage = new NettyMessage();
                    nettyMessage.setBusinessType(QuartzConstants.BUSINESS_TYPE_HOLD_ONE);
                    nettyMessage.setJsonObject(QuartzConstants.BUSINESS_TYPE_HOLD_ONE,tblTaskConfigInfoJson);
                    if(CollectionUtils.isEmpty(oneList)){//没有全局唯一性任务实例，需要启动一个
                        logger.info("全局唯一性任务 taskNo:{}需要启动一个",tblTaskConfigInfo.getTaskNo());
                        Map param = new HashMap();
                        param.put("taskNo",tblTaskConfigInfo.getTaskNo());
                        param.put("ddlStatus",new String[]{QuartzConstants.DDL_STATUS_020 , QuartzConstants.DDL_STATUS_030});
                        List<TblTaskRunningLog> okRuningLogList =  tblTaskRunningLogSerMapper.queryAddrList(param);
                        if(!CollectionUtils.isEmpty(okRuningLogList) && okRuningLogList.size() > 0){
                            TblTaskRunningLog runningLog =  okRuningLogList.get(random.nextInt(okRuningLogList.size()));
                            nettyMessage.setJsonObject("oneTaskRunType","true");
                            NettyServerStartup.getInstance().sendMsg(runningLog.getTaskRunServer(),runningLog.getTaskRunPort(),nettyMessage);
                            logger.info("全局唯一性任务 taskNo:{}需要启动结束,ip={},port={}",
                                    tblTaskConfigInfo.getTaskNo(),runningLog.getTaskRunServer(),runningLog.getTaskRunPort());
                        }
                    }else {
                        if( 1 == oneList.size()){//全局唯一性任务已启动
                            logger.info("全局唯一性任务 taskNo:{}已启动,跳过",tblTaskConfigInfo.getTaskNo());
                            continue;
                        }else if(1 < oneList.size()){//全局唯一性任务有多个实例，需要只保留1个，杀掉其他的
                            int keepIndex =  random.nextInt(oneList.size());
                            logger.info("全局唯一性任务 taskNo:{}有多个实例，只保留1个，杀掉其他的",tblTaskConfigInfo.getTaskNo());
                            for(int i=0 ; i<oneList.size() ; i++){
                                nettyMessage.setJsonObject("oneTaskRunType","false");
                                if(i!= keepIndex){
                                    TblTaskRunningLog runningLog = oneList.get(i);
                                    logger.info("全局唯一性任务 taskNo:{}有多个实例，杀掉ip={},port={}",tblTaskConfigInfo.getTaskNo(),runningLog.getTaskRunServer(),runningLog.getTaskRunPort());
                                    NettyServerStartup.getInstance().sendMsg(runningLog.getTaskRunServer(),runningLog.getTaskRunPort(),nettyMessage);
                                }
                            }
                            logger.info("全局唯一性任务 taskNo:{}有多个实例，只保留1个，杀掉其他的，执行结束",tblTaskConfigInfo.getTaskNo());
                        }
                    }
                }
            }catch (Exception e){
                logger.info("全局唯一性任务 执行过程中发生error:{}",e);
            }
        }
    }
}
