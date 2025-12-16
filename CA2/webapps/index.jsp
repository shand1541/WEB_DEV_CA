<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CA2 E-commerce Website</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .nav-box { 
            background: #f4f4f4; 
            padding: 20px; 
            margin: 10px 0; 
            border-radius: 5px; 
        }

        a { text-decoration: none; color: #007cba; margin-right: 15px; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <h1>Welcome to CA2 E-commerce Site</h1>
    
    <div class="nav-box">
        <h3>Main Navigation:</h3>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <a href="dashboard.action">Dashboard</a>
    </div>
    
    <div style="margin-top: 20px; padding: 15px; background: #e6f3ff; border-radius: 5px;">
        <h3>Demo Credentials:</h3>
        <p><strong>Username:</strong> testuser</p>
        <p><strong>Password:</strong> password</p>
    </div>
</body>
</html>