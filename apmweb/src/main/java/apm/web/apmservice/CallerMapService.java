package apm.web.apmservice;

import apm.web.beans.vo.CallerMapVO;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 16:05  2018/1/25
 * @description  应用调用与被调用处理类
 */
public interface CallerMapService {

    /**
     * @param String appId 应用Id
     * @description 根据某个应用Id 查询某个应用application的拓扑调用关系
     *
     */
    public List<CallerMapVO> getAppCallerTopologyInfo(String appId,Long timeSection) throws Exception;
}
