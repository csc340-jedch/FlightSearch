package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Menu extends ClassLoader {

    public MenuItem logoutMenuOption;
    public MenuItem aboutButton;
    public Button viewSavedFlightsButton;
    public MenuItem Settings;
    @FXML
    private Button searchFlightButton;
    @FXML
    private GridPane menuGrid;

    private Window window;

    public Menu() {
        System.out.println("Loading menu");
    }

    public void searchFlightClick(ActionEvent _actionEvent) throws IOException {
        System.out.println("Searching flight!");

        // Get the parent (Menu) and let it know we are switching scenes.
        Node parent = menuGrid.getParent();

        System.out.println(parent.getId());

        Controller controller = Controller.getInstance();
        controller.changePane("searchflight");
    }

    public void logoutMenuOption(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("login");
    }

    public void aboutButtonClick(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("help");
    }

    public void viewSavedFlights(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("savedflights");
    }

    public void SettingsClick(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("accountsettings");
    }
}
