// Login action for CA2
public class LoginAction {
    
    private String username;
    private String password;
    private MemberDAO memberDAO = new MemberDAO();
    private java.util.Map<String, Object> session = new java.util.HashMap<>();
    
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
    
    public void setSession(java.util.Map<String, Object> session) { this.session = session; }
    public java.util.Map<String, Object> getSession() { return session; }
}