<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Users - CA2 E-commerce</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 0; 
            padding: 20px; 
            background-color: #f4f4f4; 
        }
        .container { 
            max-width: 1200px; 
            margin: 0 auto; 
            background-color: white; 
            padding: 20px; 
            border-radius: 8px; 
            box-shadow: 0 2px 4px rgba(0,0,0,0.1); 
        }
        .header { 
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
            margin-bottom: 20px; 
            padding-bottom: 10px; 
            border-bottom: 2px solid #007cba; 
        }
        .nav-links a { 
            margin: 0 10px; 
            text-decoration: none; 
            color: #007cba; 
            font-weight: bold; 
        }
        .nav-links a:hover { color: #005a8b; }
        .users-table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        .users-table th, .users-table td { 
            padding: 12px; 
            text-align: left; 
            border-bottom: 1px solid #ddd; 
        }
        .users-table th { 
            background-color: #007cba; 
            color: white; 
            font-weight: bold; 
        }
        .users-table tr:hover { background-color: #f5f5f5; }
        .error { 
            color: #dc3545; 
            background-color: #f8d7da; 
            border: 1px solid #f5c6cb; 
            padding: 10px; 
            border-radius: 4px; 
            margin: 10px 0; 
        }
        .success { 
            color: #155724; 
            background-color: #d4edda; 
            border: 1px solid #c3e6cb; 
            padding: 10px; 
            border-radius: 4px; 
            margin: 10px 0; 
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>All Users</h1>
                <s:if test="#session != null && #session['displayName'] != null">
                    <div style="color: #666; font-size: 14px; margin-top: 5px;">Logged in as: <s:property value="#session['displayName']"/></div>
                </s:if>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="viewItems.action">All Items</a>
                <a href="myBids.action">My Bids</a>
                <a href="logout.action">Logout</a>
            </div>
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
        
        <div class="users-section">
            <s:if test="users != null && !users.isEmpty()">
                <table class="users-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Display Name</th>
                            <th>Email</th>
                            <th>Contact Number</th>
                            <th>Address</th>
                            <th>Join Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="users" var="user">
                            <tr>
                                <td><s:property value="#user.memberId"/></td>
                                <td><s:property value="#user.loginName"/></td>
                                <td><s:property value="#user.displayName" default="N/A"/></td>
                                <td><s:property value="#user.emailAddress" default="N/A"/></td>
                                <td><s:property value="#user.contactNumber" default="N/A"/></td>
                                <td><s:property value="#user.postalAddress" default="N/A"/></td>
                                <td><s:property value="#user.joinDate" default="N/A"/></td>
                                <td>
                                    <a href="viewOtherProfile.action?userId=<s:property value='#user.memberId'/>" 
                                       style="color: #007cba; text-decoration: none; font-weight: bold;">View Profile</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                <div style="text-align: center; padding: 40px; color: #666;">
                    <h3>No users found</h3>
                    <p>There are no registered users in the system.</p>
                </div>
            </s:else>
        </div>
        
        <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>