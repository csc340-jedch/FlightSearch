package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;

public class Controller {
    private AnchorPane anchorPane;
    private final static Controller INSTANCE = new Controller();
    private static String username;

    private Controller() {
        System.out.println("Loaded ui.Controller");
    }

    public void setMasterPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public AnchorPane getMasterPane() {
        return anchorPane;
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void changePane(String fxmlLoad) throws IOException {
        // Remove existing pane
        anchorPane.getChildren().removeAll();

        // Add new pane
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/fxml/" + fxmlLoad + ".fxml"));
        anchorPane.getChildren().setAll(pane);

        System.out.println("Changing scene to: " + fxmlLoad);
    }

    public static void showMessage(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void setUsername(String username) {
        Controller.username = username;
    }

    public static String getUsername() {
        return username;
    }

}
