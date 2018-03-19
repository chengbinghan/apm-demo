package apm.web.apmcontroller;

import apm.web.apmservice.nosql.ApmNoSqlService;
import apm.web.beans.vo.NosqlVO;
import apm.web.util.apmutil.JsonObject;
import org.apache.hadoop.hbase.shaded.org.apache.avro.data.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 10:25  2018/3/15
 * @description nosql 相关
 */

@Controller
@CrossOrigin
public class ApmNoSqlController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    ApmNoSqlService apmNoSqlService;

    /**
     * @param noSqlType   nosql 类型，是redis memcached
     * @param timeSection 查询时间区间
     * @return
     */
    @RequestMapping(value = "/noSqlGeneralView", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject<NosqlVO> getNoSqlGeneralView(String noSqlType, Long timeSection) {

        JsonObject<NosqlVO> json = null;

        try {

            NosqlVO nosqlVO = apmNoSqlService.getNoSqlGeneralView(noSqlType, timeSection);

            json = new JsonObject<>();


        } catch (Exception e) {
            json = new JsonObject<>();
            json.setSuccess(false);
            json.setResponeCode(1000);
            json.setErrMsg(e.getMessage());
            logger.error(e.getMessage());


        }


        return null;
    }


}
