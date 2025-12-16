import com.opensymphony.xwork2.ActionSupport;

public class DashboardAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    
    public String execute() {
        // Simple dashboard access
        return SUCCESS;
    }
}