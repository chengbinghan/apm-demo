package apm.web.beans.vo;

/**
 * @author ChengBing Han
 * @date 1:03  2018/2/4
 * @description
 */
public class Schema{
    private   String xaxis = "TIME";
    private  String yaxis = "APDEX_VALUE";

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
