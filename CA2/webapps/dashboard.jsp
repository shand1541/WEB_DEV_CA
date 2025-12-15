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
            <a href="myProfile.action">My Profile</a>
            <a href="allUsers.action">View All Users</a>
            <a href="allItems.action">View Items</a>
            <a href="showAddItem.action">Add Item</a>
            <a href="myBids.action">My Bids</a>
            <a href="logout.action">Logout</a>
        </div>
    </div>
    
    <h2>Welcome to your Dashboard</h2>
    <p>Choose an option from the menu above to get started.</p>
</body>
</html>