package com.gomeplus.framework.admin.logic.impl;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.entity.TblTaskObjectRegister;
import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskObjectRegisterEditMapper;
import cn.com.gome.framework.dao.mapper.edit.TblTaskRunningLogEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskObjectRegisterSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;
import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhumenglong on 2017/6/28.
 */
@Service
public class ClearDirtyDataLogic implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger("ClearDirtyDataLogic");
    @Autowired
    private TblTaskRunningLogEditMapper tblTaskRunningLogEditMapper;
    @Autowired
    private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;
    @Autowired
    private TblTaskObjectRegisterEditMapper tblTaskObjectRegisterEditMapper;
    @Autowired
    private TblTaskObjectRegisterSerMapper tblTaskObjectRegisterSerMapper;

    private final int EXPIRE_TIME = 5;

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        public Thread newThread(Runnable r) {
            return new Thread(r, "ClearDirtyDataPool");
        }
    });
    public void afterPropertiesSet() throws Exception {
       // scheduledExecutorService.scheduleWithFixedDelay(new DirtyDataTask(), 60 , 60 , TimeUnit.SECONDS );
        scheduledExecutorService.scheduleWithFixedDelay(new DirtyDataTask(), 10 , 30 , TimeUnit.SECONDS );
    }

    class DirtyDataTask implements Runnable {

        public void run() {
            //定时清除脏数据
            logger.info("定时清除脏数据--服务端先重启，业务端再重启的情况");
            TblTaskObjectRegister tblTaskObjectRegister = new TblTaskObjectRegister();
            tblTaskObjectRegister.setObjectStatus(QuartzConstants.DDL_STATUS_010);
            List<TblTaskObjectRegister> tblTaskObjectRegisterList =  tblTaskObjectRegisterSerMapper.queryList(tblTaskObjectRegister);
            for(TblTaskObjectRegister  taskObjectRegister : tblTaskObjectRegisterList){
                Date upTimeDate  = null;
                Date now = new Date();
                try{
                    upTimeDate = DateUtils.parseDate(taskObjectRegister.getUpTime(), new String[]{"yyyyMMddHHmmss"});
                }catch (Exception e){
                    logger.error("字符串转日志error:{}",e );
                }
                upTimeDate = DateUtils.addMinutes(upTimeDate,EXPIRE_TIME);
                if(upTimeDate.compareTo(now) == -1){//更新时间加10分钟依然小于当前时间，设置log状态为作废
                    taskObjectRegister.setObjectStatus(QuartzConstants.DDL_STATUS_020);
                    tblTaskObjectRegisterEditMapper.edit(taskObjectRegister);
                    Map param = new HashMap();
                    param.put("ddlStatusNew",QuartzConstants.DDL_STATUS_040);
                    param.put("taskRunServer",taskObjectRegister.getTaskRunServer());
                    param.put("taskRunPort",taskObjectRegister.getTaskRunPort());
                    tblTaskRunningLogEditMapper.editInfo(param);
                    logger.info("设置log状态为作废,taskRunServer={}，taskRunPort={}",taskObjectRegister.getTaskRunServer() ,taskObjectRegister.getTaskRunPort());

                }
            }
        }

    }
}
