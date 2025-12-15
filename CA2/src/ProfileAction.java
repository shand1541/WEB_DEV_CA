import java.util.Map;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class ProfileAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    public static final String LOGIN = "login";

    private Map session;
    private MemberDAO memberDAO = new MemberDAO();
    private Member currentUser;
    private Member profileUser;
    private List<Member> allUsers;
    private int userId;
    
    // Helper methods for safe casting
    private Integer getSessionUserId() {
        Object userId = session.get("userId");
        return userId instanceof Integer ? (Integer) userId : null;
    }
    
    // View my profile
    public String myProfile() {
        try {
            Integer sessionUserId = getSessionUserId();
            if (sessionUserId == null) return LOGIN;
            
            currentUser = memberDAO.getMemberById(sessionUserId);
            if (currentUser == null) return ERROR;
            
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    // View other user's profile
    public String viewProfile() {
        try {
            Integer sessionUserId = getSessionUserId();
            if (sessionUserId == null) return LOGIN;
            
            profileUser = memberDAO.getMemberById(userId);
            if (profileUser == null) return ERROR;
            
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    // View all users
    public String allUsers() {
        try {
            Integer sessionUserId = getSessionUserId();
            if (sessionUserId == null) return LOGIN;
            
            allUsers = memberDAO.getAllMembers();
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    // Getters and Setters
    public Member getCurrentUser() { return currentUser; }
    public void setCurrentUser(Member currentUser) { this.currentUser = currentUser; }
    
    public Member getProfileUser() { return profileUser; }
    public void setProfileUser(Member profileUser) { this.profileUser = profileUser; }
    
    public List<Member> getAllUsers() { return allUsers; }
    public void setAllUsers(List<Member> allUsers) { this.allUsers = allUsers; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    @Override
    public void setSession(Map session) {
        this.session = session;
    }
}