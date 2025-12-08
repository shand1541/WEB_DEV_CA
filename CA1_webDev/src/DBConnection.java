import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database connection details
    //added this so i can copy it to other projects easily
     public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gaming_portal?serverTimezone=UTC";
        String user = "root";
        String password = "root"; 
        return DriverManager.getConnection(url, user, password);
    }
}
