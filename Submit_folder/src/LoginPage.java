import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginPage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String gamerTag = request.getParameter("gamerTag");
            String password = request.getParameter("password");

            try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
             
            "SELECT credits FROM users WHERE gamerTag=? AND password=?")) {
            
                ps.setString(1, gamerTag);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            out.println("<html><body>");

            if (rs.next()) {
                int credits = rs.getInt("credits");

                out.println("<h3>Welcome " + gamerTag + "!</h3>");
                out.println("<p>Your current game credits: " + credits + "</p>");

                out.println("<form action='" + request.getContextPath() + "/credits' method='post'>");// 404 issuefix???
                out.println("Add/Spend Credits: <input type='number' name='change' />");// changes number of credits 
                out.println("<input type='hidden' name='gamerTag' value='" + gamerTag + "' />");
                out.println("<input type='submit' value='Update Credits' />");
                out.println("</form>");
            } else {
                out.println("<h3>Invalid login. Please try again.</h3>");
                out.println("<a href='" + request.getContextPath() + "/login.html'>Go Back</a>");//more specific url 
            }

            out.println("</body></html>");
            
        } catch (SQLException e) {
                e.printStackTrace();
                out.println("<html><body>");
                out.println("<h3>Error occurred. Please try again later.</h3>");
                out.println("<a href='" + request.getContextPath() + "/login.html'>Go Back</a>");
                out.println("</body></html>");
}

    }
}   