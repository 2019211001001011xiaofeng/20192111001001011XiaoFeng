package com.XiaoFeng.week3.demo;

import com.XiaoFeng.week4.demo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//automatic -new-->servlet
@WebServlet(
        urlPatterns = {"/register"}
)
public class RegisterServlet extends HttpServlet {

    Connection con = null;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        String driver = servletContext.getInitParameter("driver");
        String url = servletContext.getInitParameter("url");
        String username = servletContext.getInitParameter("username");
        String password = servletContext.getInitParameter("password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            //System.out.println("init()-->"+con);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//request come here- <from method=post>
        //get parameter from request
       /* String username = request.getParameter( "username");//name of input type<input type="text" name="username">
        String password = request.getParameter( "password");//<input type="password" name="password"/>
        String email = request.getParameter( "email");//<input type="text" name="email"/>
        String gender = request.getParameter( "gender");//<input type="radio" name="gender">
        String birthDate = request.getParameter( "birthDate");//<input type="text name=" name="birthDate">

        //print -- write into response
        PrintWriter writer=response.getWriter();
        writer.println("<br>username :"+username);
        writer.println("<br>password :"+username);
        writer.println("<br>email :"+username);
        writer.println("<br>gender :"+username);
        writer.println("<br>birth Date :"+username);
        writer.close(); */
        response.setContentType("text/html;charset=utf-8");


        List<User> list = new ArrayList<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");

        String sql = "INSERT INTO usertable(username,password,email,gender,birthdate) VALUES (?,?,?,?,?)";

        int resultSet;
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, gender);
            ps.setString(5, birthDate);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql2 = "SELECT * FROM usertable";
        Statement statement = null;
        ResultSet resultSet2 = null;
        try {
            statement = con.createStatement();
            resultSet2 = statement.executeQuery(sql2);
//            while (resultSet2.next())
//            {
//                User user = new User();
//                user.setId(resultSet2.getInt(1));
//                user.setUsername(resultSet2.getString(2));
//                user.setPassword(resultSet2.getString(3));
//                user.setEmail(resultSet2.getString(4));
//                user.setGender(resultSet2.getString(5));
//                user.setBirthDate(resultSet2.getString(6));
//                list.add(user);
//            }
//            resultSet2.close();
//            statement.close();
            // con.close();

            //request.setAttribute("rsname", resultSet2);
            // request.getRequestDispatcher("userList.jsp").forward(request,response);
            // System.out.println("i am  in RegisterServlet-->doPost()--> afterward()");

            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        finally {
//            PrintWriter writer = null;
           /* try {
                writer = response.getWriter();
                response.setContentType("text/html;charset=utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            writer.println("<table border=\"1\">");
//            writer.println("<tr>");
//            writer.println("<td>ID</td>");
//            writer.println("<td>UserName</td>");
//            writer.println("<td>Password</td>");
//            writer.println("<td>Email</td>");
//            writer.println("<td>Gender</td>");
//            writer.println("<td>Birthday</td>");
//            writer.println("</tr>");
//            for (User user : list) {
                writer.println("<tr>");
                writer.println("<td>"+user.getId()+"</td>");
                writer.println("<td>"+user.getUsername()+"</td>");
                writer.println("<td>"+user.getPassword()+"</td>");
                writer.println("<td>"+user.getEmail()+"</td>");
                writer.println("<td>"+user.getGender()+"</td>");
                writer.println("<td>"+user.getBirthDate()+"</td>");
                writer.println("</tr>");
            }*/
//            writer.println("</table>");
//            writer.close();
//        }
        con = (Connection) getServletContext().getAttribute("con");

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
