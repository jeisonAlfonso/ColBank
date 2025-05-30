package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import extras.SessionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import runner.Main;

public class Profile {

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label documentNumberLabel;

    @FXML
    private Label documentTypeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label namesLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Button profileBackButton;

    @FXML
    private Label surnamesLabel;

    @FXML
    void returnToHomePage(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

    public void cargarDatosUsuario() {//Metodo que usa el archivo
        String cedulaActual = SessionData.getCedula();
        if (cedulaActual == null) return;

        File archivo = new File("src/documents/UserData.txt");
        if (!archivo.exists()) return;

        try {
            List<String> lineas = Files.readAllLines(archivo.toPath());

            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 9 && partes[3].trim().equals(cedulaActual)) {
                    namesLabel.setText(partes[0].trim());
                    surnamesLabel.setText(partes[1].trim());
                    documentTypeLabel.setText(partes[2].trim());
                    documentNumberLabel.setText(partes[3].trim());
                    birthDateLabel.setText(partes[4].trim());
                    genderLabel.setText(partes[5].trim());
                    phoneNumberLabel.setText(partes[6].trim());
                    emailLabel.setText(partes[7].trim());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        cargarDatosUsuario();
    }
}