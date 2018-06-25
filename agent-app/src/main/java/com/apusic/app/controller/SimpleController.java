package com.apusic.app.controller;

import com.apusic.app.dao.SimpleDao;
import com.apusic.app.service.SimpleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "SimpleController", urlPatterns = {"/hello"})
public class SimpleController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        final SimpleService simpleService = new SimpleService();
        try {
            simpleService.service("start" + new Date());
            response.getWriter().write("invoke success" + new Date().getTime());
        } catch (InterruptedException e) {
            response.getWriter().write("invoke error" + new Date().getTime());
            e.printStackTrace();
        }
    }
}
