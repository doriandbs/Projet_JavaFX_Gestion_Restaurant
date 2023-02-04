package Main.java.controller;
import Main.java.constantes.Constants;

import Main.bdd.ConnectionClass;
import Main.java.ValidationInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

import static Main.java.utils.HashPassword.hashPassword;

public class LoginController {
    public TextField input_nom;
    public PasswordField input_psw;
    public Label isConnected;
    public Label errormsg;
    public Button button_connexion;
    public Hyperlink button_register;

    Stage stage;
    Scene scene;

    public void login() {
        boolean nom = ValidationInput.textFieldNull(input_nom);
        boolean password = ValidationInput.textFieldNull(input_psw);

        if (nom && password) errormsg.setText(Constants.verifCh);
        else if (nom) errormsg.setText(Constants.nomRec);
        else if (password) errormsg.setText(Constants.pswRec);
        else errormsg.setText(Constants.userNotFound);
        if(!nom && !password) {

            try {
                ConnectionClass conn = new ConnectionClass();
                Connection connection = conn.getConnection();
                PreparedStatement requete = connection.prepareStatement("SELECT * FROM user WHERE NOM = ? AND PASSWORD = ? ");
                requete.setString(1, input_nom.getText());
                requete.setString(2, input_psw.getText());
                ResultSet resultSet = requete.executeQuery();


                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString("NOM"), input_nom.getText()) && Objects.equals(resultSet.getString("PASSWORD"), input_psw.getText())) {
                        System.out.println(resultSet.getString("NOM") + " est connecté");
                        isConnected.setText(Constants.connSucc);
                        isConnected.setTextFill(Color.GREEN);
                        errormsg.setText("");
                    } else {
                        errormsg.setText(Constants.nomOrpswI);
                    }
                }
                requete.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/register_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}