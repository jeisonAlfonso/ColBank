package validations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import extras.Alerts;
import javafx.scene.control.Alert;

public class LogInValidations {

    public static boolean validateCompletedForm(String usuarioIngresado, String contrasenaIngresada){
       
        if (usuarioIngresado == null || usuarioIngresado.isEmpty() || contrasenaIngresada == null || contrasenaIngresada.isEmpty()){
            
        Alerts.mostrarAlerta("Warning", "You must complete all the form.", Alert.AlertType.WARNING);
            return false;
        }else{
            return true;
        }
    }

    public static boolean usuarioExiste(String cedula) {//Metodo que usa el archivo
        
        File archivo = new File("src/documents/UserData.txt");

        if (!archivo.exists()) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(" / ");
                if (datos.length >= 9 && datos[3].trim().equals(cedula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return false;
    }

    public static boolean esNumerico(String cedula) {
        if (cedula == null || !cedula.matches("\\d+")) {
            Alerts.mostrarAlerta("Warning", "The document number must contain only numbers.", Alert.AlertType.WARNING);
            return false;
        }
    return true;
    }

    public static boolean esContrasenaCorrecta(String cedula, String contrasenaIngresada) {//Metodo que usa el archivo
        File archivo = new File("src/documents/UserData.txt");

        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(" / ");
                if (datos.length >= 9 && datos[3].trim().equals(cedula)) {
                    if (datos[8].trim().equals(contrasenaIngresada)) {
                        return true;
                    } else {
                        Alerts.mostrarAlerta("Warning", "Incorrect password", Alert.AlertType.WARNING);
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return false;
    }
}
