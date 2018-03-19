package apm.web.apmservice;

import apm.web.beans.module.ApmApplication;
import apm.web.beans.vo.ApdexVO;
import apm.web.beans.vo.ApplicationVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 10:30  2018/1/22
 * @description  应用处理类
 */
public interface ApplicationService {

    /**
     * @description 查询所有应用
     * @return
     */
    List<ApmApplication> selectAllApplicationNames();

    /**
     * @description 查询应用 列表相关信息
     * @return
     */
    List<ApplicationVO> getApmApplicationInfo(Long timeSection);

    /**
     *
     * @param appId
     * @description 计算apdex 值
     * @return
     */
    ApdexVO getApdexVOByTimeAndAppId(String appId) throws Exception;
}
