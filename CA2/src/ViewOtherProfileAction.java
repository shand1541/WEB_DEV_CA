import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class ViewOtherProfileAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Member viewedProfile;
    private String userId;
    private Map session;
    
    public String execute() {
        try {
            // Validate userId parameter
            if (userId == null || userId.trim().isEmpty()) {
                addActionError("User ID is required to view profile");
                return ERROR;
            }
            
            // Get the user profile by ID
            MemberDAO memberDAO = new MemberDAO();
            int userIdInt = Integer.parseInt(userId.trim());
            viewedProfile = memberDAO.getMemberById(userIdInt);
            
            if (viewedProfile == null) {
                addActionError("User not found");
                return ERROR;
            }
            
            return SUCCESS;
            
        } catch (NumberFormatException e) {
            addActionError("Invalid user ID format");
            return ERROR;
        } catch (Exception e) {
            addActionError("Error loading user profile: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    // Getters and Setters
    public Member getViewedProfile() { return viewedProfile; }
    public void setViewedProfile(Member viewedProfile) { this.viewedProfile = viewedProfile; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}