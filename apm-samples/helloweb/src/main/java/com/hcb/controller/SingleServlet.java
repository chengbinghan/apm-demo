package com.hcb.controller;


import com.hcb.utils.TimeUtil;
import com.hcb.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



@WebServlet(name = "SingleServlet", urlPatterns = {"/updateDb"})
public class SingleServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long startMillis = System.currentTimeMillis();

        try {
            System.out.println("O==||============>>>start:" + TimeUtil.getCurrentTime());


            TestService testService = new TestService();

            String str = "com.hcb.controller.doGet ==》invoke TestService.testServiceMethod:" + TimeUtil.getCurrentTime();

            testService.testServiceMethod(str);


            System.out.println("O==||============>>>end:" + TimeUtil.getCurrentTime());
        } catch (Exception e) {
            response.getWriter().write("调用发生异常。");
            response.getWriter().write(e.getMessage());
        }
        long endMillis = System.currentTimeMillis();
        response.getWriter().write("三层调用耗时为：" + (endMillis - startMillis));


    }

    public static void main(String[] args) throws IOException {
        getUrl();
    }

    public static String getUrl() throws IOException {


        File f = new File("");
        String absoluteFile = f.getAbsolutePath();
        System.out.println(absoluteFile);


        Properties properties = new Properties();

        File file = new File(f + "/host.properties");
        file.createNewFile();
        FileReader fileReader = new FileReader(file);
        properties.load(fileReader);

        String url = (String) properties.get("url");
        return url;

    }
}
