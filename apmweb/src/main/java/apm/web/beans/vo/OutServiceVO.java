package apm.web.beans.vo;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 15:22  2018/2/5
 * @description 外部服务VO 类
 */
public class OutServiceVO {

       private String applicationName;
       private String serviceType;
       private String per;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }
}
