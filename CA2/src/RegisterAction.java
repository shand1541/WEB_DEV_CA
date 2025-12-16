import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String email;
    private String password;
    private String displayName;
    private String phone;
    private String address;
    
    private MemberDAO memberDAO;
    
    public RegisterAction() {
        // Default constructor required by Struts2
    }
    
    public String execute() {
        // Check if required fields are filled
        if (username == null || email == null || password == null || 
            username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            return INPUT; // go back to form
        }
        
        try {
            // Initialize DAO here instead of in constructor
            memberDAO = new MemberDAO();
            
            // Check if username already taken
            if (memberDAO.usernameExists(username.trim())) {
                addActionError("Username already exists. Please choose a different username.");
                return ERROR; // show error
            }
            
            // Create new user object
            Member member = new Member();
            member.setLoginName(username.trim());
            member.setEmailAddress(email.trim());
            member.setPasswordHash(password); 
            member.setDisplayName(displayName != null ? displayName.trim() : username.trim());
            member.setContactNumber(phone != null ? phone.trim() : null);
            member.setPostalAddress(address != null ? address.trim() : null);
            
            if (memberDAO.registerMember(member)) {
                System.out.println("Registration successful for user: " + username);
                return SUCCESS;
            } else {
                addActionError("Registration failed. Please try again.");
                System.err.println("Registration failed for user: " + username);
                return ERROR;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Database connection error. Please try again later.");
            System.err.println("Registration error for user " + username + ": " + e.getMessage());
            return ERROR;
        }
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}