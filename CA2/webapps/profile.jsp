<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .profile-info { background-color: #f9f9f9; padding: 15px; margin: 10px 0; }
    </style>
</head>
<body>
    <h2>My Profile</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <s:if test="currentUser != null">
        <div class="profile-info">
            <h3>Profile Information</h3>
            <p><strong>Username:</strong> <s:property value="currentUser.loginName"/></p>
            <p><strong>Display Name:</strong> <s:property value="currentUser.displayName"/></p>
            <p><strong>Email:</strong> <s:property value="currentUser.emailAddress"/></p>
            <p><strong>Phone:</strong> <s:property value="currentUser.contactNumber"/></p>
            <p><strong>Address:</strong> <s:property value="currentUser.postalAddress"/></p>
            <p><strong>Member Since:</strong> <s:property value="currentUser.registrationDate"/></p>
        </div>
    </s:if>
    
    <s:else>
        <p>Profile not found.</p>
    </s:else>
    
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>