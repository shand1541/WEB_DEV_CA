<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .error { color: red; margin: 10px 0; }
        .form-container { max-width: 400px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 8px; margin: 5px 0; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Login</h2>
        
        <!-- Display action errors -->
        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror/>
            </div>
        </s:if>
        
        <!-- Display field errors -->
        <s:if test="hasFieldErrors()">
            <div class="error">
                <s:fielderror/>
            </div>
        </s:if>
        
        <s:form action="LoginServlet" method="post">
            <s:textfield name="username" label="Username" required="true"/>
            <s:password name="password" label="Password" required="true"/>
            <s:submit value="Login" />
        </s:form>
        
        <p><a href="register.jsp">Don't have an account? Register here</a></p>
        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</body>
</html>