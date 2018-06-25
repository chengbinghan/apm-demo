package com.apusic.agent.context;

/**
 * @author ChengBing Han
 * @date 10:40  2018/6/22
 * @description
 */
public class AgentContext {

    private String pid;
    private String jar;
    private String args;
    private String configPath;

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setJar(String jar) {
        this.jar = jar;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getPid() {
        return pid;
    }

    public String getJar() {
        return jar;
    }

    public String getArgs() {
        return args;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;

    }
}
