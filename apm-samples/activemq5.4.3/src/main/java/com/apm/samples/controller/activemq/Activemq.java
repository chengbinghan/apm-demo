package com.apm.samples.controller.activemq;

import com.apm.samples.service.activemq.ActivemqService;
import com.apm.samples.utils.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Activemq", urlPatterns = {"/activemq"})
public class Activemq extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        ActivemqService activemqServcie = new ActivemqService();
        try {
            activemqServcie.productor();
        } catch (Exception e) {
            response.getWriter().write("调用发生异常" + e.getMessage());

        }

        try {
            activemqServcie.consumer();
        } catch (Exception e) {
            response.getWriter().write("消费发生异常" + e.getMessage());

        }

        response.getWriter().write("创建并消费一条消息" + TimeUtil.getCurrentTime());




    }
}
