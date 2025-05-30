package logic;


import extras.Alerts;
import extras.SessionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import runner.Main;
import validations.LogInValidations;

public class LogIn {

    @FXML
    private Button ingresaroBoton;

    @FXML
    private PasswordField passwordtextfield;

    @FXML
    private TextField usuariotf;

   @FXML
    void Login(ActionEvent event) throws Exception {
        String usuarioIngresado = usuariotf.getText().trim();
        String contrasenaIngresada = passwordtextfield.getText().trim();

        boolean completedForm = LogInValidations.validateCompletedForm(usuarioIngresado, contrasenaIngresada);
        if (!completedForm) return;

        boolean documentoValido = LogInValidations.esNumerico(usuarioIngresado);
        if (!documentoValido) return;

        boolean usuarioExiste = LogInValidations.usuarioExiste(usuarioIngresado);
        if (!usuarioExiste) {
            Alerts.mostrarAlerta("Warning", "Unregistered user.", Alert.AlertType.WARNING);
            return;
        }

        boolean contrasenaCorrecta = LogInValidations.esContrasenaCorrecta(usuarioIngresado, contrasenaIngresada);
        if (!contrasenaCorrecta) return;

        SessionData.setCedula(usuarioIngresado);
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void toregister(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/SignUpView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void backToStart(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/StartView.fxml", "COLBANK", 600, 400);
    }
}
