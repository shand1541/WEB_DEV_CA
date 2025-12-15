import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.ArrayList;

public class MyBidsServlet extends HttpServlet {
    
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
        
        out.println("<html><head><title>My Bids - CA2</title>");
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
        
        out.println("<h2>My Bids</h2>");
        
        // For now, show a placeholder since we need to implement BidDAO
        out.println("<p>Welcome, " + user.getDisplayName() + "!</p>");
        out.println("<p><em>Your bidding history will appear here once you start bidding on items.</em></p>");
        
        out.println("<table>");
        out.println("<tr><th>Item</th><th>My Bid Amount</th><th>Current High Bid</th><th>Status</th><th>Bid Date</th></tr>");
        out.println("<tr><td colspan='5' style='text-align: center; color: #666;'>No bids placed yet</td></tr>");
        out.println("</table>");
        
        out.println("<p><a href='viewItems'>Browse Items to Bid On</a> | <a href='dashboard.jsp'>Back to Dashboard</a></p>");
        
        out.println("</body></html>");
    }
}