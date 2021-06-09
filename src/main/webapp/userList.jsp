<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<h1>User List</h1>
<table border=1>
    <tr>
        <td>Id</td>
        <td>Username</td>
        <td>password</td>
        <td>Email</td>
        <td>Gender</td>
        <td>Birthday</td>
    </tr>
    <%
        ResultSet result2 = (ResultSet) request.getAttribute("rsname");
        if (result2 == null) {

    %>
    <tr>
        <td>No Data!!!</td>
    </tr>

    <%
        } else
            while (result2.next()) {
                //get one
                out.println("<tr>");
                out.println("<td>" + result2.getString("Id") + "</td>");
                out.println("<td>" + result2.getString("username") + "</td>");
                out.println("<td>" + result2.getString("password") + "</td>");
                out.println("<td>" + result2.getString("email") + "</td>");
                out.println("<td>" + result2.getString("gender") + "</td>");
                out.println("<td>" + result2.getString("birthdate") + "</td>");
                out.println("</tr>");

            }

    %>
</table>

<%@include file="footer.jsp" %>