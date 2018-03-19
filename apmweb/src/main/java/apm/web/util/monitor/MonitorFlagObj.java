package apm.web.util.monitor;

/**
 * @author ChengBing Han
 * @date 20:07  2018/2/13
 * @description  判断是否修改了监控用的几张数据库表
 */
public class MonitorFlagObj {

    //每次查询后将该值设为false, 一旦对监控用的表插入新数据了，就改为true.

    private static Boolean operateMonitorTable = true;

    public static Boolean getOperateMonitorTable() {
        return operateMonitorTable;
    }

    public static void setOperateMonitorTable(Boolean operateMonitorTable) {
        MonitorFlagObj.operateMonitorTable = operateMonitorTable;
    }
}
