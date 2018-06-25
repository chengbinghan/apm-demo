package com.apusic.agent.controller;

import com.apusic.agent.context.AgentContext;
import com.apusic.agent.service.startup.AgentStartUp;
import com.apusic.agent.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InstallAgent", urlPatterns = {"/installAgent"})
public class AgentInitServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {

            String pid = System.getProperty("agent.pid");
            String jar = System.getProperty("agent.jar");
            String args = System.getProperty("agent.args");
            String config = System.getProperty("agent.config");

            if (StringUtil.isEmpty(pid)) {
                pid = request.getParameter("pid");
            }
            if (StringUtil.isEmpty(jar)) {
                jar = request.getParameter("jar");
            }
            if (StringUtil.isEmpty(args)) {
                args = request.getParameter("args");
            }

            final AgentContext agentContext = new AgentContext();
            agentContext.setArgs(args);
            agentContext.setConfigPath(config);
            agentContext.setJar(jar);
            agentContext.setPid(pid);

            new AgentStartUp().startUp(agentContext);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("install agent fail");

        }

    }
}
