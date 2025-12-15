import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

@SuppressWarnings("rawtypes")
public class LogoutAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    
    private Map session;
    
    public String execute() {
        try {
            // Clear user session data
            if (session != null) {
                session.remove("user");
                session.remove("userId");
                session.remove("displayName");
                session.clear();
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
    
    @Override
    public void setSession(Map session) {
        this.session = session;
    }
}