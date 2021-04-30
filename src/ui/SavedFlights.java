package ui;

import accounts.AccountSavedFlights;
import api.Flight;
import db.ConnectToDB;
import db.FlightInformation;
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
    public TableView<Flight> flightsTableView;
    public TableColumn<String, String> carrierIdColumn;
    public TableColumn<String, String> carrierColumn;
    public TableColumn<String, String> quoteColumn;

    @FXML
    protected void initialize() {
        // TODO: Populate flights

        // Set column ids
        carrierIdColumn.setCellValueFactory(new PropertyValueFactory<>("carrierID"));
        carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("quote"));

        Flight[] flights = AccountSavedFlights.getSavedFlights(Controller.getUsername());

        // Show the valid flights
        ObservableList<Flight> list = FXCollections.observableArrayList();
        list.addAll(flights);
        flightsTableView.setItems(list);
    }

    public void exit(ActionEvent _actionEvent) throws IOException {
        Controller controller = Controller.getInstance();
        controller.changePane("menu");
    }

    public void deleteFlight(ActionEvent _actionEvent) {
        int selectedIndex = flightsTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex > -1) {
            String username = Controller.getUsername();
            String carrierID = ConnectToDB.getDatabaseValue("flight_table", "username", username, "carrier_id");

            FlightInformation.removeFlight(carrierID,username);

            flightsTableView.getItems().remove(selectedIndex);
            Controller.showMessage("Selected flight has successfully been deleted!", "Flight Deleted");
        } else {
            Controller.showMessage("Please select a valid flight.", "Invalid Flight");
        }
    }
}
