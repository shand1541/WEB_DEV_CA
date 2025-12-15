import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class AdminAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private List<Member> allUsers;
    private List<Product> allProducts;
    private Map session;
    private String adminMessage;
    
    public String execute() {
        try {
            // Check if user is logged in
            if (session == null || session.get("user") == null) {
                return "login";
            }
            
            Member currentUser = (Member) session.get("user");
            
            // Basic admin check (you can implement proper role-based security)
            if (!"admin".equalsIgnoreCase(currentUser.getLoginName()) && 
                !"administrator".equalsIgnoreCase(currentUser.getLoginName())) {
                addActionError("You do not have administrative privileges");
                return "unauthorized";
            }
            
            // Load admin data
            MemberDAO memberDAO = new MemberDAO();
            ProductDAO productDAO = new ProductDAO();
            
            allUsers = memberDAO.getAllMembers();
            allProducts = productDAO.getAllProducts();
            
            adminMessage = "Welcome to the Admin Panel, " + currentUser.getDisplayName() + "!";
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Admin panel error: " + e.getMessage());
            return ERROR;
        }
    }
    
    // Getters and Setters
    public List<Member> getAllUsers() { return allUsers; }
    public void setAllUsers(List<Member> allUsers) { this.allUsers = allUsers; }
    
    public List<Product> getAllProducts() { return allProducts; }
    public void setAllProducts(List<Product> allProducts) { this.allProducts = allProducts; }
    
    public String getAdminMessage() { return adminMessage; }
    public void setAdminMessage(String adminMessage) { this.adminMessage = adminMessage; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}