package ui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Window {
    @FXML
    private AnchorPane rootPane;

    public Window() {
        System.out.println("Loaded window!");
    }

    @FXML
    protected void initialize() throws IOException {
        // Set the root pane
        Controller controller = Controller.getInstance();
        controller.setMasterPane(rootPane);

        // Load login
        controller.changePane("login");
    }
}
