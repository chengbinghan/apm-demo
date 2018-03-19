package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 21:21  2018/1/28
 * @description
 */
public class SlowestComponentVO {

    private String totalTime;
    private Integer invokeCount;
    private String timePer;
    private String apiInfo;
    private String apiId;


    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getTimePer() {
        return timePer;
    }

    public void setTimePer(String timePer) {
        this.timePer = timePer;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
}
