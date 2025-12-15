<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile - CA2 E-commerce</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 0; 
            padding: 20px; 
            background-color: #f4f4f4; 
        }
        .container { 
            max-width: 800px; 
            margin: 0 auto; 
            background-color: white; 
            padding: 30px; 
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
        .user-status { 
            color: #666; 
            font-size: 14px; 
        }
        .nav-links a { 
            margin: 0 10px; 
            text-decoration: none; 
            color: #007cba; 
            font-weight: bold; 
        }
        .nav-links a:hover { color: #005a8b; }
        .profile-info { 
            background-color: #f8f9fa; 
            padding: 20px; 
            border-radius: 4px; 
            margin: 20px 0; 
        }
        .info-row { 
            display: flex; 
            justify-content: space-between; 
            margin-bottom: 15px; 
            padding-bottom: 10px; 
            border-bottom: 1px solid #dee2e6; 
        }
        .info-row:last-child { 
            border-bottom: none; 
            margin-bottom: 0; 
        }
        .info-label { 
            font-weight: bold; 
            color: #495057; 
            min-width: 140px; 
        }
        .info-value { 
            color: #212529; 
            flex: 1; 
        }
        .error { 
            color: #dc3545; 
            background-color: #f8d7da; 
            border: 1px solid #f5c6cb; 
            padding: 15px; 
            border-radius: 4px; 
            margin: 20px 0; 
        }
        .back-link { 
            display: inline-block; 
            margin-top: 20px; 
            padding: 10px 20px; 
            background-color: #6c757d; 
            color: white; 
            text-decoration: none; 
            border-radius: 4px; 
        }
        .back-link:hover { 
            background-color: #545b62; 
            color: white; 
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>User Profile</h1>
                <s:if test="#session != null && #session['displayName'] != null">
                    <div class="user-status">Logged in as: <s:property value="#session['displayName']"/></div>
                </s:if>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="viewUsers.action">All Users</a>
                <a href="viewItems.action">All Items</a>
                <a href="logout.action">Logout</a>
            </div>
        </div>
        
        <!-- Display action errors -->
        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror/>
            </div>
        </s:if>
        
        <s:if test="viewedProfile != null">
            <div class="profile-info">
                <h2>Profile Information</h2>
                
                <div class="info-row">
                    <div class="info-label">Member ID:</div>
                    <div class="info-value"><s:property value="viewedProfile.memberId"/></div>
                </div>
                
                <div class="info-row">
                    <div class="info-label">Username:</div>
                    <div class="info-value"><s:property value="viewedProfile.loginName"/></div>
                </div>
                
                <div class="info-row">
                    <div class="info-label">Display Name:</div>
                    <div class="info-value"><s:property value="viewedProfile.displayName" default="Not specified"/></div>
                </div>
                
                <div class="info-row">
                    <div class="info-label">Email Address:</div>
                    <div class="info-value"><s:property value="viewedProfile.emailAddress" default="Not specified"/></div>
                </div>
                
                <div class="info-row">
                    <div class="info-label">Contact Number:</div>
                    <div class="info-value"><s:property value="viewedProfile.contactNumber" default="Not specified"/></div>
                </div>
                
                <div class="info-row">
                    <div class="info-label">Postal Address:</div>
                    <div class="info-value"><s:property value="viewedProfile.postalAddress" default="Not specified"/></div>
                </div>
                
                <s:if test="viewedProfile.registrationDate != null">
                    <div class="info-row">
                        <div class="info-label">Member Since:</div>
                        <div class="info-value"><s:property value="viewedProfile.registrationDate"/></div>
                    </div>
                </s:if>
                
                <s:if test="viewedProfile.accountStatus != null">
                    <div class="info-row">
                        <div class="info-label">Account Status:</div>
                        <div class="info-value"><s:property value="viewedProfile.accountStatus"/></div>
                    </div>
                </s:if>
            </div>
        </s:if>
        <s:else>
            <div class="error">
                <p>Profile information could not be loaded.</p>
            </div>
        </s:else>
        
        <a href="viewUsers.action" class="back-link">← Back to All Users</a>
        
        <div style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>