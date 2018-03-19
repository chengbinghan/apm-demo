package com.hcb.controller;

import com.hcb.service.RedisService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RedisController", urlPatterns = {"/redis"})
public class RedisController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        System.out.println("调用redis====================>>>Controller-start");

        RedisService redisService = new RedisService();
        try {
            redisService.testRedis();
        } catch (Exception e) {
            e.printStackTrace();

            response.getWriter().write(e.getMessage());
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateStr = simpleDateFormat.format(date);
        response.getWriter().write("redis invoke success,at" + dateStr);


        System.out.println("调用redis====================>>>Controller-end");



    }
}
