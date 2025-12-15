import java.sql.*;
import java.math.BigDecimal;

//  products/items for sale
public class ProductDAO {
    
    // Add new item for sale
    public boolean addProduct(String name, String details, String category, BigDecimal startBid, int ownerId) {
        String sql = "INSERT INTO products (product_name, product_details, product_category, minimum_bid, owner_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, details);
            stmt.setString(3, category);
            stmt.setBigDecimal(4, startBid);
            stmt.setInt(5, ownerId);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            return false;
        }
    }
    
    // Get all items for sale
    public ResultSet getAllProducts() {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT * FROM products WHERE listing_status = 'active'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting products: " + e.getMessage());
            return null;
        }
    }
} 
