package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 13:38  2018/2/7
 * @description 外部服务Service Schema
 */
public class ServiceSchema {
    private String xaxis = "TIME";
    private String yaxis = "OutServiceByTimeVO.avgTime";

    public String getXaxis() {
        return xaxis;
    }

    public void setXaxis(String xaxis) {
        this.xaxis = xaxis;
    }

    public String getYaxis() {
        return yaxis;
    }

    public void setYaxis(String yaxis) {
        this.yaxis = yaxis;
    }
}
