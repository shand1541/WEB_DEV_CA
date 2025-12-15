<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Items - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .item-list { margin: 10px 0; }
        .item { background-color: #f9f9f9; padding: 15px; margin: 10px 0; border-radius: 5px; }
        .bid-button { background-color: #007cba; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; }
    </style>
</head>
<body>
    <h2>Items for Sale</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <div class="item-list">
        <s:if test="allItems != null && !allItems.empty">
            <s:iterator value="allItems" var="item">
                <div class="item">
                    <h3><s:property value="#item.productName"/></h3>
                    <p><strong>Description:</strong> <s:property value="#item.productDetails"/></p>
                    <p><strong>Category:</strong> <s:property value="#item.productCategory"/></p>
                    <p><strong>Starting Bid:</strong> $<s:property value="#item.minimumBid"/></p>
                    <p><strong>Current Highest Bid:</strong> $<s:property value="#item.highestBid"/></p>
                    <p><strong>Status:</strong> <s:property value="#item.listingStatus"/></p>
                    
                    <a href="itemBids.action?productId=<s:property value='#item.productId'/>">View Bids</a> |
                    
                    <s:form action="placeBid" method="post" theme="simple" cssStyle="display: inline;">
                        <s:hidden name="productId" value="%{#item.productId}"/>
                        <s:textfield name="bidAmount" placeholder="Enter bid amount" type="number" 
                                   cssStyle="width: 120px; padding: 3px;" step="0.01" min="0.01"/>
                        <s:submit value="Place Bid" cssClass="bid-button" cssStyle="margin-left: 5px;"/>
                    </s:form>
                </div>
            </s:iterator>
        </s:if>
        <s:else>
            <p>No items available.</p>
        </s:else>
    </div>
    
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
    <p><a href="showAddItem.action">Add New Item</a></p>

</body>
</html>