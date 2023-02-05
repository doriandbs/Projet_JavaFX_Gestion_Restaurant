package Main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class HomeController {
    @FXML
    private Pane pnl_menu, pnl_burger, pnl_boisson, pnl_dessert, pnl_supp;
    @FXML
    private ImageView img_menu, img_burger, img_drink, img_dessert, img_supp;


    @FXML
    private void handleButtonAction(MouseEvent mouseDragEvent) {
        if (mouseDragEvent.getSource() == img_menu) {
            pnl_menu.toFront();
            System.out.println("Test bien appuyé");
        } else if (mouseDragEvent.getSource() == img_burger) {
            pnl_burger.toFront();
        } else if (mouseDragEvent.getSource() == img_drink) {
            pnl_boisson.toFront();
        } else if (mouseDragEvent.getSource() == img_dessert) {
            pnl_dessert.toFront();
        } else if (mouseDragEvent.getSource() == img_supp) {
            pnl_supp.toFront();
        }
    }
}
