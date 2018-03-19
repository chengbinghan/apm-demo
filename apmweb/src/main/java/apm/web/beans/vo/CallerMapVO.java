package apm.web.beans.vo;

import apm.web.beans.module.CallerMap;

/**
 * @author ChengBing Han
 * @date 16:00  2018/1/25
 * @description  某个应用的应用拓扑表格VO
 */
public class CallerMapVO {


   private CallerMap callerMap;

    //调用次数
    private long callTimes;

    //调用名称
    private String apperName;
    //调用类型
    private String apperType;
    //被调用者名称
    private String appeeName;
    //被调用者类型
    private String appeeType;


    public String getApperName() {
        return apperName;
    }

    public void setApperName(String apperName) {
        this.apperName = apperName;
    }

    public String getApperType() {
        return apperType;
    }

    public void setApperType(String apperType) {
        this.apperType = apperType;
    }

    public String getAppeeName() {
        return appeeName;
    }

    public void setAppeeName(String appeeName) {
        this.appeeName = appeeName;
    }

    public String getAppeeType() {
        return appeeType;
    }

    public void setAppeeType(String appeeType) {
        this.appeeType = appeeType;
    }

    public CallerMap getCallerMap() {
        return callerMap;
    }

    public void setCallerMap(CallerMap callerMap) {
        this.callerMap = callerMap;
    }

    public long getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(long callTimes) {
        this.callTimes = callTimes;
    }
}
