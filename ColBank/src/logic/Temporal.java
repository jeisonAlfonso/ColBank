package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import runner.Main;

public class Temporal {

    @FXML
    private Button backTemporalButton;

    @FXML
    void backToHomePage(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

}
