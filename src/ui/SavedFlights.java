package ui;

import api.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class SavedFlights {
    public Button exitButton;
    public Button deleteFlightButton;
    public TableView flightsTableView;
    public TableColumn carrierIdColumn;
    public TableColumn carrierColumn;
    public TableColumn quoteColumn;

    @FXML
    protected void initialize() {
        // TODO: Populate flights

        // Set column ids
        carrierIdColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));

        // TODO: Get saved flights
        Flight flight1 = new Flight(1, "A", 450);
        Flight flight2 = new Flight(2, "B", 320);
        Flight[] flights = { flight1, flight2 };

        // Show the valid flights
        ObservableList<Flight> list = FXCollections.observableArrayList();
        list.addAll(flights);
        flightsTableView.setItems(list);
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void deleteFlight(ActionEvent actionEvent) {
        int selectedIndex = flightsTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex > -1) {
            // TODO: Actually remove the saved flight from the database

            flightsTableView.getItems().remove(selectedIndex);
            Controller.showMessage("Selected flight has successfully been deleted!", "Flight Deleted");
        } else {
            Controller.showMessage("Please select a valid flight.", "Invalid Flight");
        }
    }
}
