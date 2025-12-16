import com.opensymphony.xwork2.ActionSupport;

public class ProfileAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    private Member userProfile;
    private String displayName;
    private String emailAddress;
    private String contactNumber;
    private String postalAddress;
    
    public String execute() {
        try {
            // Simple profile access - create empty profile
            userProfile = new Member();
            userProfile.setDisplayName("User");
            userProfile.setEmailAddress("user@example.com");
            userProfile.setContactNumber("000-000-0000");
            userProfile.setPostalAddress("Address not set");
            
            // Populate form fields
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
            // Create user profile
            userProfile = new Member();
            
            // Update user information
            userProfile.setDisplayName(displayName != null ? displayName.trim() : "User");
            userProfile.setEmailAddress(emailAddress != null ? emailAddress.trim() : "user@example.com");
            userProfile.setContactNumber(contactNumber != null ? contactNumber.trim() : "000-000-0000");
            userProfile.setPostalAddress(postalAddress != null ? postalAddress.trim() : "Address not set");
            
            // Simulate successful update
            addActionMessage("Profile updated successfully!");
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
}