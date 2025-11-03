import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CreditPage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String gamerTag = request.getParameter("gamerTag");
        int change;

        // Validate credit change input
        try {
            change = Integer.parseInt(request.getParameter("change"));
        } catch (NumberFormatException e) { //makes sure its valid numbers 
            out.println("<html><body>");
            out.println("<h3>Invalid credit change amount. Please enter a valid number.</h3>");
            out.println("<a href='" + request.getContextPath() + "/login.html'>Back</a>");
            out.println("</body></html>");
            return;
        }

        // Try-with-resources for DB connection and statements
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT credits FROM users WHERE gamerTag=?")) {

            ps.setString(1, gamerTag);

            try (ResultSet rs = ps.executeQuery()) {

                out.println("<html><body>");

                if (rs.next()) {
                    int current = rs.getInt("credits");
                    int newCredits = current + change;

                    if (newCredits < 0) {
                        out.println("<h3>Insufficient credits! Current balance: " + current + "</h3>");
                    } else {
                        // Update credits
                        try (PreparedStatement update = conn.prepareStatement(
                                "UPDATE users SET credits=? WHERE gamerTag=?")) {
                            update.setInt(1, newCredits);
                            update.setString(2, gamerTag);
                            update.executeUpdate();
                        }

                        out.println("<h3>Credits updated!</h3>");
                        out.println("<p>" + gamerTag + ", your new balance: " + newCredits + "</p>");
                    }
                } else {
                    out.println("<h3>User not found.</h3>");
                }

                out.println("<a href='" + request.getContextPath() + "/login.html'>Back</a>");
                out.println("</body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h3>Database error occurred. Please try again later.</h3>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("<a href='" + request.getContextPath() + "/login.html'>Back to Login</a>");
            out.println("</body></html>");
        }
    }
}
