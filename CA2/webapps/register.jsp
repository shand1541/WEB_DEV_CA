<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - CA2 E-commerce</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { max-width: 500px; }
        table { margin-top: 10px; }
        td { padding: 5px; vertical-align: top; }
        label { font-weight: bold; width: 120px; display: inline-block; }
        input[type="submit"] { background-color: #007cba; color: white; border: none; padding: 10px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #005a8b; }
    </style>
</head>
<body>
    <h2>Register New Account</h2>
    
    <!-- Display action errors -->
    <s:if test="hasActionErrors()">
        <div class="error" style="color: red; background: #ffe6e6; padding: 10px; margin: 10px 0; border: 1px solid red;">
            <strong>Error:</strong><br/>
            <s:actionerror/>
        </div>
    </s:if>
    
    <!-- Display field errors -->
    <s:if test="hasFieldErrors()">
        <div class="error" style="color: red; background: #ffe6e6; padding: 10px; margin: 10px 0; border: 1px solid red;">
            <strong>Field Errors:</strong><br/>
            <s:fielderror/>
        </div>
    </s:if>
    
    <s:form action="register" method="post" theme="simple">
        <table style="width: 100%;">
            <tr>
                <td><label>Username:</label></td>
                <td><s:textfield name="username" required="true" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td><label>Email Address:</label></td>
                <td><s:textfield name="email" required="true" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td><label>Password:</label></td>
                <td><s:password name="password" required="true" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td><label>Display Name:</label></td>
                <td><s:textfield name="displayName" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td><label>Phone Number:</label></td>
                <td><s:textfield name="phone" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td><label>Address:</label></td>
                <td><s:textarea name="address" rows="3" style="width: 100%; padding: 8px;"/></td>
            </tr>
            <tr>
                <td colspan="2"><s:submit value="Register" style="background-color: #007cba; color: white; border: none; padding: 10px; cursor: pointer; width: 100%;"/></td>
            </tr>
        </table>
    </s:form>
    <p><a href="login.jsp">Already have an account? Login here</a></p>
</body>
</html>