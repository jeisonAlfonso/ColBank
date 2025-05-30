package validations;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import extras.Alerts;
import javafx.scene.control.Alert;

public class SignUpValidations{

    public static void validarDocumento(String nume_Docu, List<String> documentosExistentes) {
        if (documentosExistentes.contains(nume_Docu)) {
            Alerts.mostrarAlerta("Warning", "The document number is already registered.", Alert.AlertType.WARNING);
        }
        
        if (nume_Docu.matches(".*[a-zA-Z]+.*")) {
            Alerts.mostrarAlerta("Warning", "The document number cannot contain letters.", Alert.AlertType.WARNING);
        }
    }

    public static void validarEdad(LocalDate fechaNac){
        if (fechaNac == null) {
            Alerts.mostrarAlerta("Warning", "Select a birth of date.", Alert.AlertType.WARNING);
        }
        int edad = Period.between(fechaNac, LocalDate.now()).getYears();
        if (edad < 16) {
            Alerts.mostrarAlerta("Warning", "Must be over 16 years old.", Alert.AlertType.WARNING);
        }
    }

    public static void validarCorreo(String corr,List<String> correosExistentes) {
        if (correosExistentes.contains(corr)) {
            Alerts.mostrarAlerta("Warning", "The email is already registered.", Alert.AlertType.WARNING);
        }
        if (!corr.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")) {
            Alerts.mostrarAlerta("Warning", "The email is no valid.", Alert.AlertType.WARNING);
        }
    }

    public static void validarTelefono(String tele, List<String> numerosExistentes){
        if (numerosExistentes.contains(tele)) {
            Alerts.mostrarAlerta("Warning", "The phone number is already registered.", Alert.AlertType.WARNING);
        }
        if (tele.matches(".*[a-zA-Z]+.*")) {
            Alerts.mostrarAlerta("Warning", "The phone number cannot contain letters.", Alert.AlertType.WARNING);
        }
        if (!tele.matches("\\d{10}")) {
            Alerts.mostrarAlerta("Warning", "The phone number must have 10 digits.", Alert.AlertType.WARNING);
        }
    }

    public static void validarContraseña(String password){
        if (password.length() < 8) {
            Alerts.mostrarAlerta("Warning", "The password must be at least 8 characters long.", Alert.AlertType.WARNING);
        }
    }

    public static void validateCompletedForm(String nomb, String apel, String tipo_Docu, String nume_Docu, LocalDate fech_Naci, String gene, String corr, String tele, String password){
        if(nomb == null || apel == null || tipo_Docu == null || nume_Docu == null || fech_Naci == null || gene == null || corr == null || tele == null || password == null){
            Alerts.mostrarAlerta("Warning", "You must complete all fields in the form.", Alert.AlertType.WARNING);
        }
    }
}
