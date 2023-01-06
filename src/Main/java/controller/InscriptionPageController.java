package Main.java.controller;


import Main.bdd.ConnectionClass;
import Main.java.ValidationInput;
import Main.java.constantes.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class InscriptionPageController {

    public TextField input_nomRegister;
    public TextField input_prenomRegister;
    public PasswordField input_pswRegister;

    public Label name_errorLabel;
    public Label firstname_errorLabel;
    public Label psw_errorLabel;
    public Label userNotFound;
    public Button button_inscription;


    Stage stage;
    Scene scene;

    public void addUser() {
        boolean nameError = ValidationInput.textFieldNull(input_nomRegister);
        boolean firstNameError = ValidationInput.textFieldNull(input_prenomRegister);
        boolean mdpError = ValidationInput.PasswordRegister(input_pswRegister);
        boolean mdpNull = ValidationInput.textFieldNull(input_pswRegister);

        try {
            ConnectionClass conn = new ConnectionClass();
            Connection connection = conn.getConnection();

            PreparedStatement requete1 = connection.prepareStatement("SELECT * FROM user WHERE NOM = ? AND PRENOM = ? AND PASSWORD = ?");
            requete1.setString(1, input_nomRegister.getText().toUpperCase());
            requete1.setString(2, input_prenomRegister.getText().toUpperCase());
            requete1.setString(3, input_pswRegister.getText().toUpperCase());

            if(requete1.executeQuery().next()){
                userNotFound.setText(Constants.userExist);
                userNotFound.setTextFill(Color.RED);
                requete1.close();
            }
            else if (nameError && firstNameError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                firstname_errorLabel.setText(Constants.prenomRec);
                psw_errorLabel.setText(Constants.pswRec);
            }
            else if (nameError && firstNameError) {
                name_errorLabel.setText(Constants.nomRec);
                firstname_errorLabel.setText(Constants.prenomRec);
                psw_errorLabel.setText("");
            }
            else if (firstNameError && mdpNull) {
                firstname_errorLabel.setText(Constants.prenomRec);
                psw_errorLabel.setText(Constants.pswRec);
                name_errorLabel.setText("");
            }
            else if (nameError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                psw_errorLabel.setText(Constants.pswRec);
                firstname_errorLabel.setText("");
            }
            else if (nameError) {
                userNotFound.setText("");
                name_errorLabel.setText(Constants.nomRec);
                firstname_errorLabel.setText("");
                psw_errorLabel.setText("");
            }
            else if (firstNameError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                firstname_errorLabel.setText(Constants.prenomRec);
                psw_errorLabel.setText("");
            } else if (mdpNull) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                firstname_errorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRec);
            } else if (mdpError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                firstname_errorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRegister);
            }
            else {
                userNotFound.setText(Constants.userCreat);
                userNotFound.setTextFill(Color.GREEN);
                psw_errorLabel.setText("");
                PreparedStatement requete = connection.prepareStatement("insert into user(NOM,PRENOM,PASSWORD) values(?,?,?)");
                requete.setString(1, input_nomRegister.getText().toUpperCase());
                requete.setString(2, input_prenomRegister.getText().toUpperCase());
                requete.setString(3, input_pswRegister.getText().toUpperCase());
                int n = requete.executeUpdate();
                System.out.println(n);
                requete.close();
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/login_page.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
