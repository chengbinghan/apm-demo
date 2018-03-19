package apm.web.dao.apmmysql;

import apm.web.beans.module.CallerMap;
import apm.web.beans.vo.CallerMapVO;

import java.util.HashMap;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:57  2018/1/25
 * @description
 */
public interface CallerMapDao {


    List<CallerMap>  getAppCallerByAppId(String appId);


    List<CallerMapVO> getAppCalleeCountByAppId(HashMap<String,String> hashMap);
}
