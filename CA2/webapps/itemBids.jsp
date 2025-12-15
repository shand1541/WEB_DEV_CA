<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Bids - CA2 E-commerce</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 0; 
            padding: 20px; 
            background-color: #f4f4f4; 
        }
        .container { 
            max-width: 1000px; 
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
        .product-info { 
            background-color: #f8f9fa; 
            padding: 20px; 
            border-radius: 8px; 
            margin: 20px 0; 
            border-left: 5px solid #007cba; 
        }
        .product-details { 
            display: grid; 
            grid-template-columns: 1fr 1fr; 
            gap: 15px; 
            margin-top: 15px; 
        }
        .detail-item { 
            display: flex; 
            justify-content: space-between; 
        }
        .detail-label { 
            font-weight: bold; 
            color: #495057; 
        }
        .detail-value { 
            color: #212529; 
        }
        .price { 
            font-weight: bold; 
            color: #007cba; 
        }
        .bids-section { 
            margin-top: 30px; 
        }
        .bids-table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        .bids-table th, .bids-table td { 
            padding: 12px; 
            text-align: left; 
            border-bottom: 1px solid #ddd; 
        }
        .bids-table th { 
            background-color: #007cba; 
            color: white; 
            font-weight: bold; 
        }
        .bids-table tr:hover { 
            background-color: #f5f5f5; 
        }
        .highest-bid { 
            background-color: #d4edda !important; 
            font-weight: bold; 
        }
        .bid-amount { 
            font-weight: bold; 
            color: #28a745; 
        }
        .no-bids { 
            text-align: center; 
            padding: 40px; 
            color: #666; 
            background-color: #f8f9fa; 
            border-radius: 8px; 
            margin: 20px 0; 
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
                <h1>Item Bids</h1>
                <s:if test="#session != null && #session['displayName'] != null">
                    <div class="user-status">Logged in as: <s:property value="#session['displayName']"/></div>
                </s:if>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="viewItems.action">All Items</a>
                <a href="myBids.action">My Bids</a>
                <a href="logout.action">Logout</a>
            </div>
        </div>
        
        <!-- Display action errors -->
        <s:if test="hasActionErrors()">
            <div class="error">
                <s:actionerror/>
            </div>
        </s:if>
        
        <s:if test="selectedProduct != null">
            <!-- Product Information -->
            <div class="product-info">
                <h2><s:property value="selectedProduct.productName"/></h2>
                <div class="product-details">
                    <div class="detail-item">
                        <span class="detail-label">Product ID:</span>
                        <span class="detail-value"><s:property value="selectedProduct.productId"/></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Category:</span>
                        <span class="detail-value"><s:property value="selectedProduct.productCategory" default="General"/></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Minimum Bid:</span>
                        <span class="detail-value price">$<s:property value="selectedProduct.minimumBid"/></span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Current Status:</span>
                        <span class="detail-value"><s:property value="selectedProduct.listingStatus" default="Available"/></span>
                    </div>
                </div>
                <s:if test="selectedProduct.productDetails != null && !selectedProduct.productDetails.trim().isEmpty()">
                    <div style="margin-top: 15px;">
                        <span class="detail-label">Description:</span><br/>
                        <span class="detail-value"><s:property value="selectedProduct.productDetails"/></span>
                    </div>
                </s:if>
            </div>
            
            <!-- Bids Section -->
            <div class="bids-section">
                <h2>All Bids for this Item</h2>
                
                <s:if test="itemBids != null && !itemBids.isEmpty()">
                    <table class="bids-table">
                        <thead>
                            <tr>
                                <th>Rank</th>
                                <th>Bidder</th>
                                <th>Display Name</th>
                                <th>Bid Amount</th>
                                <th>Bid Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="itemBids" var="bid" status="stat">
                                <tr <s:if test="#stat.index == 0">class="highest-bid"</s:if>>
                                    <td>
                                        <s:if test="#stat.index == 0">
                                            🏆 #<s:property value="#stat.index + 1"/>
                                        </s:if>
                                        <s:else>
                                            #<s:property value="#stat.index + 1"/>
                                        </s:else>
                                    </td>
                                    <td><s:property value="#bid.bidderName"/></td>
                                    <td><s:property value="#bid.bidderDisplayName" default="N/A"/></td>
                                    <td class="bid-amount">$<s:property value="#bid.bidAmount"/></td>
                                    <td><s:property value="#bid.bidTimestamp"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                    
                    <div style="margin-top: 15px; padding: 15px; background-color: #e9ecef; border-radius: 4px;">
                        <strong>Bidding Statistics:</strong><br/>
                        Total Bids: <s:property value="itemBids.size()"/><br/>
                        <s:if test="itemBids.size() > 0">
                            Highest Bid: $<s:property value="itemBids.get(0).bidAmount"/> by <s:property value="itemBids.get(0).bidderDisplayName"/>
                        </s:if>
                    </div>
                </s:if>
                <s:else>
                    <div class="no-bids">
                        <h3>No Bids Yet</h3>
                        <p>This item has not received any bids.</p>
                        <p>Be the first to place a bid!</p>
                    </div>
                </s:else>
            </div>
        </s:if>
        <s:else>
            <div class="error">
                <p>Product information could not be loaded.</p>
            </div>
        </s:else>
        
        <a href="viewItems.action" class="back-link">← Back to All Items</a>
        
        <div style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>