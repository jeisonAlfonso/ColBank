package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import runner.Main;
import validations.StrongboxIValidations;

public class StrongboxI {

    @FXML
    private Button backToHomePageButton;

    @FXML
    private Button goToStrongboxIIButton;

    @FXML
    private PasswordField passwordStrongboxTF;

    @FXML
    void backToHomePage(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void goToStrongboxII(ActionEvent event) {
        String contrasenaIngresada = passwordStrongboxTF.getText().trim();

        boolean completedForm = StrongboxIValidations.contraseñaEscrita(contrasenaIngresada);
        if (!completedForm) return;

        boolean contrasenaCorrecta = StrongboxIValidations.esContrasenaCorrecta(contrasenaIngresada);
        if (!contrasenaCorrecta) return;

        Main.getScreenController().switchScene("/resources/StrongboxIIView.fxml", "COLBANK", 600, 400);
    }

}
