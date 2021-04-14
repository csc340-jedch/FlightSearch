package ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Help {
    public Button backButton;

    public Help() {

    }

    public void backButtonClick(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }
}
