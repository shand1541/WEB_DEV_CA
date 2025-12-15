import java.util.Map;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class ProfileAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> session;
    private MemberDAO memberDAO = new MemberDAO();
    private Member currentUser;
    private Member profileUser;
    private List<Member> allUsers;
    private int userId;
    
    // View my profile
    public String myProfile() {
        try {
            Integer sessionUserId = (Integer) session.get("userId");
            if (sessionUserId == null) return "login";
            
            currentUser = memberDAO.getMemberById(sessionUserId);
            if (currentUser == null) return "error";
            
            return "success";
            
        } catch (Exception e) {
            return "error";
        }
    }
    
    // View other user's profile
    public String viewProfile() {
        try {
            Integer sessionUserId = (Integer) session.get("userId");
            if (sessionUserId == null) return "login";
            
            profileUser = memberDAO.getMemberById(userId);
            if (profileUser == null) return "error";
            
            return "success";
            
        } catch (Exception e) {
            return "error";
        }
    }
    
    // View all users
    public String allUsers() {
        try {
            Integer sessionUserId = (Integer) session.get("userId");
            if (sessionUserId == null) return "login";
            
            allUsers = memberDAO.getAllMembers();
            return "success";
            
        } catch (Exception e) {
            return "error";
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
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}