package Main.java.controller;

import Main.java.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;
    Stage stage;
    Scene scene;
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
                isConnected.setText("CONNEXION REUSSIE");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Main/resources/fxml/register_page.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}