package Main.bdd;
import Main.java.constantes.Constants;

import java.sql.*;


public class ConnectionClass {
    Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(Constants.driver);
            connection = DriverManager.getConnection(Constants.url, Constants.user, Constants.psw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
