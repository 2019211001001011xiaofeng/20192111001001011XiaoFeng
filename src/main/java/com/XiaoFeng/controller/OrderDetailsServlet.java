package com.XiaoFeng.controller;

import com.XiaoFeng.dao.OrderDao;
import com.XiaoFeng.model.Item;

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

@WebServlet(name = "OrderDetailsServlet", value = "/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=userdb", "sa", "123456");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = request.getParameter("orderId") != null ? Integer.parseInt(request.getParameter("orderId")) : 0;
        Item item = new Item();
        OrderDao orderDao = new OrderDao();
        List<Item> itemList = orderDao.findItemsByOrderId(con, orderId);
        request.setAttribute("itemList", itemList);
        String path = "WEB-INF/orderDetails.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
