package com.XiaoFeng.controller;

import com.XiaoFeng.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "GetImgServlet", value = "/getImg")
public class GetImgServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        if (request.getParameter("id") != null)
            id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDao();
        byte[] imgByte = new byte[0];
        try {
            if (con == null) {
                System.out.println("Img未连接");
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=userdb", "sa", "123456");
            }
            imgByte = productDao.getPictureById(id, con);
            if (imgByte != null) {
                response.setContentType("image/gif");
                OutputStream out = response.getOutputStream();
                out.write(imgByte);
                out.flush();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
