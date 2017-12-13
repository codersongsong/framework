package com.gomeplus.framework.admin.logic.impl;

import cn.com.gome.framework.dao.entity.TblTaskConfigInfo;
import cn.com.gome.framework.dao.entity.TblTaskPartConfig;
import cn.com.gome.framework.dao.entity.TblTaskRunningLog;
import cn.com.gome.framework.dao.mapper.edit.TblTaskPartConfigEditMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskConfigInfoSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskPartConfigSerMapper;
import cn.com.gome.framework.dao.mapper.ser.TblTaskRunningLogSerMapper;
import com.gomeplus.commons.quartz.cache.QuartzConstants;
import com.gomeplus.commons.quartz.netty.NettyMessage;
import com.gomeplus.commons.quartz.netty.server.NettyServerStartup;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by zhumenglong on 2017/6/28.
 */
@Service
public class TaskPartComputeLogic implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger("TaskPartComputeLogic");
    @Autowired
    private TblTaskConfigInfoSerMapper tblTaskConfigInfoSerMapper;
    @Autowired
    private TblTaskRunningLogSerMapper tblTaskRunningLogSerMapper;

    @Autowired
    private TblTaskPartConfigSerMapper tblTaskPartConfigSerMapper;

    @Autowired
    private TblTaskPartConfigEditMapper tblTaskPartConfigEditMapper;

    private final static String SEPARATOR = ",";

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        public Thread newThread(Runnable r) {
            return new Thread(r, "TaskPartComputePool");
        }
    });

    public void afterPropertiesSet() throws Exception {

        scheduledExecutorService.scheduleWithFixedDelay(new PartComputeTask(), 60*2 , 60 , TimeUnit.SECONDS );
       // scheduledExecutorService.scheduleWithFixedDelay(new PartComputeTask(), 10, 30, TimeUnit.SECONDS);
    }

    class PartComputeTask implements Runnable {

        private void addOrUpdateTblTaskPartConfig(TblTaskRunningLog tblTaskRunningLog, TblTaskPartConfig tblTaskPartConfig, String partValue) {
            TblTaskPartConfig partConfig = new TblTaskPartConfig();
            partConfig.setTaskNo(tblTaskRunningLog.getTaskNo());
            partConfig.setObjTaskKey(tblTaskRunningLog.getObjTaskName());
            partConfig.setTaskRunServer(tblTaskRunningLog.getTaskRunServer());
            partConfig.setTaskRunPort(tblTaskRunningLog.getTaskRunPort());
            partConfig = tblTaskPartConfigSerMapper.queryObj(partConfig);
            if (partConfig == null) {
                partConfig = new TblTaskPartConfig();
                partConfig.setTaskNo(tblTaskRunningLog.getTaskNo());
                partConfig.setObjTaskKey(tblTaskRunningLog.getObjTaskName());
                partConfig.setTaskRunServer(tblTaskRunningLog.getTaskRunServer());
                partConfig.setTaskRunPort(tblTaskRunningLog.getTaskRunPort());
                partConfig.setPartValue(partValue);
                partConfig.setModeValue(tblTaskPartConfig.getModeValue());
                partConfig.setObjectTal(tblTaskRunningLog.getObjectTal());
                partConfig.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                tblTaskPartConfigEditMapper.save(partConfig);
            } else {
                partConfig.setObjectTal(tblTaskRunningLog.getObjectTal());
                partConfig.setUpTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                partConfig.setPartValue(partValue);
                partConfig.setModeValue(tblTaskPartConfig.getModeValue());
                tblTaskPartConfigEditMapper.edit(partConfig);
            }
        }

        private void addOrUpdatePart(String[] modeArr, List<TblTaskRunningLog> tblTaskRunningLogList, TblTaskPartConfig tblTaskPartConfig,Boolean keepOldValue) {
            List<String> groupList = computeGroupList(modeArr, tblTaskRunningLogList.size());//当分隔符小于实例个数，会有分配不到的情况
            NettyMessage nettyMessage = new NettyMessage();
            nettyMessage.setBusinessType(QuartzConstants.TASK_PART_SET);
            Collections.shuffle(tblTaskRunningLogList);
            for (int i = 0; i < tblTaskRunningLogList.size(); i++) {//剩下的重新分配
                TblTaskRunningLog tblTaskRunningLog = tblTaskRunningLogList.get(i);
                nettyMessage.setJsonObject("businessObjName", tblTaskRunningLog.getObjTaskName());
                nettyMessage.setJsonObject("setTypes", "true");
                nettyMessage.setJsonObject("modeValue", tblTaskPartConfig.getModeValue());
                String partValue = "";
                if(keepOldValue){//是否保持旧值
                    partValue = tblTaskRunningLog.getPartValue();
                    if (!StringUtils.isEmpty(partValue)) {
                        if (!StringUtils.isEmpty(groupList.get(i))) {
                            partValue = tblTaskRunningLog.getPartValue() + SEPARATOR + groupList.get(i);
                        } else {
                            partValue = tblTaskRunningLog.getPartValue();
                        }
                    } else {
                        partValue = groupList.get(i);
                    }
                }else {
                    partValue = groupList.get(i);
                }
                nettyMessage.setJsonObject("partValue", partValue);
                NettyServerStartup.getInstance().sendMsg(tblTaskRunningLog.getTaskRunServer(), tblTaskRunningLog.getTaskRunPort(), nettyMessage);
                addOrUpdateTblTaskPartConfig(tblTaskRunningLog, tblTaskPartConfig, groupList.get(i));
            }
        }

        public void run() {
            //没有进行配置过分片的不管
            List<TblTaskPartConfig> tblTaskPartConfigs = tblTaskPartConfigSerMapper.queryUniquePartList();
            for (TblTaskPartConfig tblTaskPartConfig : tblTaskPartConfigs) {
                Map param = new HashMap();
                param.put("taskNo", tblTaskPartConfig.getTaskNo());
                param.put("ddlStatus", new String[]{QuartzConstants.DDL_STATUS_010, QuartzConstants.DDL_STATUS_020});
                List<TblTaskRunningLog> tblTaskRunningLogList = tblTaskRunningLogSerMapper.queryListByStatus(param);
                if (CollectionUtils.isEmpty(tblTaskRunningLogList)) {
                    continue;
                }
                String[] modeArr = getModeValues(tblTaskPartConfig.getModeValue());
                if(modeArr.length == 0){//取模值为空，不分片
                    continue;
                }
                String oldModeValue = getMaxNumKey(tblTaskRunningLogList);//出现次数最多的是老的分片值
                String isRePart = isRePart(tblTaskPartConfig.getModeValue(), oldModeValue);
                logger.info("taskNo:{} ,isRePart:{};tblTaskRunningLogList_size:{};ModelValue" ,tblTaskPartConfig.getTaskNo() ,
                        oldModeValue,tblTaskRunningLogList.size() ,tblTaskPartConfig.getModeValue());
                if ("0".equals(isRePart)) {//0 重新生成分片;1两次值相等不继续;2保留原有分片，继续计算
                    addOrUpdatePart(modeArr, tblTaskRunningLogList, tblTaskPartConfig, false);
                }else  if("1".equals(isRePart)){
                    continue;
                }else  if("2".equals(isRePart)){
                    List<String> modeList = new CopyOnWriteArrayList<String>(modeArr);
                    Set<String> containModeList = new HashSet<String>();
                    Set<TblTaskRunningLog> hasPartValueSet = new HashSet<TblTaskRunningLog>();//设置过分片的集合
                    Set<TblTaskRunningLog> noPartValueSet = new HashSet<TblTaskRunningLog>();//未设置过分片的集合，或者分片值不在设置的字符串中
                    for (Iterator it = tblTaskRunningLogList.iterator(); it.hasNext(); ) {//已经设置过的不参与分片
                        TblTaskRunningLog tblTaskRunningLog = (TblTaskRunningLog) it.next();
                        if (!StringUtils.isEmpty(tblTaskRunningLog.getPartValue())) {
                            String[] partValues = tblTaskRunningLog.getPartValue().split(SEPARATOR);
                            for (String partValue : partValues) {
                                if (modeList.contains(partValue)) {
                                    containModeList.add(partValue);
                                    if (!tblTaskRunningLog.getModeValue().equals(tblTaskPartConfig.getModeValue())) {
                                        tblTaskRunningLog.setModeValue(tblTaskPartConfig.getModeValue());
                                        addOrUpdateTblTaskPartConfig(tblTaskRunningLog, tblTaskPartConfig, tblTaskRunningLog.getModeValue());
                                        NettyMessage nettyMessage = new NettyMessage();
                                        nettyMessage.setBusinessType(QuartzConstants.TASK_PART_SET);
                                        nettyMessage.setJsonObject("businessObjName", tblTaskRunningLog.getObjTaskName());
                                        nettyMessage.setJsonObject("setTypes", "true");
                                        nettyMessage.setJsonObject("partValue", tblTaskPartConfig.getPartValue());
                                        nettyMessage.setJsonObject("modeValue", tblTaskRunningLog.getModeValue());
                                        NettyServerStartup.getInstance().sendMsg(tblTaskRunningLog.getTaskRunServer(), tblTaskRunningLog.getTaskRunPort(), nettyMessage);
                                    }
                                    hasPartValueSet.add(tblTaskRunningLog);
                                } else {
                                    tblTaskRunningLog.setModeValue("");
                                    noPartValueSet.add(tblTaskRunningLog);
                                }
                            }
                        } else {
                            tblTaskRunningLog.setModeValue("");
                            noPartValueSet.add(tblTaskRunningLog);
                        }
                    }
                    modeList.removeAll(containModeList);//剩余的part参数
                    if (modeList.size() == 0) {
                        if (noPartValueSet.size() == 0) {//分片已全部设置 实例也全部分片
                            logger.info("taskNo:{} ,分片已全部设置 实例也全部分片,跳过" ,tblTaskPartConfig.getTaskNo() );
                            continue;
                        } else {//分片已全部设置，但是实例有剩余,关闭实例tblTaskRunningLog？？   还是重新分片？？
                            logger.info("taskNo:{} ,分片已全部设置，但是实例有剩,重新分片" ,tblTaskPartConfig.getTaskNo() );
                            addOrUpdatePart(modeArr, new ArrayList<TblTaskRunningLog>(noPartValueSet), tblTaskPartConfig,true);
                        }
                    } else {
                        if (noPartValueSet.size() == 0) {//分片还有 ，但是实例没了 咋整??重新分配到已分片的实例上
                            logger.info("taskNo:{} ,分片还有 ，但是实例没,重新分配到已分片的实例上" ,tblTaskPartConfig.getTaskNo() );
                            addOrUpdatePart(modeList.toArray(new String[]{}), new ArrayList<TblTaskRunningLog>(hasPartValueSet), tblTaskPartConfig,true);
                        } else {//分片还有剩余 ，实例也有剩余，计算分片
                            logger.info("taskNo:{} ,分片还有 ，实例也有,计算分片" ,tblTaskPartConfig.getTaskNo() );
                            addOrUpdatePart(modeList.toArray(new String[]{}), new ArrayList<TblTaskRunningLog>(noPartValueSet), tblTaskPartConfig,true);
                        }
                    }
                }

            }
        }
    }


    /**
     * 是否需要重新生成分片
     *
     * @param modelValueNew
     * @param modelValueOld
     * @return 0 重新生成分片;1两次值相等不继续;2保留原有分片，继续计算
     */
    public static String isRePart(String modelValueNew, String modelValueOld) {
        String flag = "2";
        // 0 数字，1字母
        int modelValueNew_Type = 0;
        int modelValueOld_Type = 0;
        try {
            Integer.valueOf(modelValueNew);
        } catch (Exception e) {
            modelValueNew_Type = 1;
        }
        try {
            Integer.valueOf(modelValueOld);
        } catch (Exception e) {
            modelValueOld_Type = 1;
        }
        if (modelValueNew_Type == modelValueOld_Type) {//类型一致
            if (modelValueNew_Type == 0) {//数字,比较大小，大于等于则不重新分，小则重新分
                if(Integer.valueOf(modelValueOld) != 0 ){//
                    if(Integer.valueOf(modelValueNew) > Integer.valueOf(modelValueOld)){
                        flag = "2";
                    }else if(Integer.valueOf(modelValueNew) == Integer.valueOf(modelValueOld)){
                        flag = "2";
                    }else if(Integer.valueOf(modelValueNew) < Integer.valueOf(modelValueOld)){
                        flag = "0";
                    }
                }else {
                    flag = "0";
                }
            } else {//字母 其他逻辑处理
                Set<String> modelValueNewSet = new HashSet(Arrays.asList(modelValueNew.split(SEPARATOR)));
                Set<String> modelValueOldSet = new HashSet(Arrays.asList(modelValueOld.split(SEPARATOR)));
                if(CollectionUtils.isEqualCollection(modelValueNewSet,modelValueOldSet)){
                    flag = "2";
                }else {
                    // [a,b,c] [a,b,d]
                    // [a,b,c,d] [a,b,d]
                    // [a,b] [a,b,d]
                    if(modelValueNewSet.containsAll(modelValueOldSet)){
                        flag = "2";
                    }else {
                        flag = "0";
                    }
                }
            }
        } else {//类型不一致
            flag = "0";
        }
        return flag;
    }

    public static String[] getModeValues(String modeValue) {
        String[] modeArr = new String[]{};
        if (StringUtils.isEmpty(modeValue)) {
            return modeArr;
        }
        if (modeValue.contains(SEPARATOR)) {//字母分片
            modeArr = modeValue.split(SEPARATOR);
        } else {//数字分片
            modeArr = new String[Integer.valueOf(modeValue)];
            for (int i = 0; i < Integer.valueOf(modeValue); i++) {
                modeArr[i] = String.valueOf(i);
            }
        }
        return modeArr;
    }

    public static int[] computeGroup(String[] modeArr, int groupNum) {
        int[] taskNums = new int[groupNum];
        int numOfSingle = modeArr.length / groupNum;
        int otherNum = modeArr.length % groupNum;
        for (int i = 0; i < groupNum; i++) {
            if (i < otherNum) {
                taskNums[i] = numOfSingle + 1;
            } else {
                taskNums[i] = numOfSingle;
            }
        }
        return taskNums;
    }

    public static List<String> computeGroupList(String[] modeArr, int groupNum) {
        int[] taskNums = computeGroup(modeArr, groupNum);
        List<String> groupList = new ArrayList<String>();
        int count = 0;
        for (int i = 0; i < taskNums.length; i++) {
            int orginCount = count;
            count = count + taskNums[i];
            StringBuilder sb = new StringBuilder();
            for (int j = orginCount; j < count; j++) {
               sb.append(modeArr[j]).append(SEPARATOR);
            }
            if (sb.length() >= 1) {
                sb.setLength(sb.length() - 1);
            }
            groupList.add(sb.toString());
        }
        return groupList;
    }
     public static String getMaxNumKey(List<TblTaskRunningLog> tblTaskRunningLogList){
         Map<String,Integer> map = new HashMap();
         for (TblTaskRunningLog temp : tblTaskRunningLogList) {
             String tempValue = temp.getModeValue();
             if(StringUtils.isEmpty(tempValue)){
                 tempValue ="0";
             }
             Integer count = map.get(tempValue);
             map.put(tempValue, (count == null) ? 1 : count + 1);
         }
         String key ="0"; int num = 0;
         for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > num){
                key = entry.getKey();
                num = entry.getValue();
            }
         }
         return  key;
     }
    public static void main(String[] args) {
        int groupNum = 3;
        List<String> list0 = computeGroupList(getModeValues(""), groupNum);
        System.out.println(list0);
        List<String> list1 = computeGroupList(getModeValues("a,b"), groupNum);
        System.out.println(list1);
        List<String> list2 = computeGroupList(getModeValues("2"), groupNum);
        System.out.println(list2);

//        List<String> modeList  = Arrays.asList(new String[]{"a","b","c"});
//        modeList.remove("b");
//        System.out.println(modeList);

        CopyOnWriteArrayList<String> modeList = new CopyOnWriteArrayList<String>(new String[]{"a", "b", "c"});
        modeList.remove("b");
        System.out.println(modeList);
        String[] part = modeList.toArray(new String[]{});
        System.out.println(modeList.toArray());
        System.out.println(part);

        System.out.println("---------------");
        System.out.println(isRePart("2", "3") );
        System.out.println(isRePart("3", "3"));
        System.out.println(isRePart("4", "3"));
        System.out.println(isRePart("a,b,c", "3"));
        System.out.println(isRePart("a,b", "a,b,c"));
        System.out.println(isRePart("a,b,c", "a,b,c"));
        System.out.println(isRePart("a,b,c,d", "a,b,c"));

        List<String> list_1 = new ArrayList<String>();
        list_1.add("a");
        list_1.add("b");
        list_1.add("d");

        List<String> list_2 = new ArrayList<String>();
        list_2.add("a");
        list_2.add("b");
        list_2.add("d");
        list_2.add("d");
//        list_1.retainAll(list_2);// list 交集
//        System.out.println(list_1);
   //     list_1.removeAll(list_2);// list 差集
     //   System.out.println(list_1);
        System.out.println( CollectionUtils.isEqualCollection(new HashSet(list_1),new HashSet(list_2)));
       ;
    }
}
