import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ProfileAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Member userProfile;
    private String displayName;
    private String emailAddress;
    private String contactNumber;
    private String postalAddress;
    private Map session;
    
    public String execute() {
        try {
            // Check if user is logged in
            if (session == null || session.get("user") == null) {
                return "login";
            }
            
            userProfile = (Member) session.get("user");
            
            // Populate form fields with current user data
            displayName = userProfile.getDisplayName();
            emailAddress = userProfile.getEmailAddress();
            contactNumber = userProfile.getContactNumber();
            postalAddress = userProfile.getPostalAddress();
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Error loading profile: " + e.getMessage());
            return ERROR;
        }
    }
    
    public String updateProfile() {
        try {
            // Check if user is logged in
            if (session == null || session.get("user") == null) {
                return "login";
            }
            
            userProfile = (Member) session.get("user");
            
            // Update user information
            userProfile.setDisplayName(displayName != null ? displayName.trim() : userProfile.getDisplayName());
            userProfile.setEmailAddress(emailAddress != null ? emailAddress.trim() : userProfile.getEmailAddress());
            userProfile.setContactNumber(contactNumber != null ? contactNumber.trim() : userProfile.getContactNumber());
            userProfile.setPostalAddress(postalAddress != null ? postalAddress.trim() : userProfile.getPostalAddress());
            
            // Save to database
            MemberDAO memberDAO = new MemberDAO();
            if (memberDAO.updateMember(userProfile)) {
                // Update session with new data
                session.put("user", userProfile);
                session.put("displayName", userProfile.getDisplayName());
                
                addActionMessage("Profile updated successfully!");
            } else {
                addActionError("Failed to update profile. Please try again.");
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Error updating profile: " + e.getMessage());
            return ERROR;
        }
    }
    
    // Getters and Setters
    public Member getUserProfile() { return userProfile; }
    public void setUserProfile(Member userProfile) { this.userProfile = userProfile; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getPostalAddress() { return postalAddress; }
    public void setPostalAddress(String postalAddress) { this.postalAddress = postalAddress; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}