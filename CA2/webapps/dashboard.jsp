<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .header { background-color: #f4f4f4; padding: 10px; margin-bottom: 20px; }
        .nav-links a { margin-right: 15px; text-decoration: none; color: #333; }
        .nav-links a:hover { color: #007cba; }
    </style>
</head>
<body>
    <div class="header">
        <% if (session.getAttribute("displayName") != null) { %>
            <h3>Logged in as: <%= session.getAttribute("displayName") %></h3>
        <% } else { %>
            <h3>Welcome to CA2 E-commerce</h3>
        <% } %>
        
        <div class="nav-links">
            <a href="profile">My Profile</a>
            <a href="viewUsers">View All Users</a>
            <a href="viewItems">View Items</a>
            <a href="addItemForm">Add Item</a>
            <a href="myBids">My Bids</a>
            <a href="logout">Logout</a>
        </div>
    </div>
    
    <h2>Welcome to your Dashboard</h2>
    <p>Choose an option from the menu above to get started.</p>
</body>
</html>