package apm.web.beans.module;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 9:11  2018/3/13
 * @description
 */
public class ApmPlugin {

    //每个插件对应的serviceName
    private String serviceName;

    //插件对应的serviceCode
    private Integer serviceCode;
   //插件对于的annnotationKeyCode
    private Integer annotationKeyCode;
    //插件对应的annotriongKeyName
    private String annotationKeyName;

    //埋点信息，类和方法用逗号隔开，有多个eg:com.xx.xxx.Login,login
    private List<String> detectorList;


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

    public Integer getAnnotationKeyCode() {
        return annotationKeyCode;
    }

    public void setAnnotationKeyCode(Integer annotationKeyCode) {
        this.annotationKeyCode = annotationKeyCode;
    }

    public String getAnnotationKeyName() {
        return annotationKeyName;
    }

    public void setAnnotationKeyName(String annotationKeyName) {
        this.annotationKeyName = annotationKeyName;
    }

    public List<String> getDetectorList() {
        return detectorList;
    }

    public void setDetectorList(List<String> detectorList) {
        this.detectorList = detectorList;
    }
}
