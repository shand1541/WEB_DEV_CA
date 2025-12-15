<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Item - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { max-width: 500px; }
        input[type="text"], textarea, select { width: 100%; padding: 8px; margin: 5px 0; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>Add Item for Sale</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <div class="form-container">
        <s:form action="addItem" method="post">
            <s:textfield name="itemName" label="Item Name" required="true"/>
            <s:textarea name="itemDescription" label="Description" rows="4"/>
            <s:select name="category" label="Category" 
                     list="{'Electronics','Clothing','Books','Sports','Home','Other'}" 
                     value="'Other'"/>
            <s:textfield name="startingBid" label="Starting Bid ($)" required="true" type="number" step="0.01"/>
            <s:submit value="Add Item"/>
        </s:form>
    </div>
    
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
    <p><a href="allItems.action">View All Items</a></p>
</body>
</html>