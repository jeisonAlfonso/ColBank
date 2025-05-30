package validations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import extras.Alerts;
import extras.SessionData;
import javafx.scene.control.Alert;

public class StrongboxIValidations {

     public static boolean contraseñaEscrita(String contrasenaIngresada){
       
        if (contrasenaIngresada == null || contrasenaIngresada.isEmpty()){
            
        Alerts.mostrarAlerta("Warning", "Please enter your password.", Alert.AlertType.WARNING);
            return false;
        }else{
            return true;
        }
    }

    public static boolean esContrasenaCorrecta(String contrasenaIngresada) {//Metodo que usa el archivo
        File archivo = new File("src/documents/UserData.txt");

        if (!archivo.exists()) {
            return false;
        }

        String cedula = SessionData.getCedula();

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
