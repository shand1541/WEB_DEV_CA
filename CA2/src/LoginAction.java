import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

// Login action for CA2
public class LoginAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private MemberDAO memberDAO = new MemberDAO();
    private Map session;
    
    public String execute() {
        // Check if fields are filled in
        if (username == null || password == null || 
            username.trim().isEmpty() || password.trim().isEmpty()) {
            return "input"; // go back to login
        }
        
        try {
            // Try to login 
            Member member = memberDAO.loginMember(username.trim(), password);
            if (member != null) {
                // Login successful 
                session.put("user", member);
                return "success"; // go to dashboard
            } else {
                // Login failed
                return "error"; //  error message
            }
        } catch (Exception e) {
            return "error";
        }
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public void setSession(Map session) { this.session = session; }
}