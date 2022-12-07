package Main.java.controller;


import Main.bdd.ConnectionClass;
import Main.java.ValidationInput;
import Main.java.constantes.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class InscriptionPageController {

    public TextField input_nomRegister;
    public TextField input_prenomRegister;
    public TextField input_pswRegister;

    public Label name_errorLabel;
    public Label firstname_errorLabel;
    public Label psw_errorLabel;


    Stage stage;
    Scene scene;

    public void addUser() {
        boolean nameError = ValidationInput.textFieldNull(input_nomRegister);
        boolean firstNameError = ValidationInput.textFieldNull(input_prenomRegister);
        boolean mdpError = ValidationInput.PasswordRegister(input_pswRegister);

        try {
            if (nameError) name_errorLabel.setText(Constants.nomRec);
            else if (firstNameError) firstname_errorLabel.setText(Constants.prenomRec);
            else if (mdpError) psw_errorLabel.setText(Constants.pswRegister);


                ConnectionClass conn = new ConnectionClass();
                Connection connection = conn.getConnection();
                PreparedStatement requete = connection.prepareStatement("insert into user(NOM,PRENOM,PASSWORD) values(?,?,?)");
                requete.setString(1, input_nomRegister.getText().toUpperCase());
                requete.setString(2, input_prenomRegister.getText().toUpperCase());
                requete.setString(3, input_pswRegister.getText().toUpperCase());
                int n = requete.executeUpdate();
                System.out.println(n);
                requete.close();
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
