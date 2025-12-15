import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class ViewItemsAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private List<Product> products;
    private Map session;
    
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
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}