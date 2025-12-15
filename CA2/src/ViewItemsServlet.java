import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ViewItemsServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>All Items - CA2</title>");
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
        
        out.println("<h2>All Items for Sale</h2>");
        
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> products = productDAO.getAllProducts();
            
            if (products != null && !products.isEmpty()) {
                out.println("<table>");
                out.println("<tr><th>Title</th><th>Description</th><th>Price</th><th>Category</th><th>Seller</th><th>Actions</th></tr>");
                
                for (Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getProductName() + "</td>");
                    out.println("<td>" + product.getProductDetails() + "</td>");
                    out.println("<td>$" + product.getMinimumBid() + "</td>");
                    out.println("<td>" + product.getProductCategory() + "</td>");
                    out.println("<td>Owner ID: " + product.getOwnerId() + "</td>");
                    out.println("<td><a href='viewItem.jsp?id=" + product.getProductId() + "'>View Details</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>No items found.</p>");
            }
            
        } catch (Exception e) {
            out.println("<p style='color: red;'>Error loading items: " + e.getMessage() + "</p>");
        }
        
        out.println("<p><a href='dashboard.jsp'>Back to Dashboard</a> | <a href='addItemForm'>Add New Item</a></p>");
        out.println("</body></html>");
    }
}