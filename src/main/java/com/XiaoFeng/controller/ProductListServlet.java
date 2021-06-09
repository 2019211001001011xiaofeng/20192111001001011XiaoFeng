package com.XiaoFeng.controller;

import com.XiaoFeng.dao.ProductDao;
import com.XiaoFeng.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/admin/productList")
public class ProductListServlet extends HttpServlet {
    Connection con = null;

    public void init() {
        try {
            super.init();
            con = (Connection) getServletContext().getAttribute("con");
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("到达此处1");
        ProductDao productDao = new ProductDao();
        try {
            if (con == null) {
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=userdb", "sa", "123456");
            }
            List<Product> productList = productDao.findAll(con);
            if (productList == null) {
                System.out.println("没有任何信息");
            } else {
                System.out.println(productList);
            }
            request.setAttribute("productList", productList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String path = "/WEB-INF/views/admin/productList.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
