package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 10:40  2018/3/15
 * @description nosql 总体概览信息
 */
public class NosqlVO {

    //nosqlType :redis ormemcached
    private String noSqlType;

    //get花费总时间
    private Long totalGetTime;
    //set次数
    private Long setCount;
    //get次数
    private Long getCount;

    //set花费总时间
    private Long totalSetTime;


    public Long getGetCount() {
        return getCount;
    }

    public void setGetCount(Long getCount) {
        this.getCount = getCount;
    }

    public Long getSetCount() {

        return setCount;
    }

    public void setSetCount(Long setCount) {
        this.setCount = setCount;
    }

    public String getNoSqlType() {
        return noSqlType;
    }

    public void setNoSqlType(String noSqlType) {
        this.noSqlType = noSqlType;
    }

    public Long getTotalSetTime() {
        return totalSetTime;
    }

    public void setTotalSetTime(Long totalSetTime) {
        this.totalSetTime = totalSetTime;
    }

    public Long getTotalGetTime() {

        return totalGetTime;
    }

    public void setTotalGetTime(Long totalGetTime) {
        this.totalGetTime = totalGetTime;
    }
}
