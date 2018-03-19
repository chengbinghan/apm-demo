package apm.web.apmcontroller;


import apm.web.beans.module.Span;
import apm.web.beans.module.Transaction;
import apm.web.beans.vo.TransactionVO;
import apm.web.apmservice.ApmTransactionService;
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
 * @description 事物相关接口
 */
@CrossOrigin
@Controller
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private ApmTransactionService apmTransactionService;


    /**
     * @param appId 应用id
     * @return List<Transaction>
     * @description 用于查询拓扑图表格
     */
    @RequestMapping(value = "/transactionList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TransactionVO> getTransactionListByAppId(String appId,Long timeSection) {

        JsonObject<TransactionVO> json = null;

        try {
            List<TransactionVO> transactionList = apmTransactionService.getTransactionPercent(appId,timeSection);

            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(transactionList);
            json.setResponeCode(200);
        }catch (Exception e ){
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setErrMsg(e.getMessage());
        }

        return json;
    }


    /**
     * @param topNum 查询topN 个耗时事务
     * @description 用于查询拓扑图表格
     */

    @RequestMapping(value = "/expensiveTranList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TransactionVO> getExpensiveTranList(Integer topNum, String appId,Long timeSection) {


        JsonObject<TransactionVO> json = new JsonObject<>();
        try {

            List<TransactionVO> expensiveTranList = apmTransactionService.getExpensiveTranList(topNum, appId,timeSection);
            json.setSuccess(true);
            json.setObjectList(expensiveTranList);
            json.setResponeCode(200);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
        }
        return json;

    }

    /**
     * @param topNum 查询某个事务下的所有Id
     * @description 根据事务Id 查询 其所有span
     */
    @RequestMapping(value = "/spanListByTranId", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Span> expensiveTranList(String transactionId) {

        JsonObject<Span> json = new JsonObject<>();


        return json;
    }


    /**
     * @param appId 某个application
     * @description 查询出耗时的事务列表。比如用户访问2次首页，1次登陆比较慢，那么就有3条记录。
     */


    @Deprecated
    @RequestMapping(value = "/slowTransactionList", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<Transaction> slowTransactionList(String appId) {
        // TODO: 2018/1/24 后期慢事务要分页


        JsonObject<Transaction> json = new JsonObject<>();
        try {

            List<Transaction> transactionList = apmTransactionService.slowTransactionList(appId);

            json.setObjectList(transactionList);
            json.setResponeCode(200);
            json.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json.setErrMsg(e.getMessage());
            json.setSuccess(false);
            json.setResponeCode(1000);

        }
        return json;
    }



    
}
