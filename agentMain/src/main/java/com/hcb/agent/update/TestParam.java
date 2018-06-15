package com.hcb.agent.update;

import java.util.Properties;

/**
 * @author ChengBing Han
 * @date 15:13  2018/6/15
 * @description
 */
public class TestParam {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("asdffasfd");

        System.setProperty("javaplugin.vm.options","-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8787");
        final String property1 = System.getProperty("javaplugin.vm.options");
        System.out.println(property1);

        Thread.sleep(10000000);
    }
}
