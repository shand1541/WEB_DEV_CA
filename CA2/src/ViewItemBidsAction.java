import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class ViewItemBidsAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Product selectedProduct;
    private List<ItemBidInfo> itemBids;
    private String productId;
    private Map session;
    
    public String execute() {
        try {
            // Validate productId parameter
            if (productId == null || productId.trim().isEmpty()) {
                addActionError("Product ID is required to view bids");
                return ERROR;
            }
            
            // Get the product details
            ProductDAO productDAO = new ProductDAO();
            int productIdInt = Integer.parseInt(productId.trim());
            selectedProduct = productDAO.getProductById(productIdInt);
            
            if (selectedProduct == null) {
                addActionError("Product not found");
                return ERROR;
            }
            
            // Load all bids for this item
            loadItemBids(productIdInt);
            
            return SUCCESS;
            
        } catch (NumberFormatException e) {
            addActionError("Invalid product ID format");
            return ERROR;
        } catch (Exception e) {
            addActionError("Error loading item bids: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    private void loadItemBids(int productId) {
        itemBids = new ArrayList<ItemBidInfo>();
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT m.login_name, m.display_name, b.bid_value, b.bid_timestamp " +
                        "FROM bidding_history b " +
                        "JOIN members m ON b.member_id = m.member_id " +
                        "WHERE b.product_id = ? " +
                        "ORDER BY b.bid_value DESC, b.bid_timestamp DESC";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemBidInfo bid = new ItemBidInfo();
                bid.setBidderName(rs.getString("login_name"));
                bid.setBidderDisplayName(rs.getString("display_name"));
                bid.setBidAmount(rs.getDouble("bid_value"));
                bid.setBidTimestamp(rs.getTimestamp("bid_timestamp"));
                itemBids.add(bid);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            addActionError("Error loading bids: " + e.getMessage());
        }
    }
    
    // Inner class for bid information
    public static class ItemBidInfo {
        private String bidderName;
        private String bidderDisplayName;
        private double bidAmount;
        private java.util.Date bidTimestamp;
        
        // Getters and Setters
        public String getBidderName() { return bidderName; }
        public void setBidderName(String bidderName) { this.bidderName = bidderName; }
        
        public String getBidderDisplayName() { return bidderDisplayName; }
        public void setBidderDisplayName(String bidderDisplayName) { this.bidderDisplayName = bidderDisplayName; }
        
        public double getBidAmount() { return bidAmount; }
        public void setBidAmount(double bidAmount) { this.bidAmount = bidAmount; }
        
        public java.util.Date getBidTimestamp() { return bidTimestamp; }
        public void setBidTimestamp(java.util.Date bidTimestamp) { this.bidTimestamp = bidTimestamp; }
    }
    
    // Getters and Setters
    public Product getSelectedProduct() { return selectedProduct; }
    public void setSelectedProduct(Product selectedProduct) { this.selectedProduct = selectedProduct; }
    
    public List<ItemBidInfo> getItemBids() { return itemBids; }
    public void setItemBids(List<ItemBidInfo> itemBids) { this.itemBids = itemBids; }
    
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}