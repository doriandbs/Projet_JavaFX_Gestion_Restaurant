package Main.java.controller;

import Main.java.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;

    public void login(ActionEvent actionEvent){
        ConnectionClass conn = new ConnectionClass();
        Connection connection = conn.getConnection();
        try {
            Statement statement = connection.createStatement();
            String requete = "SELECT * FROM user WHERE input_nom = '" + input_nom.getText() + "' AND PASSWORD = '" + input_psw.getText();
            ResultSet resultSet = statement.executeQuery(requete);
            if(resultSet.next()){
                isConnected.setText("CONNEXION REUSSIE");
            }else{
                isConnected.setText("CONNEXION FAILED");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
