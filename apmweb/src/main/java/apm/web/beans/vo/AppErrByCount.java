package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 11:44  2018/2/2
 * @description 在某app, 最近一段固定时间内，查询出现n次错误,错误率（一次事务在本app中为一个span,该span有10个spanevent,spanevent 错误的有1个，错误率为0.1）随时间变化。
 */
public class AppErrByCount {
   private String rpc;
    private String errPer;
    private String appId;
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getErrPer() {
        return errPer;
    }

    public void setErrPer(String errPer) {
        this.errPer = errPer;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
