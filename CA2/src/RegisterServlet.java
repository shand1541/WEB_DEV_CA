import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String displayName = request.getParameter("displayName");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Registration Result</title></head>");
        out.println("<body>");
        out.println("<h2>Registration Successful!</h2>");
        out.println("<p><strong>Username:</strong> " + username + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("<p><strong>Display Name:</strong> " + (displayName != null ? displayName : username) + "</p>");
        out.println("<p>Your account has been created successfully.</p>");
        out.println("<p><a href='login.jsp'>Login Now</a> | <a href='index.jsp'>Home</a></p>");
        out.println("</body></html>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}