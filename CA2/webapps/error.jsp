<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .error { color: red; background-color: #ffe6e6; padding: 15px; border-radius: 5px; }
    </style>
</head>
<body>
    <h2>Application Error</h2>
    
    <div class="error">
        <s:if test="hasActionErrors()">
            <s:actionerror/>
        </s:if>
        <s:else>
            <p>An unexpected error occurred while processing your request.</p>
        </s:else>
    </div>
    
    <p><a href="index.jsp">Return to Home</a> | <a href="dashboard.jsp">Dashboard</a></p>
</body>
</html>