<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Bids - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .bid-list { margin: 10px 0; }
        .bid { background-color: #f9f9f9; padding: 15px; margin: 10px 0; border-radius: 5px; }
    </style>
</head>
<body>
    <h2>My Bids</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <div class="bid-list">
        <% 
        ResultSet myBids = (ResultSet) request.getAttribute("myBids");
        if (myBids != null) {
            boolean hasBids = false;
            while (myBids.next()) {
                hasBids = true;
        %>
            <div class="bid">
                <h3><%= myBids.getString("product_name") %></h3>
                <p><strong>My Bid:</strong> $<%= myBids.getBigDecimal("bid_value") %></p>
                <p><strong>Bid Date:</strong> <%= myBids.getTimestamp("bid_timestamp") %></p>
                <p><strong>Product ID:</strong> <%= myBids.getInt("product_id") %></p>
                
                <a href="itemBids.action?productId=<%= myBids.getInt("product_id") %>">View All Bids for this Item</a>
            </div>
        <% 
            }
            if (!hasBids) {
        %>
            <p>You haven't placed any bids yet.</p>
        <% 
            }
        } else {
        %>
            <p>No bids found.</p>
        <% } %>
    </div>
    
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
    <p><a href="allItems.action">Browse Items</a></p>
</body>
</html>