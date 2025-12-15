import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class ViewUsersAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private List<Member> users;
    private Map session;
    
    public String execute() {
        try {
            // Check if user is logged in and has admin privileges
            if (session == null || session.get("user") == null) {
                addActionError("You must be logged in to view users");
                return ERROR;
            }
            
            // Load all users from database
            MemberDAO memberDAO = new MemberDAO();
            users = memberDAO.getAllMembers();
            
            if (users == null || users.isEmpty()) {
                addActionMessage("No users found in the system.");
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Error loading users: " + e.getMessage());
            e.printStackTrace();
            return ERROR;
        }
    }
    
    // Getters and Setters
    public List<Member> getUsers() { return users; }
    public void setUsers(List<Member> users) { this.users = users; }
    
    @Override
    public void setSession(Map session) { 
        this.session = session; 
    }
}