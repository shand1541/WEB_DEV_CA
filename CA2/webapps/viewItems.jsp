<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Items - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }
        .container { max-width: 1200px; margin: 0 auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #007cba; }
        .nav-links a { margin: 0 10px; text-decoration: none; color: #007cba; font-weight: bold; }
        .nav-links a:hover { color: #005a8b; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007cba; color: white; font-weight: bold; }
        tr:hover { background-color: #f5f5f5; }
        .error { color: #dc3545; background-color: #f8d7da; border: 1px solid #f5c6cb; padding: 10px; border-radius: 4px; margin: 10px 0; }
        .action-link { color: #007cba; text-decoration: none; margin-right: 10px; }
        .action-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>All Items for Sale</h1>
                <s:if test="#session != null && #session['displayName'] != null">
                    <div style="color: #666; font-size: 14px; margin-top: 5px;">Logged in as: <s:property value="#session['displayName']"/></div>
                </s:if>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="addItemForm.action">Add Item</a>
                <a href="myBids.action">My Bids</a>
                <a href="logout.action">Logout</a>
            </div>
        </div>
    
    <!-- Display action errors -->
    <s:if test="hasActionErrors()">
        <div class="error">
            <s:actionerror/>
        </div>
    </s:if>
    
    <p><em>Displaying <s:property value="products.size()"/> items from database (Struts2)</em></p>
    
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Minimum Bid</th>
                <th>Category</th>
                <th>Seller</th>
                <th>Actions</th>
            </tr>
            
            <s:if test="products != null && products.size() > 0">
                <s:iterator value="products">
                    <tr>
                        <td><s:property value="productId"/></td>
                        <td><s:property value="productName"/></td>
                        <td><s:property value="productDetails" default="No description"/></td>
                        <td style="font-weight: bold; color: #007cba;">$<s:property value="minimumBid"/></td>
                        <td><s:property value="productCategory" default="General"/></td>
                        <td>Owner ID: <s:property value="ownerId"/></td>
                        <td>
                            <a href="myBids.action" class="action-link">Place Bid</a> | 
                            <a href="viewItemBids.action?productId=<s:property value='productId'/>" class="action-link">View Bids</a>
                        </td>
                    </tr>
                </s:iterator>
            </s:if>
            <s:else>
                <tr><td colspan="7" style="text-align: center; color: #666;">No items available</td></tr>
            </s:else>
        </table>
        
        <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>