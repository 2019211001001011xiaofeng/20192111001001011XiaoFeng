package com.XiaoFeng.week2.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//now its just java class
//extend HttpServlet

public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //when Client method is get -here- inside doGet()
        //we want to send Hello to Client
        //we need to write Hello in response
        //get writer - java.io
        PrintWriter writer = response.getWriter();
        writer.println("Hello Client");
        //next we need to tell about this servlet to tomcat -- how？ -web.xml

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {


    }
}
