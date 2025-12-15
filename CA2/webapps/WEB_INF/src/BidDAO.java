import java.sql.*;
import java.math.BigDecimal;

// Simple class to handle bidding operations
public class BidDAO {
    
    // Place a bid on an item
    public boolean placeBid(int productId, int memberId, BigDecimal bidAmount) {
        try (Connection conn = DatabaseManager.getConnection()) {
            
            // Insert the bid
            String sql = "INSERT INTO bidding_history (product_id, member_id, bid_value) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.setInt(2, memberId);
            stmt.setBigDecimal(3, bidAmount);
            stmt.executeUpdate();
            
            // Update highest bid on product
            String updateSql = "UPDATE products SET highest_bid = ? WHERE product_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setBigDecimal(1, bidAmount);
            updateStmt.setInt(2, productId);
            updateStmt.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error placing bid: " + e.getMessage());
            return false;
        }
    }
    
    // Get my bids
    public ResultSet getMyBids(int memberId) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT bh.*, p.product_name FROM bidding_history bh JOIN products p ON bh.product_id = p.product_id WHERE bh.member_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting my bids: " + e.getMessage());
            return null;
        }
    }
    
    // Get all bids for a product
    public ResultSet getBidsForProduct(int productId) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT bh.*, m.display_name FROM bidding_history bh JOIN members m ON bh.member_id = m.member_id WHERE bh.product_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting bids: " + e.getMessage());
            return null;
        }
    }
}
