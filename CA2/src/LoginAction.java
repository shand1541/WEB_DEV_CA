import java.util.Map;

// Login action for CA2
public class LoginAction {
    
    private String username;
    private String password;
    private MemberDAO memberDAO = new MemberDAO();
    private Map<String, Object> session;
    
    public String execute() {
        // Check if fields are filled in
        if (username == null || password == null) {
            return "input"; // go back to login 
        }
        
        // Try to login 
        Member member = memberDAO.loginMember(username, password);
        if (member != null) {
            // Login successful 
            session.put("user", member);
            return "success"; // go to dashboard
        } else {
            // Login failed
            return "error"; //  error message
        }
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public void setSession(Map<String, Object> session) { this.session = session; }
}