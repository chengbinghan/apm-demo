package apm.web.beans.vo;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 19:13  2018/2/3
 * @description apdex 随时间变化。
 */
public class ApdexVO {

    private List<Long> xaxisList;
    private List<Double> yaxisList;
    private Schema schema = new Schema();

    public List<Long> getXaxisList() {
        return xaxisList;
    }

    public void setXaxisList(List<Long> xaxisList) {
        this.xaxisList = xaxisList;
    }

    public List<Double> getYaxisList() {
        return yaxisList;
    }

    public void setYaxisList(List<Double> yaxisList) {
        this.yaxisList = yaxisList;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}

