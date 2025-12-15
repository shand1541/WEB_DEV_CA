import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Simple validation for demo
        if ("admin".equals(username) && "password".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            out.println("<html><head><title>Login Successful</title></head>");
            out.println("<body>");
            out.println("<h2>Welcome " + username + "!</h2>");
            out.println("<p>Login successful. Redirecting to dashboard...</p>");
            out.println("<p><a href='dashboard.jsp'>Go to Dashboard</a></p>");
            out.println("</body></html>");
        } else {
            out.println("<html><head><title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<h2>Login Failed</h2>");
            out.println("<p>Invalid username or password.</p>");
            out.println("<p>Try: Username = 'admin', Password = 'password'</p>");
            out.println("<p><a href='login.jsp'>Try Again</a></p>");
            out.println("</body></html>");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}