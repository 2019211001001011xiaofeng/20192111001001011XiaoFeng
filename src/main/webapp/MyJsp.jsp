<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp" %>

<a href="index.jsp">go to ecjtu</a><!--method is get-->
<form method="post"><!-- what is method when we use form?--><!--is get why?default is get -->
    <!-- is better to use post in form,data in not added in URL,you can see-->
    Name:<input type="text" name="name"><br/>
    ID:<input type="text" name="id"><br/>
    <input type="submit" value="send data to server"/>
</form>
<%@include file="footer.jsp" %>

