package apm.web.apmcontroller;

import apm.web.apmservice.reportform.ReportFormService;
import apm.web.beans.vo.DatabaseReportFormVO;
import apm.web.beans.vo.TransactionReportFormVO;
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
 * @date 9:45  2018/2/23
 * @description 报表相关controller
 */


@Controller
@CrossOrigin
public class ReportFormsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ReportFormService reportFormService;


    /**
     * @param appId   应用名称
     * @param timeStr 应用名称
     *                "ALLDAY,WEEKBEFORE,WEEK,YESTERDAY,TODAY" 分别表示查询的是今天，昨天，一周，一周前，所有
     * @return 报表 对象
     */
    @RequestMapping(value = "/transactionForm", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<TransactionReportFormVO> getTransactionFormByTime(String timeStr, String appId) {

        JsonObject<TransactionReportFormVO> json = null;

        try {

            List<TransactionReportFormVO> transactionReportFormVOList = reportFormService.getTransactionFormByTime(timeStr, appId);

            json = new JsonObject<>();
            json.setResponeCode(200);
            json.setSuccess(true);
            json.setObjectList(transactionReportFormVOList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
        }
        return json;

    }


    /**
     * @param appId   应用名称
     * @param timeStr 应用名称
     *                "ALLDAY,WEEKBEFORE,WEEK,YESTERDAY,TODAY" 分别表示查询的是今天，昨天，一周，一周前，所有
     * @return 报表 对象
     */
    @RequestMapping(value = "/databaseForm", method = RequestMethod.GET)
    @ResponseBody

    public JsonObject<DatabaseReportFormVO> getDatabaseFormByTime(String timeStr, String appId) {

        JsonObject<DatabaseReportFormVO> json = null;

        try {

            List<DatabaseReportFormVO> databaseReportFormVOList = reportFormService.getDatabaseFormByTime(timeStr, appId);
            json = new JsonObject<>();
            json.setSuccess(true);
            json.setObjectList(databaseReportFormVOList);
            json.setResponeCode(200);

        } catch (Exception e) {
            logger.error(e.getMessage());
            json = new JsonObject<>();
            json.setErrMsg(e.getMessage());
            json.setResponeCode(1000);
            json.setSuccess(false);

        }

        return json;
    }


}
