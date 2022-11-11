package Main.java.controller;

import Main.java.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;

    public void login(ActionEvent actionEvent){
        try {
            //Statement statement = connection.createStatement();
            ConnectionClass conn = new ConnectionClass();
            Connection connection = conn.getConnection();
            PreparedStatement  requete = connection.prepareStatement("SELECT * FROM user WHERE NOM like ? AND  PASSWORD like ? ");
            requete.setString(1, input_nom.getText());
            requete.setString(2, input_psw.getText());
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("NOM") + "est connecté");
            }
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