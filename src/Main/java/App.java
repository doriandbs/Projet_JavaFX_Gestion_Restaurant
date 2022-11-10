package Main.java;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {
    /* public static void main(String[] args){
    }*/

    public void start(Stage stage) throws Exception {
        AnchorPane pan = FXMLLoader.load(getClass().getResource("/Main/resources/fxml/login_page.fxml"));
        Scene scene = new Scene(pan);
        stage.setTitle("Gestionnaire de restaurant");
        stage.setScene(scene);
        stage.show();

    }
}
