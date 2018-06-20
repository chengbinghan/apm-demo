package com.hcb.agent.param;

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.VMOption;
import sun.management.HotSpotDiagnostic;

import java.util.List;

/**
 * @author ChengBing Han
 * @date 21:32  2018/6/18
 * @description
 */
public class UpdateParam {
    public static void main(String[] args) {
        HotSpotDiagnosticMXBean hotSpotDiagnosticMXBean = new HotSpotDiagnostic();
        List<VMOption> diagnosticOptions = hotSpotDiagnosticMXBean.getDiagnosticOptions();
        System.out.println(diagnosticOptions);
        hotSpotDiagnosticMXBean.setVMOption("PrintGCDetails","true");


    }
}
