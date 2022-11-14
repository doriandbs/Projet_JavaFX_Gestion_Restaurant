package Main.java;

import javafx.scene.control.TextField;

public class ValidationInput {

    public static boolean textFieldNull(TextField inputTextField) {
        return inputTextField.getText().isEmpty();
    }
}

