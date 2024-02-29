package chap10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/ocp";
        String username = "postgres";
        String password = "password";
//        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(url, username, password);
    }
}
