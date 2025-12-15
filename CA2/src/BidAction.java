import java.util.Map;
import java.math.BigDecimal;
import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class BidAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> session;
    
    // Bid form fields
    private int productId;
    private BigDecimal bidAmount;
    
    // For display
    private ResultSet myBids;
    private ResultSet productBids;
    
    // Place a bid on an item
    public String placeBid() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return "login";
        
        try (Connection conn = DatabaseManager.getConnection()) {
            // Insert the bid
            String sql = "INSERT INTO bidding_history (product_id, member_id, bid_value) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, userId);
            stmt.setBigDecimal(3, bidAmount);
            stmt.executeUpdate();
            
            // Update highest bid on product
            String updateSql = "UPDATE products SET highest_bid = ? WHERE product_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setBigDecimal(1, bidAmount);
            updateStmt.setInt(2, productId);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error placing bid: " + e.getMessage());
        }
        
        return "success";
    }
    
    // View my bids
    public String myBids() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return "login";
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT bh.*, p.product_name FROM bidding_history bh JOIN products p ON bh.product_id = p.product_id WHERE bh.member_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            myBids = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting my bids: " + e.getMessage());
        }
        
        return "success";
    }
    
    // View all bids on a specific item
    public String viewItemBids() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return "login";
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT bh.*, m.display_name FROM bidding_history bh JOIN members m ON bh.member_id = m.member_id WHERE bh.product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            productBids = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting bids: " + e.getMessage());
        }
        
        return "success";
    }
    
    // Show bid form for a specific product
    public String showBidForm() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return "login";
        return "input";
    }
    
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public BigDecimal getBidAmount() { return bidAmount; }
    public void setBidAmount(BigDecimal bidAmount) { this.bidAmount = bidAmount; }
    
    public ResultSet getMyBids() { return myBids; }
    public void setMyBids(ResultSet myBids) { this.myBids = myBids; }
    
    public ResultSet getProductBids() { return productBids; }
    public void setProductBids(ResultSet productBids) { this.productBids = productBids; }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}