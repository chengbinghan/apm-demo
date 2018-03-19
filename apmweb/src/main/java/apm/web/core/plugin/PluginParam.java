package apm.web.core.plugin;

import java.util.Map;

/**
 * @author ChengBing Han
 * @date 18:33  2018/3/9
 * @description
 * 抽象一个插件的相关信息
 */
public class PluginParam {


    //service 如TOMCAT
    private String serviceName;
    //如tomcat  对应的code 1010
    private Integer serviceCode;





    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }


}
