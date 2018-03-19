package apm.web.apmservice;

import apm.web.beans.module.ApiMethod;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 19:40  2018/1/24
 * @description  方法相关
 */
public interface MethodService {

    ApiMethod getApiMethodById(Integer id);


    /**
     * @param ids 多个id
     * @description
     */
    List<ApiMethod> getApiMethodByIds(String ids);
}
