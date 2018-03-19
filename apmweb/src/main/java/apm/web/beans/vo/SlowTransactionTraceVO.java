package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 20:32  2018/1/28
 * @description  慢事务追踪 页面json
 */
public class SlowTransactionTraceVO {

    //spanEvnet id
    private String spanEventId;
    //方法时间
    private Integer endElapsed;
    //方法id
    private String methodId;
    //方法名
    private String apiInfo;


    public String getSpanEventId() {
        return spanEventId;
    }

    public void setSpanEventId(String spanEventId) {
        this.spanEventId = spanEventId;
    }

    public Integer getEndElapsed() {
        return endElapsed;
    }

    public void setEndElapsed(Integer endElapsed) {
        this.endElapsed = endElapsed;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(String apiInfo) {
        this.apiInfo = apiInfo;
    }
}
