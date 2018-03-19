package apm.web.beans.module;

/**
 * @author ChengBing Han
 * @date 13:44  2018/2/21
 * @description 每一个监控线程对应
 */
public class MonitorPro {

    private Integer id;

    //对应表中的appId
    private ApmApplication app;
    //对应表中的userId, 表中用逗号隔开。
    private String userIds;




    //对应strategyId
    private MonitorStrategy monitorStrategy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ApmApplication getApp() {
        return app;
    }

    public void setApp(ApmApplication app) {
        this.app = app;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public MonitorStrategy getMonitorStrategy() {
        return monitorStrategy;
    }

    public void setMonitorStrategy(MonitorStrategy monitorStrategy) {
        this.monitorStrategy = monitorStrategy;
    }


}
