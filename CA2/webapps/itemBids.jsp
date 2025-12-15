<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Bids - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .bid-list { margin: 10px 0; }
        .bid { background-color: #f9f9f9; padding: 10px; margin: 5px 0; border-radius: 5px; }
        .highest-bid { background-color: #e7f3ff; border-left: 4px solid #007cba; }
    </style>
</head>
<body>
    <h2>Bids for Item</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <div class="bid-list">
        <h3>All Bids:</h3>
        <% 
        ResultSet productBids = (ResultSet) request.getAttribute("productBids");
        if (productBids != null) {
            boolean hasBids = false;
            double highestBid = 0;
            
            // First pass to find highest bid
            while (productBids.next()) {
                double currentBid = productBids.getDouble("bid_value");
                if (currentBid > highestBid) {
                    highestBid = currentBid;
                }
            }
            
            // Reset cursor to beginning
            productBids.beforeFirst();
            
            while (productBids.next()) {
                hasBids = true;
                double bidValue = productBids.getDouble("bid_value");
                boolean isHighest = (bidValue == highestBid);
        %>
            <div class="bid <%= isHighest ? "highest-bid" : "" %>">
                <p><strong>Bidder:</strong> <%= productBids.getString("display_name") %></p>
                <p><strong>Bid Amount:</strong> $<%= productBids.getBigDecimal("bid_value") %></p>
                <p><strong>Bid Time:</strong> <%= productBids.getTimestamp("bid_timestamp") %></p>
                <% if (isHighest) { %>
                    <p><em>(Current Highest Bid)</em></p>
                <% } %>
            </div>
        <% 
            }
            if (!hasBids) {
        %>
            <p>No bids have been placed on this item yet.</p>
        <% 
            }
        } else {
        %>
            <p>No bids found.</p>
        <% } %>
    </div>
    
    <p><a href="allItems.action">Back to Items</a></p>
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>