<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 400px; }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; margin: 5px 0; box-sizing: border-box; }
        input[type="submit"] { background-color: #007cba; color: white; border: none; padding: 10px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #005a8b; }
    </style>
</head>
<body>
    <h2>Register New Account</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="displayName">Display Name:</label><br>
        <input type="text" id="displayName" name="displayName" required><br><br>
        
        <label for="phone">Phone:</label><br>
        <input type="text" id="phone" name="phone"><br><br>
        
        <label for="address">Address:</label><br>
        <textarea id="address" name="address" rows="3" cols="30"></textarea><br><br>
        
        <input type="submit" value="Register">
    </form>
    <p><a href="login.jsp">Already have an account? Login here</a></p>
</body>
</html>