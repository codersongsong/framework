package cn.com.gome.generator.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by liuxianzhao on 2017/6/14.
 */
public class DubboMethodPo {
    private String methodName;
    private String methodAsk;
    private String serviceMethod;
    private String serviceMethodAsk;
    private ServicePo servicePo;

    public String getMethodName() {
        return methodName;
    }

    public DubboMethodPo setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getMethodAsk() {
        return methodAsk;
    }

    public DubboMethodPo setMethodAsk(String methodAsk) {
        this.methodAsk = methodAsk;
        return this;
    }

    public ServicePo getServicePo() {
        return servicePo;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public DubboMethodPo setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        return this;
    }

    public String getServiceMethodAsk() {
        return serviceMethodAsk;
    }

    public DubboMethodPo setServiceMethodAsk(String serviceMethodAsk) {
        this.serviceMethodAsk = serviceMethodAsk;
        return this;
    }

    public DubboMethodPo setServicePo(ServicePo servicePo) {
        this.servicePo = servicePo;
        return this;
    }

    private DubboMethodPo(String methodName, String methodAsk) {
        this.methodName = methodName;
        this.methodAsk = methodAsk;
    }

    private DubboMethodPo() {
    }

    public static DubboMethodPo create(String methodName, String methodAsk) {
        return new DubboMethodPo(methodName, methodAsk);
    }

    public static DubboMethodPo create() {
        return new DubboMethodPo();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
