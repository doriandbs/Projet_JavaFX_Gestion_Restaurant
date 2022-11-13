package Main.java;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionClass {
    Connection connection;

    public Connection getConnection() {
        try {
            String user = "root";
            String psw = "";
            String url = "jdbc:mysql://localhost:3306/projetrestaurant";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, psw);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
