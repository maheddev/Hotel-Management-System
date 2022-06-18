import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecter {
    public static Connection connection;
    public Connecter(){
        String databaseurl = "jdbc:sqlserver://localhost;databaseName=HotelManagement;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        try {
            connection = DriverManager.getConnection(databaseurl);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
