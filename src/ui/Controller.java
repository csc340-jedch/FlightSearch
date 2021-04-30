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

    public void setMasterPane(AnchorPane _anchorPane) {
        this.anchorPane = _anchorPane;
    }

    public AnchorPane getMasterPane() {
        return anchorPane;
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void changePane(String _fxmlLoad) throws IOException {
        // Remove existing pane
        anchorPane.getChildren().removeAll();

        // Add new pane
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/ui/fxml/" + _fxmlLoad + ".fxml"));
        anchorPane.getChildren().setAll(pane);

        System.out.println("Changing scene to: " + _fxmlLoad);
    }

    public static void showMessage(String _infoMessage, String _titleBar)
    {
        JOptionPane.showMessageDialog(null, _infoMessage, _titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void setUsername(String _username) {
        Controller.username = _username;
    }

    public static String getUsername() {
        return username;
    }

}
