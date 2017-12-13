package cn.com.gome.generator.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * service服务po
 * Created by liuxianzhao on 2017/6/14.
 */
public class ServicePo {
    private String serviceClassName;
    private String serviceClassAsk;
    private List<LogicPo> logicList;
    private List<ServiceMethodPo> serviceMethodList;

    public String getServiceClassName() {
        return serviceClassName;
    }

    public ServicePo setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
        return this;
    }

    public String getServiceClassAsk() {
        return serviceClassAsk;
    }

    public ServicePo setServiceClassAsk(String serviceClassAsk) {
        this.serviceClassAsk = serviceClassAsk;
        return this;
    }

    public List<ServiceMethodPo> getServiceMethodList() {
        return serviceMethodList;
    }

    public ServicePo setServiceMethodList(List<ServiceMethodPo> serviceMethodList) {
        this.serviceMethodList = serviceMethodList;
        return this;
    }

    public List<LogicPo> getLogicList() {
        return logicList;
    }

    public ServicePo setLogicList(List<LogicPo> logicList) {
        this.logicList = logicList;
        return this;
    }


    public static ServicePo create(String serviceClassName, String serviceClassAsk) {
        return new ServicePo(serviceClassName, serviceClassAsk);
    }

    public static ServicePo create() {
        return new ServicePo();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    private ServicePo(String serviceClassName, String serviceClassAsk) {
        this.serviceClassName = serviceClassName;
        this.serviceClassAsk = serviceClassAsk;
    }

    private ServicePo() {
    }
}
