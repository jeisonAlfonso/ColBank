package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import extras.Alerts;
import extras.SessionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import runner.Main;
import validations.StrongboxIIValidations;

public class StrongboxII {

    @FXML
    private Button addMoneyButton;

    @FXML
    private Button backToStrongboxIButton;

    @FXML
    private TextField moneySB;

    @FXML
    private Label moneyStrongboxTotalLabel;

    @FXML
    private Button removeMoneyButton;

     @FXML
    void initialize() {
        mostrarSaldoCajaFuerte();
    }

    @FXML
    void addMoneySB(ActionEvent event) {
        String input = moneySB.getText().trim();
        
        if (!StrongboxIIValidations.campoNoVacio(input)) return;

        if (!StrongboxIIValidations.esNumeroValido(input)) return;

        try {
            double cantidad = Double.parseDouble(input);
            String cedula = SessionData.getCedula();

            File folder = new File("src/documents");
            if (!folder.exists()) folder.mkdirs();

            File archivo = new File("src/documents/UserData.txt");
            if (!archivo.exists()) {
                return;
            }

            List<String> lineas = Files.readAllLines(archivo.toPath());
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 10 && partes[3].trim().equals(cedula)) {
                    double saldoCuenta = Double.parseDouble(partes[9].trim());
                    double saldoCaja = partes.length == 11 ? Double.parseDouble(partes[10].trim()) : 0.0;

                    if(!StrongboxIIValidations.cantidadMayorAlTotal(cantidad, saldoCuenta))return;

                    double nuevoSaldoCuenta = saldoCuenta - cantidad;
                    double nuevoSaldoCaja = saldoCaja + cantidad;

                    String nuevaLinea = String.join(" / ", Arrays.copyOf(partes, 9)) + " / " + nuevoSaldoCuenta + " / " + nuevoSaldoCaja;
                    nuevasLineas.add(nuevaLinea);
                } else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(archivo.toPath(), nuevasLineas);
            Alerts.mostrarAlerta("Success", "Money added to strongbox successfully.", Alert.AlertType.INFORMATION);
            mostrarSaldoCajaFuerte();
            moneySB.clear();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToStrongboxI(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/StrongboxIView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void removeMoney(ActionEvent event) {
        String input = moneySB.getText().trim();
        
        if (!StrongboxIIValidations.campoNoVacio(input)) return;

        if (!StrongboxIIValidations.esNumeroValido(input)) return;

        try {
            double cantidad = Double.parseDouble(input);
            String cedula = SessionData.getCedula();

            File folder = new File("src/documents");
            if (!folder.exists()) folder.mkdirs();

            File archivo = new File("src/documents/UserData.txt");
            if (!archivo.exists()) {
                return;
            }

            List<String> lineas = Files.readAllLines(archivo.toPath());
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 10 && partes[3].trim().equals(cedula)) {
                    double saldoCuenta = Double.parseDouble(partes[9].trim());
                    double saldoCaja = partes.length == 11 ? Double.parseDouble(partes[10].trim()) : 0.0;

                    if(!StrongboxIIValidations.cantidadMayorAlTotalDelSB(cantidad, saldoCaja))return;

                    double nuevoSaldoCuenta = saldoCuenta + cantidad;
                    double nuevoSaldoCaja = saldoCaja - cantidad;

                    String nuevaLinea = String.join(" / ", Arrays.copyOf(partes, 9)) + " / " + nuevoSaldoCuenta + " / " + nuevoSaldoCaja;
                    nuevasLineas.add(nuevaLinea);
                } else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(archivo.toPath(), nuevasLineas);
            Alerts.mostrarAlerta("Success", "Money removed from strongbox successfully.", Alert.AlertType.INFORMATION);
            mostrarSaldoCajaFuerte();
            moneySB.clear();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarSaldoCajaFuerte() {
        try {
            File folder = new File("src/documents");
            if (!folder.exists()) folder.mkdirs();

            File archivo = new File("src/documents/UserData.txt");
            if (!archivo.exists()) {
                return;
            }
            List<String> lineas = Files.readAllLines(archivo.toPath());
            String cedula = SessionData.getCedula();

            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 10 && partes[3].trim().equals(cedula)) {
                    double saldoCaja = partes.length == 11 ? Double.parseDouble(partes[10].trim()) : 0.0;
                    moneyStrongboxTotalLabel.setText(String.format("$ %.2f", saldoCaja));
                    return;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
