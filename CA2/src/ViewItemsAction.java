import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

public class ViewItemsAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    private List<Product> products;
    
    public String execute() {
        try {
            ProductDAO productDAO = new ProductDAO();
            products = productDAO.getAllProducts();
            
            if (products == null) {
                addActionError("Could not load products from database.");
                return ERROR;
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Database error: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    // Getters and Setters
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}