module Projet.Java.Fx {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mysql.connector.j;
    exports Main.java to javafx.graphics;
    exports Main.java.controller to javafx.fxml;
    opens Main.java.controller to javafx.fxml;
    exports Main.bdd to javafx.graphics;

}