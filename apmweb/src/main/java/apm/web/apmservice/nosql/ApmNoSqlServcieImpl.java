package apm.web.apmservice.nosql;

import apm.web.beans.module.ApiMethod;
import apm.web.beans.vo.NosqlVO;
import apm.web.core.properties.NosqlProperties;
import apm.web.dao.apmmysql.ApiMetaDataDao;
import apm.web.dao.apmmysql.ApmNoSqlDao;
import apm.web.util.apmconstant.APMConstant;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.apmutil.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ChengBing Han
 * @date 10:48  2018/3/15
 * @description
 */
@Service
public class ApmNoSqlServcieImpl implements ApmNoSqlService {


    public static final String NOSQL_TYPE_HASHMAP_KEY = "noSqlType";
    public static final String NOSQL_SET = "SET";
    public static final String NOSQL_GET = "GET";

    @Resource
    ApmNoSqlDao apmNoSqlDao;

    @Resource
    ApiMetaDataDao apiMetaDataDao;

    /**
     * 查询 NoSql put，get 概况
     *
     * @param noSqlType
     * @param timeSection
     * @return
     */
    @Override
    public NosqlVO getNoSqlGeneralView(String noSqlType, Long timeSection) {

        boolean noSqlTypeCorrect = validtateNoSqlType(noSqlType);

        if (!noSqlTypeCorrect) {
            throw new IllegalArgumentException("param noSqlType is null or empty no not support!!!");
        }
        //是否使用默认的查询时间区间
        if (timeSection == null || timeSection <= 0) {
            timeSection = APMConstant.DEFAULT_NOSQL_TIME_SECTION;
        }


        Map<String, String> hashMap = new HashMap<>();

        hashMap.put(NOSQL_TYPE_HASHMAP_KEY, noSqlType);
        hashMap.put(APMConstant.TIME_SECTION, timeSection.toString());


        NosqlDaoParam nosqlDaoParam = new NosqlDaoParam();
        nosqlDaoParam.setTimeSection(timeSection);
        nosqlDaoParam.setCurrentTime(TimeUtil.getCurrentMillisecond());

        List<Integer> noSqlSetApiId = getNoSqlSetApiId(noSqlType);
        nosqlDaoParam.setApiIds(noSqlSetApiId);

        NosqlVO nosqlVO = apmNoSqlDao.getNosqlSetGeneralInfo(nosqlDaoParam);
        return nosqlVO;

    }


    /**
     * 验证nosql Type 是否是目前已经支持的，目前已经支持的在常量中都有定义
     *
     * @param noSqlType
     * @return
     */
    @Override
    public boolean validtateNoSqlType(String noSqlType) {

        if (ApmStringUtil.isEmpty(noSqlType)) {
            return false;
        }
        //是否是redis
        if (APMConstant.NOSQL_REDIS_TYPE.equals(noSqlType)) {
            return true;
        }

        //是否是memcached
        if (APMConstant.NOSQL_MEMCACHED_TYEP.equals(noSqlType)) {
            return true;
        }


        return false;
    }

    @Override
    public List<Integer> getNoSqlSetApiId(String noSqlType) {

        List<Integer> apiIdList = new ArrayList<Integer>();

        boolean noSqlTypeCorrect = validtateNoSqlType(noSqlType);
        if (!noSqlTypeCorrect) {
            return null;
        }
        /*
            从properties 中获取那些是set,那些是get
         */
        NosqlProperties nosqlProperties = NosqlProperties.getNosqlProperties();
        String startKey = null;
        if (APMConstant.NOSQL_REDIS_TYPE.equals(noSqlType)) {

            startKey = APMConstant.NOSQL_REDIS_TYPE + "_" + NOSQL_SET;


        } else if (APMConstant.NOSQL_MEMCACHED_TYEP.endsWith(noSqlType)) {
            startKey = APMConstant.NOSQL_MEMCACHED_TYEP + "_" + NOSQL_SET;

        }

        Set<Object> keySet = nosqlProperties.keySet();
        for (Object key : keySet) {
            String noSqlKey = (String) key;

            if (noSqlKey.startsWith(startKey)) {
                String apiInfo = (String) nosqlProperties.get(noSqlKey);
                ApiMethod apiMethod = apiMetaDataDao.getApiIdByApiInFo(apiInfo);
                if (apiMethod != null && apiMethod.getApiId() != null) {
                    apiIdList.add(apiMethod.getApiId());
                }
            }


        }


        return apiIdList;
    }

    /**
     * 找到noSql中属于get的方法
     *
     * @param noSqlType
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getNoSqlGetApiId(String noSqlType) {

        List<Integer> apiIdList = new ArrayList<Integer>();

        boolean noSqlTypeCorrect = validtateNoSqlType(noSqlType);
        if (!noSqlTypeCorrect) {
            return null;
        }
        /*
            从properties 中获取那些是set,那些是get
         */
        NosqlProperties nosqlProperties = NosqlProperties.getNosqlProperties();
        String startKey = null;
        if (APMConstant.NOSQL_REDIS_TYPE.equals(noSqlType)) {

            startKey = APMConstant.NOSQL_REDIS_TYPE + "_" + NOSQL_GET;


        } else if (APMConstant.NOSQL_MEMCACHED_TYEP.endsWith(noSqlType)) {
            startKey = APMConstant.NOSQL_MEMCACHED_TYEP + "_" + NOSQL_GET;

        }

        Set<Object> keySet = nosqlProperties.keySet();
        for (Object key : keySet) {
            String noSqlKey = (String) key;

            if (noSqlKey.startsWith(startKey)) {
                String apiInfo = (String) nosqlProperties.get(noSqlKey);
                ApiMethod apiMethod = apiMetaDataDao.getApiIdByApiInFo(apiInfo);
                if (apiMethod != null && apiMethod.getApiId() != null) {
                    apiIdList.add(apiMethod.getApiId());
                }
            }
        }


        return apiIdList;

    }

}



