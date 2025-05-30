package logic;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Label;
import java.nio.file.Files;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import runner.Main;
import extras.SessionData;

public class HomePage{

    @FXML
    private Button LogOutButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button addMoneyButton;

    @FXML
    private Label moneyTotalLabel;

    @FXML
    private Button registerKeyButton;

    @FXML
    private Button sendMoneyButton;

    @FXML
    private Button strongboxButton;

    @FXML
    void addMoney(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/AddMoneyView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void profile(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/ProfileView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void registerKey(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/KeysView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void sendMoney(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void strongbox(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/StrongboxIView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void logOut(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/LogOutView.fxml", "COLBANK", 600, 400);
    }
    
    @FXML
    public void initialize() {
        String cedula = SessionData.getCedula();
        if (cedula != null) {
            double saldo = obtenerSaldoDesdeArchivo(cedula);
            moneyTotalLabel.setText("$ " + String.format("%.2f", saldo));
        }
    }

    private double obtenerSaldoDesdeArchivo(String cedula) {//Metodo que usa el archivo
        File archivo = new File("src/documents/UserData.txt");
        if (!archivo.exists()) return 0.0;

        try {
            List<String> lineas = Files.readAllLines(archivo.toPath());
            for (String linea : lineas) {
                String[] partes = linea.split(" / ");
                if (partes.length >= 10 && partes[3].trim().equals(cedula)) {
                    
                        return Double.parseDouble(partes[9].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }    
}
