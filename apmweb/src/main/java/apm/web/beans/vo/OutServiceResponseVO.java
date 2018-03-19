package apm.web.beans.vo;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 13:42  2018/2/7
 * @description
 */
public class OutServiceResponseVO {

    private List<Long> xaxisList;
    private List<OutServiceByTimeVO> yaxisList;


    public List<Long> getXaxisList() {
        return xaxisList;
    }

    public void setXaxisList(List<Long> xaxisList) {
        this.xaxisList = xaxisList;
    }

    public List<OutServiceByTimeVO> getYaxisList() {
        return yaxisList;
    }

    public void setYaxisList(List<OutServiceByTimeVO> yaxisList) {
        this.yaxisList = yaxisList;
    }
}
