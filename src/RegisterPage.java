import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterPage extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String gamerTag = request.getParameter("gamerTag");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        //validation 
         if (gamerTag == null || gamerTag.isEmpty() || 
            password == null || password.isEmpty() || 
            confirm == null || confirm.isEmpty()) {
            
            out.println("<html><body>");
            out.println("<h3>All fields are required. Please fill in all details.</h3>");
            out.println("<a href='register.html'>Go Back</a>");
            out.println("</body></html>");
            return;
        }

        //check passwords match 
        if(!password.equals(confirm)) {
            out.println("<html><body>");
            out.println("<h3>Passwords do not match. Please try again.</h3>");
            out.println("<a href='register.html'>Go back to registration page</a>");
            out.println("</body></html>");
            return;
        }

        //insert into database 
        try {
            // Database connection
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (gamerTag, password, credits) VALUES (?, ?, ?)");
            ps.setString(1, gamerTag);
            ps.setString(2, password);
            ps.setInt(3, 500);//set credits to 500
            int result = ps.executeUpdate();

            out.println("<html><body>");
            if (result > 0) {
          
                out.println("<h3>Registration successful!</h3>");
                out.println("<p>Welcome " + gamerTag + "! You have been awarded 500 game credits.</p>");
                out.println("<a href='login.html'>Login Now</a>");
            } else {
                out.println("<h3>Registration failed. Please try again.</h3>");
            }
            out.println("</body></html>");
                ps.close();
                conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h3>Error occurred while registering. Please try again later.</h3>");


            out.println("<a href='register.html'>Go Back</a>");//go back to registration page
            out.println("</body></html>");
        }
    }

}
