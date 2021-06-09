<%@ page import="com.XiaoFeng.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<h1> User Info</h1>
<%--<%--%>
<%--    Cookie [] allCookies=request.getCookies();--%>
<%--    for(Cookie c:allCookies){--%>
<%--        out.println("<br/>"+c.getName()+" -- "+c.getValue());--%>
<%--    }--%>
<%--%>--%>
<%
    User u = (User) session.getAttribute("user");
%>
<table>
    <tr>
    <tr>
        <td>Username:</td>
        <td><%=u.getUsername()%>
        </td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><%=u.getPassword()%>
        </td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><%=u.getEmail()%>
        </td>
    </tr>
    <tr>
        <td>Gender:</td>
        <td><%=u.getGender()%>
        </td>
    </tr>
    <tr>
        <td>BirthDate:</td>
        <td><%=u.getBirthDate()%>
        </td>
    </tr>
    <td>
    <td><a href="updateUser">update</a></td>
    </tr>
    </tr>
</table>

<%@include file="footer.jsp" %>