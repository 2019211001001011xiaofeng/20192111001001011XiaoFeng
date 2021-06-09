package com.XiaoFeng.week5.demo;

import com.XiaoFeng.dao.UserDao;
import com.XiaoFeng.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")

public class LoginServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() {
//        ServletContext servletContext = getServletContext();
//        String driver =  servletContext.getInitParameter("driver");
//        String url =  servletContext.getInitParameter("url");
//        String username =  servletContext.getInitParameter("username");
//        String password =  servletContext.getInitParameter("password");
//        try {
//            Class.forName(driver);
//          //  con = DriverManager.getConnection(url,username,password);
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
        con = (Connection) getServletContext().getAttribute("con");
        //System.out.println(con);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost(request,response);
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        /*try {
            String sql = "SELECT * FROM usertable WHERE username=? and password=?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, request.getParameter("username"));
            ps.setString(2, request.getParameter("password"));
            ResultSet resultset;
            resultset = ps.executeQuery();
            if(resultset.next()) {
                //writer.println("Login Success!\n"+"Welcome"+request.getParameter("username"));
                request.setAttribute("id",resultset.getInt("id"));
                request.setAttribute("username",resultset.getString("username"));
                request.setAttribute("password",resultset.getString("password"));
                request.setAttribute("email",resultset.getString("email"));
                request.setAttribute("gender",resultset.getString("gender"));
                request.setAttribute("birthdate",resultset.getString("birthdate"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else {
               // writer.println("username or password error");
                request.setAttribute("message","username or password Error!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(con==null){
            System.out.println("xxxxxxxxxxxxxx");
        }
        UserDao userDao = new UserDao();
        if(userDao==null)
            System.out.println("MMMMMMMMM");
        try {
            User user = userDao.findByUsernamePassword(con, username, password);
            if (user != null) {

                String rememberMe = request.getParameter("rememberMe");
                if (rememberMe != null && rememberMe.equals("1")) {
                    Cookie usernameCookie = new Cookie("cUsername", user.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword", user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe", rememberMe);


                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);

                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
//                Cookie c=new Cookie("sessionid",""+user.getId());
//                c.setMaxAge(10*60);
//                response.addCookie(c);
                HttpSession session = request.getSession();
                System.out.println("session id-->" + session.getId());
                session.setMaxInactiveInterval(10000);
                session.setAttribute("user", user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Username or Password Error!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}