package apm.web.beans.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 14:50  2018/2/28
 * @description
 */
public class DatabaseReportFormVO {


    //详细sql信息
    private String sqlInfo;

    //简单的sql信息
    private String simpleSqlStr;

    //该条sql对于的rpc(url), 一般形式为： /aa/bb:SELECT,/aa/bb:UPDATE
    private String rpcWithSqlMsg;

    List<DatabaseReportFormInfoVO> list  = new ArrayList<>();

    public String getSqlInfo() {
        return sqlInfo;
    }

    public void setSqlInfo(String sqlInfo) {
        this.sqlInfo = sqlInfo;
    }

    public List<DatabaseReportFormInfoVO> getList() {
        return list;
    }
    public void setList(List<DatabaseReportFormInfoVO> list) {
        this.list = list;
    }

    public String getSimpleSqlStr() {
        return simpleSqlStr;
    }

    public void setSimpleSqlStr(String simpleSqlStr) {
        this.simpleSqlStr = simpleSqlStr;
    }

    public String getRpcWithSqlMsg() {
        return rpcWithSqlMsg;
    }

    public void setRpcWithSqlMsg(String rpcWithSqlMsg) {
        this.rpcWithSqlMsg = rpcWithSqlMsg;
    }
}
