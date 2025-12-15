<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel - CA2 E-commerce</title>
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
            padding: 30px; 
            border-radius: 8px; 
            box-shadow: 0 2px 4px rgba(0,0,0,0.1); 
        }
        .header { 
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
            margin-bottom: 30px; 
            padding-bottom: 20px; 
            border-bottom: 3px solid #dc3545; 
        }
        .admin-badge { 
            background-color: #dc3545; 
            color: white; 
            padding: 5px 15px; 
            border-radius: 20px; 
            font-size: 12px; 
            font-weight: bold; 
        }
        .nav-links a { 
            margin: 0 10px; 
            text-decoration: none; 
            color: #007cba; 
            font-weight: bold; 
        }
        .nav-links a:hover { color: #005a8b; }
        .admin-section { 
            margin: 30px 0; 
            padding: 20px; 
            background-color: #f8f9fa; 
            border-radius: 8px; 
            border-left: 5px solid #007cba; 
        }
        .stats-grid { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); 
            gap: 20px; 
            margin: 20px 0; 
        }
        .stat-card { 
            background-color: white; 
            padding: 20px; 
            border-radius: 8px; 
            text-align: center; 
            box-shadow: 0 2px 4px rgba(0,0,0,0.1); 
        }
        .stat-number { 
            font-size: 2em; 
            font-weight: bold; 
            color: #007cba; 
        }
        .data-table { 
            width: 100%; 
            border-collapse: collapse; 
            margin: 20px 0; 
        }
        .data-table th, .data-table td { 
            padding: 12px; 
            text-align: left; 
            border-bottom: 1px solid #ddd; 
        }
        .data-table th { 
            background-color: #007cba; 
            color: white; 
        }
        .data-table tr:hover { background-color: #f5f5f5; }
        .error { 
            color: #dc3545; 
            background-color: #f8d7da; 
            border: 1px solid #f5c6cb; 
            padding: 15px; 
            border-radius: 4px; 
            margin: 20px 0; 
        }
        .success { 
            color: #155724; 
            background-color: #d4edda; 
            border: 1px solid #c3e6cb; 
            padding: 15px; 
            border-radius: 4px; 
            margin: 20px 0; 
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>Admin Panel</h1>
                <span class="admin-badge">ADMINISTRATOR</span>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="viewItems.action">All Items</a>
                <a href="viewUsers.action">All Users</a>
                <a href="logout.action">Logout</a>
            </div>
        </div>
        
        <!-- Display admin message -->
        <s:if test="adminMessage != null">
            <div class="success">
                <s:property value="adminMessage"/>
            </div>
        </s:if>
        
        <!-- Display action errors -->
        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror/>
            </div>
        </s:if>
        
        <!-- System Statistics -->
        <div class="admin-section">
            <h2>System Statistics</h2>
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-number">
                        <s:if test="allUsers != null">
                            <s:property value="allUsers.size()"/>
                        </s:if>
                        <s:else>0</s:else>
                    </div>
                    <div>Total Users</div>
                </div>
                <div class="stat-card">
                    <div class="stat-number">
                        <s:if test="allProducts != null">
                            <s:property value="allProducts.size()"/>
                        </s:if>
                        <s:else>0</s:else>
                    </div>
                    <div>Total Products</div>
                </div>
                <div class="stat-card">
                    <div class="stat-number">
                        <s:if test="allProducts != null">
                            <s:set var="activeCount" value="0"/>
                            <s:iterator value="allProducts" var="product">
                                <s:if test='#product.listingStatus.equals("available")'>
                                    <s:set var="activeCount" value="#activeCount + 1"/>
                                </s:if>
                            </s:iterator>
                            <s:property value="#activeCount"/>
                        </s:if>
                        <s:else>0</s:else>
                    </div>
                    <div>Active Listings</div>
                </div>
            </div>
        </div>
        
        <!-- Recent Users -->
        <div class="admin-section">
            <h2>Recent Users</h2>
            <s:if test="allUsers != null && !allUsers.isEmpty()">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Display Name</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>Join Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="allUsers" var="user" end="9">
                            <tr>
                                <td><s:property value="#user.memberId"/></td>
                                <td><s:property value="#user.loginName"/></td>
                                <td><s:property value="#user.displayName" default="N/A"/></td>
                                <td><s:property value="#user.emailAddress"/></td>
                                <td><s:property value="#user.accountStatus" default="active"/></td>
                                <td><s:property value="#user.registrationDate" default="N/A"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <s:if test="allUsers.size() > 10">
                    <p><em>Showing 10 of <s:property value="allUsers.size()"/> total users.</em></p>
                </s:if>
            </s:if>
            <s:else>
                <p>No users found in the system.</p>
            </s:else>
        </div>
        
        <!-- Recent Products -->
        <div class="admin-section">
            <h2>Recent Products</h2>
            <s:if test="allProducts != null && !allProducts.isEmpty()">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Minimum Bid</th>
                            <th>Status</th>
                            <th>Owner ID</th>
                            <th>Listed Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="allProducts" var="product" end="9">
                            <tr>
                                <td><s:property value="#product.productId"/></td>
                                <td><s:property value="#product.productName"/></td>
                                <td><s:property value="#product.productCategory" default="General"/></td>
                                <td>$<s:property value="#product.minimumBid"/></td>
                                <td><s:property value="#product.listingStatus"/></td>
                                <td><s:property value="#product.ownerId"/></td>
                                <td><s:property value="#product.listingDate" default="N/A"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <s:if test="allProducts.size() > 10">
                    <p><em>Showing 10 of <s:property value="allProducts.size()"/> total products.</em></p>
                </s:if>
            </s:if>
            <s:else>
                <p>No products found in the system.</p>
            </s:else>
        </div>
        
        <div style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform - Administrative Interface</p>
            <p><strong>WARNING:</strong> This is a restricted area. Unauthorized access is prohibited.</p>
        </div>
    </div>
</body>
</html>