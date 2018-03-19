package com.hcb.controller;

import com.hcb.utils.HttpRequestUtil;
import com.hcb.utils.TimeUtil;
import com.hcb.service.TestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

@WebServlet(name = "InvokeServlet", urlPatterns = {"/invoke"})
public class InvokeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long startMillis = System.currentTimeMillis();

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String test_url = null;
        String test_param = null;
        String thisNode = null;

        String nextNodeId = null;
        try {
            System.out.println("O==||============>>>start:" + TimeUtil.getCurrentTime());


            //从配置文件中读取下一个要调用节点的ip, 端口号等信息
            /*
            配置文件大概形式，形式如下：
            node1_url=http://192.168.110.128:8080/test
            node2_url=http://192.168.110.128:8080/test
            node3_url=http://192.168.110.128:8080/test
            node4_url=http://192.168.110.128:8080/test
            node5_url=http://192.168.110.128:8080/test
            thisNode=testNode.....................
            test_param=k1=node3
             */
            File file = new File("host.properties");
            FileReader fileReader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(fileReader);
            test_param = (String) properties.get("test_param");//k1=v1&k2=v2

            String[] paramArr = test_param.split("&");
            nextNodeId = paramArr[0].split("=")[1];


            if ("node1".equals(nextNodeId)) {
                test_url = (String) properties.get("node1_url");
            } else if ("node2".equals(nextNodeId)) {
                test_url = (String) properties.get("node2_url");
            } else if ("node3".equals(nextNodeId)) {
                test_url = (String) properties.get("node3_url");
            } else if ("node4".equals(nextNodeId)) {
                test_url = (String) properties.get("node4_url");
            } else if ("node5".equals(nextNodeId)) {
                test_url = (String) properties.get("node5_url");
            }

            System.out.println(test_param);
            System.out.println(test_url);


            TestService testService = new TestService();

            thisNode = properties.getProperty("thisNode");
            testService.testServiceMethod(thisNode);

            HttpRequestUtil.sendGet(test_url, test_param);
            System.out.println("访问下一个地址：");
            System.out.println(test_url + "/" + test_param);
            System.out.println("O==||============>>>end:" + TimeUtil.getCurrentTime());
        } catch (Exception e) {
            response.getWriter().write(test_url + "   " + test_param + "调用发生异常。");
            response.getWriter().write(e.getMessage());
        }
        long endMillis = System.currentTimeMillis();
        response.getWriter().write(test_url + "\r\n" + test_param + "调用成功\r\n" + "在本节点Node：" + thisNode + "调用下一节点" +nextNodeId +"耗时" + (endMillis - startMillis));


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
