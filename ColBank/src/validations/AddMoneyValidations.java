package validations;

import extras.Alerts;
import javafx.scene.control.Alert;

public class AddMoneyValidations {

    public static boolean campoNoVacio(String input) {
        if (input == null || input.isEmpty()) {
            Alerts.mostrarAlerta("Warning", "The field cannot be empty.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    public static boolean esNumeroValido(String input) {
        if (!input.matches("\\d+(\\.\\d+)?")) {
            Alerts.mostrarAlerta("Warning", "Enter only valid numbers (no negative numbers, letters, or symbols).", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
}
