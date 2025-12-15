import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class MyBidsAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private List<Product> availableProducts;
    private List<BidInfo> userBids;
    private String selectedProductId;
    private String bidAmount;
    private Map session;
    
    public String execute() {
        try {
            // Load available products for bidding
            ProductDAO productDAO = new ProductDAO();
            availableProducts = productDAO.getAllProducts();
            
            // Load user's current bids
            loadUserBids();
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Database error: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String placeBid() {
        try {
            // Validate input
            if (selectedProductId == null || bidAmount == null || bidAmount.trim().isEmpty()) {
                addFieldError("bidAmount", "Please enter a valid bid amount");
                return execute(); // Reload page with error
            }
            
            // Check if user is logged in
            if (session == null || session.get("user") == null) {
                addActionError("You must be logged in to place bids");
                return execute();
            }
            
            Member user = (Member) session.get("user");
            int userId = user.getMemberId();
            int productId = Integer.parseInt(selectedProductId);
            BigDecimal bid = new BigDecimal(bidAmount);
            
            // Validate bid amount against minimum
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                addActionError("Selected product not found");
                return execute();
            }
            
            if (bid.compareTo(product.getMinimumBid()) < 0) {
                addFieldError("bidAmount", "Bid must be at least $" + product.getMinimumBid());
                return execute();
            }
            
            // Place the bid
            if (placeBidInDatabase(userId, productId, bid)) {
                addActionMessage("Bid of $" + bid + " placed successfully on " + product.getProductName() + "!");
            } else {
                addActionError("Failed to place bid. Please try again.");
            }
            
            return execute(); // Reload page with updated data
            
        } catch (NumberFormatException e) {
            addFieldError("bidAmount", "Please enter a valid numeric amount");
            return execute();
        } catch (Exception e) {
            addActionError("Error placing bid: " + e.getMessage());
            e.printStackTrace();
            return execute();
        }
    }
    
    private void loadUserBids() {
        userBids = new ArrayList<BidInfo>();
        
        String username = "Guest";
        if (session != null && session.get("user") != null) {
            Member user = (Member) session.get("user");
            username = user.getLoginName();
        }
        
        // Get user's bids from database
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT p.product_name, b.bid_value, p.minimum_bid, b.bid_timestamp " +
                        "FROM bidding_history b JOIN products p ON b.product_id = p.product_id " +
                        "JOIN members m ON b.member_id = m.member_id " +
                        "WHERE m.login_name = ? ORDER BY b.bid_timestamp DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                BidInfo bid = new BidInfo();
                bid.setItemName(rs.getString("product_name"));
                bid.setBidAmount(rs.getDouble("bid_value"));
                bid.setCurrentHigh(rs.getDouble("minimum_bid"));
                bid.setStatus(rs.getDouble("bid_value") >= rs.getDouble("minimum_bid") ? "Active" : "Below Min");
                bid.setTimestamp(rs.getTimestamp("bid_timestamp"));
                userBids.add(bid);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            addActionError("Error loading bids: " + e.getMessage());
        }
    }
    
    private boolean placeBidInDatabase(int userId, int productId, BigDecimal bidAmount) {
        try {
            Connection conn = DatabaseManager.getConnection();
            
            // Create bidding_history table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS bidding_history (" +
                                  "bid_id INT AUTO_INCREMENT PRIMARY KEY, " +
                                  "member_id INT NOT NULL, " +
                                  "product_id INT NOT NULL, " +
                                  "bid_value DECIMAL(10,2) NOT NULL, " +
                                  "bid_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                                  "FOREIGN KEY (member_id) REFERENCES members(member_id), " +
                                  "FOREIGN KEY (product_id) REFERENCES products(product_id))";
            
            Statement createStmt = conn.createStatement();
            createStmt.execute(createTableSQL);
            createStmt.close();
            
            // Insert the bid
            String sql = "INSERT INTO bidding_history (member_id, product_id, bid_value) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setBigDecimal(3, bidAmount);
            
            int result = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            return result > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Getters and Setters
    public List<Product> getAvailableProducts() { return availableProducts; }
    public void setAvailableProducts(List<Product> availableProducts) { this.availableProducts = availableProducts; }
    
    public List<BidInfo> getUserBids() { return userBids; }
    public void setUserBids(List<BidInfo> userBids) { this.userBids = userBids; }
    
    public String getSelectedProductId() { return selectedProductId; }
    public void setSelectedProductId(String selectedProductId) { this.selectedProductId = selectedProductId; }
    
    public String getBidAmount() { return bidAmount; }
    public void setBidAmount(String bidAmount) { this.bidAmount = bidAmount; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}