package apm.web.dao.apmmysql;

import apm.web.beans.vo.ExpensiveOutserviceVO;
import apm.web.beans.vo.OutServiceByTimeVO;
import apm.web.beans.vo.OutServiceVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 15:33  2018/2/5
 * @description
 */
public interface OutServiceDao {


    List<OutServiceVO> getOutServiceList(Map<String, String> hashMap);

    OutServiceByTimeVO getTheOutServiceByTime(HashMap<String, String> hashMap);


    List<ExpensiveOutserviceVO> getTopNExpensiveOutService(Map<String,String> hashMap);
}
