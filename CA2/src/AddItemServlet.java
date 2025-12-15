import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddItemServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Add New Item - CA2</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f8f9fa; }");
        out.println(".form-container { max-width: 500px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        out.println("form { }");
        out.println("label { display: block; margin-bottom: 5px; color: #333; font-weight: bold; }");
        out.println("input[type='text'], input[type='number'], textarea, select { width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }");
        out.println("textarea { height: 100px; resize: vertical; }");
        out.println("button { background-color: #007cba; color: white; padding: 12px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("button:hover { background-color: #005a8b; }");
        out.println(".nav-links a { margin-right: 15px; text-decoration: none; color: #007cba; }");
        out.println("</style></head>");
        out.println("<body>");
        
        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard.jsp'>&larr; Back to Dashboard</a>");
        out.println("</div>");
        
        out.println("<div class='form-container'>");
        out.println("<h2>Add New Item for Sale</h2>");
        out.println("<form action='addItemProcess' method='post'>");
        
        out.println("<label for='title'>Item Title:</label>");
        out.println("<input type='text' id='title' name='title' required>");
        
        out.println("<label for='description'>Description:</label>");
        out.println("<textarea id='description' name='description' required></textarea>");
        
        out.println("<label for='price'>Price ($):</label>");
        out.println("<input type='number' id='price' name='price' step='0.01' required>");
        
        out.println("<label for='category'>Category:</label>");
        out.println("<select id='category' name='category' required>");
        out.println("<option value=''>Select Category</option>");
        out.println("<option value='Electronics'>Electronics</option>");
        out.println("<option value='Books'>Books</option>");
        out.println("<option value='Clothing'>Clothing</option>");
        out.println("<option value='Sports'>Sports</option>");
        out.println("<option value='Home'>Home & Garden</option>");
        out.println("<option value='Other'>Other</option>");
        out.println("</select>");
        
        out.println("<button type='submit'>Add Item</button>");
        out.println("</form>");
        out.println("</div>");
        
        out.println("</body></html>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?message=Please login first");
            return;
        }
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String category = request.getParameter("category");
        
        Member user = (Member) session.getAttribute("user");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            double price = Double.parseDouble(priceStr);
            java.math.BigDecimal minBid = new java.math.BigDecimal(price);
            
            ProductDAO productDAO = new ProductDAO();
            boolean success = productDAO.addProduct(title, description, category, minBid, user.getMemberId());
            
            if (success) {
                out.println("<html><head><title>Success - CA2</title></head>");
                out.println("<body style='font-family: Arial; margin: 20px;'>");
                out.println("<h2>Item Added Successfully!</h2>");
                out.println("<p>Your item '" + title + "' has been added to the marketplace.</p>");
                out.println("<p><a href='dashboard.jsp'>Back to Dashboard</a> | <a href='viewItems'>View All Items</a></p>");
                out.println("</body></html>");
            } else {
                throw new Exception("Failed to add item to database");
            }
            
        } catch (Exception e) {
            out.println("<html><head><title>Error - CA2</title></head>");
            out.println("<body style='font-family: Arial; margin: 20px;'>");
            out.println("<h2>Error Adding Item</h2>");
            out.println("<p style='color: red;'>Error: " + e.getMessage() + "</p>");
            out.println("<p><a href='addItemForm'>&larr; Try Again</a> | <a href='dashboard.jsp'>Back to Dashboard</a></p>");
            out.println("</body></html>");
        }
    }
}