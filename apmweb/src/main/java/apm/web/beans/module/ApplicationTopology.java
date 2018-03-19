package apm.web.beans.module;




import java.util.List;

/**
 * @author ChengBing Han
 * @description  拓扑列表信息
 */
public class ApplicationTopology {

    //应用调用者
    private ApmApplication applicationCaller;
    //应用被调用者，调用信息
    private List<ApplicationCalleeInfo> appCalleeInfoList;


    public List<ApplicationCalleeInfo> getAppCalleeInfoList() {
        return appCalleeInfoList;
    }

    public void setAppCalleeInfoList(List<ApplicationCalleeInfo> appCalleeInfoList) {
        this.appCalleeInfoList = appCalleeInfoList;
    }


    public ApmApplication getApplicationCaller() {
        return applicationCaller;
    }

    public void setApplicationCaller(ApmApplication applicationCaller) {
        this.applicationCaller = applicationCaller;
    }
}
