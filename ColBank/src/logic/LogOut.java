package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import runner.Main;

public class LogOut {

    @FXML
    private Button NoLogOutButton;

    @FXML
    private Button YesLogOutButton;

    @FXML
    void noLogOut(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void yesLogOut(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/StartView.fxml", "COLBANK", 600, 400);
    }

}
