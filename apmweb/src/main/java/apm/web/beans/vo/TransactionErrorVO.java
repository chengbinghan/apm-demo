package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 13:27  2018/2/1
 * @description 事务错误率。
 */
public class    TransactionErrorVO {
    //某个事务的错误率
    private String errPer;

    //transactionRpc
    private String rpc;
   //应用id
    private String appId;

    //调用次数
    private Integer invokeCount;

    //错误次数
    private Integer errCount;


    //方法调用次数
    private Long totalMethodCount;

    public Integer getErrCount() {
        return errCount;
    }

    public Long getTotalMethodCount() {
        return totalMethodCount;
    }

    public void setTotalMethodCount(Long totalMethodCount) {
        this.totalMethodCount = totalMethodCount;
    }

    public void setErrCount(Integer errCount) {
        this.errCount = errCount;
    }

    public Integer getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(Integer invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getErrPer() {
        return errPer;
    }

    public void setErrPer(String errPer) {
        this.errPer = errPer;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }



}
