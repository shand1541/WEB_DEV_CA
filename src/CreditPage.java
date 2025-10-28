import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CreditPage extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //gets gamerTag and changes credits 
        String gamerTag = request.getParameter("gamerTag");
        int change = Integer.parseInt(request.getParameter("change")); //changes the number of credits from login 
            //changes string to int to get credit number (incase of a - number)
        try {
            Connection conn = DBConnection.getConnection();

            // Get current credits
            PreparedStatement ps = conn.prepareStatement(
                "SELECT credits FROM users WHERE gamerTag=?");
            ps.setString(1, gamerTag);
            ResultSet rs = ps.executeQuery();

            out.println("<html><body>");
            if (rs.next()) { //check if user exists 
                int current = rs.getInt("credits");
                int newCredits = current + change;//update the balance 
            //make sure credits isnt 0
                if (newCredits < 0) {
                    out.println("<h3>Insufficient credits! Current balance: " + current + "</h3>");
                } else {
                    //updates new balance 
                    PreparedStatement update = conn.prepareStatement(
                        "UPDATE users SET credits=? WHERE gamerTag=?");
                    update.setInt(1, newCredits);
                    update.setString(2, gamerTag);
                    update.executeUpdate();
                    update.close();
                    //display message 
                    out.println("<h3>Credits updated!</h3>");
                    out.println("<p>" + gamerTag + ", your new balance: " + newCredits + "</p>");
                }
            } else {//no user found
                out.println("<h3>User not found.</h3>");
            }//back to login 
            out.println("<a href='login.html'>Back</a>");
            out.println("</body></html>");
            //close all 
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
}
