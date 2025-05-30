package runner;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import extras.ScreenController;

public class Main extends Application {

    private static ScreenController screenController;

    @Override
    public void start(Stage stage) throws IOException {
        screenController = new ScreenController(stage);
        screenController.switchScene("/resources/StartView.fxml", "COLBANK", 600, 400);
    }

    public static ScreenController getScreenController() {
        return screenController;
    }

    public static void main(String[] args) {
        launch();
    }
}
