package com.apusic.agent.service.startup;

import com.apusic.agent.context.AgentContext;
import com.apusic.agent.util.StringUtil;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;

/**
 * @author ChengBing Han
 * @date 10:23  2018/6/22
 * @description
 */
public class AgentStartUp {


    public void startUp(AgentContext agentContext) {

        try {
            handleParam(agentContext);
            // 被监控jvm的pid(windows上可以通过任务管理器查看)
            // Attach到被监控的JVM进程上
            VirtualMachine virtualmachine = VirtualMachine.attach(agentContext.getPid());

            virtualmachine.loadAgent(agentContext.getJar(), agentContext.getConfigPath());

            virtualmachine.detach();


        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("add agent error");
        }


    }

    private void handleParam(AgentContext agentContext) throws Exception {
        if (StringUtil.isEmpty(agentContext.getPid())) {
            throw new Exception("param pid is null");
        }
        if (StringUtil.isEmpty(agentContext.getJar())) {
            throw new Exception("agent path  is null");
        }

        final File file = new File(agentContext.getJar());
        if (!file.exists()) {
            throw new Exception("agent jar is not exist!!!!");
        }
    }
}
