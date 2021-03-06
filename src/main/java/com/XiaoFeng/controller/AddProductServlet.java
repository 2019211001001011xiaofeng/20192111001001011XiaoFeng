package com.XiaoFeng.controller;

import com.XiaoFeng.dao.ProductDao;
import com.XiaoFeng.model.Category;
import com.XiaoFeng.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con = null;

    public void init() {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=userdb", "sa", "123456");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            List<Category> categoryList = Category.findAllCategory(con);
            request.setAttribute("categoryList", categoryList);
            //user later
            String path = "/WEB-INF/views/admin/addProduct.jsp";
            request.getRequestDispatcher(path).forward(request, response);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        double price = request.getParameter("price") != null ? Double.parseDouble(request.getParameter("price")) : 0.0;
        int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 8;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;
        Part fileParts = request.getPart("picture");
        if (fileParts != null) {
            inputStream = fileParts.getInputStream();
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setPrice(price);
        product.setCategoryId(categoryId);

        ProductDao productDao = new ProductDao();
        try {
            int n = productDao.save(product, con);
            if (n > 0) {
                response.sendRedirect("productList");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
