package com.hcb.debug;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author ChengBing Han
 * @date 16:27  2018/6/7
 * @description
 */
public class TestUpdate {
    public static void main(String[] args) throws Exception {
        // 被监控jvm的pid(windows上可以通过任务管理器查看)
        String targetVmPid = "11024";
        // Attach到被监控的JVM进程上
        VirtualMachine virtualmachine = VirtualMachine.attach(targetVmPid);

        // 让JVM加载jmx Agent
        String javaHome = virtualmachine.getSystemProperties().getProperty("java.home");
        String agentPath = "C:\\kingdee\\study\\bytecode\\bytecode-workspace\\update-clazz\\agentMain\\target\\agentMain123.jar";
        // String jmxAgent = javaHome + File.separator + "lib" + File.separator + "agentMain.jar";
        virtualmachine.loadAgent(agentPath, "com.sun.management.jmxremote");
    }

}
