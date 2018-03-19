package apm.web.core.monitor;

import apm.web.beans.module.User;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 14:35  2018/2/24
 * @description 监控警报信息封装类
 */
public class MonitorWarnInfo {

    private String appId;
    private String occurTime;
    private String warnInfo;
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    @Override
    public String toString() {
        return "MonitorWarnInfo{" +
                "appId='" + appId + '\'' +
                ", occurTime='" + occurTime + '\'' +
                ", warnInfo='" + warnInfo + '\'' +
                ", userList=" + userList +
                '}';
    }
}

