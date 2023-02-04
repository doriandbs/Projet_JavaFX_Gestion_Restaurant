package Main.java.controller;


import Main.bdd.DatabaseSingleton;
import Main.java.ValidationInput;
import Main.java.constantes.Constants;
import Main.java.utils.Md5;
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
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;


public class InscriptionPageController {

    public TextField input_nameRegister;
    public TextField input_badgeRegister;
    public PasswordField input_pswRegister;

    public Label name_errorLabel;
    public Label badge_ErrorLabel;
    public Label psw_errorLabel;
    public Label userNotFound;
    public Button button_inscription;


    Stage stage;
    Scene scene;

    public void addUser(ActionEvent event) {
        boolean nameError = ValidationInput.textFieldNull(input_nameRegister);
        boolean BadgeError = ValidationInput.textFieldNull(input_badgeRegister);
        boolean mdpError = ValidationInput.PasswordRegister(input_pswRegister);
        boolean mdpNull = ValidationInput.textFieldNull(input_pswRegister);

        try {
            DatabaseSingleton db = DatabaseSingleton.getInstance();
            db.connect();
            PreparedStatement requete1 = db.prepareStatement("SELECT * FROM user WHERE NOM = ? AND BADGE = ? AND PASSWORD = ?");
            requete1.setString(1, input_nameRegister.getText());
            requete1.setString(2, input_badgeRegister.getText());
            requete1.setString(3, input_pswRegister.getText());

            if (requete1.executeQuery().next()) {
                userNotFound.setText(Constants.userExist);
                userNotFound.setTextFill(Color.RED);
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText("");
            } else if (nameError && BadgeError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText(Constants.pswRec);
            } else if (nameError && BadgeError) {
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText("");
            } else if (BadgeError && mdpNull) {
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText(Constants.pswRec);
                name_errorLabel.setText("");
            } else if (nameError && mdpNull) {
                name_errorLabel.setText(Constants.nomRec);
                psw_errorLabel.setText(Constants.pswRec);
                badge_ErrorLabel.setText("");
            } else if (nameError) {
                userNotFound.setText("");
                name_errorLabel.setText(Constants.nomRec);
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText("");
            } else if (BadgeError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText(Constants.badgeRec);
                psw_errorLabel.setText("");
            } else if (mdpNull) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRec);
            } else if (mdpError) {
                userNotFound.setText("");
                name_errorLabel.setText("");
                badge_ErrorLabel.setText("");
                psw_errorLabel.setText(Constants.pswRegister);
            }
            else {
                userNotFound.setText(Constants.userCreat);
                userNotFound.setTextFill(Color.GREEN);
                psw_errorLabel.setText("");
                String hashpwd = Md5.generateHash(input_pswRegister.getText());
                System.out.println(hashpwd);
                PreparedStatement requete = db.prepareStatement("insert into user(NOM,BADGE,PASSWORD) values(?,?,?)");
                requete.setString(1, input_nameRegister.getText());
                requete.setString(2, input_badgeRegister.getText());
                requete.setString(3, hashpwd);
                int n = requete.executeUpdate();
                System.out.println(n);
                requete.close();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/login_page.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            requete1.close();
            db.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException e) {
            userNotFound.setText(Constants.userExist);
        } catch (NoSuchAlgorithmException e) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
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