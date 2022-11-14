package Main.java;

import javafx.scene.control.TextField;

public class ValidationInput {

    public static boolean textFieldNull(TextField inputTextField) {
        return inputTextField.getText().isEmpty();
    }
    public static boolean PasswordRegister(TextField inputTextField){
        boolean DataLenght = false;
        if(!inputTextField.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")){
            DataLenght = true;
        }
        return DataLenght;
    }

}

