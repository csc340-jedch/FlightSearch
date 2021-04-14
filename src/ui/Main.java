package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Create config if not found
        if (!ConfigManager.configExists()) {
            ConfigManager.writeConfig("", "");
        }

        // Load the main parent window (This will be controlled by the Window class)
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/window.fxml"));
        primaryStage.setTitle("Flight GUI");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
