package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import runner.Main;

public class RegisterKeys {

    @FXML
    private Button addfavoriteButton;

    @FXML
    private TextField aliasField;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteFavoriteButton;

    @FXML
    private Button editFavoriteButton;

    @FXML
    private Button listFavoriteButton;

    @FXML
    private TextField llaveField;

    @FXML
    private Button validateFavoriteButton;

    @FXML
    void addFavorite(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void backToHomePage(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/HomePageView.fxml", "COLBANK", 600, 400);
    }

    @FXML
    void deleteFavorite(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void editFavorite(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void listFavorite(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

    @FXML
    void validateFavorite(ActionEvent event) {
        Main.getScreenController().switchScene("/resources/TemporalView.fxml", "COLBANK", 600, 400);//Temporal
    }

}
