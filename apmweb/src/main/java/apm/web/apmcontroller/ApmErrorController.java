package apm.web.apmcontroller;

import apm.web.beans.vo.AppErrByCount;
import apm.web.beans.vo.ErrMsgVO;
import apm.web.beans.vo.TransactionErrorVO;
import apm.web.apmservice.ApmErrorService;
import apm.web.util.apmutil.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChengBing Han
 * @date 13:21  2018/2/1
 * @description 错误率相关处理类
 */

@Controller
@CrossOrigin
public class ApmErrorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private ApmErrorService apmErrorService;

    /**
     * @param appId 应用applicationId
     * @description 用于查询某个事务transaction在某个application 中的span.
     */
    @RequestMapping(value = "/transactionErrPer", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TransactionErrorVO> getTransactionErrPerList(String appId) {

        JsonObject<TransactionErrorVO> json = null;

        try {
            List<TransactionErrorVO> transactionErrorVOs = apmErrorService.getTransactionErrPerList(appId);
            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(transactionErrorVOs);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);
        }

        return json;

    }


    /**
     * @param appId      应用applicationId
     * @param errorCount 错误条数
     * @description 用于根据rpc 划分 查询某个app(appId) 的 n条sql错误时间，
     */
    @RequestMapping(value = "/errPerByErrCount", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<AppErrByCount> getErrPerByErrCount(String appId, String errorCount) {

        JsonObject<AppErrByCount> json = null;

        json = apmErrorService.getErrPerByErrCount(appId, errorCount);

        return json;
    }


    /**
     * @param appId.
     * @description 最近异常列表查询
     */
    @RequestMapping(value = "/lastErrorList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<ErrMsgVO> getLastErrorList(String appId) {

        JsonObject<ErrMsgVO> json = null;

        try {

            List<ErrMsgVO> errMsgVOList = apmErrorService.getLastErrorList(appId);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setResponeCode(200);
            json.setObjectList(errMsgVOList);
        }catch (Exception e){
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);
            json.setSuccess(false);

        }
        return json;
    }


}
