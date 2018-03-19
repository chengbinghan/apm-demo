package apm.web.apmcontroller.monitor;

import apm.web.apmservice.monitor.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 项目启动执行监控，不可以采用servlet.init()方法。
 */
@Service
public class StartGateServiceData implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    @javax.annotation.Resource
    MonitorService monitorService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            // 在web项目中（spring mvc），系统会存在两个容器，一个是root application context
            // ,另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
            // 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免这个问题，我们可以只在root
            // application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理。
            if (event.getApplicationContext().getParent() == null) {

                // TODO: 2018/2/27 开发期间关闭监控
                if(true){
                    return;
                }


                // 需要实现的功能
                logger.info("O===||=============================>>" +
                        "\r\nAPM后台警报监控开始执行\r\n" +
                        "O===||=============================>>");
                monitorService.initMonitorThreadPool();

            }
        } catch (Exception e) {
              logger.error(e.getMessage());
        }
    }
}