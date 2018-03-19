package apm.web.beans.vo;

import apm.web.beans.module.Transaction;

/**
 * @author ChengBing Han
 * @date 10:29  2018/1/26
 * @description
 */
public class TransactionVO {

    //事务实体类
    private Transaction transaction;
    //改事务耗时百分比
    private float transactionPer;

   private Long maxTransactionTime;
    private Long minTransactionTime;
    private String avgTransactionTime;
    private Long transactionCount;

    public Long getMaxTransactionTime() {
        return maxTransactionTime;
    }

    public void setMaxTransactionTime(Long maxTransactionTime) {
        this.maxTransactionTime = maxTransactionTime;
    }

    public Long getMinTransactionTime() {
        return minTransactionTime;
    }

    public void setMinTransactionTime(Long minTransactionTime) {
        this.minTransactionTime = minTransactionTime;
    }

    public String getAvgTransactionTime() {
        return avgTransactionTime;
    }

    public void setAvgTransactionTime(String avgTransactionTime) {
        this.avgTransactionTime = avgTransactionTime;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public float getTransactionPer() {
        return transactionPer;
    }

    public void setTransactionPer(float transactionPer) {
        this.transactionPer = transactionPer;
    }
}
