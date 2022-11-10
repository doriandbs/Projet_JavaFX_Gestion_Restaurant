module Projet.Java.Fx {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    exports Main.java to javafx.graphics;

}