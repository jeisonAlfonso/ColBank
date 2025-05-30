package validations;

import extras.Alerts;
import javafx.scene.control.Alert;

public class StrongboxIIValidations {

    public static boolean cantidadMayorAlTotal(double cantidad, double saldoCuenta){
        if (cantidad > saldoCuenta){
            Alerts.mostrarAlerta("Warning", "Insufficient balance in your account.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    
    public static boolean cantidadMayorAlTotalDelSB(double cantidad, double saldoCaja){
        if (cantidad > saldoCaja) {
            Alerts.mostrarAlerta("Warning", "Insuficient balance in your strongbox.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    public static boolean campoNoVacio(String input) {
        if (input == null || input.isEmpty()) {
            Alerts.mostrarAlerta("Warning", "Please write your password.", Alert.AlertType.WARNING);
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
