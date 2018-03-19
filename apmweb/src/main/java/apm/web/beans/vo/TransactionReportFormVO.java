package apm.web.beans.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 11:33  2018/2/23
 * @description
 */
public class TransactionReportFormVO  extends BaseReportFormVO{
    //事务url
    private String transactionUrl;


    List<TransactionReportFormInfoVO> list  = new ArrayList<>();

    public List<TransactionReportFormInfoVO> getList() {
        return list;
    }

    public void setList(List<TransactionReportFormInfoVO> list) {
        this.list = list;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public void setTransactionUrl(String transactionUrl) {
        this.transactionUrl = transactionUrl;
    }

}
