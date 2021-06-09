package com.XiaoFeng.week3.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LifeCycleServlet extends HttpServlet {
    //1.tomcate read wen.xml file and find all Servlet class
    //2.load servlet - when ? 2.first request for this servlet come in -from client
    //3.all default constructor --add code

    public LifeCycleServlet() {
        System.out.println("i am a constructor --> LifeCycleServlet()");//line 1
    }

    //4.init() add code
    //use for
    public void init() {
        System.out.println("i am init() ");//line 2
    }
    //5.tomcat call service()--> call doGet() or doPost()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("i am in service() --> doGet() ");//line 3
        //line 4 --2nd request
        //line 5 --3rd request
        //and so on -- many times -for each request
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        System.out.println("i am in destroy()");//when?stop the tomcat
    }
}
