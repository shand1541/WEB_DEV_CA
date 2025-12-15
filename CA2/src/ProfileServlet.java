import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?message=Please login first");
            return;
        }
        
        Member user = (Member) session.getAttribute("user");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>My Profile - CA2</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f8f9fa; }");
        out.println(".profile-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        out.println(".profile-field { margin-bottom: 20px; }");
        out.println(".profile-field label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }");
        out.println(".profile-field .value { background-color: #f8f9fa; padding: 10px; border-radius: 4px; border: 1px solid #ddd; }");
        out.println("button { background-color: #007cba; color: white; padding: 12px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin-right: 10px; }");
        out.println("button:hover { background-color: #005a8b; }");
        out.println(".nav-links a { margin-right: 15px; text-decoration: none; color: #007cba; }");
        out.println("</style></head>");
        out.println("<body>");
        
        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard.jsp'>&larr; Back to Dashboard</a>");
        out.println("</div>");
        
        out.println("<div class='profile-container'>");
        out.println("<h2>My Profile</h2>");
        
        out.println("<div class='profile-field'>");
        out.println("<label>Username:</label>");
        out.println("<div class='value'>" + user.getLoginName() + "</div>");
        out.println("</div>");
        
        out.println("<div class='profile-field'>");
        out.println("<label>Display Name:</label>");
        out.println("<div class='value'>" + user.getDisplayName() + "</div>");
        out.println("</div>");
        
        out.println("<div class='profile-field'>");
        out.println("<label>Email Address:</label>");
        out.println("<div class='value'>" + user.getEmailAddress() + "</div>");
        out.println("</div>");
        
        out.println("<div class='profile-field'>");
        out.println("<label>Membership Status:</label>");
        out.println("<div class='value'>Active Member</div>");
        out.println("</div>");
        
        out.println("<br>");
        out.println("<button onclick=\"location.href='editProfile'\">Edit Profile</button>");
        out.println("<button onclick=\"location.href='viewMyItems'\">My Items</button>");
        out.println("<button onclick=\"location.href='myBids'\">My Bids</button>");
        out.println("</div>");
        
        out.println("</body></html>");
    }
}