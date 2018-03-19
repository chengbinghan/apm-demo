package apm.web.apmservice.nosql;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 14:11  2018/3/16
 * @description
 */
public class NosqlDaoParam {


    private Long timeSection;
    private List<Integer> apiIds;
    private Long currentTime;

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Long getTimeSection() {
        return timeSection;
    }

    public void setTimeSection(Long timeSection) {
        this.timeSection = timeSection;
    }

    public List<Integer> getApiIds() {
        return apiIds;
    }

    public void setApiIds(List<Integer> apiIds) {
        this.apiIds = apiIds;
    }
}
