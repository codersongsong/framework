package cn.com.gome.generator.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * Created by liuxianzhao on 2017/6/14.
 */
public class ServiceMethodPo {
    private String methodName;
    private String methodAsk;

    private List<LogicPo> logicList;


    public String getMethodName() {
        return methodName;
    }

    public ServiceMethodPo setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getMethodAsk() {
        return methodAsk;
    }

    public ServiceMethodPo setMethodAsk(String methodAsk) {
        this.methodAsk = methodAsk;
        return this;
    }

    public List<LogicPo> getLogicList() {
        return logicList;
    }

    public ServiceMethodPo setLogicList(List<LogicPo> logicList) {
        this.logicList = logicList;
        return this;
    }

    public static ServiceMethodPo create(String methodName, String methodAsk) {
        return new ServiceMethodPo(methodName, methodAsk);
    }

    public static ServiceMethodPo create() {
        return new ServiceMethodPo();
    }

    private ServiceMethodPo(String methodName, String methodAsk) {
        this.methodName = methodName;
        this.methodAsk = methodAsk;
    }

    private ServiceMethodPo() {
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
