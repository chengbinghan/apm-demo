package apm.web.dao.apmmysql;

import apm.web.beans.vo.DataBaseSqlViewVO;
import apm.web.beans.vo.DatabaseResponseVO;
import apm.web.beans.vo.TopNSqlVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 13:37  2018/1/30
 * @description 数据库相关
 */
public interface DataBaseDao {


    List<DataBaseSqlViewVO> getDbOverView(Map<String,String> hashMap);

    List<TopNSqlVO> getTopNExpensiveSql(@Param("appId")String appId, @Param("beginTime") Long beginTime, @Param("topN") Integer topN);


    List<TopNSqlVO> getExpensiveSqlByThreshold(Map<String, String> hashMap);

    TopNSqlVO getTheSqlInfo(Map<String, String> hashMap);

    List<DatabaseResponseVO> getDabaseResponse(Map<String, String> hashMap);

    List<DatabaseResponseVO> getDBSqlListBySqlId(Map<String, String> hashMap);
}
