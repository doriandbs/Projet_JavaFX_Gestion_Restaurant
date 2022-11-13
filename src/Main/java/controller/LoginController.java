package Main.java.controller;

import Main.java.ConnectionClass;
import Main.java.ValidationInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;
    public Label errormsg;
    Stage stage;
    Scene scene;

    public void login() {
        boolean nom = ValidationInput.textFieldNull(input_nom, errormsg, "Nom requis");

        try {
            ConnectionClass conn = new ConnectionClass();
            Connection connection = conn.getConnection();
            PreparedStatement requete = connection.prepareStatement("SELECT * FROM user WHERE NOM like ? AND PASSWORD like ? ");
            requete.setString(1, input_nom.getText());
            requete.setString(2, input_psw.getText());
            ResultSet resultSet = requete.executeQuery();

           /* if (!Objects.equals(resultSet.getString("NOM"), input_nom) || !Objects.equals(resultSet.getString("PASSWORD"), input_psw)) {
                isConnected.setText("NOM OU MOT DE PASSE INCORRECT");
            }*/
            while (resultSet.next()) {

                if (Objects.equals(input_nom.getText(), "") || Objects.equals(input_psw.getText(), "")) {
                    errormsg.setText("VEUILLEZ RENTRER TOUT LES CHAMPS");
                }
                else if (Objects.equals(resultSet.getString("NOM"), input_nom.getText()) && Objects.equals(resultSet.getString("PASSWORD"), input_psw.getText())) {
                    System.out.println(resultSet.getString("NOM") + " est connecté");
                    isConnected.setText("CONNEXION REUSSIE");
                    isConnected.setTextFill(Color.GREEN);
                    errormsg.setText("");
                }
                else {
                    errormsg.setText("NOM OU MOT DE PASSE INCORRECT");
                }

            }
            requete.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/fxml/register_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}