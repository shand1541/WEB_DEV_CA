<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - CA2 E-commerce</title>
</head>
<body>
    <h2>Register New Account</h2>
    <s:form action="register">
        <s:textfield name="username" label="Username" />
        <s:textfield name="email" label="Email" />
        <s:password name="password" label="Password" />
        <s:textfield name="displayName" label="Display Name" />
        <s:textfield name="phone" label="Phone" />
        <s:textfield name="address" label="Address" />
        <s:submit value="Register" />
    </s:form>
    <p><a href="login.jsp">Already have an account? Login here</a></p>
</body>
</html>