<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Login</h2>
<form action="login" method="POST">
    <p>Username: <input type="text" name="username"/></p>
    <p>Password: <input type="password" name="password"/></p>

    <%
        if(request.getAttribute("error") != null){
            out.print("<p>Invalid Credentials</p>");
        }
    %>

    <input type="submit" value="login">
</form>
<p>Dont have an account?<a href="register.jsp">Register</a></p>
</body>
</html>
