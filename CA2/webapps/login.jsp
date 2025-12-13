<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - CA2 E-commerce</title>
</head>
<body>
    <h2>Login</h2>
    <s:form action="login">
        <s:textfield name="username" label="Username" />
        <s:password name="password" label="Password" />
        <s:submit value="Login" />
    </s:form>
    <p><a href="register.jsp">Don't have an account? Register here</a></p>
</body>
</html>