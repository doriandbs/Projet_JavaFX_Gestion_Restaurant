package Main.java.controller;

import Main.java.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InscriptionPageController {

    public TextField input_nom;
    public TextField input_prenom;
    public TextField input_psw;
    public Label isConnected;

    public void addUser() {
        try {
            //Statement statement = connection.createStatement();
            ConnectionClass conn = new ConnectionClass();
            Connection connection = conn.getConnection();
            PreparedStatement requete = connection.prepareStatement("insert into user(N0M,PRENOM,PASSWORD) values (?,?,?)");
            requete.setString(1, input_nom.getText().toUpperCase());
            requete.setString(2, input_prenom.getText().toUpperCase());
            requete.setString(3, input_psw.getText().toUpperCase());
//            ResultSet resultSet;
            int n = requete.executeUpdate();
            System.out.println(n);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}