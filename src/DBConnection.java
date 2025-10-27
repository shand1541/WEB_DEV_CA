import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database connection details
     public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gaming_portal?serverTimezone=UTC";
        String user = "root";
        String password = "root"; // Change if needed
        return DriverManager.getConnection(url, user, password);
    }
}
