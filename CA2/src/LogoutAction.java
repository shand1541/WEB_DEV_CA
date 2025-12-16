import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    public String execute() {
        try {
            // Logout successful 
            return SUCCESS;
            
        } catch (Exception e) {
            return ERROR;
        }
    }
}