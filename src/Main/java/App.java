package Main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    public void start(Stage stage) throws Exception {

        AnchorPane pan = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Main/resources/Views/login_page.fxml")));
        Scene scene = new Scene(pan);
        stage.setTitle("Gestionnaire de restaurant");
        //stage.initStyle(StageStyle.UNDECORATED); Enleve la croix de l'écran
        stage.setScene(scene);
        stage.show();
    }

}





