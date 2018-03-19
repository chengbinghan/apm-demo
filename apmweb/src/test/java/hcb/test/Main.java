package hcb.test;

import apm.web.core.monitor.MonitorVO;
import apm.web.util.apmutil.ApmStringUtil;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * JavaMail 版本: 1.6.0
 * JDK 版本: JDK 1.7 以上（必须）
 */
public class Main {

    public static void main(String[] args) {

        String str = ",,,";
        String[] split = str.split(", ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        String monitorVOStrs = "MONITOR_APDEX,,, MONITOR_ERROR,, MONITOR_RESPNOSE_TIME11,22,33";
        String[] monitorProArr = monitorVOStrs.split(" ");

        List<MonitorVO> monitorVOList = new ArrayList<>();

        for (int i = 0; i < monitorProArr.length; i++) {


            MonitorVO monitorVO = new MonitorVO();
            if(monitorProArr[i].contains(",,")){
                continue;
            }
            String[] strArr = monitorProArr[i].split(",");
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
            monitorVO.setMonitorTime(Integer.parseInt(threhold));
            monitorVOList.add(monitorVO);
        }

        MonitorVO[] monitorVOArr = new MonitorVO[monitorVOList.size()];
        for (int i = 0; i < monitorVOList.size(); i++) {
            monitorVOArr[i] = monitorVOList.get(i);
        }



    }

}