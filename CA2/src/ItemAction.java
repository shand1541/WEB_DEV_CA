import java.util.Map;
import java.util.List;
import java.math.BigDecimal;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class ItemAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map session;
    private ProductDAO productDAO = new ProductDAO();
    
    // Item form fields
    private String itemName;
    private String itemDescription;
    private String category;
    private BigDecimal startingBid;
    
    // For display
    private List<Product> allItems;
    
    // Add new item for sale
    public String addItem() {
        try {
            Integer userId = (Integer) session.get("userId");
            if (userId == null) return LOGIN;
            
            if (itemName == null || itemName.trim().isEmpty()) return INPUT;
            if (startingBid == null || startingBid.compareTo(BigDecimal.ZERO) <= 0) return INPUT;
            
            boolean success = productDAO.addProduct(
                itemName.trim(),
                itemDescription != null ? itemDescription.trim() : "",
                category != null ? category.trim() : "General",
                startingBid,
                userId
            );
            
            if (success) {
                return SUCCESS;
            } else {
                return ERROR;
            }
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    // View all items for sale
    public String viewAllItems() {
        try {
            Integer userId = (Integer) session.get("userId");
            if (userId == null) return LOGIN;
            
            allItems = productDAO.getAllProducts();
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    // Show add item form
    public String showAddForm() {
        Integer userId = (Integer) session.get("userId");
        if (userId == null) return LOGIN;
        return INPUT;
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
    
    public List<Product> getAllItems() { return allItems; }
    public void setAllItems(List<Product> allItems) { this.allItems = allItems; }
    
    @Override
    public void setSession(Map session) {
        this.session = session;
    }
}