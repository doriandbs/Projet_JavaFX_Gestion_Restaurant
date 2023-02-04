package Main.java.controller;

import Main.bdd.DatabaseSingleton;
import Main.java.ValidationInput;
import Main.java.constantes.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import static Main.java.utils.Md5.generateHash;

public class LoginController {
    public TextField input_badge;
    public PasswordField input_psw;
    public Label isConnected;
    public Label errormsg;
    public Button button_connexion;
    public Hyperlink button_register;

    Stage stage;
    Scene scene;

    public void login() {
        boolean badge = ValidationInput.textFieldNull(input_badge);
        boolean password = ValidationInput.textFieldNull(input_psw);

        if (badge && password) errormsg.setText(Constants.verifCh);
        else if (badge) errormsg.setText(Constants.badgeRec);
        else if (password) errormsg.setText(Constants.pswRec);
        else errormsg.setText(Constants.userNotFound);
        if (!badge && !password) {

            try {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                db.connect();
                PreparedStatement requete = db.prepareStatement("SELECT * FROM user WHERE BADGE = ? AND PASSWORD = ? ");
//                System.out.println(checkPassword(input_badge.getText(),input_psw.getText()));
                String pswHash = generateHash(input_psw.getText());
                System.out.println(pswHash);
                requete.setString(1, input_badge.getText());
                requete.setString(2, pswHash);
                ResultSet resultSet = requete.executeQuery();


                while (resultSet.next()) {
                    if (Objects.equals(resultSet.getString("BADGE"), input_badge.getText()) && Objects.equals(resultSet.getString("PASSWORD"), pswHash)) {
                        //rajouter condition if si ISADMIN 1 ou 0
                        System.out.println(resultSet.getString("NOM") + "badge : " + resultSet.getString("BADGE") + " est connecté");
                        isConnected.setText(Constants.connSucc);
                        isConnected.setTextFill(Color.GREEN);
                        errormsg.setText("");
                    } else {
                        errormsg.setText(Constants.nomOrpswI);
                    }
                }
                requete.close();
                db.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/register.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}