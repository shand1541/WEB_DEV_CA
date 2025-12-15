<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Bids - CA2 E-commerce</title>
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
        .bid-section { 
            display: flex; 
            gap: 20px; 
            margin-bottom: 30px; 
        }
        .available-items, .my-bids { 
            flex: 1; 
            background-color: #f9f9f9; 
            padding: 15px; 
            border-radius: 5px; 
        }
        .item-list, .bid-list { 
            max-height: 400px; 
            overflow-y: auto; 
        }
        .item, .bid { 
            background-color: white; 
            margin: 10px 0; 
            padding: 15px; 
            border-radius: 5px; 
            border-left: 4px solid #007cba; 
        }
        .item h4, .bid h4 { 
            margin: 0 0 10px 0; 
            color: #333; 
        }
        .item-details, .bid-details { 
            font-size: 14px; 
            color: #666; 
            margin: 5px 0; 
        }
        .bid-form { 
            margin-top: 10px; 
        }
        .bid-form input[type="number"] { 
            padding: 5px; 
            border: 1px solid #ddd; 
            border-radius: 3px; 
            width: 100px; 
        }
        .bid-form input[type="submit"] { 
            background-color: #28a745; 
            color: white; 
            border: none; 
            padding: 6px 12px; 
            border-radius: 3px; 
            cursor: pointer; 
            margin-left: 5px; 
        }
        .bid-form input[type="submit"]:hover { background-color: #218838; }
        .status-active { color: #28a745; font-weight: bold; }
        .status-below { color: #dc3545; font-weight: bold; }
        .price { 
            font-weight: bold; 
            color: #007cba; 
        }
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
                <h1>My Bids</h1>
                <s:if test="#session != null && #session['displayName'] != null">
                    <div style="color: #666; font-size: 14px; margin-top: 5px;">Logged in as: <s:property value="#session['displayName']"/></div>
                </s:if>
            </div>
            <div class="nav-links">
                <a href="dashboard.action">Dashboard</a>
                <a href="viewItems.action">All Items</a>
                <a href="addItemForm.action">Add Item</a>
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
        
        <div class="bid-section">
            <!-- Available Items for Bidding -->
            <div class="available-items">
                <h2>Available Items</h2>
                <div class="item-list">
                    <s:if test="availableProducts != null && !availableProducts.isEmpty()">
                        <s:iterator value="availableProducts" var="product">
                            <div class="item">
                                <h4><s:property value="#product.productName"/></h4>
                                <div class="item-details">
                                    <div><strong>Description:</strong> <s:property value="#product.description" default="No description"/></div>
                                    <div><strong>Category:</strong> <s:property value="#product.category" default="General"/></div>
                                    <div><strong>Minimum Bid:</strong> <span class="price">$<s:property value="#product.minimumBid"/></span></div>
                                    <div><strong>Seller:</strong> <s:property value="#product.sellerName" default="Unknown"/></div>
                                </div>
                                
                                <!-- Bid Form -->
                                <s:form action="placeBid" method="post" cssClass="bid-form">
                                    <s:hidden name="selectedProductId" value="%{#product.productId}"/>
                                    <label for="bidAmount_<s:property value='#product.productId'/>">Your Bid: $</label>
                                    <s:textfield name="bidAmount" 
                                               id="bidAmount_%{#product.productId}" 
                                               type="number" 
                                               step="0.01" 
                                               min="%{#product.minimumBid}" 
                                               placeholder="%{#product.minimumBid}"/>
                                    <s:submit value="Place Bid"/>
                                </s:form>
                            </div>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <div class="item">
                            <p>No items available for bidding at the moment.</p>
                        </div>
                    </s:else>
                </div>
            </div>
            
            <!-- My Current Bids -->
            <div class="my-bids">
                <h2>My Current Bids</h2>
                <div class="bid-list">
                    <s:if test="userBids != null && !userBids.isEmpty()">
                        <s:iterator value="userBids" var="bid">
                            <div class="bid">
                                <h4><s:property value="#bid.itemName"/></h4>
                                <div class="bid-details">
                                    <div><strong>My Bid:</strong> <span class="price">$<s:property value="#bid.bidAmount"/></span></div>
                                    <div><strong>Minimum Required:</strong> <span class="price">$<s:property value="#bid.currentHigh"/></span></div>
                                    <div><strong>Status:</strong> 
                                        <span class="<s:if test='#bid.status.equals(\"Active\")'>status-active</s:if><s:else>status-below</s:else>">
                                            <s:property value="#bid.status"/>
                                        </span>
                                    </div>
                                    <s:if test="#bid.timestamp != null">
                                        <div><strong>Bid Placed:</strong> <s:property value="#bid.timestamp"/></div>
                                    </s:if>
                                </div>
                            </div>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <div class="bid">
                            <p>You haven't placed any bids yet.</p>
                            <p>Browse the available items on the left to start bidding!</p>
                        </div>
                    </s:else>
                </div>
            </div>
        </div>
        
        <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #ddd; text-align: center; color: #666;">
            <p>&copy; 2024 CA2 E-commerce Platform. All rights reserved.</p>
        </div>
    </div>
</body>
</html>