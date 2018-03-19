package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 23:47  2018/1/29
 * @description
 */
public class TraceDetailSpanEvent {

    private String apiInfo;
    private String  elapsed;
    private String timePer;

    private String  accumulateTime;


    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public String getTimePer() {
        return timePer;
    }

    public void setTimePer(String timePer) {
        this.timePer = timePer;
    }

    public String getAccumulateTime() {
        return accumulateTime;
    }

    public void setAccumulateTime(String accumulateTime) {
        this.accumulateTime = accumulateTime;
    }
}
