<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .user-list { margin: 10px 0; }
        .user-item { background-color: #f9f9f9; padding: 10px; margin: 5px 0; border-radius: 5px; }
    </style>
</head>
<body>
    <h2>All Users</h2>
    
    <% if (session.getAttribute("displayName") != null) { %>
        <p>Logged in as: <%= session.getAttribute("displayName") %></p>
    <% } %>
    
    <div class="user-list">
        <s:if test="allUsers != null && !allUsers.empty">
            <s:iterator value="allUsers" var="user">
                <div class="user-item">
                    <h4><s:property value="#user.displayName"/></h4>
                    <p>Username: <s:property value="#user.loginName"/></p>
                    <p>Email: <s:property value="#user.emailAddress"/></p>
                    <p>Member since: <s:property value="#user.registrationDate"/></p>
                    <a href="viewProfile.action?userId=<s:property value='#user.memberId'/>">View Profile</a>
                </div>
            </s:iterator>
        </s:if>
        <s:else>
            <p>No users found.</p>
        </s:else>
    </div>
    
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>