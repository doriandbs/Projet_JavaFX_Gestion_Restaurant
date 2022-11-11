package Main.java;


import javafx.fxml.FXMLLoader;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


import java.util.Objects;

public class App extends Application {
//    public static void main(String[] args) {}

    public void start(Stage stage) throws Exception {
        AnchorPane pan = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/fxml/login_page.fxml")));
        Scene scene = new Scene(pan);
        stage.setTitle("Gestionnaire de restaurant");
        stage.setScene(scene);
        stage.show();
    }

}



