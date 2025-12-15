import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LoginAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private MemberDAO memberDAO = new MemberDAO();
    private Map session;
    
    public String execute() {
        // Check if fields are filled in - first time loading login.jsp
        if (username == null || password == null || 
            username.trim().isEmpty() || password.trim().isEmpty()) {
            return INPUT; // go back to login form
        }
        
        try {
            // Try to login 
            Member member = memberDAO.loginMember(username.trim(), password);
            if (member != null) {
                // Login successful 
                session.put("user", member);
                session.put("userId", member.getMemberId());
                session.put("displayName", member.getDisplayName());
                return SUCCESS; 
            } else {
                // Login failed
                return ERROR; // show error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}