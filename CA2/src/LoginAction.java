import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private MemberDAO memberDAO;
    
    public LoginAction() {
        // Default constructor for Struts2
    }
    
    public String execute() {
        // Check if fields are filled in 
        if (username == null || password == null || 
            username.trim().isEmpty() || password.trim().isEmpty()) {
            return INPUT; // go back to login form
        }
        
        try {
           
            memberDAO = new MemberDAO();
            
            // Try to login 
            Member member = memberDAO.loginMember(username.trim(), password);
            if (member != null) {
                // Login successful
                System.out.println("Login successful for user: " + username);
                return SUCCESS; 
            } else {
                // Login failed
                addActionError("Invalid username or password. Please try again.");
                System.out.println("Login failed for user: " + username);
                return ERROR; // show error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Database connection error. Please try again later.");
            System.err.println("Login error for user " + username + ": " + e.getMessage());
            return ERROR;
        }
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}