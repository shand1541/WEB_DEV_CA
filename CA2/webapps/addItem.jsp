<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Item - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { max-width: 500px; margin: 0 auto; }
        form { }
        label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }
        input[type='text'], input[type='number'], textarea, select { 
            width: 100%; padding: 10px; margin-bottom: 15px; 
            border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; 
        }
        textarea { height: 100px; resize: vertical; }
        button { 
            background-color: #007cba; color: white; padding: 12px 20px; 
            border: none; border-radius: 4px; cursor: pointer; font-size: 16px; 
        }
        button:hover { background-color: #005a8b; }
        .error { color: red; }
        .success { color: green; }
        .nav-links a { margin-right: 15px; text-decoration: none; color: #007cba; }
    </style>
</head>
<body>
    <div class="nav-links">
        <a href="dashboard.jsp">&larr; Back to Dashboard</a>
    </div>
    
    <div class="form-container">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
        <h2>Add New Item for Sale</h2>
        <s:if test="#session != null && #session['displayName'] != null">
            <div style="color: #666; font-size: 14px;">Logged in as: <s:property value="#session['displayName']"/></div>
        </s:if>
    </div>
        
        <!-- Display action messages -->
        <s:if test="hasActionMessages()">
            <div class="success">
                <s:actionmessage/>
            </div>
        </s:if>
        
        <!-- Display action errors -->
        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror/>
            </div>
        </s:if>
        
        <s:form action="addItem" method="post">
            <s:textfield name="title" label="Item Title" required="true"/>
            <s:textarea name="description" label="Description" rows="4"/>
            <s:select name="category" label="Category" 
                list="{'Electronics','Books','Sports','Clothing','Home','Other'}" 
                value="'Other'"/>
            <s:textfield name="price" label="Starting Price ($)" required="true" type="number" step="0.01" min="0.01"/>
            <s:submit value="Add Item"/>
        </s:form>
        
        <p><a href="viewItems.action">View All Items</a> | <a href="dashboard.jsp">Dashboard</a></p>
    </div>
</body>
</html>