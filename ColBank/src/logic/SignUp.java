package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import runner.Main;
import validations.SignUpValidations;
import extras.Alerts;

public class SignUp implements Initializable {

    @FXML
    private TextField sbApel;

    @FXML
    private TextField sbCorr;

    @FXML
    private DatePicker sbFech_naci;

    @FXML
    private ChoiceBox<String> sbGene;

    @FXML
    private TextField sbNomb;

    @FXML
    private TextField sbNume_docu;

    @FXML
    private ChoiceBox<String> sbTipo_docu;

    @FXML
    private Button scGuardar;

    @FXML
    private TextField sbTele;

    @FXML
    private PasswordField sbCont;

    @FXML
    private Button signUpBackButton;

    private List<String> documentosExistentes = new ArrayList<>();
    private List<String> correosExistentes = new ArrayList<>();
    private List<String> numerosExistentes = new ArrayList<>();

    @FXML
    void signUpBack(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/LogInView.fxml", "COLBANK", 600, 400);
    }
    
    @FXML
    void guardarArchivo(ActionEvent event) {
        boolean registroExitoso = crearArchivo();

        if (registroExitoso) {
            Main.getScreenController().switchScene("/resources/LoginView.fxml", "COLBANK", 600, 400);
        }
    }

    private boolean crearArchivo(){//Metodo que usa el archivo
        try {
            String nomb = sbNomb.getText().trim();
            String apel = sbApel.getText().trim();
            String tipo_Docu = sbTipo_docu.getSelectionModel().getSelectedItem();
            String nume_Docu = sbNume_docu.getText().trim();
            LocalDate fech_Naci = sbFech_naci.getValue();
            String gene = sbGene.getSelectionModel().getSelectedItem();
            String corr = sbCorr.getText().trim();
            String tele = sbTele.getText().trim();
            String password = sbCont.getText().trim();

            cargarDatos();
            SignUpValidations.validateCompletedForm(nomb, apel, tipo_Docu, nume_Docu, fech_Naci, gene, corr, tele, password);
            SignUpValidations.validarDocumento(nume_Docu, documentosExistentes);
            SignUpValidations.validarEdad(fech_Naci);
            SignUpValidations.validarCorreo(corr, correosExistentes);
            SignUpValidations.validarTelefono(tele, numerosExistentes);
            SignUpValidations.validarContraseña(password);

            documentosExistentes.add(nume_Docu);
            correosExistentes.add(corr);

            File folder = new File("src/documents");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File archivo1 = new File(folder, "UserData.txt");
            try (FileWriter writer = new FileWriter(archivo1, true)) {
                writer.write(nomb + " / " + apel + " / " + tipo_Docu + " / " + nume_Docu + " / " + fech_Naci + " / " + gene + " / " + corr + " / " + tele + " / " + password + "\n");
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            Alerts.mostrarAlerta("Success", "The user has been registered successfully.", Alert.AlertType.INFORMATION);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void cargarDatos() {//Metodo que usa el archivo
        File archivo = new File("src/documents/UserData.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        documentosExistentes.clear();
        correosExistentes.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(" / ");
                if (datos.length >= 8) {
                    String nume_DocuL = datos[3];
                    String corrL = datos[6];
                    String teleL = datos[7];
                    documentosExistentes.add(nume_DocuL);
                    correosExistentes.add(corrL);
                    numerosExistentes.add(teleL);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sbTipo_docu.getItems().addAll("CC", "CE", "TI", "NU", "PP", "PE");
        sbGene.getItems().addAll("Male", "Female", "Other");

        cargarDatos();
    }
}