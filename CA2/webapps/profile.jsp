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
            margin-bottom: 30px; 
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
        .profile-form { 
            max-width: 600px; 
            margin: 0 auto; 
        }
        .form-group { 
            margin-bottom: 20px; 
        }
        label { 
            display: block; 
            margin-bottom: 5px; 
            font-weight: bold; 
            color: #333; 
        }
        input[type="text"], input[type="email"], textarea { 
            width: 100%; 
            padding: 10px; 
            border: 1px solid #ddd; 
            border-radius: 4px; 
            font-size: 14px; 
            box-sizing: border-box; 
        }
        textarea { 
            height: 80px; 
            resize: vertical; 
        }
        .btn { 
            background-color: #007cba; 
            color: white; 
            padding: 12px 24px; 
            border: none; 
            border-radius: 4px; 
            cursor: pointer; 
            font-size: 16px; 
            margin-right: 10px; 
        }
        .btn:hover { background-color: #005a8b; }
        .btn-secondary { 
            background-color: #6c757d; 
        }
        .btn-secondary:hover { background-color: #545b62; }
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
        .profile-info { 
            background-color: #f8f9fa; 
            padding: 20px; 
            border-radius: 4px; 
            margin-bottom: 20px; 
        }
        .readonly-info { 
            display: flex; 
            justify-content: space-between; 
            margin-bottom: 10px; 
        }
        .readonly-info strong { color: #495057; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>My Profile</h1>
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
        
        <s:if test="userProfile != null">
            <!-- Read-only profile information -->
            <div class="profile-info">
                <h3>Account Information</h3>
                <div class="readonly-info">
                    <strong>Member ID:</strong>
                    <span><s:property value="userProfile.memberId"/></span>
                </div>
                <div class="readonly-info">
                    <strong>Username:</strong>
                    <span><s:property value="userProfile.loginName"/></span>
                </div>
            </div>
            
            <!-- Editable profile form -->
            <div class="profile-form">
                <h3>Update Profile Information</h3>
                
                <s:form action="profile" method="post">
                    <s:hidden name="action:updateProfile"/>
                    
                    <div class="form-group">
                        <label for="displayName">Display Name:</label>
                        <s:textfield name="displayName" 
                                   id="displayName" 
                                   value="%{displayName}"
                                   placeholder="Enter your display name"/>
                        <s:fielderror fieldName="displayName" cssClass="error"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="emailAddress">Email Address:</label>
                        <s:textfield name="emailAddress" 
                                   id="emailAddress" 
                                   type="email"
                                   value="%{emailAddress}"
                                   placeholder="Enter your email address"/>
                        <s:fielderror fieldName="emailAddress" cssClass="error"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="contactNumber">Contact Number:</label>
                        <s:textfield name="contactNumber" 
                                   id="contactNumber" 
                                   value="%{contactNumber}"
                                   placeholder="Enter your phone number"/>
                        <s:fielderror fieldName="contactNumber" cssClass="error"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="postalAddress">Postal Address:</label>
                        <s:textarea name="postalAddress" 
                                  id="postalAddress" 
                                  value="%{postalAddress}"
                                  placeholder="Enter your postal address"/>
                        <s:fielderror fieldName="postalAddress" cssClass="error"/>
                    </div>
                    
                    <div class="form-group" style="text-align: center; margin-top: 30px;">
                        <s:submit value="Update Profile" cssClass="btn"/>
                        <a href="dashboard.action" class="btn btn-secondary" style="text-decoration: none; display: inline-block;">Cancel</a>
                    </div>
                </s:form>
            </div>
        </s:if>
        <s:else>
            <div class="error">
                <p>Unable to load profile information. Please try logging in again.</p>
                <a href="login.action" class="btn">Login</a>
            </div>
        </s:else>
        
        <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>