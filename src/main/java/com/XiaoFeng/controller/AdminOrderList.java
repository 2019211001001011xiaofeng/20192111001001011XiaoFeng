package com.XiaoFeng.controller;

import com.XiaoFeng.dao.OrderDao;
import com.XiaoFeng.model.Order;
import com.XiaoFeng.model.Payment;

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

@WebServlet("/admin/orderList")
public class AdminOrderList extends HttpServlet {
    private Connection con=null;
    public void destroy(){
        super.destroy();
    }
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
    public void dopost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        doGet(request,response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Payment> paymentTypeList=Payment.findAllPayment(con);
        request.setAttribute("paymentTypeList",paymentTypeList);
        OrderDao orderDao=new OrderDao();
        List<Order> orderList=orderDao.findAll(con);
        request.setAttribute("orderList",orderList);
        String path="/WEB-INF/views/admin/orderList.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
