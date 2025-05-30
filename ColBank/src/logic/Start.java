package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import runner.Main;

public class Start {

    @FXML
    private Button startButton;

    @FXML
    void startColBank(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/LogInView.fxml", "COLBANK", 600, 400);
    }
}
