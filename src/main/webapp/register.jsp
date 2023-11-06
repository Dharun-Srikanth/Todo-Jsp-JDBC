<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Register</title>
</head>
<body>
<form method="post" action="register">
<p>Enter User Name: <input type="text" name="name"></p>
<p>Enter Password: <input type="password" name="password"></p>
<p>Enter Confirm Password: <input type="password" name="confirmPassword"></p>
<input type="submit" value="Create Account">
<%
if(request.getAttribute("error")!=null){
out.print("<p>Password Mismatch</p>");
}
%>
</form>
</body>
</html>