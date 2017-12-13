package cn.com.gome.generator.bean;

import cn.com.gome.framework.dao.entity.TblInterfaceInfo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * Logic层Po
 * Created by liuxianzhao on 2017/6/14.
 */
public class LogicPo {
    private String logicClassName;
    private String logicClassAsk;
    private boolean isNeedTx = false;//是否需要事务
    private List<TblInterfaceInfo> interfaceInfos;

    public String getLogicClassName() {
        return logicClassName;
    }

    public LogicPo setLogicClassName(String logicClassName) {
        this.logicClassName = logicClassName;
        return this;
    }

    public String getLogicClassAsk() {
        return logicClassAsk;
    }

    public LogicPo setLogicClassAsk(String logicClassAsk) {
        this.logicClassAsk = logicClassAsk;
        return this;
    }

    public boolean isNeedTx() {
        return isNeedTx;
    }

    public LogicPo setNeedTx(boolean needTx) {
        isNeedTx = needTx;
        return this;
    }

    public static LogicPo create() {
        return new LogicPo();
    }

    public static LogicPo create(String logicClassName, String logicClassAsk) {
        return new LogicPo(logicClassName, logicClassAsk);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    private LogicPo(String logicClassName, String logicClassAsk) {
        this.logicClassName = logicClassName;
        this.logicClassAsk = logicClassAsk;
    }

    private LogicPo() {
    }

    public List<TblInterfaceInfo> getInterfaceInfos() {
        return interfaceInfos;
    }

    public LogicPo setInterfaceInfos(List<TblInterfaceInfo> interfaceInfos) {
        this.interfaceInfos = interfaceInfos;
        return this;
    }
}
