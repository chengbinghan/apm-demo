package apm.web.apmcontroller;

import apm.web.apmservice.MethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author ChengBing Han
 * @date 19:48  2018/1/24
 * @description 方法相关api_meta_data 表。
 */
public class MethodController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private MethodService methodService;

    @RequestMapping(value = "/method", method = RequestMethod.GET)
    @ResponseBody
    //ids 逗号分割
    public void getMethodById(String ids){

        methodService.getApiMethodByIds(ids);



    }



}
