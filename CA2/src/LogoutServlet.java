import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Logout Successful</title></head>");
        out.println("<body>");
        out.println("<h2>Logout Successful</h2>");
        out.println("<p>You have been successfully logged out.</p>");
        out.println("<p><a href='index.jsp'>Return to Home</a></p>");
        out.println("<p><a href='login.jsp'>Login Again</a></p>");
        out.println("</body></html>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        doGet(request, response);
    }
}