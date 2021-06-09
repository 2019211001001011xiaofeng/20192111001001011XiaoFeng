<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<h1> User Info</h1>
<table>
    <tr>
        <td>Username:</td>
        <td><%=request.getAttribute("username")%>
        </td>
        <td>Password:</td>
        <td><%=request.getAttribute("password")%>
        </td>
        <td>Email:</td>
        <td><%=request.getAttribute("email")%>
        </td>
        <td>Gender:</td>
        <td><%=request.getAttribute("gender")%>
        </td>
        <td>BirthDate:</td>
        <td><%=request.getAttribute("birthdate")%>
        </td>
    </tr>
</table>

<%@include file="footer.jsp" %>