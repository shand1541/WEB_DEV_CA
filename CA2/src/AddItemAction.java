import java.math.BigDecimal;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class AddItemAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String description;
    private String category;
    private String price;
    private Map session;
    
    public String execute() {
        
        if (title == null || title.trim().isEmpty()) {
            return INPUT; // Show add item form
        }
        
        try {
            // Validate input
            if (description == null) description = "";
            if (category == null) category = "General";
            
            BigDecimal minBid = new BigDecimal(price);
            if (minBid.compareTo(BigDecimal.ZERO) <= 0) {
                addFieldError("price", "Price must be greater than 0");
                return INPUT;
            }
            
            // Get user ID from session 
            int userId = 1;
            if (session != null && session.get("userId") != null) {
                userId = (Integer) session.get("userId");
            }
            
            // Add item to database
            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.addProduct(title.trim(), description.trim(), category.trim(), minBid, userId);
            
            if (success) {
                addActionMessage("Item '" + title + "' added successfully!");
                return SUCCESS;
            } else {
                addActionError("Failed to add item to database.");
                return ERROR;
            }
            
        } catch (NumberFormatException e) {
            addFieldError("price", "Invalid price format. Please enter a valid number.");
            return INPUT;
        } catch (Exception e) {
            addActionError("Database error: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}