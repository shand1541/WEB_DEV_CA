import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map<String, Object> session;
    
    public String execute() {
        try {
            // Clear user session data
            if (session != null) {
                session.remove("user");
                session.remove("userId");
                session.remove("displayName");
                session.clear();
            }
            
            return "success";
            
        } catch (Exception e) {
            return "error";
        }
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}