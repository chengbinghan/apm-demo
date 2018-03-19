package apm.web.apmcontroller.ppcontroller;

import com.navercorp.pinpoint.web.service.CommonService;
import com.navercorp.pinpoint.web.view.ServerTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ChengBing Han
 * @date 21:13  2018/2/2
 * @description  获取服务器时间
 */
@Controller
public class ServerTimeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/apmServerTime", method = RequestMethod.GET)
    @ResponseBody
    public ServerTime getServerTime() {
        return new ServerTime();
    }

}
