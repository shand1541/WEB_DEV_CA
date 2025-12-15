<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Profile - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .profile-info { background-color: #f9f9f9; padding: 15px; margin: 10px 0; }
    </style>
</head>
<body>
    <h2>User Profile</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <s:if test="profileUser != null">
        <div class="profile-info">
            <h3><s:property value="profileUser.displayName"/>'s Profile</h3>
            <p><strong>Username:</strong> <s:property value="profileUser.loginName"/></p>
            <p><strong>Display Name:</strong> <s:property value="profileUser.displayName"/></p>
            <p><strong>Email:</strong> <s:property value="profileUser.emailAddress"/></p>
            <p><strong>Member Since:</strong> <s:property value="profileUser.registrationDate"/></p>
        </div>
    </s:if>
    
    <s:else>
        <p>Profile not found.</p>
    </s:else>
    
    <p><a href="allUsers.action">Back to All Users</a></p>
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>