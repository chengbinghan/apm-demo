package apm.web.apmservice.monitor;

import apm.web.beans.module.Monitor;
import apm.web.beans.module.MonitorPro;
import apm.web.beans.module.MonitorStrategy;
import apm.web.core.monitor.MonitorVO;
import apm.web.dao.apmmysql.MonitorDao;
import apm.web.dao.apmmysql.MonitorProDao;
import apm.web.util.apmconstant.MonitorConstant;
import apm.web.util.apmutil.ApmStringUtil;
import apm.web.util.monitor.MonitorFlagObj;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChengBing Han
 * @date 16:08  2018/2/26
 * @description 主要用于监控策略的CRUD
 */
@Service
//注意有俩个add, 要都成功、添加事务

public class MonitorProServiceImpl implements MonitorProService {


    @Resource
    MonitorDao monitorDao;
    @Resource
    MonitorProDao monitorProDao;

    @Transactional
    @Override
    public void addMonitorStrategy(MonitorStrategy monitorStrategy, String monitorVOStrs) throws Exception {


       /*
        monitorVOStrs 是  monitorType1,monitorRpm1,monitorTime1,monitorThrehold1_ monitorType2,monitorRpm2,monitorTime2,monitorThrehold2
        封装为 一个MonitorVO 数组，并校验。
         */

        String[] monitorProArr = monitorVOStrs.split(" ");

        List<MonitorVO> monitorVOList = new ArrayList<>();

        for (int i = 0; i < monitorProArr.length; i++) {
            MonitorVO monitorVO = new MonitorVO();
            if(monitorProArr[i].contains(",,")){
                continue;
            }
            String[] strArr = monitorProArr[i].split(",");
            if(strArr.length < 4){
                continue;
            }
            String type = strArr[0];
            String rpm = strArr[1];
            String time = strArr[2];
            String threhold  = strArr[3];

            if(ApmStringUtil.isEmpty(type)){
                continue;
            }
            if(ApmStringUtil.isEmpty(rpm)){
                continue;
            }
            if(ApmStringUtil.isEmpty(time)){
                continue;
            }
            if(ApmStringUtil.isEmpty(threhold)){
                continue;
            }

            monitorVO.setMonitorType(type);
            monitorVO.setMonitorRpm(Integer.parseInt(rpm));
            monitorVO.setMonitorTime(Integer.parseInt(time));
            monitorVO.setMonitorThreshold(threhold);
            monitorVOList.add(monitorVO);
        }

        MonitorVO[] monitorVOArr = new MonitorVO[monitorVOList.size()];
        for (int i = 0; i < monitorVOList.size(); i++) {
            monitorVOArr[i] = monitorVOList.get(i);
        }




        // TODO: 2018/2/26 假数据==================================》
/*        monitorStrategy = new MonitorStrategy();
        monitorStrategy.setType(MonitorConstant.MONITOR_STRATEGY_OUTSERVICE);
        monitorStrategy.setName("name..");
        MonitorVO monitorVO1 = new MonitorVO();
        monitorVO1.setMonitorRpm(911111111);
        monitorVO1.setMonitorRpm(3);
        monitorVO1.setMonitorType(MonitorConstant.MONITOR_TYPE_RESPNOSE_TIME);
        monitorVO1.setMonitorThreshold(1111111111 + "");

        MonitorVO monitorVO2 = new MonitorVO();
        monitorVO2.setMonitorRpm(911111111);
        monitorVO2.setMonitorRpm(3);
        monitorVO2.setMonitorType(MonitorConstant.MONITOR_TYPE_RESPNOSE_TIME);
        monitorVO2.setMonitorThreshold(1111111111 + "");
        monitorVOArr = new MonitorVO[2];
        monitorVOArr[0] = monitorVO1;
        monitorVOArr[1] = monitorVO1;*/




        if (monitorStrategy == null || monitorVOArr == null) {
            throw new Exception("param is null!");
        }

        if (ApmStringUtil.isEmpty(monitorStrategy.getType()) || monitorVOArr.length == 0) {
            throw new Exception("param is empty or null!!");
        }






        String monitorIds = "";
        //封装数据
        for (MonitorVO monitorVO : monitorVOArr) {
            Monitor monitor = new Monitor();

            //查询数据库中目前最大的monitor id.
            //多线程，防止出现问题
            synchronized (this) {
                Integer monitorId = monitorDao.getMaxMonitorId();
                monitor.setId(++monitorId);
                monitorIds = monitorIds + monitorId + ",";

            }
            monitor.setRpm(monitorVO.getMonitorRpm());
            monitor.setThreshold(monitorVO.getMonitorThreshold());
            monitor.setTime(monitorVO.getMonitorTime());
            monitor.setType(monitorVO.getMonitorType());

            monitorDao.addMonitor(monitor);

        }
        monitorStrategy.setMonitors(monitorIds);

        monitorDao.addMonitorStrategy(monitorStrategy);

    }

    @Override
    public List<MonitorStrategy> findMonitorStrategy() {

        List<MonitorStrategy> monitorStrategyList = monitorDao.findMonitorStrategy();


        if (monitorStrategyList != null) {
            for (MonitorStrategy monitorStrategy : monitorStrategyList) {
                String monitors = monitorStrategy.getMonitors();
                if (monitors != null) {
                    String[] monitroIdArr = monitors.split(",");

                    List<Monitor> monitorsByIds = monitorDao.getMonitorsByIds(monitroIdArr);
                    monitorStrategy.setMonitorList(monitorsByIds);

                }
            }
        }


        return monitorStrategyList;
    }

    /**
     * @param monitorPro
     * @param appId
     * @description 添加监控项目
     */
    @Override
    public void addMonitorPro(MonitorPro monitorPro, String appId, String strategyId) throws Exception {

        // TODO: 2018/2/26 固定参数=================》
        monitorPro = new MonitorPro();
        monitorPro.setUserIds("1,2");
        appId = "11";
        strategyId = "1";


        if (monitorPro == null || ApmStringUtil.isEmpty(appId) || ApmStringUtil.isEmpty(strategyId)) {

            throw new Exception("param is empty or null!");


        }


        Map<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("appId", appId);
        hashMap.put("userId", monitorPro.getUserIds());
        hashMap.put("strategyId", strategyId);
        monitorDao.addMonitorPro(hashMap);

        /*
        每次添加后将，监控标志未设置为true,这样会再查询一次数据库
         */
        MonitorFlagObj.setOperateMonitorTable(true);



    }





    /**
     * @description 查找MonitorPro
     */
    @Override
    public List<MonitorPro> findMonitorProList() {

        List<MonitorPro> monitorProList = monitorProDao.getAllMonitorPro();

        // TODO: 2018/2/21 此处操作待优化
        for (MonitorPro monitorPro : monitorProList) {


            String monitorsStr = monitorPro.getMonitorStrategy().getMonitors();
            String[] monitorsIdArr = monitorsStr.split(",");

            List<Monitor> monitorList = monitorDao.getMonitorsByIds(monitorsIdArr);

            monitorPro.getMonitorStrategy().setMonitorList(monitorList);


        }


        return monitorProList;
    }

}
