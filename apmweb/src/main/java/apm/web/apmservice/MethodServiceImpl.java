package apm.web.apmservice;

import apm.web.beans.module.ApiMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 19:41  2018/1/24
 * @description
 */
public class MethodServiceImpl implements MethodService {
    @Override
    public ApiMethod getApiMethodById(Integer id) {

        ApiMethod apiMethod = new ApiMethod();
        apiMethod.setApiId(1);
        apiMethod.setApiInfo("com.hcb.f(String, int");
        return apiMethod;
    }

    /**
     * @param ids 多个id
     * @description
     */
    @Deprecated
    @Override
    public List<ApiMethod> getApiMethodByIds(String ids) {
        // TODO: 2018/1/24 逗号分割ids,

        ArrayList<ApiMethod> apiMethodList = new ArrayList<>();

        ApiMethod apiMethod = new ApiMethod();
        apiMethod.setApiId(1);
        apiMethod.setApiInfo("com.hcb.f(String, int");

        ApiMethod apiMethod1 = new ApiMethod();
        apiMethod.setApiId(1);
        apiMethod.setApiInfo("com.hcb.f(String, int");

        return null;
    }
}
