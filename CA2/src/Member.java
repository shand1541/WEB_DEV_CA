import java.sql.Timestamp;

// Member class 
public class Member {
    
    // User info
    private int memberId;        // unique ID 
    private String loginName;    // username 
    private String emailAddress; // user email
    private String passwordHash; // password
    private String displayName;  // display name
    
    // phone number
    private String contactNumber;
    
    //address for shipping items
    private String postalAddress;
    
    // registration date
    private Timestamp registrationDate;
    
    // Account status: 
    private String accountStatus;
    
    // Default constructor
    public Member() {
        // Set default account status 
        this.accountStatus = "active";
    }
    // Parameterized constructor
    public Member(String loginName, String emailAddress, String passwordHash, 
                 String displayName, String contactNumber, String postalAddress) {
        this.loginName = loginName;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.displayName = displayName;
        this.contactNumber = contactNumber;
        this.postalAddress = postalAddress;
        this.accountStatus = "active";
    }
    
    // Getter and Setter methods
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }
    
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getPostalAddress() { return postalAddress; }
    public void setPostalAddress(String postalAddress) { this.postalAddress = postalAddress; }
    
    public Timestamp getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Timestamp registrationDate) { this.registrationDate = registrationDate; }
    
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
    
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", loginName='" + loginName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
