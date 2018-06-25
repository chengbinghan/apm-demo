package com.apusic.agent.controller;

import com.apusic.agent.service.detector.Detector;
import com.apusic.agent.service.detector.DetectorService;
import com.apusic.agent.service.detector.OperateENUM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ParamController", urlPatterns = {"/detector"})
public class ParamController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");


        final String clazz = request.getParameter("clazz");
        final String method = request.getParameter("method");
        final String operator = request.getParameter("operator");

        // TODO: 2018/6/22 validate param

        final Detector detector = new Detector();
        detector.setClazz(clazz);
        detector.setMethod(method);



        final DetectorService detectorService = new DetectorService();
        if (OperateENUM.ADD.toString().equals(operator)) {
            detectorService.addDetector(detector);
        }else if(OperateENUM.REMOVE.toString().equals(operator)){
            detectorService.removeDetector(detector);
        }





    }
}
