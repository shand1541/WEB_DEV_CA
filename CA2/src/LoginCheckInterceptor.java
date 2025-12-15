import java.util.Map;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("rawtypes")
public class LoginCheckInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // Get the session map
        Map session = invocation.getInvocationContext().getSession();
        
        // Check if user is logged in
        if (session == null || session.get("user") == null) {
            // User is not logged in, redirect to login page
            return "login";
        }
        
        // User is logged in, proceed with the action
        return invocation.invoke();
    }
}