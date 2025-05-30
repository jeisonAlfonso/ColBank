package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import runner.Main;
import validations.*;
import extras.*;

public class AddMoney {

    @FXML
    private Button addButton;

    @FXML
    private Button addMoneyBackButton;

    @FXML
    private TextField addMoneyTF;

    @FXML
    void addMoney(ActionEvent event) {//Metodo que usa el archivo
        String input = addMoneyTF.getText().trim();

        if (!AddMoneyValidations.campoNoVacio(input)) return;

        if (!AddMoneyValidations.esNumeroValido(input)) return;

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
            boolean encontrado = false;

            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 9 && partes[3].trim().equals(cedula)) {
                    double saldoActual = 0.0;
                    if (partes.length == 10) {
                        saldoActual = Double.parseDouble(partes[9].trim());
                    }

                    double nuevoSaldo = saldoActual + cantidad;

                    String nuevaLinea = String.join(" / ", Arrays.copyOf(partes, 9)) + " / " + nuevoSaldo;
                    nuevasLineas.add(nuevaLinea);
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            }

            if (!encontrado) {
                return;
            }

            Files.write(archivo.toPath(), nuevasLineas);
            Alerts.mostrarAlerta("Success", "Money added successfully.", Alert.AlertType.INFORMATION);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addMoneyBackButtonM(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }
}
