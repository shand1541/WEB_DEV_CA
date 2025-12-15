import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ViewUsersServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>All Users - CA2</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
        out.println("table { border-collapse: collapse; width: 100%; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".nav-links a { margin-right: 15px; text-decoration: none; color: #007cba; }");
        out.println("</style></head>");
        out.println("<body>");
        
        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard.jsp'>&larr; Back to Dashboard</a>");
        out.println("</div>");
        
        out.println("<h2>All Registered Users</h2>");
        
        try {
            MemberDAO memberDAO = new MemberDAO();
            List<Member> members = memberDAO.getAllMembers();
            
            if (members != null && !members.isEmpty()) {
                out.println("<table>");
                out.println("<tr><th>Username</th><th>Display Name</th><th>Email</th><th>Registration Date</th></tr>");
                
                for (Member member : members) {
                    out.println("<tr>");
                    out.println("<td>" + member.getLoginName() + "</td>");
                    out.println("<td>" + member.getDisplayName() + "</td>");
                    out.println("<td>" + member.getEmailAddress() + "</td>");
                    out.println("<td>Recent</td>");  // Placeholder for registration date
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>No users found.</p>");
            }
            
        } catch (Exception e) {
            out.println("<p style='color: red;'>Error loading users: " + e.getMessage() + "</p>");
        }
        
        out.println("<p><a href='dashboard.jsp'>Back to Dashboard</a></p>");
        out.println("</body></html>");
    }
}