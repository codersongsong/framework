package cn.com.gome.generator.bean;

import cn.com.gome.framework.dao.entity.TblInterfaceInfo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * Created by liuxianzhao on 2017/6/14.
 */
public class DubboPo {
    public String dubboClassName;
    public String dubboServiceAsk;
    public List<ServicePo> serviceList;
    public List<DubboMethodPo> dubboMethodPoList;

    public List<TblInterfaceInfo> getTblInterfaceInfoList() {
        return tblInterfaceInfoList;
    }

    public DubboPo setTblInterfaceInfoList(List<TblInterfaceInfo> tblInterfaceInfoList) {
        this.tblInterfaceInfoList = tblInterfaceInfoList;
        return this;
    }

    public List<TblInterfaceInfo> tblInterfaceInfoList;

    public String getDubboClassName() {
        return dubboClassName;
    }

    public DubboPo setDubboClassName(String dubboClassName) {
        this.dubboClassName = dubboClassName;
        return this;
    }

    public String getDubboServiceAsk() {
        return dubboServiceAsk;
    }

    public DubboPo setDubboServiceAsk(String dubboServiceAsk) {
        this.dubboServiceAsk = dubboServiceAsk;
        return this;
    }

    public List<ServicePo> getServiceList() {
        return serviceList;
    }

    public DubboPo setServiceList(List<ServicePo> serviceList) {
        this.serviceList = serviceList;
        return this;
    }




    public List<DubboMethodPo> getDubboMethodPoList() {
        return dubboMethodPoList;
    }

    public DubboPo setDubboMethodPoList(List<DubboMethodPo> dubboMethodPoList) {
        this.dubboMethodPoList = dubboMethodPoList;
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    private DubboPo(String dubboClassName, String dubboServiceAsk) {
        this.dubboClassName = dubboClassName;
        this.dubboServiceAsk = dubboServiceAsk;
    }

    private DubboPo() {
    }


    public static DubboPo create(String dubboClassName, String dubboServiceAsk) {
        return new DubboPo(dubboClassName, dubboServiceAsk);
    }

    public static DubboPo create() {
        return new DubboPo();
    }

}
