import java.util.Map;
import java.math.BigDecimal;
import java.sql.ResultSet;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class ItemAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> session;
    private ProductDAO productDAO = new ProductDAO();
    
    // Item form fields
    private String itemName;
    private String itemDescription;
    private String category;
    private BigDecimal startingBid;
    
    // For display
    private ResultSet allItems;
    
    // Add new item for sale
    public String addItem() {
        try {
            Integer userId = (Integer) session.get("userId");
            if (userId == null) return "login";
            
            if (itemName == null || itemName.trim().isEmpty()) return "input";
            if (startingBid == null || startingBid.compareTo(BigDecimal.ZERO) <= 0) return "input";
            
            boolean success = productDAO.addProduct(
                itemName.trim(),
                itemDescription != null ? itemDescription.trim() : "",
                category != null ? category.trim() : "General",
                startingBid,
                userId
            );
            
            if (success) {
                return "success";
            } else {
                return "error";
            }
            
        } catch (Exception e) {
            return "error";
        }
    }
    
    // View all items for sale
    public String viewAllItems() {
        try {
            Integer userId = (Integer) session.get("userId");
            if (userId == null) return "login";
            
            allItems = productDAO.getAllProducts();
            return "success";
            
        } catch (Exception e) {
            return "error";
        }
    }
    
    // Show add item form
    public String showAddForm() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return "login";
        return "input";
    }
    
    // Getters and Setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public String getItemDescription() { return itemDescription; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public BigDecimal getStartingBid() { return startingBid; }
    public void setStartingBid(BigDecimal startingBid) { this.startingBid = startingBid; }
    
    public ResultSet getAllItems() { return allItems; }
    public void setAllItems(ResultSet allItems) { this.allItems = allItems; }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}