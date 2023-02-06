package Main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminController {

    @FXML
    private Pane pnl_stocks, pnl_users, pnl_money, pnl_product;
    @FXML
    private ImageView img_stock, img_users, img_money, img_product;

    Stage stage;
    Scene scene;

    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == img_stock) {
            pnl_stocks.toFront();
            System.out.println("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == img_money) {
            pnl_money.toFront();
        } else if (mouseDragEvent.getSource() == img_users) {
            pnl_users.toFront();
        } else if (mouseDragEvent.getSource() == img_product) {
            pnl_product.toFront();
        }
    }


    public void MappingLogout(ActionEvent event) {
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
