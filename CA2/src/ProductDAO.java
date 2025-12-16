import java.sql.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

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
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE listing_status = 'active' ORDER BY listing_date DESC";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDetails(rs.getString("product_details"));
                product.setProductCategory(rs.getString("product_category"));
                product.setMinimumBid(rs.getBigDecimal("minimum_bid"));
                product.setHighestBid(rs.getBigDecimal("highest_bid"));
                product.setOwnerId(rs.getInt("owner_id"));
                product.setListingStatus(rs.getString("listing_status"));
                product.setListingDate(rs.getTimestamp("listing_date"));
                products.add(product);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting products: " + e.getMessage());
        }
        return products;
    }
    
    // Get product by ID
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductDetails(rs.getString("product_details"));
                product.setProductCategory(rs.getString("product_category"));
                product.setMinimumBid(rs.getBigDecimal("minimum_bid"));
                product.setHighestBid(rs.getBigDecimal("highest_bid"));
                product.setOwnerId(rs.getInt("owner_id"));
                product.setListingStatus(rs.getString("listing_status"));
                product.setListingDate(rs.getTimestamp("listing_date"));
                rs.close();
                return product;
            }
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error getting product by ID: " + e.getMessage());
        }
        return null;
    }
}
