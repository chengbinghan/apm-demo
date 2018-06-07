package com.hcb.debug;



import com.hcb.agent.update.HcbSimplePrincipal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        final HcbSimplePrincipal hcbSimplePrincipal = new HcbSimplePrincipal("old");
        final String name = hcbSimplePrincipal.getName();

        System.out.println("servert name:" + name);
        final Date date = new Date();
        final long time = date.getTime();
        response.getWriter().write(time + " : " + name);


    }
}
