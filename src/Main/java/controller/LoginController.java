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
import java.util.Objects;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;
    Stage stage;
    Scene scene;
    public void login(){
        try {
            ConnectionClass conn = new ConnectionClass();
            Connection connection = conn.getConnection();
            PreparedStatement  requete = connection.prepareStatement("SELECT * FROM user WHERE NOM like ? AND PASSWORD like ? ");
            requete.setString(1, input_nom.getText());
            requete.setString(2, input_psw.getText());
            ResultSet resultSet = requete.executeQuery();
            while (resultSet.next()){
                if (Objects.equals(input_nom.getText(), null) || Objects.equals(input_psw.getText(), "")) {
                    isConnected.setText("VEUILLEZ RENTRER TOUT LES CHAMPS");
                }
                if (Objects.equals(resultSet.getString("NOM"), input_nom.getText()) && Objects.equals(resultSet.getString("PASSWORD"), input_psw.getText())){
                    System.out.println(resultSet.getString("NOM") + " est connecté");
                    isConnected.setText("CONNEXION REUSSIE");
                }
                if (!Objects.equals(resultSet.getString("NOM"), input_nom.getText()) || !Objects.equals(resultSet.getString("PASSWORD"), input_psw.getText())){
                    isConnected.setText("NOM OU MOT DE PASSE INCORRECT");
                }
            }
            requete.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/fxml/register_page.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}