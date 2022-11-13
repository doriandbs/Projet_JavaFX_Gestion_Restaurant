package Main.java;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidationInput {

    public static boolean textFieldNull(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;
        if (inputTextField.getText().isEmpty()) {
            validationString = validationText;
            isNull = true;
        }
        inputLabel.setText(validationString);
        return isNull;

    }
}

