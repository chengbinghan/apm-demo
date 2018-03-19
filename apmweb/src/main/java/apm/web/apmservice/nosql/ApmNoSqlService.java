package apm.web.apmservice.nosql;

import apm.web.beans.vo.NosqlVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 10:47  2018/3/15
 * @description nosql 业务处理
 */
public interface ApmNoSqlService {

    /**
     * 查询 NoSql put，get 概况
     *
     * @param noSqlType
     * @param timeSection
     * @return
     */
    NosqlVO getNoSqlGeneralView(String noSqlType, Long timeSection);


    /**
     * 验证nosql Type 是否是目前已经支持的，目前已经支持的在常量中都有定义
     *
     * @param noSqlType
     * @return
     */
    boolean validtateNoSqlType(String noSqlType);


    /**
     * 找到noSql中属于set的方法
     *
     * @param noSqlType
     * @return
     * @throws Exception
     */
    List<Integer> getNoSqlSetApiId(String noSqlType) throws Exception;

    /**
     * 找到noSql中属于get的方法
     * @param noSqlType
     * @return
     * @throws Exception
     */
    List<Integer> getNoSqlGetApiId(String noSqlType) throws Exception;
}

