package Main.java;

import javafx.scene.control.TextField;

public class ValidationInput {

    public static boolean textFieldNull(TextField inputTextField) {
        boolean isNull = false;
        String validationString = null;
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
        }
        return isNull;
    }
}

